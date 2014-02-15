package com.aware.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aware.commons.Utils.STATUS;
import com.aware.db.dao.ClientDao;
import com.aware.db.model.Client;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ClientService {

	private final ClientDao clientDao;
	private final SendingService sendingService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	public ClientService(ClientDao clientDao, SendingService sendingService) {
		this.clientDao = clientDao;
		this.sendingService = sendingService;
	}

	public Client authenticateClient( String regId,
			String name,
			String password){

		Client client = clientDao.getByName(name);

		if(client == null){
			throw new IllegalAccessError(STATUS.INVALID_CLIENT_NAME.name());
		}

		if(!client.getPassword().equals(password)){
			throw new IllegalAccessError(STATUS.INVALID_CLIENT_PASSWORD.name());
		}


		client.setRegId(regId);
		client.setAuthenticationTime(new Date());

//		clientDao.update(client);

		return client;

	}
	
    public void populateDb() throws SQLException {
		// persist people
		Client p1 = new Client();
		p1.setName("Person 1");
		p1.setLatitude(10.52d);
		p1.setLongitude(1.64d);
		clientDao.create(p1);

		Client p2 = new Client();
		p2.setName("Person 2");
		p2.setLatitude(2.52d);
		p2.setLongitude(2.64d);
		clientDao.create(p2);

		Client p3 = new Client();
		p3.setName("Person 3");
		p3.setLatitude(3.52d);
		p3.setLongitude(3.64d);
		clientDao.create(p3);
		
		
//		GcmUsers gcmUsers = new GcmUsers();
//  		gcmUsers.setEmail("email");
//  		gcmUsers.setGcmRegId("gcmRegId");
//  		gcmUsers.setName("name");
//  		gcmUsers.setCreatedAt(new Date());
  		
//  		clientDao.create(gcmUsers);
	}
	
	
	public Client createClient( String regId,
			String name,
			String email, String password){

		Client client = clientDao.getByName(name);

		if(client != null){
			throw new IllegalAccessError(STATUS.INVALID_CLIENT_NAME.name());
		}

		client = new Client();
		client.setRegId(regId);
		client.setAuthenticationTime(new Date());
		client.setDate(new Date());
		client.setName(name);
		client.setEmail(email);
		client.setPassword(password);
		clientDao.create(client);

		return client;

	}
	

	public List<Client> getAllClients(){
		return clientDao.findAll();
	}
	
	public String sendMessgae(String message, String friendName){
		Client friend = clientDao.getByName(friendName);

		if(friend!=null){
		sendingService.sendMessageTo(message, friend.getRegId());
		return "OK";
		}
		return "ERROR SENDING MSG";
		
		
		
	}
	
	

}
