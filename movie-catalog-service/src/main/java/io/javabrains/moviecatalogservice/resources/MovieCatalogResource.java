package io.javabrains.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.javabrains.moviecatalogservice.models.Catalog;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private WebClient.Builder webClientBuilder;

  @RequestMapping("/{userId}")
  public List<Catalog> getCatalog(@PathVariable("userId") String userId) {

    // get all rated movie IDs
    List<Rating> ratings = Arrays.asList(
        new Rating("1234", 4),
        new Rating("5678", 3));
    return ratings.stream().map(rating -> {
      // Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" +
      // rating.getMovieId(), Movie.class);

      Movie movie = webClientBuilder.build()
          .get()
          .uri("http://localhost:8082/movies/" + rating.getMovieId())
          .retrieve()
          .bodyToMono(Movie.class)
          .block();
      return new Catalog(movie.getMovieName(), "Test", rating.getRating());
    }).collect(Collectors.toList());

    // for each movie ID, call movie info service and get details
    // put them all together
  }

}
