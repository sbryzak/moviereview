package org.moviereview.action;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.moviereview.model.Review;

public @Model class ReviewSearch
{
   @Inject EntityManager entityManager;
//   @Inject Foo foo;
   
   /**
    * Returns a list of the latest 5 movie reviews
    * 
    * @return
    */
   public List<Review> getLatestReviews()
   {
      return null; /*entityManager.createQuery(
            "select r from Review r order by r.reviewDate desc")
         .setMaxResults(5)
         .getResultList();*/
   }
}
