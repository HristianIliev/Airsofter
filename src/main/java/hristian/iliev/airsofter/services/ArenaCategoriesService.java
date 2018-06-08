package hristian.iliev.airsofter.services;

import hristian.iliev.airsofter.contracts.IArenaCategoriesService;
import hristian.iliev.airsofter.contracts.IRepository;
import hristian.iliev.airsofter.models.ArenaCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArenaCategoriesService implements IArenaCategoriesService{
  private final IRepository<ArenaCategory> arenaCategoriesRepository;

  @Autowired
  public ArenaCategoriesService(IRepository<ArenaCategory> arenaCategoriesRepository){
    this.arenaCategoriesRepository = arenaCategoriesRepository;
  }

  @Override
  public List<ArenaCategory> getAll() {
    return this.arenaCategoriesRepository.getAll();
  }
}
