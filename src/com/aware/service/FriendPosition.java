package com.aware.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public  class FriendPosition {
	private final double latitude;
	private final double longitude;
	private final String name;

	public FriendPosition(String name, double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
	}

	@JsonProperty
	public double getLatitude() {
		return latitude;
	}

	@JsonProperty
	public double getLongitude() {
		return longitude;
	}

	@JsonProperty
	public String getName() {
		return name;
	}

	
	
}