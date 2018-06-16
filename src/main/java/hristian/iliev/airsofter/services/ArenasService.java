package hristian.iliev.airsofter.services;

import hristian.iliev.airsofter.contracts.IArenasService;
import hristian.iliev.airsofter.contracts.IRepository;
import hristian.iliev.airsofter.models.Arena;
import hristian.iliev.airsofter.models.ArenaCategory;
import hristian.iliev.airsofter.models.Timetable;
import hristian.iliev.airsofter.models.User;
import hristian.iliev.airsofter.models.request.ArenaMainSettings;
import hristian.iliev.airsofter.models.request.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArenasService implements IArenasService {
  private final IRepository<Arena> arenasRepository;
  private final IRepository<ArenaCategory> arenaCategoriesRepository;
  private final IRepository<User> usersRepository;
  private final IRepository<Timetable> timetablesRepository;

  @Autowired
  public ArenasService(IRepository<Arena> arenasRepository,
                       IRepository<ArenaCategory> arenaCategoriesRepository,
                       IRepository<User> usersRepository,
                       IRepository<Timetable> timetablesRepository) {
    this.arenasRepository = arenasRepository;
    this.arenaCategoriesRepository = arenaCategoriesRepository;
    this.usersRepository = usersRepository;
    this.timetablesRepository = timetablesRepository;
  }

  @Override
  public Arena getArena(int id) {
    return this.arenasRepository.getById(id);
  }

  @Override
  public Arena createArena(Arena arena, User user) {
    List<ArenaCategory> arenaCategoriesToInsert = new ArrayList<>();
    for (int i = 0; i < arena.getArenaCategories().size(); i++) {
      ArenaCategory toInsert = this.getArenaCategoryByName(arena.getArenaCategories().get(i).getName().trim());
      arenaCategoriesToInsert.add(toInsert);
    }

    arena.setArenaCategories(arenaCategoriesToInsert);

    arena.getTimetable().setArena(arena);

    user.setArena(arena);
    user.getArena().setOwner(user);

    return this.usersRepository.update(user).getArena();
  }

  @Override
  public ArenaCategory getArenaCategoryByName(String name) {
    return this.arenaCategoriesRepository.getAll().stream()
            .filter(ac -> ac.getName().equals(name))
            .findFirst()
            .orElse(null);
  }

  @Override
  public List<Arena> getAll() {
    return this.arenasRepository.getAll();
  }

  @Override
  public Arena changeArenaMainSettings(User owner, ArenaMainSettings arenaMainSettings) {
    Arena toChange = owner.getArena();
    toChange.setName(arenaMainSettings.getName());
    toChange.setDescription(arenaMainSettings.getDescription());
    toChange.setTelephone(arenaMainSettings.getTelephone());

    List<ArenaCategory> arenaCategoriesToInsert = new ArrayList<>();
    for (int i = 0; i < arenaMainSettings.getArenaCategories().size(); i++) {
      ArenaCategory toInsert = this.getArenaCategoryByName(arenaMainSettings.getArenaCategories().get(i).getName().trim());
      arenaCategoriesToInsert.add(toInsert);
    }

    toChange.setArenaCategories(arenaCategoriesToInsert);

    owner.setArena(toChange);

    return this.usersRepository.update(owner).getArena();
  }

  @Override
  public Arena changeLatLng(User owner, LatLng latLng) {
    Arena toChange = owner.getArena();
    toChange.setLatitude(latLng.getLatitude());
    toChange.setLongitude(latLng.getLongitude());

    owner.setArena(toChange);

    return this.usersRepository.update(owner).getArena();
  }

  @Override
  public Arena changeTimetable(User owner, Timetable timetable) {
    Arena toChange = owner.getArena();

    this.timetablesRepository.delete(toChange.getTimetable());

    timetable.setArena(toChange);

    toChange.setTimetable(timetable);

    owner.setArena(toChange);

    return this.usersRepository.update(owner).getArena();
  }
}
