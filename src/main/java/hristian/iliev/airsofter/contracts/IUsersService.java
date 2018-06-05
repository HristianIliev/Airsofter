package hristian.iliev.airsofter.contracts;

import hristian.iliev.airsofter.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUsersService extends UserDetailsService {
  User getUserByUsername(String username);

  User register(User user);
}
