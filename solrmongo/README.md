1) Run:
- to start mongo and solr
	docker-compose up -d
- to start only mongo
    docker-compose up -d mongodb
- to start only solr
    docker-compose up -d solr

2) solr and mongo starts on default ports:
- mongo - 27017
- solr - 8983  

3) solr administration console -> http://localhost:8983/

4) solr create core bioface on starttime

5) Run mongo console
https://www.attosol.com/docker-for-developers-setting-up-mongodb/

docker exec -it solrmongo_mongodb_1 bash
docker exec -it solrmongo_solr_1 bash

RUN mongo bioface scripts/dbcreate.js

docker build mongodb/


Remove solr volumes:
docker volume rm solrmongo_solr-core



Search examples:
http://localhost:8983/solr/bioface/query?q=biobankId_s:12
http://localhost:8983/solr/bioface/query?q=collectionId_s:35
