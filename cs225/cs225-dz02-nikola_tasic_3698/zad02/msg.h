//
// Created by nik on 11/13/21.
//
#pragma once

#ifndef ZAD02_MSG_H
#define ZAD02_MSG_H

#include <stdio.h>

#define MSG_BUFSIZ BUFSIZ

#define MSG_TYPE_TEXT 0
#define MSG_TYPE_EXIT 1

typedef struct msg {
	int type;
	char buf[BUFSIZ];
} msg_t;

#endif //ZAD02_MSG_H
