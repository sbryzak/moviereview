package org.moviereview.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * A mapping between movies and their genres.  A movie may have more than one genre
 */
@Entity
public class MovieGenre implements Serializable
{
   private static final long serialVersionUID = 8915942942297268859L;
   
   @Id @GeneratedValue private Long id;
   @ManyToOne @JoinColumn(name = "GENRE_ID") Genre genre;
   @ManyToOne @JoinColumn(name = "MOVIE_ID") Movie movie;
   
   public Long getId()
   {
      return id;
   }
   
   public void setId(Long id)
   {
      this.id = id;
   }
   
   public Genre getGenre()
   {
      return genre;
   }
   
   public void setGenre(Genre genre)
   {
      this.genre = genre;
   }
   
   public Movie getMovie()
   {
      return movie;
   }
   
   public void setMovie(Movie movie)
   {
      this.movie = movie;
   }
}
