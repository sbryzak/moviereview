package org.moviereview.action;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.moviereview.event.ReviewCreatedEvent;
import org.moviereview.model.Review;

public @ApplicationScoped @Named class ReviewSearch
{
   @Inject EntityManager entityManager;
   
   private List<Review> latestReviews;
   
   @Inject public void loadLatestReviews()
   {
      latestReviews = entityManager.createQuery(
            "select r from Review r order by r.reviewDate desc")
         .setMaxResults(5)
         .getResultList();      
      
      
   }
   
   /**
    * Returns a list of the latest 5 movie reviews
    * 
    * @return
    */
   public List<Review> getLatestReviews()
   {
      return latestReviews;
   }
   
   public void reviewCreated(@Observes ReviewCreatedEvent event)
   {
      latestReviews.add(0, event.getReview());
      while (latestReviews.size() > 5)
      {
         latestReviews.remove(latestReviews.size() - 1);
      }
   }
   
}
