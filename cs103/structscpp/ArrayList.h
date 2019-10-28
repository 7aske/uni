//
// Created by nik on 10/28/19.
//

#ifndef STRUCTSCPP_ARRAYLIST_H
#define STRUCTSCPP_ARRAYLIST_H

#pragma once

#include <cstring>
#include <cstdlib>
#include <exception>
#include "List.h"

typedef unsigned int uint;

struct IndexOutOfBounds : public std::exception {
	const char* what() const throw() {
		return "Index out of bounds";
	}
};

template<typename T>
class ArrayList : public List<T> {
public:
	ArrayList() = default;

	inline explicit ArrayList(uint capacity) {
		this->capacity = capacity;
		this->data = (T*) calloc(this->capacity, sizeof(T));
	}

	inline explicit ArrayList(T* arr, uint length) {
		this->capacity = length * 2;
		this->data = (T*) calloc(this->capacity, sizeof(T));
		memcpy(this->data, arr, length * sizeof(T));
	}

	T get(uint) override;

	int indexOf(T) override;

	int lastIndexOf(T) override;

	bool contains(T) override;

	void add(T) override;

	void add(T, uint) override;

	void remove(T) override;

	void set(T, uint) override;
};

template<typename T>
T ArrayList<T>::get(uint index) {
	if (index < this->size()) {
		return this->data[index];
	} else {
		throw IndexOutOfBounds();
	}
}

template<typename T>
int ArrayList<T>::indexOf(T elem) {
	for (int i = 0; i < this->size(); ++i) {
		if (elem == this->data[i]) {
			return i;
		}
	}
	return -1;
}

template<typename T>
int ArrayList<T>::lastIndexOf(T elem) {
	for (int i = this->size() - 1; i > -1; ++i) {
		if (elem == this->data[i]) {
			return i;
		}
	}
	return -1;
}

template<typename T>
bool ArrayList<T>::contains(T elem) {
	return this->indexOf(elem) != -1;
}

template<typename T>
void ArrayList<T>::add(T elem) {
	if (this->size() + 1 == this->capacity) {
		this->resize();
	}
	this->data[this->size()] = elem;
	this->length++;
}

template<typename T>
void ArrayList<T>::add(T elem, uint index) {
	if (this->size() + 1 == this->capacity) {
		this->resize();
	}
	for (int i = this->size(); i > index; --i) {
		this->data[i] = this->data[i - 1];
	}
	this->data[index] = elem;
}

template<typename T>
void ArrayList<T>::remove(T elem) {
	int index = this->indexOf(elem);
	if (index != -1) {
		for (int i = index; i < this->size() - 1; ++i) {
			this->data[i] = this->data[i + 1];
		}
	}
}

template<typename T>
void ArrayList<T>::set(T elem, uint index) {
	if (index < this->size()) {
		this->data[index] = elem;
	}
}


#endif //STRUCTSCPP_ARRAYLIST_H
