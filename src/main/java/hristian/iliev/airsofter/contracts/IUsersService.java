package hristian.iliev.airsofter.contracts;

import hristian.iliev.airsofter.models.Request;
import hristian.iliev.airsofter.models.User;
import hristian.iliev.airsofter.models.helper.RequestsInformation;
import hristian.iliev.airsofter.models.request.Names;
import hristian.iliev.airsofter.models.request.Passwords;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.text.ParseException;

public interface IUsersService extends UserDetailsService {
  User getUserByEmail(String username);

  User register(User user);

  void installationCompleted(User user);

  User getById(int id);

  User changeNames(User current, Names names);

  User changePasswords(User current, Passwords passwords);

  RequestsInformation getRequestsInformation(User owner) throws ParseException;
}
