//
// Created by nik on 10/28/19.
//

#ifndef STRUCTSCPP_ARRAYLIST_H
#define STRUCTSCPP_ARRAYLIST_H

#pragma once

#include "List.h"

typedef unsigned int uint;


/**
 * @brief List container implementing array list data structure.
 * @tparam T type of elements in the list
 */
template<typename T>
class ArrayList : public List<T> {
public:
	/**
	 * @brief Default constructor.
	 */
	ArrayList() : ArrayList(16) {}

	/**
	 * @brief Initialize the list with starting capacity.
	 * @param capacity Initial capacity.
	 */
	inline explicit ArrayList(uint capacity) {
		this->capacity = capacity;
		this->data = new T[this->capacity];
	}

	/**
	 * @brief Initialize the list with starting capacity.
	 * @param capacity Initial capacity.
	 */
	inline explicit ArrayList(T* arr, uint length) {
		this->capacity = length * 2;
		this->data = new T[this->capacity];
		for (uint i = 0; i < length; ++i) {
			this->data[i] = arr[i];
		}
	}

	uint size() override;

	T get(uint) override;

	int indexOf(T) override;

	int lastIndexOf(T) override;

	bool contains(T) override;

	void add(T) override;

	void add(T, uint) override;

	void remove(T) override;

	void set(T, uint) override;

	void clear() override;

	bool isEmpty() override;

private:
	void resize() override;

};


template<typename T>
T ArrayList<T>::get(uint index) {
	if (index < this->size()) {
		return this->data[index];
	} else {
		throw IndexOutOfBounds();
	}
}

/**
 * @brief Searches for the index of the elements in the array head to tail.
 * @param elem - Element that is being searched for.
 * @return Returns the index of the element if found or -1 if not found.
 */
template<typename T>
int ArrayList<T>::indexOf(T elem) {
	for (int i = 0; i < this->size(); ++i) {
		if (elem == this->data[i]) {
			return i;
		}
	}
	return -1;
}

/**
 * @brief Searches for the index of the elements in the array tail to head.
 * @param elem - Element that is being searched for.
 * @return Returns the index of the element if found or -1 if not found.
 */
template<typename T>
int ArrayList<T>::lastIndexOf(T elem) {
	for (int i = this->size() - 1; i > -1; ++i) {
		if (elem == this->data[i]) {
			return i;
		}
	}
	return -1;
}

/**
 * @brief Checks whether the element elem is in the list.
 * @param elem - Element that is being searched for.
 * @return Returns true if element has been found.
 */
template<typename T>
bool ArrayList<T>::contains(T elem) {
	return this->indexOf(elem) != -1;
}

/**
 * @brief Adds the element to the end of the list.
 * @param elem - Element to be added to the list
 */
template<typename T>
void ArrayList<T>::add(T elem) {
	if (this->size() + 1 == this->capacity) {
		this->resize();
	}
	this->data[this->size()] = elem;
	this->length++;
}

/**
 * @brief Adds the element to the end of the list at the position index.
 * @param elem - Element to be added to the list
 * @param index - Index where the element will be placed in the list.
 */
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

/**
 * @brief Removes the element from the list. Comparison is preformed using equals operator.
 * @param elem - Element to be removed from the list
 */
template<typename T>
void ArrayList<T>::remove(T elem) {
	int index = this->indexOf(elem);
	if (index != -1) {
		for (int i = index; i < this->size() - 1; ++i) {
			this->data[i] = this->data[i + 1];
		}
	}
}

/**
 * @brief Sets the element at the given index to the value specified by elem.
 * @param elem Value to be set.
 * @param index Index at which value will be set.
 */
template<typename T>
void ArrayList<T>::set(T elem, uint index) {
	if (index < this->size()) {
		this->data[index] = elem;
	} else {
		throw IndexOutOfBounds();

	}
}

/**
 * @brief Frees memory allocated by the list and sets size to 0.
 */
template<typename T>
void ArrayList<T>::clear() {
	List<T>::clear();
}

/**
 * @brief Counts the elements in the list.
 * @return Returns the size of the list.
 */
template<typename T>
uint ArrayList<T>::size() {
	return List<T>::size();
}

/**
 * @brief Returns whether the list is empty.
 * @return Returns true if the list is empty.
 */
template<typename T>
bool ArrayList<T>::isEmpty() {
	return List<T>::isEmpty();
}

/**
 * @brief Resizes the list to fit new elements.
 */
template<typename T>
void ArrayList<T>::resize() {
	List<T>::resize();
}


#endif //STRUCTSCPP_ARRAYLIST_H
