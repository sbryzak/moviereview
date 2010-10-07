package org.moviereview.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A list of movie genres
 */
@Entity
public class Genre implements Serializable
{
   private static final long serialVersionUID = -225969864253613719L;
   @Id private Integer id;
   private String description;
   
   public Integer getId()
   {
      return id;
   }
   
   public void setId(Integer id)
   {
      this.id = id;
   }
   
   public String getDescription()
   {
      return description;
   }
   
   public void setDescription(String description)
   {
      this.description = description;
   }
}
