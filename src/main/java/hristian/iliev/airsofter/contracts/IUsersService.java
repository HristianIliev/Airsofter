package hristian.iliev.airsofter.contracts;

import hristian.iliev.airsofter.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUsersService extends UserDetailsService {
  User getUserByEmail(String username);

  User register(User user);

  void installationCompleted(User user);

  User getById(int id);

}
