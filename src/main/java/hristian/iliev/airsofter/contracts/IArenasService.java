package hristian.iliev.airsofter.contracts;

import hristian.iliev.airsofter.models.Arena;
import hristian.iliev.airsofter.models.ArenaCategory;

public interface IArenasService {
  Arena getArena(int id);

  Arena createArena(Arena arena);

  ArenaCategory getArenaCategoryByName(String name);
}
