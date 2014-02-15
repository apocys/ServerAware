package com.aware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aware.commons.Action;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.inject.Singleton;

@Singleton
public class SendingService {

	private static final String SENDER = "AIzaSyB6w9ifOQL62B814laoTQ8KUuyd-pX7D0Y";
	private Logger logger = LoggerFactory.getLogger(this.getClass());	
	
	private Sender sender;
	
	public SendingService() {
		this.sender =  new Sender(SENDER);;
	}
	
	
	
	public void sendBroadcastMessageTo(String msg, List<String> regIds){
		try {

			Message message = new Message.Builder()
			.collapseKey("1")
			.timeToLive(3)
			.delayWhileIdle(true)
			.addData("action",Action.MESSAGE.name())
			.addData("data",msg)
			.build();


			MulticastResult result = sender.send(message, regIds, 1);


			logger.info(result.toString());			
		} catch (Exception e) {
			logger.error("send broadcast message failed",e);
		}

	}

	public void sendActionMessageTo(Action action, String from, String toRegId){
		try {

			Message message = new Message.Builder()
			.collapseKey("1")
			.timeToLive(3)
			.delayWhileIdle(true)
			.addData("action",Action.FRIEND_ADD_REQUEST.name())
			.addData("data",from)
			.build();


			Result result = sender.send(message, toRegId, 1);


			logger.info(result.toString());			
		} catch (Exception e) {
			logger.error("send broadcast message failed",e);
		}

	}
	
	public void updatePosition(FriendPosition position, List<String> regIds){
		try {

			Message message = new Message.Builder()
			.collapseKey("1")
			.timeToLive(3)
			.delayWhileIdle(true)
			.addData("action",Action.POSITION_UPDATE.name())
			.addData("name",position.getName())
			.addData("latitude",String.valueOf(position.getLatitude()))
			.addData("name",String.valueOf(position.getLongitude()))
			.build();


			MulticastResult result = sender.send(message, regIds, 1);


			logger.info(result.toString());			
		} catch (Exception e) {
			logger.error("send broadcast message failed",e);
		}
		
		
	}

	
	
	public String sendMessageTo(String msg, String regId){

		try {

			Message message = new Message.Builder()
			.collapseKey("1")
			.timeToLive(3)
			.delayWhileIdle(true)
			.addData("message",msg)
			.build();


			Result result = sender.send(message, regId, 1);


			logger.info(result.toString());			
		} catch (Exception e) {
			logger.error("send message failed",e);
			return "ERROR";
		}
		
		return "OK";

	}


}
