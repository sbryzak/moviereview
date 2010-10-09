package org.moviereview.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Actor details
 */
@Entity
public class Actor implements Serializable
{
   private static final long serialVersionUID = -5577759231154572526L;
   
   @Id private Long id;
   private String firstName;
   private String lastName;
   private Date dob;

   public Long getId()
   {
      return id;
   }
   
   public void setId(Long id)
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
   
   public Date getDateOfBirth()
   {
      return dob;
   }
   
   public void setDateOfBirth(Date dateOfBirth)
   {
      this.dob = dateOfBirth;
   }
}
