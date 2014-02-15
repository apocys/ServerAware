package com.aware.service;

import com.aware.db.dao.ClientDao;
import com.aware.db.dao.FriendsDao;
import com.aware.db.model.Client;
import com.aware.db.model.Friends;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class FriendsService {

	private ClientDao clientDao;
	private FriendsDao friendsDao;

	@Inject
	public FriendsService(ClientDao clientDao,FriendsDao friendsDao ) {
		this.clientDao = clientDao;
		this.friendsDao = friendsDao;
	}


	public Client addNewFriend(Long clientId,String friendName){

		Client friend = clientDao.getByName(friendName);
		if(clientId.equals(friend.getId())){
			throw new IllegalStateException("Cannot add self as friend");
		}


		Friends friends = new Friends();
		friends.setProviderId(friend.getId());
		friends.setRequestId(clientId);

		//change this with correct enum
		friends.setStatus(0L);


		return friend;
	}


	public void removeFriend(Long clientId,String friendName){

		Client friend = clientDao.getByName(friendName);
		if(clientId.equals(friend.getId())){
			throw new IllegalStateException("Cannot remove self as friend");
		}

		Friends friends = friendsDao.getFriends(clientId,friend.getId());

		friendsDao.remove(friends);
	}




}
