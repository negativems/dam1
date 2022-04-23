package ga.mmbh.cfgs.netflixdb.config;

public class Config {
	
	private String email, password;

	public Config(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
}
