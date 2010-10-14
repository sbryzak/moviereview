package org.moviereview.event;

import org.moviereview.model.Review;

public class ReviewCreatedEvent
{
   private Review review;
   
   public ReviewCreatedEvent(Review review)
   {
      this.review = review;
   }
   
   public Review getReview()
   {
      return review;
   }
}
