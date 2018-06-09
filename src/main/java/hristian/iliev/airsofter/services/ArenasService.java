package hristian.iliev.airsofter.services;

import hristian.iliev.airsofter.contracts.IArenasService;
import hristian.iliev.airsofter.contracts.IRepository;
import hristian.iliev.airsofter.models.Arena;
import hristian.iliev.airsofter.models.ArenaCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArenasService implements IArenasService{
  private final IRepository<Arena> arenasRepository;
  private final IRepository<ArenaCategory> arenaCategoriesRepository;

  @Autowired
  public ArenasService(IRepository<Arena> arenasRepository,
                       IRepository<ArenaCategory> arenaCategoriesRepository){
    this.arenasRepository = arenasRepository;
    this.arenaCategoriesRepository = arenaCategoriesRepository;
  }

  @Override
  public Arena getArena(int id) {
    return this.arenasRepository.getById(id);
  }

  @Override
  public Arena createArena(Arena arena) {
    List<ArenaCategory> arenaCategories = arena.getArenaCategories();
    for(int i = 0; i < arenaCategories.size(); i ++){
      ArenaCategory toInsert = this.getArenaCategoryByName(arenaCategories.get(i).getName());
      arenaCategories.set(i, toInsert);
    }

    return this.arenasRepository.create(arena);
  }

  @Override
  public ArenaCategory getArenaCategoryByName(String name){
    return this.arenaCategoriesRepository.getAll().stream()
            .filter(ac -> ac.getName().equals(name))
            .findFirst()
            .orElse(null);
  }
}
