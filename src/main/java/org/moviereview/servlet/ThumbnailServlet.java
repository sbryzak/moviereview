package org.moviereview.servlet;

import java.io.IOException;

import javax.enterprise.inject.UnsatisfiedResolutionException;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.weld.extensions.beanManager.BeanManagerAccessor;
import org.moviereview.action.ThumbnailSearch;
import org.moviereview.model.MovieThumbnail;

/**
 * 
 * Serves movie thumbnail images
 *
 */
public class ThumbnailServlet extends HttpServlet
{     
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException
   {      
      String[] parts = request.getRequestURI().split("/");
      
      String contentType = null;
      byte[] data = null;
      
      try
      {
         Long movieId = Long.parseLong(parts[parts.length - 1]);

         if (movieId != null)
         {
            MovieThumbnail thumbnail = getThumbnailSearch().getMovieThumbnail(movieId);
            if (thumbnail != null)
            {
               contentType = thumbnail.getContentType();
               data = thumbnail.getData();
            }
         }         
      }
      catch (NumberFormatException ex)
      {
         response.sendError(500, "Invalid movie ID specified");
         return;
      }

           
      if (data != null)
      {
         response.setContentType(contentType);
         response.getOutputStream().write(data);
         response.getOutputStream().flush();
      }
      else
      {
         response.sendError(404, "Thumbnail image not found");
      }
   }
   
   private ThumbnailSearch getThumbnailSearch()
   {
      BeanManager beanManager = BeanManagerAccessor.getBeanManager();
      Bean<?> bean = beanManager.resolve(beanManager.getBeans(ThumbnailSearch.class));
      if (bean == null)
      {
         throw new UnsatisfiedResolutionException("Unable to resolve @Default ThumbnailSearch");
      }
      return ThumbnailSearch.class.cast(beanManager.getReference(bean, 
            ThumbnailSearch.class, beanManager.createCreationalContext(bean)));
   }
}
