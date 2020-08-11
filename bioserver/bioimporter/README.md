1) Build jar: 
	./gradlew fatJar
2) Prepare config.json file(template: config/config.json.template):
	"csvFiles" : array of CSVFiles:
		"path" : path to CSVFile 
		"descriptionPath" : path to json file describing CSVFile(example: config/dataDesc.json)
	"mongo" : 
		"url" : url to mongo 
	"solr" : 
		"url" : url to solr server
		"idColumns" : array of columns to create unique id key
		"schemaRemoveFields" : array of field to remove from schema in solr(by field name)
		"schemaAddFields" : array of field to add to schema in solr(type from csv columns)
			add "all_fields" to "schemaAddFields" for add all fields from csv to schema
		
3) Run application:
	java -jar build/libs/bioimporter-app-0.0.1-SNAPSHOT.jar path_to_config_file
	
	
4) TMP: for test after run bioimporter, keys list: 
	http://172.17.0.3:8098/types/biotype/buckets/test_bucket/keys?keys=true

5) Search example: 
	http://localhost:8098/search/query/bioindex?wt=json&q=*:*
	http://localhost:8983/search/query/bioindex?wt=json&q=*:*


Some links:
https://www.attosol.com/docker-for-web-developer-setting-up-your-developer-machine/

docker exec -it solrmongo_mongodb_1 bash
