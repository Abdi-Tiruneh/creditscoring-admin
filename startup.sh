#!/bin/bash
cd /home/ec2-user
aws s3 cp s3://my-cs-bucket/creditscoring-0.0.1-SNAPSHOT.jar .
java -jar creditscoring-0.0.1-SNAPSHOT.jar