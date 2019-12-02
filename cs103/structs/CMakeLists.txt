cmake_minimum_required(VERSION 3.15)
project(structs C)

set(CMAKE_C_STANDARD 99)
include_directories(include)

add_executable(structs test.c include/htable.h)