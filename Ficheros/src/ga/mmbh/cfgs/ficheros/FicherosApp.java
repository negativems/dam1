package ga.mmbh.cfgs.ficheros;

import ga.mmbh.cfgs.ficheros.managers.ShowManager;
import ga.mmbh.cfgs.ficheros.managers.UserManager;
import ga.mmbh.cfgs.ficheros.views.HomeView;

public class FicherosApp {
	
	private static FicherosApp instance;
	
	private final UserManager userManager;
	private final ShowManager showManager;
	
	public FicherosApp() {
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
	
	public static FicherosApp getInstance() {
		return instance;
	}
}
