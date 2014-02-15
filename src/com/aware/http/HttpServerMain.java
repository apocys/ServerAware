/*
 * Copyright © 2011. Team Lazer Beez (http://teamlazerbeez.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aware.http;

import org.slf4j.Logger;

import com.aware.db.dao.ClientDao;
import com.aware.http.client.MainModule;
import com.aware.http.client.MainServletModule;
import com.aware.sandwich.SandwichModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;


public class HttpServerMain extends GuiceServletContextListener {
	
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(HttpServerMain.class);
	
	private static ClientDao clientDao;
	
	  @Override
	  protected Injector getInjector() {
      
			Injector injector = Guice.createInjector(new AbstractModule() {
	            @Override
	            protected void configure() {
	                binder().requireExplicitBindings();

	                
	                install(new MainServletModule());
//	                install(new MainModule());
	                
//	                bind(GuiceFilter.class);
	                
	            }
	        });

	    	//initialize the db
			clientDao = injector.getInstance(ClientDao.class);

//			gcmUserDao = injector.getInstance(GcmUserDao.class);
//			try {
//				clientDao.removeAll();
//				LOGGER.info("Populating DB..");
//				populateDb();
//			} catch (SQLException e) {
//				LOGGER.error("There was an error while populating db.. ", e);
//			}

			LOGGER.info("Querying DB for person with name Person 2..");
			
			LOGGER.info("Retrieved {} entities", clientDao.findAll().size());
			
			// retrieve one person from the persisted people
//			Client retrieved = personDao.getByName("Person 2");
//			LOGGER.info("Retrieved {}" , retrieved.getName());
	    	

//	        Server server = new Server(PORT);
//
//	        ServletContextHandler handler = new ServletContextHandler();
//	        handler.setContextPath("/");
//
//	        handler.addServlet(new ServletHolder(new InvalidRequestServlet()), "/*");
//
//	        FilterHolder guiceFilter = new FilterHolder(injector.getInstance(GuiceFilter.class));
//	        handler.addFilter(guiceFilter, "/*", EnumSet.allOf(DispatcherType.class));
//
//	        server.setHandler(handler);
//	        server.start();  
		  
		  
		  
		  
//    	Injector injector = Guice.createInjector( new AbstractModule() {
//            @Override
//            protected void configure() {
////                binder().requireExplicitBindings();
//
//                
////                install(new SandwichModule());
////                install(new JerseyMetricsModule());
//                install(new DbModule());
//                install(new MainModule());
//                
//               
//        }}, new MainServletModule());

    	//initialize the db
//    	injector.getInstance(DbInitializer.class);
//		clientDao = injector.getInstance(ClientDao.class);

//		gcmUserDao = injector.getInstance(GcmUserDao.class);
//		try {
//			clientDao.removeAll();
//			LOGGER.info("Populating DB..");
//			populateDb();
//		} catch (SQLException e) {
//			LOGGER.error("There was an error while populating db.. ", e);
//		}

//		LOGGER.info("Querying DB for person with name Person 2..");
		// retrieve one person from the persisted people
//		Client retrieved = personDao.getByName("Person 2");
//		LOGGER.info("Retrieved {}" , retrieved.getName());
    	

//        Server server = new Server(PORT);
//
//        ServletContextHandler handler = new ServletContextHandler();
//        handler.setContextPath("/");
//
//        handler.addServlet(new ServletHolder(new InvalidRequestServlet()), "/*");
//
//        FilterHolder guiceFilter = new FilterHolder(injector.getInstance(GuiceFilter.class));
//        handler.addFilter(guiceFilter, "/*", EnumSet.allOf(DispatcherType.class));
//
//        server.setHandler(handler);
//        server.start();
//		filter("/*").through(MyFilter.class);
		return injector;
        
//        'https://android.googleapis.com/gcm/send
        
    }
    
   
    
    
//    @Transactional
//    private static void populateDb() throws SQLException {
//		// persist people
//		Client p1 = new Client();
//		p1.setName("Person 1");
//		p1.setLatitude(10.52d);
//		p1.setLongitude(1.64d);
//		clientDao.create(p1);
//
//		Client p2 = new Client();
//		p2.setName("Person 2");
//		p2.setLatitude(2.52d);
//		p2.setLongitude(2.64d);
//		clientDao.create(p2);
//
//		Client p3 = new Client();
//		p3.setName("Person 3");
//		p3.setLatitude(3.52d);
//		p3.setLongitude(3.64d);
//		clientDao.create(p3);
//		
//		
////		GcmUsers gcmUsers = new GcmUsers();
////  		gcmUsers.setEmail("email");
////  		gcmUsers.setGcmRegId("gcmRegId");
////  		gcmUsers.setName("name");
////  		gcmUsers.setCreatedAt(new Date());
//  		
////  		clientDao.create(gcmUsers);
//	}
}
