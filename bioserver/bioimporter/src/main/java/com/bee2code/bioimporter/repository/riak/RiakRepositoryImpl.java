package com.bee2code.bioimporter.repository.riak;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.ListKeys;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.api.commands.search.StoreIndex;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.basho.riak.client.core.query.search.YokozunaIndex;
import com.basho.riak.client.core.util.BinaryValue;
import com.bee2code.bioimporter.exception.ImporterServiceException;
import com.bee2code.bioimporter.exception.RiakRepositoryException;
import com.google.common.collect.Lists;

public class RiakRepositoryImpl implements RiakRepository {

	private RiakClient riakClient;

	public RiakRepositoryImpl(RiakClient riakClient) throws ImporterServiceException {
		this.riakClient = riakClient;

	}

	@Override
	public void addObject(String bucket, String key, String bioObject) throws RiakRepositoryException {
		System.out.println("OBJECT: " + bioObject);
		Location location = new Location(new Namespace("biotype", bucket), key);

		RiakObject objectToStore = new RiakObject().setContentType("application/json").setValue(BinaryValue.create(bioObject));

		StoreValue sv = new StoreValue.Builder(objectToStore).withLocation(location).build();
		try {
			riakClient.execute(sv);
		} catch (ExecutionException | InterruptedException e) {
			throw new RiakRepositoryException("Error add object", e);
		}
	}

	@Override
	public String getObject(String bucket, String key) throws RiakRepositoryException {
		Location location = new Location(new Namespace(bucket), key);

		FetchValue fv = new FetchValue.Builder(location).build();
		FetchValue.Response response;
		try {
			response = riakClient.execute(fv);
			return response.getValue(String.class);
		} catch (ExecutionException | InterruptedException e) {
			throw new RiakRepositoryException("Error get object", e);
		}

	}

	@Override
	public void deleteObject(String bucket, String key) throws RiakRepositoryException {
		Location location = new Location(new Namespace(bucket), key);

		DeleteValue dv = new DeleteValue.Builder(location).build();
		try {
			riakClient.execute(dv);
		} catch (ExecutionException | InterruptedException e) {
			throw new RiakRepositoryException("Error delete object", e);
		}

	}

	@Override
	public List<Location> listKeys(String bucket, String key) throws RiakRepositoryException {
		List<Location> result = Lists.newArrayList();
		Namespace ns = new Namespace(bucket);
		ListKeys lk = new ListKeys.Builder(ns).build();
		ListKeys.Response response;
		try {
			response = riakClient.execute(lk);
		} catch (ExecutionException | InterruptedException e) {
			throw new RiakRepositoryException("Error list keys", e);
		}
		response.forEach(loc -> result.add(loc));
		return result;
	}

	@Override
	public void createIndex(String bucket, String indexName) throws RiakRepositoryException {
		YokozunaIndex bioIndex = new YokozunaIndex(indexName);
		StoreIndex storeIndex = new StoreIndex.Builder(bioIndex).build();
		try {
			riakClient.execute(storeIndex);
		} catch (ExecutionException | InterruptedException e) {

		}

	}

}
