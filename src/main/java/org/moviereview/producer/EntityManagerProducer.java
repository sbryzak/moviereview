package org.moviereview.producer;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;

import org.jboss.seam.persistence.SeamManaged;

public class EntityManagerProducer
{
   @Resource(mappedName="java:/moviereviewEntityManagerFactory")
   private EntityManagerFactory emf;
      
   @RequestScoped
   @Produces
   @SeamManaged
   public EntityManagerFactory createEmf()
   {
      return emf;
   }
}
