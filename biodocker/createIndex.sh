#!/bin/bash


export RIAK_HOST="http://172.17.0.1:8098"

#Create schema
curl -XPUT http://localhost:8098/search/schema/bioschema \
  -H "Content-Type: application/xml" \
  --data-binary @bioschema.xml

#Create index
curl -XPUT $RIAK_HOST/search/index/bioindex \
  -H 'Content-Type: application/json' \
  -d '{"schema":"bioschema"}'

#Create bucket type
#curl -XPUT $RIAK_HOST/types/biotype/buckets/biobucket/props \
#     -H'content-type:application/json' \
#     -d'{"props":{"search_index":"bioindex"}}'

docker-compose exec coordinator riak-admin bucket-type create biotype '{"props":{"search_index":"bioindex"}}'

docker-compose exec coordinator riak-admin bucket-type activate biotype
