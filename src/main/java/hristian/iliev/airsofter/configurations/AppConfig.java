package hristian.iliev.airsofter.configurations;

import hristian.iliev.airsofter.contracts.IRepository;
import hristian.iliev.airsofter.models.ArenaCategory;
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
}
