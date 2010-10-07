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
   
   @Id @GeneratedValue private Integer id;
   @OneToOne @JoinColumn(name = "MOVIE_ID") private Movie movie;
   private byte[] data;

   public Integer getId()
   {
      return id;
   }
   
   public void setId(Integer id)
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
}
