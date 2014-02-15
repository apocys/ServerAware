package com.aware.commons;


public class Utils {

	public enum STATUS{
		FAILED,//
		SUCCESSFUL,//
		SIGN_UP_CLIENTNAME_CRASHED,//
		ADD_NEW_CLIENTNAME_NOT_FOUND,//
		TIME_INTERVAL_FOR_CLIENT_STATUS,//
		CLIENT_APPROVED,//
		CLIENT_UNAPPROVED,//
		INVALID_CLIENT_NAME,//
		INVALID_CLIENT_PASSWORD;
	}
	
}


////if operation is failed by unknown reason
//define("FAILED", 0);
//
//define("SUCCESSFUL", 1);
////when  signing up, if username is already taken, return this error
//define("SIGN_UP_USERNAME_CRASHED", 2);  
////when add new friend request, if friend is not found, return this error 
//define("ADD_NEW_USERNAME_NOT_FOUND", 2);
//
////TIME_INTERVAL_FOR_USER_STATUS: if last authentication time of user is older 
////than NOW - TIME_INTERVAL_FOR_USER_STATUS, then user is considered offline
//define("TIME_INTERVAL_FOR_USER_STATUS", 60);
//
//define("USER_APPROVED", 1);
//define("USER_UNAPPROVED", 0);