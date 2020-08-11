package com.bee2code.bioimporter;

import java.net.UnknownHostException;

import com.basho.riak.client.api.RiakClient;

public class Utills {

	/**
	 * Create riakClient
	 * 
	 * @param port
	 * @param nodes
	 * @return
	 * @throws UnknownHostException
	 */
	public static RiakClient getRiakClient(Integer port, String... nodes) throws UnknownHostException {
		return RiakClient.newClient(port, nodes);
	}

}
