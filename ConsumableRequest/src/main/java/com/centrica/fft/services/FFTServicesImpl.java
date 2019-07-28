package com.centrica.fft.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/fapp")
public class FFTServicesImpl implements FFTServices {

	@GET
	@Path("/engineers/v1/{engineerId}/partcollections")
	@Produces(MediaType.APPLICATION_JSON)
	public String partcollections(String reqObj, @PathParam("engineerId") String engineerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
