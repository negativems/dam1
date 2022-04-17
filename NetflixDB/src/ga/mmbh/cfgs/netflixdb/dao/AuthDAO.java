package ga.mmbh.cfgs.netflixdb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import ga.mmbh.cfgs.netflixdb.models.User;

public class AuthDAO extends AbstractDAO {
	
	public AuthDAO() {
		super("temporary_codes");
	}

}
