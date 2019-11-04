cmake_minimum_required(VERSION 3.15)
project(structs C)

set(CMAKE_C_STANDARD 99)

add_executable(structs main.c arraylist.h linkedlist.h quicksort.h stack.h)