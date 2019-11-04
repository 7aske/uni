cmake_minimum_required(VERSION 3.15)
project(structscpp)

set(CMAKE_CXX_STANDARD 14)

set(PROJECT_HEADERS
        ArrayList.h
        List.h
        )
set(PROJECT_SOURCES
        main.cpp
        LinkedList.h Stack.h)

add_executable(structscpp ${PROJECT_SOURCES} ${PROJECT_HEADERS})