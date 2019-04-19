#pragma once

#include <vector>

#include "item.h"


class Shop {
public:
    Shop();

    const vector<Item> &getItems() const;

    void addItem(Item &i);

private:
    vector<Item> items;
};
