package ga.mmbh.cfgs.ficheros.models;

public class Show {

	private int id;
	private String type, title, director, cast,
				   country, dateAdded, releaseYear, rating,
				   duration, listedIn, description;

	public Show(int id, String type, String title, String director, String cast, String country,
			String date_added, String release_year, String rating, String duration, String listed_in,
			String description) {
		super();
		this.id = id;
		this.type = type;
		this.title = title;
		this.director = director;
		this.cast = cast;
		this.country = country;
		this.dateAdded = date_added;
		this.releaseYear = release_year;
		this.rating = rating;
		this.duration = duration;
		this.listedIn = listed_in;
		this.description = description;
	}
	
	public Show(String[] columns) {
		this(Integer.parseInt(columns[0].substring(1)), columns[1], columns[2], columns[3], columns[4],
			 columns[5], columns[6], columns[7], columns[8], columns[9],
			 columns[10], columns[11]);
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public String getDirector() {
		return director;
	}

	public String getCast() {
		return cast;
	}

	public String getCountry() {
		return country;
	}

	public String getAddedDate() {
		return dateAdded;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public String getRating() {
		return rating;
	}

	public String getDuration() {
		return duration;
	}

	public String getListedIn() {
		return listedIn;
	}

	public String getDescription() {
		return description;
	}
	
		
}
