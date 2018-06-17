package hristian.iliev.airsofter.services;

import hristian.iliev.airsofter.contracts.IRepository;
import hristian.iliev.airsofter.contracts.IUsersService;
import hristian.iliev.airsofter.models.Request;
import hristian.iliev.airsofter.models.User;
import hristian.iliev.airsofter.models.helper.RequestsInformation;
import hristian.iliev.airsofter.models.request.Names;
import hristian.iliev.airsofter.models.request.Passwords;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class UsersService implements IUsersService {
  private final IRepository<User> usersRepository;
  private final PasswordEncoder passwordEncoder;
  private final IRepository<Request> requestsRepository;

  public UsersService(IRepository<User> usersRepository,
                      IRepository<Request> requestsRepository,
                      PasswordEncoder passwordEncoder) {
    this.usersRepository = usersRepository;
    this.requestsRepository = requestsRepository;
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

  @Override
  public User getById(int id) {
    return this.usersRepository.getById(id);
  }

  @Override
  public User changeNames(User current, Names names) {
    current.setName(names.getName());
    current.setLastName(names.getLastName());

    return this.usersRepository.update(current);
  }

  @Override
  public User changePasswords(User current, Passwords passwords) {
    if (!passwords.getOldPassword().equals(current.getPassword())) {
      return null;
    }

    current.setPassword(passwords.getNewPassword());

    return this.usersRepository.update(current);
  }

  @Override
  public RequestsInformation getRequestsInformation(User owner) throws ParseException {
    int clientsForToday = this.getClientsForToday(owner);
    Request nextRequest = this.getNextRequest(owner);
    String timeUntilNextClient = "";
    if (nextRequest == null) {
      timeUntilNextClient = "Няма намерени";
    } else {
      timeUntilNextClient = this.getTimeUntilNextClient(nextRequest);
    }

    int pendingRequests = this.getPendingRequests(owner);
    int doneClients = this.getDoneClients(owner);
    List<Request> requestsForTheDay = this.getRequestsForTheDay(owner);
    List<Request> requestsToAccept = this.getRequestsToAccept(owner);

    return new RequestsInformation(nextRequest,
            clientsForToday,
            timeUntilNextClient,
            pendingRequests,
            doneClients,
            requestsForTheDay,
            requestsToAccept);
  }

  @Override
  public boolean checkIfThereAreProbDoneRequests(User owner) {
    for (int i = 0; i < owner.getRequestsForTheArena().size(); i++) {
      if (owner.getRequestsForTheArena().get(i).getStatus().equals("PROB_DONE")) {
        return true;
      }
    }

    return false;
  }

  @Override
  public void markRequestsThatAreProbablyDone(User owner) throws ParseException {
    List<Request> toUpdate = new ArrayList<>();
    for (int i = 0; i < owner.getRequestsForTheArena().size(); i++) {
      Calendar now = Calendar.getInstance();
      Calendar requestTime = Calendar.getInstance();
      now.setTime(new Date());

      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      Date requestDate = sdf.parse(owner.getRequestsForTheArena().get(i).getDaytime());
      requestTime.setTime(requestDate);

      Date nowDate = now.getTime();
      Date requestDateToCompare = requestTime.getTime();

      boolean isBeforeCurrentTime = requestDateToCompare.before(nowDate);

      if (isBeforeCurrentTime && owner.getRequestsForTheArena().get(i).getStatus().equals("ACCEPTED")) {
        owner.getRequestsForTheArena().get(i).setStatus("PROB_DONE");
        toUpdate.add(owner.getRequestsForTheArena().get(i));
      }
    }

    for (Request request :
            toUpdate) {
      this.requestsRepository.update(request);
    }
  }

  private List<Request> getRequestsToAccept(User owner) {
    List<Request> result = new ArrayList<>();
    for (int i = 0; i < owner.getRequestsForTheArena().size(); i++) {
      if (owner.getRequestsForTheArena().get(i).getStatus().equals("PENDING")) {
        result.add(owner.getRequestsForTheArena().get(i));
      }
    }

    return result;
  }

  private List<Request> getRequestsForTheDay(User owner) throws ParseException {
    List<Request> requestsForTheDay = new ArrayList<>();
    for (int i = 0; i < owner.getRequestsForTheArena().size(); i++) {
      Calendar now = Calendar.getInstance();
      Calendar requestTime = Calendar.getInstance();
      now.setTime(new Date());
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      Date requestDate = sdf.parse(owner.getRequestsForTheArena().get(i).getDaytime());
      requestTime.setTime(requestDate);

      boolean isSameDay = now.get(Calendar.YEAR) == requestTime.get(Calendar.YEAR) &&
              now.get(Calendar.DAY_OF_YEAR) == requestTime.get(Calendar.DAY_OF_YEAR);

      boolean isSameHour = now.get(Calendar.HOUR_OF_DAY) == requestTime.get(Calendar.HOUR_OF_DAY);

      boolean isAfterCurrentTime = false;
      if (isSameHour) {
        isAfterCurrentTime = now.get(Calendar.MINUTE) < requestTime.get(Calendar.MINUTE);
      } else {
        isAfterCurrentTime = now.get(Calendar.HOUR_OF_DAY) < requestTime.get(Calendar.HOUR_OF_DAY);
      }

      if (isSameDay && isAfterCurrentTime) {
        requestsForTheDay.add(owner.getRequestsForTheArena().get(i));
      }
    }

    return requestsForTheDay;
  }

  private int getDoneClients(User owner) {
    int doneClients = 0;
    for (int i = 0; i < owner.getRequestsForTheArena().size(); i++) {
      if (owner.getRequestsForTheArena().get(i).getStatus().equals("DONE")) {
        doneClients++;
      }
    }

    return doneClients;
  }

  private int getPendingRequests(User owner) {
    int pendingRequests = 0;
    for (int i = 0; i < owner.getRequestsForTheArena().size(); i++) {
      if (owner.getRequestsForTheArena().get(i).getStatus().equals("PENDING")) {
        pendingRequests++;
      }
    }

    return pendingRequests;
  }

  private String getTimeUntilNextClient(Request nextRequest) throws ParseException {
    PrettyTime p = new PrettyTime(new Locale("bg"));

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    Date requestDate = sdf.parse(nextRequest.getDaytime());
    return p.format(requestDate);
  }

  private int getClientsForToday(User owner) throws ParseException {
    int clientsForToday = 0;
    for (int i = 0; i < owner.getRequestsForTheArena().size(); i++) {
      Calendar now = Calendar.getInstance();
      Calendar requestTime = Calendar.getInstance();
      now.setTime(new Date());
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      Date requestDate = sdf.parse(owner.getRequestsForTheArena().get(i).getDaytime().substring(0, 10));
      requestTime.setTime(requestDate);

      boolean isSameDay = now.get(Calendar.YEAR) == requestTime.get(Calendar.YEAR) &&
              now.get(Calendar.DAY_OF_YEAR) == requestTime.get(Calendar.DAY_OF_YEAR);

      if (isSameDay) {
        clientsForToday++;
      }
    }

    return clientsForToday;
  }

  private Request getNextRequest(User owner) throws ParseException {
    Request nextRequest = null;
    SimpleDateFormat minSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    Date minDate = minSdf.parse("01/01/2000 23:59");
    Calendar minTime = Calendar.getInstance();
    minTime.setTime(minDate);
    for (int i = 0; i < owner.getRequestsForTheArena().size(); i++) {
      Calendar now = Calendar.getInstance();
      Calendar requestTime = Calendar.getInstance();
      now.setTime(new Date());
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      Date requestDate = sdf.parse(owner.getRequestsForTheArena().get(i).getDaytime());
      requestTime.setTime(requestDate);

      boolean isSameDay = now.get(Calendar.YEAR) == requestTime.get(Calendar.YEAR) &&
              now.get(Calendar.DAY_OF_YEAR) == requestTime.get(Calendar.DAY_OF_YEAR);

      boolean isSameHour = now.get(Calendar.HOUR_OF_DAY) == requestTime.get(Calendar.HOUR_OF_DAY);

      boolean isAfterCurrentTime = false;
      if (isSameHour) {
        isAfterCurrentTime = now.get(Calendar.MINUTE) < requestTime.get(Calendar.MINUTE);
      } else {
        isAfterCurrentTime = now.get(Calendar.HOUR_OF_DAY) < requestTime.get(Calendar.HOUR_OF_DAY);
      }

      if (isSameDay && isAfterCurrentTime) {
        boolean isSameHourAsMin = minTime.get(Calendar.HOUR_OF_DAY) == requestTime.get(Calendar.HOUR_OF_DAY);
        boolean shouldReplaceMin = false;
        if (isSameHourAsMin) {
          shouldReplaceMin = minTime.get(Calendar.MINUTE) > requestTime.get(Calendar.MINUTE);
        } else {
          shouldReplaceMin = minTime.get(Calendar.HOUR_OF_DAY) > requestTime.get(Calendar.HOUR_OF_DAY);
        }

        if (shouldReplaceMin) {
          minTime = requestTime;
          nextRequest = owner.getRequestsForTheArena().get(i);
        }
      }
    }

    return nextRequest;
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
