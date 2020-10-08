//
// Created by nik on 10/28/19.
//

#ifndef STRUCTSCPP_LIST_H
#define STRUCTSCPP_LIST_H

#pragma once

#include <exception>

typedef unsigned int uint;

struct IndexOutOfBounds : public std::exception {
	const char* what() const noexcept override {
		return "Index out of bounds";
	}
};


template<typename T>
class List {
protected:
	List() = default;

public:

	uint length = 0;
	uint capacity = 16;
	T* data = nullptr;

	inline virtual void clear() {
		delete[] this->data;
		this->length = 0;
	};

	inline virtual uint size() { return this->length; };

	inline virtual bool isEmpty() { return this->length == 0; };

	inline virtual void resize() {
		this->capacity = this->capacity * 2 + 1;
		T* newdata = new T[this->capacity];
		for (int i = 0; i < this->size(); ++i) {
			newdata[i] = this->data[i];
		}
		delete[] this->data;
		this->data = newdata;
	};

	virtual T get(uint) = 0;

	virtual int indexOf(T) = 0;

	virtual int lastIndexOf(T) = 0;

	virtual bool contains(T) = 0;

	virtual void add(T) = 0;

	virtual void add(T, uint) = 0;

	virtual void remove(T) = 0;

	virtual void set(T, uint) = 0;

	inline virtual ~List() {
		delete[] this->data;
		this->length = 0;
	};
};


#endif //STRUCTSCPP_LIST_H
