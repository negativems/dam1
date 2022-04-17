package ga.mmbh.cfgs.netflixdb.handlers;

public class SearchData {

	private String title = "";
	private String year = "";
	private String director = "";
	
	public SearchData(String title, String year, String director) {
		this.title = title;
		this.year = year;
		this.director = director;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getYear() {
		return year;
	}
	
	public String getDirector() {
		return director;
	}
}
