package com.bee2code.bioimporter.repository.riak;

import java.util.List;

import com.basho.riak.client.core.query.Location;
import com.bee2code.bioimporter.exception.RiakRepositoryException;

public interface RiakRepository {

	/**
	 * Create new object in riak
	 * 
	 * @param bucket
	 *            bucket name
	 * @param key
	 *            key as json string
	 * @param bioObject
	 *            object to store as json string
	 * @throws RiakRepositoryException
	 */
	void addObject(String bucket, String key, String bioObject) throws RiakRepositoryException;

	/**
	 * 
	 * @param bucket
	 * @param key
	 * @return
	 * @throws RiakRepositoryException
	 */
	String getObject(String bucket, String key) throws RiakRepositoryException;

	/**
	 * 
	 * @param bucket
	 * @param key
	 * @throws RiakRepositoryException
	 */
	void deleteObject(String bucket, String key) throws RiakRepositoryException;

	/**
	 * 
	 * @param bucket
	 * @param key
	 * @return
	 * @throws RiakRepositoryException
	 */
	List<Location> listKeys(String bucket, String key) throws RiakRepositoryException;

	/**
	 * 
	 * @param indexName
	 * @throws RiakRepositoryException
	 */
	void createIndex(String bucket, String indexName) throws RiakRepositoryException;

}
