package org.moviereview.action;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.seam.servlet.http.HttpParam;
import org.moviereview.model.Movie;
import org.moviereview.model.Review;

public @Model class MovieDetail
{
   @Inject @HttpParam("id") String movieId;
   
   @Inject EntityManager entityManager;
   
   private Movie movie;
   private List<Review> reviews;
   
   @Inject public void load()
   {
      Long id = Long.valueOf(movieId);
      movie = entityManager.find(Movie.class, id);
      reviews = entityManager.createQuery(
            "select r from Review r where r.movie.id = :id")
         .setParameter("id", id)
         .getResultList();
   }
   
   public Movie getMovie()
   {
      return movie;
   }   
   
   public List<Review> getReviews()
   {
      return reviews;
   }
   

}
