#pragma once

#include <ostream>

using namespace std;

class Team {
public:
    Team();

    const char* getName() const;

    void setName(char* const n);

    int getDiff() const;

    void setDiff(int diff);

    int getPts() const;

    void setPts(int pts);

    Team(char* const name, int diff, int pts);

    ~Team();

    friend ostream &operator<<(ostream &os, const Team &team);

private:
    char name[32];
    int diff;
    int pts;
};
