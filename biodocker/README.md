1) Run:
- to start 1 coordinator (manager and worker), 1 member (only worker) and redis
	docker-compose up -d
- to start only coordinator
    docker-compose up -d coordinator
- to add/remove workers
    docker-compose scale member=<ile_instancji>
- to start coordinator and redis
    docker-compose up -d coordinator redis

2) When all the member containers have started, you should be able to execute commands like riak-admin cluster status on the coordinator and see that the member nodes have successfully joined the cluster.
	docker-compose exec coordinator riak-admin cluster status

3) Create bucketType, index
	createIndex.sh

4) Riak admin console:
	http://localhost:8098/admin/

	
