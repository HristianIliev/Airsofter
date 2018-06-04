package hristian.iliev.airsofter.configurations;

import hristian.iliev.airsofter.contracts.IRepository;
import hristian.iliev.airsofter.models.User;
import hristian.iliev.airsofter.repositories.HibernateRepository;
import org.hibernate.SessionFactory;
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
}
