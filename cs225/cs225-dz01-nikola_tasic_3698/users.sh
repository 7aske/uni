#!/usr/bin/bash

set -x

USERS="tim robert grace"

for user in $USERS; do
	useradd -m $user
	echo -ne "$user\n$user" | passwd $user
	chage -M 15 $user
	chage -I 15 $user
	userdel -r $user
done
