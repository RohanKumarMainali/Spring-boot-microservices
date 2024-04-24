package io.javabrains.ratingsdataservice.resources;

import java.util.Arrays;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.ratingsdataservice.model.Rating;
import io.javabrains.ratingsdataservice.model.UserRating;

@RestController
@RequestMapping("/ratings")
public class RatingDataResource {

  @RequestMapping("/{movieId}")
  public Rating getRatingData(@PathVariable("movieId") String movieId) {
    return new Rating(movieId, 4);
  }

  @RequestMapping("/users/{userId}")
  public UserRating getUserRating(@PathVariable("userId") String userId) {

    UserRating userRating = new UserRating(
        userId,
        Arrays.asList(
            new Rating("100", 3),
            new Rating("200", 4)));

    return userRating;
  }

}
