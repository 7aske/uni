cmake_minimum_required(VERSION 3.21)
project (zadatak1 C)

set(CMAKE_C_STANDARD 99)

add_executable(zadatak1 main.c)

target_link_libraries(zadatak1 pthread)
