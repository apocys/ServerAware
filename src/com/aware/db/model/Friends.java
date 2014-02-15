package com.aware.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//CREATE TABLE  FRIENDS (
//		  ID INTEGER NOT NULL auto_increment,
//		  PROVIDER_ID INTEGER NOT NULL,
//		  REQUEST_ID INTEGER  NOT NULL ,
//		  STATUS INTEGER NOT NULL,
//		  PRIMARY KEY  (ID),
//		  UNIQUE KEY (PROVIDERID,REQUESTID)
//		) ;

@Entity
@Table(name = "Friends")
public class Friends {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "provider")
	private Long providerId;

	@Column(name = "request_id")
	private Long requestId;

	@Column(name = "status")
	private Long status;

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public String toString() {
		return "providerId " + providerId + " requestId " + requestId
				+ " status " + status;
	}

}