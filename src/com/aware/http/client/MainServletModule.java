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

package com.aware.http.client;

import com.aware.db.dao.ClientDao;
import com.aware.db.dao.FriendsDao;
import com.aware.sandwich.OrganicCrunchyValenciaPeanutButter;
import com.aware.sandwich.SandwichMaker;
import com.aware.sandwich.SandwichMakerResource;
import com.aware.service.ClientService;
import com.aware.service.FriendsService;
import com.aware.service.SendingService;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class MainServletModule extends ServletModule {

	@Override
	protected void configureServlets() {
//		bind(GuiceFilter.class).asEagerSingleton();

//		install(new ServletModule() {
//
//			@Override
//			protected void configureServlets() {
//				filter("/*").through(GuiceFilter.class);
//			}
//		});
		bind(PositionManager.class);
		bind(SandwichMaker.class);
		bind(ClientService.class);

		bind(FriendsService.class);
		bind(SendingService.class);
//		bind(PeanutButter.class).to(OrganicCrunchyValenciaPeanutButter.class)
//				.in(Scopes.SINGLETON);
		bind(PositionUpdateResource.class);
		bind(OrganicCrunchyValenciaPeanutButter.class);
		bind(ClientResource.class);
		bind(FriendsResource.class);

		bind(SandwichMakerResource.class);

		bind(ClientDao.class);
		bind(FriendsDao.class);

		// hook Jersey into Guice Servlet
		bind(GuiceContainer.class);

		// hook Jackson into Jersey as the POJO <-> JSON mapper
		bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);

//		Map<String, String> guiceContainerConfig = new HashMap<String, String>();
//		 guiceContainerConfig.put(ResourceConfig.PROPERTY_RESOURCE_FILTER_FACTORIES,
//		 HttpStatusCodeMetricResourceFilterFactory.class.getCanonicalName());
		serve("/*").with(GuiceContainer.class);
	}
}
