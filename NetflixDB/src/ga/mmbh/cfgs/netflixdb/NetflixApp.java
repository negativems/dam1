package ga.mmbh.cfgs.netflixdb;

import ga.mmbh.cfgs.netflixdb.managers.DatabaseManager;
import ga.mmbh.cfgs.netflixdb.managers.ShowManager;
import ga.mmbh.cfgs.netflixdb.managers.UserManager;
import ga.mmbh.cfgs.netflixdb.views.LoginView;

public class NetflixApp {
	
	private static NetflixApp instance;
	
	private final DatabaseManager databaseManager;
	private final UserManager userManager;
	private final ShowManager showManager;
	
	public NetflixApp() {
		instance = this;
		
		this.databaseManager = new DatabaseManager();
		this.userManager = new UserManager();
		this.showManager = new ShowManager();
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
	
	public static NetflixApp getInstance() {
		return instance;
	}
}
