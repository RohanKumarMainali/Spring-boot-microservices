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

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

  @Autowired
  private RestTemplate restTemplate;

  // @Autowired
  // private WebClient.Builder webClientBuilder;

  @RequestMapping("/{userId}")
  public List<Catalog> getCatalog(@PathVariable("userId") String userId) {

    UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratings/users/1", UserRating.class);
    return ratings.getRatings().stream().map(rating -> {
      Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" +
          rating.getMovieId(), Movie.class);
      return new Catalog(movie.getMovieName(), movie.getMovieDescription(), rating.getRating());
    }).collect(Collectors.toList());

  }

}
