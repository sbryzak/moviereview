package org.moviereview.action;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.seam.servlet.http.HttpParam;
import org.moviereview.model.Review;

public @Model class ReviewDetail
{
   @Inject @HttpParam("id") String reviewId;
   
   @Inject EntityManager entityManager;
   
   private Review review;
   
   @Inject public void load()
   {
      Long id = Long.valueOf(reviewId);
      review = entityManager.find(Review.class, id);
   }
   
   public Review getReview()
   {
      return review;
   }
}
