## Development

### Install Java JDK version: 8

### Install Docker and Docker compose

### Add lombok project to your IDE
ref: https://projectlombok.org/

### Install Solr version 7.3.1
ref: https://archive.apache.org/dist/lucene/solr/7.3.1/

### Install NodeJs (v8)
ref: https://nodejs.org/en/

### Install gradle (v 4.8)
ref: https://gradle.org/install/

### Configure bee2code nexus in gradle (account required)

### Run MongoDB Docker
```
cd solrmongo
docker-compose up
or if you want to run in backgroud
docker-compose up -d 
```
execute script from bioface/scripts/mongo_data_create.js - instead of database_name place you database name

```
mongo --eval 'var databaseName="database_name"' mongo_data_create.js
```

### Solr: create core and configure (https://lucene.apache.org/solr/)
```
cd solr_dir/bin
./solr start
./solr create -c bioface
``` 
Copy managed-schema file, which you can find in bioface/scripts/managed-schema.xml to your solr core (solr_dir/server/solr/bioface/conf/), then restart solr
```
./solr restart
```

### Download and configure Keycloak version 4.5.0 FINAL 
refs: https://www.keycloak.org/ , https://www.keycloak.org/archive/downloads-4.0.0.html

```
cd bin
./standalone.sh -Djboss.socket.binding.port-offset=1
```
Default port for keycloak is 8080, -Djboss.socket.binding.port-offset=1 parameter changing it to 8081, so with this parameter keycloak address is "localhost:8081"
Next import realm in admin console (Add realm -> import), file you can find in scripts/bioface-realm-keycloak.json.
In clients menu (bioface-client, bioface-client-ux) you have to set redirect paths and base url with server address where application is deployed (e.g. if ux is deploy on localhost:8082 set valid redirect uris: "http://localhost:8082/*" and base url "http://localhost:8082/")


### Run API Server
```
cd bioserver
cp rest-api-bioface/src/main/resources/application.properties.template rest-api-bioface/src/main/resources/application.properties
./gradlew bootRun
```
### Run ux2
```
cd ux2
npm install
npm run dev
```

### Properties configuration

1. Create properties file with name "application.properties" (or use template from bioserver/rest-api-bioface/src/main/resources/application.properties.template)
2. Configure properties - all properties you can see below (important one's are with comment)
3. Copy created file to bioserver/rest-api-bioface/src/main/resources/
```
springfox.documentation.swagger.v2.path=/api-docs
springfox.documentation.swagger.v2.path=/api-docs
# Context path for server
server.servlet.context-path=/bioface
# Server port
server.port=8082
spring.jackson.date-format=com.bioface.invoker.RFC3339DateFormat
spring.jackson.serialization.WRITE_DATES_AS_TIMESTAMPS=false
# MongoDB address
spring.data.mongodb.host=localhost
# MongoDB port
spring.data.mongodb.port=27017
# MongoDB database name
spring.data.mongodb.database=bioface
# Apache solr URL (with core - in this case "bioface")
solr.url=http://localhost:8983/solr/bioface

spring.servlet.multipart.max-file-size=100MB
spring.servlet.multipart.max-request-size=100MB

#location where files with data should be saved
file.upload.location=/opt/bioface/data/

# Keycloak auth URL
keycloak.auth-server-url=http://localhost:8081/auth
# Keycloak realm name
keycloak.realm=bioface
keycloak.ssl-required=external
# Keycloak client name
keycloak.resource=bioface-client
# Keycloak client secret
keycloak.credentials.secret=54b6d77f-f9e4-4008-a8d4-f2a16c1d8d1d
keycloak.confidential-port=0
# Attribute returned by principal
keycloak.principal-attribute=preferred_username


spring.h2.console.enabled=true
# H2 database source
# (!) MAKE SURE THAT FILE IS PROPERLY SECURED
spring.datasource.url=jdbc:h2:~/test;DB_CLOSE_ON_EXIT=FALSE
# H2 database username
spring.datasource.username=sa
# H2 database user password
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.liquibase.enabled=true
spring.datasource.hikari.max-active=10
spring.datasource.tomcat.max-active=10
spring.liquibase.change-log=classpath:db/changelog/master.xml

# admin keycloak client
admin.keycloak.client=admin-cli

#(!) This user should have permission ( keycloak admin console -> users -> select user -> Role Mappings tab -> Client Roles (select realm managment) -> Add role "manage-users" )

# admin username (needed for adding users from application)
admin.keycloak.username=admin
# admin password (needed for adding users from application)
admin.keycloak.password=admin
```
