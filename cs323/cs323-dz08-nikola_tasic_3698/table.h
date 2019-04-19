#pragma once

#include <vector>
#include <vector>
#include <ostream>

class Table {
public:
    Table();
    ~Table();

    void addTeam(Team t) const;

    friend ostream &operator<<(ostream &os, const Table &table);

private:
    vector<Team>* teams;
};
