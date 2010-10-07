package org.moviereview.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * A list of countries 
 */
@Entity
public class Country implements Serializable
{
   private static final long serialVersionUID = -4650150881899775164L;
   
   @Id @GeneratedValue private Integer id;
   private String name;
   
   public Integer getId()
   {
      return id;
   }
   
   public void setId(Integer id)
   {
      this.id = id;
   }
   
   public String getName()
   {
      return name;
   }
   
   public void setName(String name)
   {
      this.name = name;
   }
}
