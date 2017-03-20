#!/bin/bash

useradd -d '/var/jenkins_home' -u 1000 -m -s /bin/bash jenkins;
mkdir -p /var/log/jenkins;
chown -R jenkins:jenkins /var/log/jenkins;
