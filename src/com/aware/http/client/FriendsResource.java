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

import com.aware.commons.Action;
import com.aware.db.model.Client;
import com.aware.service.ClientService;
import com.aware.service.FriendsService;
import com.aware.service.SendingService;
import com.google.common.collect.Lists;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@ThreadSafe
@Path("/friends")
public class FriendsResource {

	//	http://localhost:9888/client/get?friends=Person%202
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private FriendsService friendsService;
	private ClientService clientService;

	private SendingService sendingService;

	@Inject
	FriendsResource(FriendsService friendsService, SendingService sendingService, ClientService clientService) {
		this.friendsService = friendsService;
		this.clientService = clientService;
		this.sendingService = sendingService;
	}


	//Use this first
	@GET
	@Path("/add")
	public String addNewFriend(@QueryParam("friendName") @DefaultValue("friendName") String friendName,
			@QueryParam("name")  @DefaultValue("name")String name,
			@QueryParam("password")  @DefaultValue("password")String password,
			@QueryParam("regId")  @DefaultValue("regId")String regId) {

		try{
			Client client = clientService.authenticateClient(regId,name,password);
			addFriend(friendName, client);

		}catch(Exception e){
			return e.getMessage();
		}
		return "NEW FRIEND  ADDED";
	}


	private void addFriend(String friendName, Client client) {
		Client friend = friendsService.addNewFriend(client.getId(),friendName);
		sendingService.sendActionMessageTo(Action.FRIEND_ADD_REQUEST,client.getName(),friend.getRegId());
	}


	//Use this first
	@GET
	@Path("/remove")
	public String removeFriend(@QueryParam("friendName") @DefaultValue("friendName") String friendName,
			@QueryParam("name")  @DefaultValue("name")String name,
			@QueryParam("password")  @DefaultValue("password")String password,
			@QueryParam("regId")  @DefaultValue("regId")String regId) {

		try{
			Client client = clientService.authenticateClient(regId,name,password);
			friendsService.removeFriend(client.getId(),friendName);

		}catch(Exception e){
			return e.getMessage();
		}
		return "FRIEND  REMOVED";
	}

	
	//Use this first
		@GET
		@Path("/responseOfFriendReqs")
		public String responseOfFriendReqs(@QueryParam("approvedFriends") @DefaultValue("approvedFriends") String approvedFriends,
				@QueryParam("unapprovedFriends") @DefaultValue("approvedFriends") String unapprovedFriends,
				@QueryParam("name")  @DefaultValue("name")String name,
				@QueryParam("password")  @DefaultValue("password")String password,
				@QueryParam("regId")  @DefaultValue("regId")String regId) {

			try{
				Client client = clientService.authenticateClient(regId,name,password);
				
				List<String> unapproveds = Lists.newArrayList(unapprovedFriends.split(","));
				List<String> approveds = Lists.newArrayList(approvedFriends.split(","));
				for(String unapproved : unapproveds){
					friendsService.removeFriend(client.getId(),unapproved);
				}
				
				for(String approved : approveds){
					addFriend(approved, client);
				}
				

			}catch(Exception e){
				return e.getMessage();
			}
			return "RESPONSE OF FRIEND REQUEST DONE";
		}



}
