#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $DIR/../bioimporter 
./gradlew fatJar
scp -P 10022 build/libs/bioimporter-app-0.0.1-SNAPSHOT.jar bioface@localhost:/opt/bioface/lib
scp -P 10022 src/integration/resources/data.csv bioface@localhost:/opt/bioface/data
scp -P 10022 src/main/resources/dataDesc.json bioface@localhost:/opt/bioface/conf
cd $DIR/../ux
npm run build
scp -r -P 10022 dist/* root@localhost:/var/www/html/bioface
echo $DIR
