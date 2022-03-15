package ga.mmbh.cfgs.ficheros.models;

import java.util.List;

public class Show {

	private String show_id, type, title, director, cast,
				   country, date_added, release_year, rating,
				   duration, listed_in, description;

	public Show(String show_id, String type, String title, String director, String cast, String country,
			String date_added, String release_year, String rating, String duration, String listed_in,
			String description) {
		super();
		this.show_id = show_id;
		this.type = type;
		this.title = title;
		this.director = director;
		this.cast = cast;
		this.country = country;
		this.date_added = date_added;
		this.release_year = release_year;
		this.rating = rating;
		this.duration = duration;
		this.listed_in = listed_in;
		this.description = description;
	}
	
	public Show(String[] columns) {
		this(columns[0], columns[1], columns[2], columns[3], columns[4],
			 columns[5], columns[6], columns[7], columns[8], columns[9],
			 columns[10], columns[11]);
	}

	public String getShow_id() {
		return show_id;
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

	public String getDate_added() {
		return date_added;
	}

	public String getRelease_year() {
		return release_year;
	}

	public String getRating() {
		return rating;
	}

	public String getDuration() {
		return duration;
	}

	public String getListed_in() {
		return listed_in;
	}

	public String getDescription() {
		return description;
	}
	
		
}
