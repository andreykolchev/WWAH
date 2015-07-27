package com.restfully.webapp.services;

/**
 *
 * @author 2015 Andrey Kolchev mailto: andreykolchev@gmail.com
 */
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("services")
public class WebApplication extends Application {
   
    private final Set<Object> singletons = new HashSet<Object>();

   public WebApplication() {
      singletons.add(new GetRESTService());
      singletons.add(new PostRESTService());
  }

   @Override
   public Set<Object> getSingletons() {
      return singletons;
   }
    
 }
