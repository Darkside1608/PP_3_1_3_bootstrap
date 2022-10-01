package ru.kata.spring.boot_security.demo.init;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Component
public class UserInit {

  private final UserServiceImpl userService;
  private final PasswordEncoder passwordEncoder;

  public UserInit(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostConstruct
  private void postConstruct() {
    List<User> users = userService.getAllUsers();
    if (users.isEmpty()) {
      Role admin = new Role("ROLE_ADMIN");
      Role user = new Role("ROLE_USER");
      Set<Role> adminRole = new HashSet<>();
      Set<Role> userRole = new HashSet<>();
      adminRole.add(admin);
      userRole.add(user);
      userService.addUser(new User
          ("admin", "admin", 30, "admin@mail.com", "admin", adminRole));
      userService.addUser(new User
          ("user", "user", 20, "user@mail.com", "user", userRole));

    }
  }
}