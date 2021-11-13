cmake_minimum_required(VERSION 3.20)
project(zad02 C)

set(CMAKE_C_STANDARD 99)

add_executable(zad02recv ipc_recv.c msg.h)
add_executable(zad02send ipc_send.c msg.h)
