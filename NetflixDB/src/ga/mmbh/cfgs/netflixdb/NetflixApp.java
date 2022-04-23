package ga.mmbh.cfgs.netflixdb;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;

import ga.mmbh.cfgs.netflixdb.config.Config;
import ga.mmbh.cfgs.netflixdb.managers.DatabaseManager;
import ga.mmbh.cfgs.netflixdb.managers.ShowManager;
import ga.mmbh.cfgs.netflixdb.managers.UserManager;
import ga.mmbh.cfgs.netflixdb.views.LoginView;

public class NetflixApp {
	
	private static NetflixApp instance;
	
	private final DatabaseManager databaseManager;
	private final UserManager userManager;
	private final ShowManager showManager;
	private Config config = null;
	
	public NetflixApp() {
		instance = this;
		
		databaseManager = new DatabaseManager();
		userManager = new UserManager();
		showManager = new ShowManager();
		
		try {
			Reader reader = Files.newBufferedReader(Path.of("resources/config.json"));
			config = new Gson().fromJson(reader, Config.class);
		} catch (IOException e) {
			System.out.println("No se ha podido cargar la configuración");
			e.printStackTrace();
		}
		
		new LoginView();
	}
	
	public DatabaseManager getDatabaseManager() {
		return this.databaseManager;
	}
	
	public UserManager getUserManager() {
		return this.userManager;
	}
	
	public ShowManager getShowManager() {
		return this.showManager;
	}
	
	public Config getConfig() {
		return this.config;
	}
	
	public static NetflixApp getInstance() {
		return instance;
	}
}
