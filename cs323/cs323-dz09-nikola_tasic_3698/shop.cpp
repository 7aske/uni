#include "shop.h"

const vector<Item> &Shop::getItems() const {
    return items;
}

void Shop::addItem(Item &i) {
    this->items.insert(this->items.end(), i);
}
