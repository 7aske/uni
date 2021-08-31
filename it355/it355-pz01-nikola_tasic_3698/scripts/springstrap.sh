#!/usr/bin/env sh

springstrap ddl/rzp.ddl \
	-d com.example.backend \
	-o ./ \
	-laAw \
	--enums ddl/enums.json