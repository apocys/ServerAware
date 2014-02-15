package com.aware.http.client;

import java.util.List;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aware.db.dao.ClientDao;
import com.aware.db.dao.FriendsDao;
import com.aware.db.model.Client;
import com.aware.db.model.Friends;
import com.aware.service.FriendPosition;
import com.aware.service.SendingService;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

@ThreadSafe
@Singleton
public class PositionManager {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final ClientDao clientDao;
	private final FriendsDao friendsDao;
	private final SendingService sendingService;
	
	

	@Inject
	public PositionManager(ClientDao clientDao, FriendsDao friendsDao, SendingService sendingService) {
		this.clientDao = clientDao;
		this.friendsDao = friendsDao;
		this.sendingService = sendingService;
	}

	public String update(Client client, double latitude, double longitude){
		
		logger.error("Client {} ",client);
		client.setLatitude(latitude);
		client.setLongitude(longitude);
		clientDao.update(client);
		
		List<Friends> friends = friendsDao.getByProviderId(client.getId());
		
		
		List<Client> allFriends = clientDao.getByIds(Lists.transform(friends, new Function<Friends, Long>() {

			public Long apply(@Nullable Friends friend) {
				return friend.getRequestId();
			}
		}));
		
		
		sendingService.updatePosition(new FriendPosition(client.getName(), client.getLatitude(), client.getLongitude()), Lists.transform(allFriends, new Function<Client, String>() {

			public String apply(@Nullable Client client) {
				return client.getRegId();
			}
		}));
		
		
		return "UPDATED ";
	}


//	public List<FriendPosition> getPostionForClient(List<String> friends) {
//
//		List<FriendPosition> results = Lists.newArrayList();
//		logger.info(Arrays.deepToString(friends.toArray()));
//		for(String friendName: friends){
//			Client friend = clientDao.getByName(friendName);
//			results.add(new FriendPosition(friend.getName(), friend.getPositionX(), friend.getPositionY()));
//		}
//
//		return results;
//	}




}

