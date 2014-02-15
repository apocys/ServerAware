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

import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aware.db.model.Client;
import com.aware.service.ClientService;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@ThreadSafe
@Path("/position")
public class PositionUpdateResource {
	//	http://localhost:9888/client/update?px=7.48&py=7.58&name=Person%201

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final PositionManager positionManager;
	private final ClientService clientService;



	@Inject
	PositionUpdateResource(PositionManager positionManager, ClientService clientService) {
		this.positionManager = positionManager;
		this.clientService = clientService;
	}

	@GET
	@Path("/update")
	public String updatePositionClient(@QueryParam("name")  @DefaultValue("name")String name,
			@QueryParam("regId")  @DefaultValue("regId")String regId,
			@QueryParam("password")  @DefaultValue("password")String password,
			@QueryParam("latitude") @DefaultValue("0.0") String latitude,
			@QueryParam("longitude") @DefaultValue("0.0") String longitude
			) {


		logger.info("CALLING UPDATE ->{}--",name);
		try{
			Client client = clientService.authenticateClient(regId,name,password);
			System.err.println(client);
			
			return positionManager.update(client, Double.valueOf(latitude), Double.valueOf(longitude));

		}catch(Exception e){
			return e.getMessage();
		}
		



	}
}
