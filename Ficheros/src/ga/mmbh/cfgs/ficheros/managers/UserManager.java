package ga.mmbh.cfgs.ficheros.managers;

import java.util.ArrayList;
import java.util.List;

import ga.mmbh.cfgs.ficheros.FicherosApp;
import ga.mmbh.cfgs.ficheros.models.User;

public class UserManager {

   private final FicherosApp ficheros;
   private User session;

   private List<User> usersList = new ArrayList<>();

   public UserManager(FicherosApp ficheros) {
      this.ficheros = ficheros;
      registerUser(new User("admin", "admin"));
      registerUser(new User("test", "test"));
   }

   public boolean login(String username, String password) {
      if (!exists(username, password)) return false;
      session = new User(username, password);
      return true;
   }

   public boolean registerUser(User user) {
      return usersList.add(user);
   }

   public boolean exists(String username, String password) {
      return usersList.stream().anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
   }

   public boolean exists(String username) {
      return exists(username, null);
   }

   // Getters & Setters
   public List<User> getUsers() {
      return usersList;
   }

}
