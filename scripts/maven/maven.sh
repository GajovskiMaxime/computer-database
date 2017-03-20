#!/bin/bash

cd /usr/src/mymaven ;
mvn clean install -Dmaven.test.skip=true;
