package hristian.iliev.airsofter.services;

import hristian.iliev.airsofter.contracts.IArenasService;
import hristian.iliev.airsofter.contracts.IRepository;
import hristian.iliev.airsofter.models.Arena;
import hristian.iliev.airsofter.models.ArenaCategory;
import hristian.iliev.airsofter.models.Request;
import hristian.iliev.airsofter.models.Timetable;
import hristian.iliev.airsofter.models.User;
import hristian.iliev.airsofter.models.request.ArenaMainSettings;
import hristian.iliev.airsofter.models.request.LatLng;
import hristian.iliev.airsofter.models.response.ChartData;
import hristian.iliev.airsofter.models.response.ChartEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    System.out.println(arena.getCity());

    arena.setCity(arena.getCity().trim());

    arena.setRating(0.0);

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

  @Override
  public ChartData getChartData(User owner) throws ParseException {
    Calendar now = Calendar.getInstance();
    now.setTime(new Date());
    now.add(Calendar.DAY_OF_YEAR, 1);
    List<ChartEntry> result = new ArrayList<>();
    List<Request> allRequests = owner.getRequestsForTheArena();

    for (int i = 0; i < 7; i++) {
      now.add(Calendar.DAY_OF_YEAR, -1);
      ChartEntry chartEntry = this.getChartEntry(now, allRequests);

      result.add(chartEntry);
    }

    return new ChartData(result);
  }

  @Override
  public List<Arena> list() {
    return this.arenasRepository.getAll();
  }

  private ChartEntry getChartEntry(Calendar dateToGet, List<Request> allRequest) throws ParseException {
    int numberOfRequestsForTheDate = 0;

    for (int i = 0; i < allRequest.size(); i++) {
      Calendar calendar = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      Date requestDate = sdf.parse(allRequest.get(i).getDaytime());
      calendar.setTime(requestDate);
      if (dateToGet.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
              dateToGet.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)) {
        numberOfRequestsForTheDate++;
      }

    }

    int dayOfMonth = dateToGet.get(Calendar.DAY_OF_MONTH);
    int month = dateToGet.get(Calendar.MONTH) + 1;

    String day = "";
    String monthStr = "";

    if (dayOfMonth < 10) {
      day = "0" + Integer.toString(dayOfMonth);
    } else {
      day = Integer.toString(dayOfMonth);
    }

    if (month < 10) {
      monthStr = "0" + Integer.toString(month);
    } else {
      monthStr = Integer.toString(month);
    }

    return new ChartEntry(day + "." + monthStr + ".", numberOfRequestsForTheDate);
  }
}
