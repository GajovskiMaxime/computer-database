#!/bin/bash

docker start -i mavenUnitTest
cd /var/jenkins_home/workspace/cdb_builds

find -iname *.war
war=$(find -iname *.war | sed 's#.*/##')
if [ -z $war ]
then
        echo "No war found"
else

        cp $(find -iname *.war) /var/jenkins_home/deploy;

        POM_DEPLOYED_LOCATION=$(find /var/jenkins_home/deploy/ -name 'pom.xml');
        cp $POM_DEPLOYED_LOCATION $DEPLOYED_DIR_COPY

        #Trick used to know which is the current version 
        POM_COPY_LOCATION=$DEPLOYED_DIR_COPY/pom.xml
        PROJECT_ARTIFACT=$(cat $POM_COPY_LOCATION | xmllint --format - | sed '2 s/xmlns=".*"//g' | xmllint --xpath '/project/artifactId/text()' -);
        PROJECT_VERSION=$(cat $POM_COPY_LOCATION | xmllint --format - | sed '2 s/xmlns=".*"//g' | xmllint --xpath '/project/version/text()' -);
        rm -rf $POM_COPY_LOCATION

        #Connect to docker
        docker login --username=$DOCKERHUB_NAME --password=$DOCKERHUB_PASSWORD
        docker commit tomcat $DOCKERHUB_NAME/$PROJECT_ARTIFACT:$PROJECT_VERSION-$BUILD_ID
        docker push $DOCKERHUB_NAME/$PROJECT_ARTIFACT
fi
