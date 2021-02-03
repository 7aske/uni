#!/usr/bin/env bash

war_name="backend-0.0.1-SNAPSHOT.war"
ctx_path="rzp-blog"

mvn clean && mvn install && cp "target/$war_name" "target/$ctx_path.war"