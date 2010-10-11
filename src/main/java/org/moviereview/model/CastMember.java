package org.moviereview.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The cast for a movie.  Maps Actors to Movies in a many-to-many relationship
 */
@Entity
public class CastMember implements Serializable
{
   private static final long serialVersionUID = -4469898253297027853L;
   
   @Id @GeneratedValue private Long id;
   @ManyToOne @JoinColumn(name = "ACTOR_ID") private Actor actor;
   @ManyToOne @JoinColumn(name = "MOVIE_ID") private Movie movie;
   private String role;
   
   public Long getId()
   {
      return id;
   }
   
   public void setId(Long id)
   {
      this.id = id;
   }
   
   public Actor getActor()
   {
      return actor;
   }
   
   public void setActor(Actor actor)
   {
      this.actor = actor;
   }
   
   public Movie getMovie()
   {
      return movie;
   }
   
   public void setMovie(Movie movie)
   {
      this.movie = movie;
   }
   
   public String getRole()
   {
      return role;
   }
   
   public void setRole(String role)
   {
      this.role = role;
   }
}
