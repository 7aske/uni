//
// Created by nik on 11/2/19.
//

#ifndef STRUCTSCPP_LINKEDLIST_H
#define STRUCTSCPP_LINKEDLIST_H

#pragma once

#include "List.h"
#include <iostream>


struct NoSuchElementException : public std::exception {
	const char* what() const noexcept override {
		return "No such element";
	}
};

/**
 *
 * @brief List container implementing doubly linked list data structure.
 *
 * @tparam T type of elements in the list
 */
template<typename T>
class LinkedList : public List<T> {
private:
	/**
	 * @brief Inline class for representing individual elements of the list.
	 */
	class Node {
	public:
		/**
		 * @param data Generic T type element that is stored in the node.
		 *
		 */
		inline explicit Node(T data) : data(data), next(nullptr), prev(nullptr) {}
		T data;
		struct Node* next;
		struct Node* prev;
	};

	Node* head = nullptr;
	Node* tail = nullptr;

public:
	inline explicit LinkedList() = default;

	void clear() override;

	uint size() override;

	bool isEmpty() override;

	T get(uint uint1) override;

	int indexOf(T elem) override;

	int lastIndexOf(T t) override;

	bool contains(T t) override;

	void add(T elem) override;

	void add_front(T elem);

	void add(T elem, uint uint1) override;

	void remove(T elem) override;

	void set(T elem, uint uint1) override;

	~LinkedList() override;

};

/**
 * @brief Frees memory allocated by all the elements in the list. Head and tail properties are set to nullptr.
 */
template<typename T>
void LinkedList<T>::clear() {
	Node* current = this->head;
	while (current != nullptr) {
		Node* temp = current;
		current = current->next;
		delete temp;
	}
	this->head = nullptr;
	this->tail = nullptr;
}

/**
 * @brief Counts the elements in the list.
 * @return Returns the element count after traversing through the list.
 */
template<typename T>
uint LinkedList<T>::size() {
	uint _index = 0;
	Node* current = this->head;
	while (current != nullptr) {
		_index++;
		current = current->next;
	}
	return _index;
}

/**
 * @brief Returns whether the list is empty by comparing head and tail pointers to nullptr.
 * @return Returns true if the list is empty.
 */
template<typename T>
bool LinkedList<T>::isEmpty() {
	return this->head == nullptr && this->tail == nullptr;
}

/**
 * @brief Searches for the element in the array head to tail.
 * @param index - Index of the element in the list.
 * @return Returns the element of the list at position index or throws.
 */
template<typename T>
T LinkedList<T>::get(uint index) {
	uint _index = 0;
	Node* current = this->head;
	while (current != nullptr) {
		if (index == _index) {
			return current->data;
		}
		_index++;
		current = current->next;
	}
	throw NoSuchElementException();
}

/**
 * @brief Searches for the index of the elements in the array head to tail.
 * @param elem - Element that is being searched for.
 * @return Returns the index of the element if found or -1 if not found.
 */
template<typename T>
int LinkedList<T>::indexOf(T elem) {
	int _index = 0;
	Node* current = this->head;
	while (current != nullptr) {
		if (current->data == elem) {
			return _index;
		}
		_index++;
		current = current->next;
	}
	return -1;
}

/**
 * @brief Searches for the index of the elements in the array tail to head.
 * @param elem - Element that is being searched for.
 * @return Returns the index of the element if found or -1 if not found.
 */
template<typename T>
int LinkedList<T>::lastIndexOf(T elem) {
	int _index = 0;
	Node* current = this->tail;
	while (current != nullptr) {
		if (current->data == elem) {
			return _index;
		}
		_index++;
		current = current->prev;
	}
	return -1;
}

/**
 * @brief Checks whether the element elem is in the list.
 * @param elem - Element that is being searched for.
 * @return Returns true if element has been found.
 */
template<typename T>
bool LinkedList<T>::contains(T elem) {
	Node* current = this->head;
	while (current != nullptr) {
		if (current->data == elem) {
			return true;
		}
		current = current->next;
	}
	return false;
}

/**
 * @brief Adds the element to the end of the list.
 * @param elem - Element to be added to the list
 */
template<typename T>
void LinkedList<T>::add(T elem) {
	auto* newnode = new Node(elem);
	if (this->head == nullptr || this->tail == nullptr) {
		this->head = newnode;
		this->tail = newnode;
	} else {
		newnode->prev = this->tail;
		this->tail->next = newnode;
		this->tail = newnode;
	}
}

/**
 * @brief Adds the element to the end of the list at the position index.
 * @param elem - Element to be added to the list
 * @param index - Index where the element will be placed in the list.
 */
template<typename T>
void LinkedList<T>::add(T elem, uint index) {
	uint _index = 0;
	Node* current = this->head;
	if (index == 0) {
		this->add_front(elem);
	} else {
		while (current != nullptr) {
			if (_index == index) {
				auto* newnode = new Node(elem);
				newnode->next = current;
				newnode->prev = current->prev;
				current->prev->next = newnode;
				current->prev = newnode;
				break;
			}
			_index++;
			current = current->next;
		}
		if (_index == index) {
			this->add(elem);
		}
	}
}

/**
 * @brief Adds the element to the beginning of the list.
 * @param elem - Element to be added to the list
 */
template<typename T>
void LinkedList<T>::add_front(T elem) {
	auto* newnode = new Node(elem);
	if (this->head == nullptr || this->tail == nullptr) {
		this->head = newnode;
		this->tail = newnode;
	} else {
		newnode->next = this->head;
		this->head->prev = newnode;
		this->head = newnode;
	}
}

/**
 * @brief Removes the element from the list. Comparison is preformed using equals operator.
 * @param elem - Element to be removed from the list
 */
template<typename T>
void LinkedList<T>::remove(T elem) {
	Node* current = this->head;
	while (current != nullptr) {
		if (current->data == elem) {
			if (current->next == nullptr) {
				current->prev->next = nullptr;
				this->tail = current->prev;
			} else if (current->prev == nullptr) {
				current->next->prev = nullptr;
				this->head = current->next;
			} else {
				current->prev->next = current->next;
				current->next->prev = current->prev;
			}
			delete current;
			break;
		}
		current = current->next;
	}
}

/**
 * @brief Replaces the element of the list at index <code>index</code> with the element <code>elem</code>.
 * @param elem - Element to replace with.
 * @param index - Index of the element to be replaced.
 */
template<typename T>
void LinkedList<T>::set(T elem, uint index) {
	uint _index = 0;
	Node* current = this->head;
	while (current != nullptr && _index <= index) {
		if (_index == index) {
			current->data = elem;
		}
		current = current->next;
	}
}

/**
 * @brief Default destructor.
 */
template<typename T>
LinkedList<T>::~LinkedList() {
	this->clear();
}

#endif //STRUCTSCPP_LINKEDLIST_H
