package org.jboss.weld.eap;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;

import org.jboss.weld.bootstrap.spi.BeanDeploymentArchive;
import org.jboss.weld.environment.servlet.util.Reflections;
import org.jboss.weld.extensions.beanManager.BeanManagerAccessor;
import org.jboss.weld.extensions.beanManager.BeanManagerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>This is a fixed version of the provider from Weld 1.1.0.Beta1, fixed in Beta2 of Weld</b>
 * 
 * Implements the Weld Extensions {@link BeanManagerProvider} SPI. Used to get {@link BeanManager}
 * from outside a Servlet context.<br/>
 * <br/>
 * This Provider has a Precedence of 101, it needs to be loaded before any other in a Google App Engine Environment. The default provided
 * JNDI based providers will fail in Google App Engine with a NoClassDefFoundError, reference to InitialContext is not allowed:<br/>
 * java.lang.NoClassDefFoundError: javax.naming.InitialContext is a restricted class. Please see the Google  App Engine developer's guide for more details.   
 * <br/>
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @see BeanManagerAccessor
 * @see BeanManagerProvider
 */
public class WeldServletBeanManagerProvider implements BeanManagerProvider, Extension
{
   private static String CONTAINER_CLASS_NAME = "org.jboss.weld.Container";
   private static String CONTAINER_INSTANCE_METHOD = "instance";
   private static String CONTAINER_AVAILABLE_METHOD = "available";
   private static String CONTAINER_BEAN_DEPLOYMENT_METHOD = "beanDeploymentArchives";
   private static String BEAN_DEPLOYMENT_ARCHIVE_ID = "flat";
   
   private static String ERROR_GETTING_BEAN_DEPLOYMENT_ARCHIVES = "Could not get BeanDeploymentArchives from Container";
   private static String ERROR_CONTAINER_NOT_AVAILABLE = "Container has not been initialized";
   
   private static final Logger log = LoggerFactory.getLogger(WeldServletBeanManagerProvider.class);
   
   public int getPrecedence()
   {
      // SLightly higher than the copy in Weld :-)
      return 102;
   }

   public BeanManager getBeanManager()
   {
      Class<?> containerClass = null;
      try
      {
         containerClass = Reflections.classForName(CONTAINER_CLASS_NAME);
      }
      catch (IllegalArgumentException e) 
      {
         // ClassNotFound, Weld not on ClassPath, move on
         return null;
      }
      try
      {
         if(isContainerAvailable(containerClass))
         {
            Object container = getAvailableContainer(containerClass);
            Map<?, ?> beanDeploymentArchives = getBeanDeploymentArchives(containerClass, container);
            return findBeanDeploymentArchiveById(beanDeploymentArchives, BEAN_DEPLOYMENT_ARCHIVE_ID);
         }
         else
         {
            log.debug(ERROR_CONTAINER_NOT_AVAILABLE);
         }
      }
      catch (Exception e) 
      {
         log.warn(ERROR_GETTING_BEAN_DEPLOYMENT_ARCHIVES, e);
      }
      return null;
   }
   
   // Container.available()   
   private boolean isContainerAvailable(Class<?> containerClass) 
   {
      Method available = Reflections.findDeclaredMethod(containerClass, CONTAINER_AVAILABLE_METHOD);
      if(available == null)
      {
         throw new IllegalArgumentException("Method " + CONTAINER_AVAILABLE_METHOD + " could not be found on " + containerClass);
      }
      return Reflections.invokeMethod(available, Boolean.class, null);
   }
   
   // Container.instance()
   private Object getAvailableContainer(Class<?> containerClass)
   {
      Method instance = Reflections.findDeclaredMethod(containerClass, CONTAINER_INSTANCE_METHOD);
      if(instance == null)
      {
         throw new IllegalArgumentException("Method " + CONTAINER_INSTANCE_METHOD + " could not be found on " + containerClass);
      }
      
      return Reflections.invokeMethod(instance, Object.class, null);
   }
   
   // Container.deploymentManager()
   private Map<?, ?> getBeanDeploymentArchives(Class<?> containerClass, Object container)
   {
      Method deploymentManager = Reflections.findDeclaredMethod(containerClass, CONTAINER_BEAN_DEPLOYMENT_METHOD);
      if(deploymentManager == null)
      {
         throw new IllegalArgumentException("Method " + CONTAINER_BEAN_DEPLOYMENT_METHOD + " could not be found on " + containerClass);
      }
      
      return Reflections.invokeMethod(deploymentManager, Map.class, container);
   }
   
   // for each BeanDeploymentArchive.getId()
   private BeanManager findBeanDeploymentArchiveById(Map<?, ?> beanDeploymentArchives, String id)
   {
      for(Entry<?, ?> beanDeploymentEntry : beanDeploymentArchives.entrySet())
      {
         Object beanManagerDeployment = beanDeploymentEntry.getKey();
         if (!(beanManagerDeployment instanceof BeanDeploymentArchive))
         {
            throw new IllegalStateException("beanDeploymentArchive is not a BeanDeploymentArchive");
         }

         String beanArchiveId = BeanDeploymentArchive.class.cast(beanManagerDeployment).getId();
         if(id.equals(beanArchiveId))
         {
            return BeanManager.class.cast(beanDeploymentEntry.getValue());
         }
      }
      return null;
   }
}