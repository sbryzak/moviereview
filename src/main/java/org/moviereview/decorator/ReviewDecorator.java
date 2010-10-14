package org.moviereview.decorator;

import java.io.Serializable;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

import org.moviereview.action.IReviewAction;
import org.moviereview.action.ReviewAction;

@Decorator
public abstract class ReviewDecorator implements IReviewAction, Serializable
{
   @Inject @Delegate ReviewAction delegate;
   
   public String saveReview()
   {
      delegate.getReview().setReview(italicizeMovieTitle(
            delegate.getReview().getMovie().getTitle(), 
            delegate.getReview().getReview()));
      return delegate.saveReview();
   }
   
   private String italicizeMovieTitle(String title, String review)
   {
      return review.replaceAll(title, "<i>" + title + "</i>");
   }
}
