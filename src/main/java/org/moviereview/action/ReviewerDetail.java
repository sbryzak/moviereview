package org.moviereview.action;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.seam.servlet.http.HttpParam;
import org.moviereview.model.Review;
import org.moviereview.model.Reviewer;

public @Model class ReviewerDetail
{
   @Inject @HttpParam("id") String reviewerId;
   
   @Inject EntityManager entityManager;
   
   private Reviewer reviewer;
   private List<Review> reviews;
   
   @Inject public void load()
   {
      Long id = Long.valueOf(reviewerId);
      reviewer = entityManager.find(Reviewer.class, id);
      reviews = entityManager.createQuery(
            "select r from Review r where r.reviewer.id = :id")
         .setParameter("id", id)
         .getResultList();      
   }
   
   public Reviewer getReviewer()
   {
      return reviewer;
   }   
   
   public List<Review> getReviews()
   {
      return reviews;
   }

}
