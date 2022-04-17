package ga.mmbh.cfgs.netflixdb.managers;

import ga.mmbh.cfgs.netflixdb.dao.ShowDAO;
import ga.mmbh.cfgs.netflixdb.dao.UserDAO;

public class DatabaseManager {
	
	private final UserDAO userDAO;
	private final ShowDAO showDAO;
	
	public DatabaseManager() {
		this.userDAO = new UserDAO();
		this.showDAO = new ShowDAO();
	}
	
	public boolean isConnected() {
		return userDAO.isConnected() && showDAO.isConnected();
	}
}