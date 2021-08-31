#!/usr/bin/env bash

read -r -p "tomcat user: " user
read -r -s -p "tomcat pass: " pass

ctx_path="rzp-blog"
src_war="$(pwd)/target/$ctx_path.war"

tomcat_ip="127.0.0.1"
tomcat_port="8181"

if [ ! -e "$src_war" ]; then
   ./scripts/build.sh
fi

curl -v -u "$user:$pass" "http://$tomcat_ip:$tomcat_port/manager/text/undeploy?path=/$ctx_path"
curl -v -u "$user:$pass" -T "$src_war" "http://$tomcat_ip:$tomcat_port/manager/text/deploy?path=/$ctx_path&update=true"
