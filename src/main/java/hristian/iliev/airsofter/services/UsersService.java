package hristian.iliev.airsofter.services;

import hristian.iliev.airsofter.contracts.IRepository;
import hristian.iliev.airsofter.contracts.IUsersService;
import hristian.iliev.airsofter.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsersService implements IUsersService {
  private final IRepository<User> usersRepository;
  private final PasswordEncoder passwordEncoder;

  public UsersService(IRepository<User> usersRepository,
                      PasswordEncoder passwordEncoder) {
    this.usersRepository = usersRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public User getUserByEmail(String username) {
    return usersRepository.getAll()
            .stream()
            .filter(u -> u.getEmail().equals(username))
            .findFirst()
            .orElse(null);
  }

  @Override
  public User register(User user) {
    if (this.isEmailFree(user.getEmail())) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setNeedsInstallation(true);
      return this.usersRepository.create(user);
    }

    return null;
  }

  @Override
  public void installationCompleted(User user) {
    user.setNeedsInstallation(false);

    this.usersRepository.update(user);
  }

  private boolean isEmailFree(String email) {
    return this.usersRepository.getAll().stream()
            .noneMatch(u -> u.getEmail().equals(email));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.getUserByEmail(username);
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
  }
}
