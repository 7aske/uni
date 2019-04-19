#pragma once

#include <string.h>

#include "team.h"

Team::Team() {
    cout << "team object created   at " << this << "\n";
}

Team::Team(char* const name, int diff, int pts) {
    strcpy((char*) (this->name), name);
    this->diff = diff;
    this->pts = pts;
}

Team::~Team() {
     cout << "team object destroyed at " << this << "\n";
}

const char* Team::getName() const {
    return name;
}

void Team::setName(char* const n) {
    strncpy(this->name, n, 32);
}


int Team::getDiff() const {
    return diff;
}

void Team::setDiff(int diff) {
    this->diff = diff;
}

int Team::getPts() const {
    return pts;
}

void Team::setPts(int pts) {
    this->pts = pts;
}

std::ostream &operator<<(std::ostream &os, const Team &team) {
    char buf[64];
    sprintf(buf, "|%-32s||%4d||%3d|\n", team.name, team.diff, team.pts);
    os << buf;
    return os;
}

