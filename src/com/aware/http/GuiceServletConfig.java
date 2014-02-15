package com.aware.http;

import com.aware.http.client.HelloResource;
import com.aware.http.client.MainModule;
import com.aware.http.client.MainServletModule;
import com.aware.sandwich.SandwichMakerResource;
import com.aware.sandwich.SandwichModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceServletConfig extends GuiceServletContextListener {
	

	@Override
	protected Injector getInjector() {
		Injector injector = Guice.createInjector(new MainServletModule());
		
		
		return injector;
//		return Guice.createInjector(new ServletModule() {
//
//			@Override
//			protected void configureServlets() {
//				
//				
////				bind(GuiceFilter.class).asEagerSingleton();
//				bind(OrganicCrunchyValenciaPeanutButter.class);
//				bind(SandwichMakerResource.class);
//				bind(SandwichMaker.class);
//				bind(HelloResource.class);
//				serve("/*").with(GuiceContainer.class, params);
//			}
//		});
	}
}