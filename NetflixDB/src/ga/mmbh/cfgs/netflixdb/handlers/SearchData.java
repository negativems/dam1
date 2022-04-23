package ga.mmbh.cfgs.netflixdb.handlers;

public class SearchData {

	private String title = "";
	private String country = "";
	private String director = "";
	private String year = "";
	
	public SearchData(String title, String country, String director, String year) {
		this.title = title;
		this.country = country;
		this.director = director;
		this.year = year;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String getYear() {
		return year;
	}
	
	public String getDirector() {
		return director;
	}
}
