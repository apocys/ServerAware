package com.aware.http.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.google.inject.servlet.RequestScoped;

@Path("hello")
@RequestScoped
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class HelloResource {
	
	@Context 
	UriInfo uriInfo;
	
	
	@GET
	@Path("/{name}")
	public String reply(@PathParam("name") String name){
		return "hELLO " + name;
	}
	

	
}
