pt-get update;
yes | apt-get upgrade;

yes | apt-get install vim;
yes | apt-get install libxml2-utils;

mkdir -p /var/jenkins_home/pom

echo 'export DOCKERHUB_NAME=mgajovski' >> /root/.bashrc
echo 'export DOCKERHUB_PASSWORD=excilys2017' >> /root/.bashrc
echo 'export DEPLOYED_DIR_COPY=/var/jenkins_home/pom' >> /root/.bashrc
source /root/.bashrc

mkdir -p /var/cache/jenkins;
mkdir -p /var/log/jenkins

chown -R jenkins:jenkins /var/log/jenkins;
chown -R jenkins:jenkins /var/cache/jenkins;
tini  -- /usr/local/bin/jenkins.sh;

