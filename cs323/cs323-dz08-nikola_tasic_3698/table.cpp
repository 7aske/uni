#pragma once

#include <algorithm>
#include "table.h"


using namespace std;

Table::Table() {
    this->teams = new vector<Team>;
}

Table::~Table() {
    // koristimo mastu i zamislimo da ova linije nije zakomentariasna
    // cout << "table object destroyed at " << this << "\n";
    delete this->teams;
}

void Table::addTeam(Team t) const {
    this->teams->insert(this->teams->end(), t);
    sort(this->teams->begin(), this->teams->end(), [](Team t1, Team t2) {
        if (t1.getPts() > t2.getPts()) {
            return true;
        } else if (t2.getPts() > t1.getPts()) {
            return false;
        } else {
            return t1.getDiff() > t2.getDiff();
        }
    });
}

ostream &operator<<(ostream &os, const Table &table) {
    os << "[P][Team                            ][Diff][Pts]\n";
    os << "---------------------------------------------\n";
    int counter = 1;
    for (auto &team : *table.teams) {
        if (counter < 10) {
            os << "0" << counter << "." << team;
        } else {
            os << counter << "." << team;
        }
        counter++;
    }
    return os;
}

