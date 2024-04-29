package io.javabrains.moviecatalogservice.models;

public class Movie {
  private String movieId;
  private String movieName;
  private String movieDescription;

  public Movie() {
  }

  public Movie(String movieId, String movieName, String movieDescription) {
    this.movieId = movieId;
    this.movieName = movieName;
    this.movieDescription = movieDescription;
  }

  public String getMovieId() {
    return movieId;
  }

  public void setMovieId(String movieId) {
    this.movieId = movieId;
  }

  public String getMovieName() {
    return movieName;
  }

  public String getMovieDescription() {
    return movieDescription;
  }

  public void setMovieDescription(String movieDescription) {
    this.movieDescription = movieDescription;
  }

  public void setMovieName(String movieName) {
    this.movieName = movieName;
  }
}
