package io.javabrains.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.javabrains.moviecatalogservice.models.Catalog;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.UserRating;
import io.javabrains.moviecatalogservice.services.MovieInfoService;
import io.javabrains.moviecatalogservice.services.RatingsDataService;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  MovieInfoService movieInfoService;

  @Autowired
  RatingsDataService ratingsDataService;

  @RequestMapping("/{userId}")
  public List<Catalog> getCatalog(@PathVariable("userId") String userId) {

    UserRating ratings = ratingsDataService.getUserRating(userId);
    return ratings.getRatings().stream().map(rating -> {
      Movie movie = movieInfoService.getMovieInfo(rating.getMovieId());
      return new Catalog(movie.getMovieName(), movie.getMovieDescription(), rating.getRating());
    }).collect(Collectors.toList());
  }

}
