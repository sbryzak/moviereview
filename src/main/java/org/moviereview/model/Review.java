package org.moviereview.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Review implements Serializable
{
   private static final long serialVersionUID = 7586263853266540284L;
   @Id private Integer id;
   private Date reviewDate;
   @ManyToOne @JoinColumn(name = "MOVIE_ID") private Movie movie;
   @ManyToOne @JoinColumn(name = "REVIEWER_ID") private Reviewer reviewer;
   private String summary;
   private String review;
   private int rating;
   
   public Integer getId()
   {
      return id;
   }
   
   public void setId(Integer id)
   {
      this.id = id;
   }
   
   public Date getReviewDate()
   {
      return reviewDate;
   }
   
   public void setReviewDate(Date reviewDate)
   {
      this.reviewDate = reviewDate;
   }
   
   public Movie getMovie()
   {
      return movie;
   }
   
   public void setMovie(Movie movie)
   {
      this.movie = movie;
   }
   
   public Reviewer getReviewer()
   {
      return reviewer;
   }
   
   public void setReviewer(Reviewer reviewer)
   {
      this.reviewer = reviewer;
   }
   
   public String getSummary()
   {
      return summary;
   }
   
   public void setSummary(String summary)
   {
      this.summary = summary;
   }
   
   public String getReview()
   {
      return review;
   }
   
   public void setReview(String review)
   {
      this.review = review;
   }
   
   public int getRating()
   {
      return rating;
   }
   
   public void setRating(int rating)
   {
      this.rating = rating;
   }
}
