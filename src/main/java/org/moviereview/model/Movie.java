package org.moviereview.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Movie details 
 */
@Entity
public class Movie implements Serializable
{
   private static final long serialVersionUID = -1428105132312729819L;

   @Id @GeneratedValue private Long id;
   private String title;
   private int year;   
   private String synopsis;
   @ManyToOne @JoinColumn(name = "COUNTRY_ID") private Country country;
   
   public Long getId()
   {
      return id;
   }
   
   public void setId(Long id)
   {
      this.id = id;
   }
   
   public String getTitle()
   {
      return title;
   }
   
   public void setTitle(String title)
   {
      this.title = title;
   }
   
   public int getYear()
   {
      return year;
   }
   
   public void setYear(int year)
   {
      this.year = year;
   }
   
   public String getSynopsis()
   {
      return synopsis;
   }
   
   public void setSynopsis(String synopsis)
   {
      this.synopsis = synopsis;
   }
   
   public Country getCountry()
   {
      return country;
   }
   
   public void setCountry(Country country)
   {
      this.country = country;
   }
}
