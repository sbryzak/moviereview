package org.moviereview.action;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.seam.persistence.transaction.Transactional;
import org.jboss.seam.servlet.http.HttpParam;
import org.moviereview.event.ReviewCreatedEvent;
import org.moviereview.model.Movie;
import org.moviereview.model.Review;
import org.moviereview.model.Reviewer;

public @ConversationScoped @Named class ReviewAction implements IReviewAction, Serializable
{
   private static final long serialVersionUID = -377817487440884371L;
   
   @Inject Conversation conversation;
   @Inject EntityManager entityManager;
   
   @Inject @HttpParam("id") Instance<String> movieId;
   
   @Inject Event<ReviewCreatedEvent> reviewCreatedEvent;
   
   private Review review;
   
   @Inject public void init()
   {
      if (review == null) createReview();
   }

   public void createReview()
   {
      Long id = Long.valueOf(movieId.get());
      Movie movie = entityManager.find(Movie.class, id);
      review = new Review();
      review.setMovie(movie);
      review.setReviewer(new Reviewer());
      review.setReviewDate(new Date());
      conversation.begin();
   }
   
   @Transactional 
   public String saveReview()
   {
      entityManager.persist(review.getReviewer());      
      entityManager.persist(review);
      conversation.end();
      
      reviewCreatedEvent.fire(new ReviewCreatedEvent(review));
      
      return "reviewSaved";
   }
   
   public Review getReview()
   {
      return review;
   }
}
