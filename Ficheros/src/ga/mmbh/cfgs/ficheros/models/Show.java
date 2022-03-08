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
		this(columns[0], columns[0], columns[0], columns[0], columns[0],
			 columns[0], columns[0], columns[0], columns[0], columns[0],
			 columns[0], columns[0]);
	}
	
}
