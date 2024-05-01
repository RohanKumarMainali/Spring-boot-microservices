package io.javabrains.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.javabrains.moviecatalogservice.models.Movie;

@Service
public class MovieInfoService {

  @Autowired
  private RestTemplate restTemplate;

  private static final String MOVIE_INFO_SERVICE = "movieInfoService";
  private static final String MOVIE_INFO_SERVICE_FALLBACK = "movieInfoServiceFallback";

  @CircuitBreaker(name = MOVIE_INFO_SERVICE, fallbackMethod = MOVIE_INFO_SERVICE_FALLBACK)
  public Movie getMovieInfo(String movieId) {
    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + movieId, Movie.class);
    return movie;
  }

  public Movie movieInfoServiceFallback(Exception e) {
    return new Movie("1", "Movie not found", "Movie not found");
  }

}
