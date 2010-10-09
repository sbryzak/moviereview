package org.moviereview.action;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.moviereview.model.MovieThumbnail;

public class ThumbnailSearch
{
   @Inject EntityManager entityManager;
   
   public MovieThumbnail getMovieThumbnail(Long movieId)
   {
      try
      {
         return (MovieThumbnail) entityManager.createQuery(
            "select t from MovieThumbnail t where t.movie.id = :id")
            .setParameter("id", movieId)
            .getSingleResult();
      }
      catch (NoResultException ex)
      {
         return null;
      }
   }
}
