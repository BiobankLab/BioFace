package com.bee2code.bioimporter.integration.repository;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.basho.riak.client.core.util.BinaryValue;
import com.bee2code.bioimporter.Utills;

public class RiakTest {

	@Test
	public void test() throws UnknownHostException, ExecutionException, InterruptedException {
		RiakClient client=Utills.getRiakClient(8087, "127.0.0.1");
		Namespace animalsBucket = new Namespace("animals");
		String json = "application/json";
		
		RiakObject liono = new RiakObject()
		        .setContentType(json)
		        .setValue(BinaryValue.create("{\"name_s\":\"Lion-o\",\"age_i\":30,\"leader_b\":true}"));
		RiakObject cheetara = new RiakObject()
		        .setContentType(json)
		        .setValue(BinaryValue.create("{\"name_s\":\"Cheetara\",\"age_i\":30,\"leader_b\":false}"));
		RiakObject snarf = new RiakObject()
		        .setContentType(json)
		        .setValue(BinaryValue.create("{\"name_s\":\"Snarf\",\"age_i\":43,\"leader_b\":false}"));
		RiakObject panthro = new RiakObject()
		        .setContentType(json)
		        .setValue(BinaryValue.create("{\"name_s\":\"Panthro\",\"age_i\":36,\"leader_b\":false}"));
		Location lionoLoc = new Location(animalsBucket, "liono");
		Location cheetaraLoc = new Location(animalsBucket, "cheetara");
		Location snarfLoc = new Location(animalsBucket, "snarf");
		Location panthroLoc = new Location(animalsBucket, "panthro");

		DeleteValue dv = new DeleteValue.Builder(cheetaraLoc).build();
		client.execute(dv);
		StoreValue lionoStore = new StoreValue.Builder(liono).withLocation(lionoLoc).build();
		//The other StoreValue operations can be built the same way

		StoreValue.Response res = client.execute(lionoStore);
		System.out.println(res);
		
	}
}
