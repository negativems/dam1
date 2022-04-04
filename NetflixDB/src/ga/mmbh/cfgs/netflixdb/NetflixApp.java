package ga.mmbh.cfgs.netflixdb;

import ga.mmbh.cfgs.netflixdb.managers.ShowManager;
import ga.mmbh.cfgs.netflixdb.managers.UserManager;
import ga.mmbh.cfgs.netflixdb.views.HomeView;

public class NetflixApp {
	
	private static NetflixApp instance;
	
	private final UserManager userManager;
	private final ShowManager showManager;
	
	public NetflixApp() {
		instance = this;
		
		this.userManager = new UserManager(this);
		this.showManager = new ShowManager();
		
		new HomeView(this);
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
