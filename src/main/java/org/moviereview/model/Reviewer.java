package org.moviereview.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reviewer implements Serializable
{
   private static final long serialVersionUID = 6856479085802179849L;
   @Id private Integer id;
   private String firstName;
   private String lastName;
   
   public Integer getId()
   {
      return id;
   }
   
   public void setId(Integer id)
   {
      this.id = id;
   }
   
   public String getFirstName()
   {
      return firstName;
   }
   
   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }
   
   public String getLastName()
   {
      return lastName;
   }
   
   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }
}
