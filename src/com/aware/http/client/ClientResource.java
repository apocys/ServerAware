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

import java.util.List;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aware.commons.Utils.STATUS;
import com.aware.db.model.Client;
import com.aware.service.ClientService;
import com.aware.service.SendingService;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@ThreadSafe
@Path("/client")
public class ClientResource {

	//	http://localhost:9888/client/get?friends=Person%202
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ClientService clientService;

	
	private SendingService sendingService;

	@Inject
	ClientResource(ClientService clientService, SendingService sendingService) {
		this.clientService = clientService;
		this.sendingService = sendingService;
	}


	//Use this first
	@GET
	@Path("/authenticate")
	public String authenticateUser(@QueryParam("regId") @DefaultValue("gcmRegId") String regId,
			@QueryParam("name")  @DefaultValue("name")String name,
			@QueryParam("password")  @DefaultValue("password")String password) {

		try{
			Client client = clientService.authenticateClient(regId,name,password);
		}catch(Exception e){
			return STATUS.CLIENT_UNAPPROVED.name();
		}
		return STATUS.CLIENT_APPROVED.name();
	}



	//Use this first
	@POST
	@Path("/sendBroadcast")
	public void sendBroadcast(@QueryParam("message") @DefaultValue("testMessage") String message) {
		List<String> clients = Lists.transform(clientService.getAllClients(), new Function<Client, String>() {

			public String apply(@Nullable Client client) {
				return client.getRegId();
			}
		});

		logger.error(">BROASDCAST SENDING : {}", message);
		if(!clients.isEmpty())
		{
			sendingService.sendBroadcastMessageTo(message,  clients);
			}
	}
	
	//Use this first
	@GET
	@Path("/send")
	public String sendBroadcast(@QueryParam("regId") @DefaultValue("gcmRegId") final String regId,
			@QueryParam("name")  @DefaultValue("name") final String name,
			@QueryParam("friendName")  @DefaultValue("friendName") final String friendName,
			@QueryParam("password")  @DefaultValue("password") final String password,
			@QueryParam("message")  @DefaultValue("message")String message) {

			try{
				Client client = clientService.authenticateClient(regId,name,password);
			}catch(Exception e){
				return STATUS.CLIENT_UNAPPROVED.name();
			}
			
			return	clientService.sendMessgae(message, friendName);
			
	}




	//Use this first
	@GET
	@Path("/create")
	public String createUser(@QueryParam("email") @DefaultValue("too@g.com") String email,
			@QueryParam("regId") @DefaultValue("gcmRegId") String regId,
			@QueryParam("name")  @DefaultValue("name")String name,
			@QueryParam("password")  @DefaultValue("password")String password) {

		try{
//			clientService.populateDb();
			Client client = clientService.createClient(regId,name,email, password);
		}catch(Exception e){
			return STATUS.CLIENT_UNAPPROVED.name();
		}
		return STATUS.CLIENT_APPROVED.name();



	}





}
