package ga.mmbh.cfgs.netflixjunit.models;

public class Movie {

	private final int ID, minAge;
	private final String name, director, imageURL;
	private final long durationMins;
	private Genre genre;

	public Movie(int ID, String name, int minAge, String director, Genre genre, int durationMins, String imageURL) {
		this.ID = ID;
		this.name = name;
		this.minAge = minAge;
		this.director = director;
		this.durationMins = durationMins;
		this.imageURL = imageURL;
		this.genre = genre;
	}
	
	// Getters
	public int getId() {
		return ID;
	}

	public int getMinAge() {
		return minAge;
	}

	public String getName() {
		return name;
	}

	public String getDirector() {
		return director;
	}

	public String getImageURL() {
		return imageURL;
	}

	public long getDuration() {
		return durationMins;
	}

	public Genre getGenre() {
		return genre;
	}
	
	
}
