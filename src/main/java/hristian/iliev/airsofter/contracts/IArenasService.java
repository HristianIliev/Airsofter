package hristian.iliev.airsofter.contracts;

import hristian.iliev.airsofter.models.Arena;
import hristian.iliev.airsofter.models.ArenaCategory;
import hristian.iliev.airsofter.models.User;

public interface IArenasService {
  Arena getArena(int id);

  Arena createArena(Arena arena, User user);

  ArenaCategory getArenaCategoryByName(String name);
}
