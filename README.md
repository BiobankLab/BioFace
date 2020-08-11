# BioFace
opensource software for sample sharing and finding

# Development

[Click to open development readme file](README_DEVELOPER.md)

# Preparation

## Requirements
1. Java JDK version 8 
3. Install Apache Solr version 7.3.1 (download: https://archive.apache.org/dist/lucene/solr/7.3.1/, doc: https://lucene.apache.org/solr/guide/7_3/)
3. Install Momngo version min. 3.7 (doc: https://docs.mongodb.com/?_ga=2.240065027.1076555779.1536906659-468070798.1536649435)
4. Install Keycloak version 4.0.0 FINAL (download: https://www.keycloak.org/archive/downloads-4.0.0.html, doc: https://www.keycloak.org/archive/documentation-4.0.html)

## Prepare data
### Keycloak and mongo
1. Keycloak - In admin panel import Realm (Add new Realm -> Import) from bioface/scripts/bioface-realm-keycloak.json
- Default port for keycloak is 8080, -Djboss.socket.binding.port-offset=1 parameter changing it to 8081, so with this parameter keycloak address is "localhost:8081"
Next import realm in admin console (Add realm -> import), file you can find in scripts/bioface-realm-keycloak.json.
In clients menu (bioface-client, bioface-client-ux) you have to set redirect paths and base url with server address where application is deployed (e.g. if ux is deploy on localhost:8082 set valid redirect uris: "http://localhost:8082/*" and base url "http://localhost:8082/")
2. MongoDb - Create database and execute script from bioface/scripts/mongo_data_create.js using mongo shell
3. Solr: create core and configure (https://lucene.apache.org/solr/)
```
cd solr_dir/bin
./solr start
./solr create -c bioface
``` 
Copy managed-schema file, which you can find in bioface/scripts/managed-schema.xml to your solr core (solr_dir/server/solr/bioface/conf/), then restart solr
```
./solr restart
```
### Properties
1. Create properties file with any name (or use template from bioserver/rest-api-bioface/src/main/resources/application.properties.template)
2. Configure properties - all properties you can see below (important one's are with comment)
```
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

#location where files should be saved
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

# Installation
## Building jar

1. Build ux2 - go into ux2 directory and type (npm required)
```
npm install
npm run build
```
2. copy ux2/dist/  to bioserver/rest-api-bioface/src/main/resources/static/. If you are in ux2 directory type:
```
1. If not exists src/main/resources/static folder (if exists - skip)
mkdir ../bioserver/rest-api-bioface/src/main/resources/static
2. Copy ux2 files
cp -R dist/* ../bioserver/rest-api-bioface/src/main/resources/static/
```

3. in bioserver directory run
```
./gradlew build
```
4. Binary file (jar) you can find in bioserver/rest-api-bioface/build/libs

## Developer run

```
java -jar jar_file_name.jar --spring.config.location=you_properties_file_location
```

## Infrastructure install

[Click to open installation description](scripts/INSTALL.md)

## Keycloak email configuration

All keycloak email configuration options you can find in keycloak administration panel (Select Realm -> Realm settings -> Email).


