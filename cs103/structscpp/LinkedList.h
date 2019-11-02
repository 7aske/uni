//
// Created by nik on 11/2/19.
//

#ifndef STRUCTSCPP_LINKEDLIST_H
#define STRUCTSCPP_LINKEDLIST_H

#pragma once

#include "List.h"
#include <iostream>


struct NoSuchElementException : public std::exception {
	const char* what() const throw() {
		return "No such element";
	}
};


template<typename T>
class LinkedList : public List<T> {
private:
	class Node {
	public:
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

	int indexOf(T t) override;

	int lastIndexOf(T t) override;

	bool contains(T t) override;

	void add(T t) override;

	void add_front(T t);

	void add(T t, uint uint1) override;

	void remove(T t) override;

	void set(T t, uint uint1) override;

	~LinkedList() override;

};


template<typename T>
void LinkedList<T>::clear() {
}

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

template<typename T>
bool LinkedList<T>::isEmpty() {
	return this->head == nullptr && this->tail == nullptr;
}

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

template<typename T>
int LinkedList<T>::indexOf(T t) {
	int _index = 0;
	Node* current = this->head;
	while (current != nullptr) {
		if (current->data == t) {
			return _index;
		}
		_index++;
		current = current->next;
	}
	return -1;
}

template<typename T>
int LinkedList<T>::lastIndexOf(T t) {
	int _index = 0;
	Node* current = this->tail;
	while (current != nullptr) {
		if (current->data == t) {
			return _index;
		}
		_index++;
		current = current->prev;
	}
	return -1;
}

template<typename T>
bool LinkedList<T>::contains(T t) {
	Node* current = this->head;
	while (current != nullptr) {
		if (current->data == t) {
			return true;
		}
		current = current->next;
	}
	return false;
}

template<typename T>
void LinkedList<T>::add(T t) {
	auto* newnode = new Node(t);
	if (this->head == nullptr || this->tail == nullptr) {
		this->head = newnode;
		this->tail = newnode;
	} else {
		newnode->prev = this->tail;
		this->tail->next = newnode;
		this->tail = newnode;
	}
}

template<typename T>
void LinkedList<T>::add(T t, uint index) {
	uint _index = 0;
	Node* current = this->head;
	if (index == 0) {
		this->add_front(t);
	} else {
		while (current != nullptr) {
			std::cout << _index << std::endl;
			if (_index == index) {
				// std::cout << current->data << std::endl;
				auto* newnode = new Node(t);
				newnode->next = current;
				newnode->prev = current->prev;
				current->prev = newnode;

			}
			_index++;
			current = current->next;
		}
	}
}

template<typename T>
void LinkedList<T>::add_front(T t) {
	auto* newnode = new Node(t);
	if (this->head == nullptr || this->tail == nullptr) {
		this->head = newnode;
		this->tail = newnode;
	} else {
		newnode->next = this->head;
		this->head->prev = newnode;
		this->head = newnode;
	}
}

template<typename T>
void LinkedList<T>::remove(T t) {

}

template<typename T>
void LinkedList<T>::set(T t, uint uint1) {

}

template<typename T>
LinkedList<T>::~LinkedList() {

}

#endif //STRUCTSCPP_LINKEDLIST_H
