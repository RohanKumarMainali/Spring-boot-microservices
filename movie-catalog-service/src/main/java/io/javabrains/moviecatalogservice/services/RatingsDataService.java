
package io.javabrains.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;

@Service
public class RatingsDataService {

  @Autowired
  private RestTemplate restTemplate;

  private static final String RATINGS_DATA_SERVICE = "ratingsDataService";
  private static final String RATINGS_DATA_SERVICE_FALLBACK = "ratingsDataServiceFallback";

  @CircuitBreaker(name = RATINGS_DATA_SERVICE, fallbackMethod = RATINGS_DATA_SERVICE_FALLBACK)
  public UserRating getUserRating(String userId) {
    UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratings/users/" + userId,
        UserRating.class);
    return ratings;
  }

  public UserRating ratingsDataServiceFallback(Exception e) {
    UserRating ratings = new UserRating();
    ratings.setUserId("1");
    ratings.setRatings(
        Arrays.asList(
            new Rating("1", 4),
            new Rating("2", 5)));
    return ratings;
  }
}
