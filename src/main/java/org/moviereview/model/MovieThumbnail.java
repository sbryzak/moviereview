package org.moviereview.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Contains a thumbnail image for each movie 
 */
@Entity
public class MovieThumbnail implements Serializable
{
   private static final long serialVersionUID = 1674512388723776252L;
   
   @Id @GeneratedValue private Long id;
   @OneToOne @JoinColumn(name = "MOVIE_ID") private Movie movie;
   private byte[] data;
   private String contentType;

   public Long getId()
   {
      return id;
   }
   
   public void setId(Long id)
   {
      this.id = id;
   }
   
   public Movie getMovie()
   {
      return movie;
   }
   
   public void setMovie(Movie movie)
   {
      this.movie = movie;
   }
   
   public byte[] getData()
   {
      return data;
   }
   
   public void setData(byte[] data)
   {
      this.data = data;
   }
   
   public String getContentType()
   {
      return contentType;
   }
   
   public void setContentType(String contentType)
   {
      this.contentType = contentType;
   }
}
