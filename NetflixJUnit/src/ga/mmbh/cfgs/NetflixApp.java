package ga.mmbh.cfgs;

import ga.mmbh.cfgs.managers.MovieManager;
import ga.mmbh.cfgs.managers.UserManager;
import ga.mmbh.cfgs.views.LoginView;

public class NetflixApp {

   private static NetflixApp instance;

   private MovieManager movieManager;
   private UserManager userManager;

   public NetflixApp() {
      instance = this;

      // Loads managers
      movieManager = new MovieManager();
      userManager = new UserManager(this);

      new LoginView(this);
   }

   // Getters & Setters
   public MovieManager getMovieManager() {
      return movieManager;
   }

   public UserManager getUserManager() {
      return userManager;
   }

   public static NetflixApp getInstance() {
      return instance;
   }
}
