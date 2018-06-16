package hristian.iliev.airsofter.configurations;

import hristian.iliev.airsofter.contracts.IRepository;
import hristian.iliev.airsofter.models.Arena;
import hristian.iliev.airsofter.models.ArenaCategory;
import hristian.iliev.airsofter.models.Request;
import hristian.iliev.airsofter.models.Timetable;
import hristian.iliev.airsofter.models.User;
import hristian.iliev.airsofter.repositories.HibernateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  @Autowired
  IRepository<User> provideUsersRepository() {
    HibernateRepository<User> repository = new HibernateRepository<>();
    repository.setEntityClass(User.class);

    return repository;
  }

  @Bean
  @Autowired
  IRepository<ArenaCategory> provideArenaCategoriesRepository() {
    HibernateRepository<ArenaCategory> repository = new HibernateRepository<>();
    repository.setEntityClass(ArenaCategory.class);

    return repository;
  }

  @Bean
  @Autowired
  IRepository<Arena> provideArenasRepository() {
    HibernateRepository<Arena> repository = new HibernateRepository<>();
    repository.setEntityClass(Arena.class);

    return repository;
  }

  @Bean
  @Autowired
  IRepository<Request> provideRequestsRepository() {
    HibernateRepository<Request> repository = new HibernateRepository<>();
    repository.setEntityClass(Request.class);

    return repository;
  }

  @Bean
  @Autowired
  IRepository<Timetable> provideTimetablesRepository() {
    HibernateRepository<Timetable> repository = new HibernateRepository<>();
    repository.setEntityClass(Timetable.class);

    return repository;
  }
}
