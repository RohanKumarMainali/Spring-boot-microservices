package io.javabrains.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.javabrains.movieinfoservice.models.Movie;
import io.javabrains.movieinfoservice.models.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieResources {

  @Value("${api.key}")
  private String apiKey;

  @Autowired
  private RestTemplate restTemplate;

  @RequestMapping("/{movieId}")
  public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
    MovieSummary movieSummay = restTemplate.getForObject(
        "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey,
        MovieSummary.class);
    return new Movie(movieId, movieSummay.getTitle(), movieSummay.getOverview());
  }
}
