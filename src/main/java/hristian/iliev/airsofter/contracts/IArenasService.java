package hristian.iliev.airsofter.contracts;

import hristian.iliev.airsofter.models.Arena;
import hristian.iliev.airsofter.models.ArenaCategory;
import hristian.iliev.airsofter.models.User;
import hristian.iliev.airsofter.models.request.ArenaMainSettings;

import java.util.List;

public interface IArenasService {
  Arena getArena(int id);

  Arena createArena(Arena arena, User user);

  ArenaCategory getArenaCategoryByName(String name);

  List<Arena> getAll();

  Arena changeArenaMainSettings(User owner, ArenaMainSettings arenaMainSettings);
}
