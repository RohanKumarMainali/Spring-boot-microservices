package io.javabrains.ratingsdataservice.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.ratingsdataservice.model.Rating;

@RestController
@RequestMapping("/ratings")
public class RatingDataResource {

  @RequestMapping("/{movieId}")
  public Rating getRatingData(@PathVariable("movieId") String movieId) {
    return new Rating(movieId, 4);
  }

}
