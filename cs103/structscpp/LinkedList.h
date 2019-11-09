//
// Created by nik on 11/7/19.
//

#ifndef STRUCTSCPP_LINKEDLIST_H
#define STRUCTSCPP_LINKEDLIST_H

#pragma once

#include "List.h"

/**
 * @brief List container implementing doubly linked list data structure.
 * @tparam T type of elements in the list
 */
template<typename T>
class LinkedList : public List<T> {
public:
	explicit LinkedList() : head(nullptr), tail(nullptr) {}

private:
	/**
	 * @brief Inline class for representing individual elements of the list.
	 */
	class Node {
	public:
		T data;
		Node* next;
		Node* prev;

		/**
         * @param data Generic T type element that is stored in the node.
         */
		explicit Node(T data) : data(data), next(nullptr), prev(nullptr) {}

		Node(T data, Node* next, Node* prev) : data(data), next(next), prev(prev) {}

		~Node() = default;
	};

	Node* head;
	Node* tail;

	void removeNode(Node* current);

public:
	void clear() override;

	uint size() override;

	bool isEmpty() override;

	T get(uint index) override;

	int indexOf(T elem) override;

	int lastIndexOf(T elem) override;

	bool contains(T elem) override;

	void add(T elem) override;

	void addFront(T elem);

	void addBack(T elem);

	void add(T elem, uint index) override;

	void remove(T elem) override;

	void removeAt(uint index);

	void removeFront();

	void removeBack();

	void set(T elem, uint index) override;

	/**
     * @brief Default destructor.
     */
	~LinkedList() { this->clear(); };
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
	uint count = 0;
	Node* current = this->head;
	while (current != nullptr) {
		current = current->next;
		count++;
	}

	return count;
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
		if (_index == index) {
			return current->data;
		}
		current = current->next;
		_index++;
	}
	throw IndexOutOfBounds();
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
		if (elem == current->data) {
			return _index;
		}
		current = current->next;
		_index++;
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
		if (elem == current->data) {
			return _index;
		}
		current = current->prev;
		_index++;
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
	return this->indexOf(elem) != -1;
}

/**
 * @brief Adds the element to the end of the list.
 * @param elem - Element to be added to the list
 */
template<typename T>
void LinkedList<T>::add(T elem) {
	Node* newnode = new Node(elem);
	if (this->head == nullptr && this->tail == nullptr) {
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
	Node* current = this->head;
	uint _index = 0;
	if (index == 0 || current == nullptr) {
		return this->addFront(elem);
	}
	while (current != nullptr && _index <= index) {
		if (_index == index) {
			if (current->next == nullptr) {
				this->add(elem);
			} else {
				Node* newnode = new Node(elem);
				newnode->next = current;
				newnode->prev = current->prev;
				current->prev->next = newnode;
				current->prev = newnode;
			}
			return;
		}
		current = current->next;
		_index++;
	}
	if (_index == index) {
		this->addBack(elem);
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
		if (elem == current->data) {
			return removeNode(current);
		}
		current = current->next;
	}
}

/**
 * @brief Removes the element from the list at given index.
 * @param index - Index of the element to be removed from the list
 */
template<typename T>
void LinkedList<T>::removeAt(uint index) {
	int _index = 0;
	Node* current = this->head;
	while (current != nullptr) {
		if (_index == index) {
			return removeNode(current);
		}
		current = current->next;
		_index++;
	}
}

/**
 * @brief Replaces the element of the list at index <code>index</code> with the element <code>elem</code>.
 * @param elem - Element to replace with.
 * @param index - Index of the element to be replaced.
 */
template<typename T>
void LinkedList<T>::set(T elem, uint index) {
	int _index = 0;
	Node* current = this->head;
	while (current != nullptr) {
		if (_index == index) {
			current->data = elem;
		}
		current = current->next;
		_index++;
	}
}

/**
 * @brief Adds the element to the beginning of the list.
 * @param elem - Element to be added to the list
 */
template<typename T>
void LinkedList<T>::addFront(T elem) {
	Node* newnode = new Node(elem);
	if (this->head == nullptr && this->tail == nullptr) {
		this->head = newnode;
		this->tail = newnode;
	} else {
		newnode->next = this->head;
		this->head->prev = newnode;
		this->head = newnode;
	}
}

/**
 * @brief Adds the element to the end of the list.
 * @param elem - Element to be added to the list
 */
template<typename T>
void LinkedList<T>::addBack(T elem) {
	this->add(elem);
}

/**
 * @brief Removes the element from the front of the list.
 */
template<typename T>
void LinkedList<T>::removeFront() {
	this->removeNode(this->head);
}

/**
 * @brief Removes the element at the end of the list.
 */
template<typename T>
void LinkedList<T>::removeBack() {
	this->removeNode(this->tail);
}

/**
 * @brief Helper function to remove the node and re-wire next and prev pointers.
 */
template<typename T>
void LinkedList<T>::removeNode(LinkedList::Node* current) {
	if (current == nullptr) {
		return;
	}

	if (current->prev == nullptr && current->next != nullptr) {
		this->head->next->prev = nullptr;
		this->head = this->head->next;
	} else if (current->next == nullptr && current->prev != nullptr) {
		this->tail->prev->next = nullptr;
		this->tail = this->tail->prev;
	} else if (current->prev != nullptr && current->next != nullptr) {
		current->prev->next = current->next;
		current->next->prev = current->prev;
	}
	return delete current;
}

#endif //STRUCTSCPP_LINKEDLIST_H
