//
// Created by nik on 11/4/19.
//

#ifndef STRUCTSCPP_STACK_H
#define STRUCTSCPP_STACK_H

/**
 * @brief Generic stack data structure implementation.
 */
template<typename T>
class Stack {
public:

	/**
	 * @brief Default constructor initialized to 32 elements.
	 */
	inline explicit Stack() : capacity(32), top(-1) {
		this->data = new T[capacity];
	}

	/**
	 * @brief Constructor that initializes initial capacity to the given ammount.
	 * @param capacity Initial capacity.
	 */
	inline explicit Stack(unsigned long capacity) : capacity(capacity), top(-1) {
		this->data = new T[capacity];
	}

	T pop();

	T& peek();

	void push(T);

	bool isEmpty();

	bool isFull();

	/**
	 * @brief Default destructor.
	 */
	~Stack() {
		delete[] this->data;
	}

private:
	struct NoSuchElementException : public std::exception {
		const char* what() const noexcept override {
			return "No such element";
		}
	};

	T* data;
	int top;
	unsigned long capacity;

	void resize();
};

/**
 * @brief Removes and returns the top element from the stack.
 * @return Top element of the stack.
 */
template<typename T>
T Stack<T>::pop() {
	if (!isEmpty()) {
		return this->data[top--];
	} else {
		throw NoSuchElementException();
	}
}

/**
 * @brief Check the top element of the stack without removing.
 * @return Returns the reference to the top element in the stack.
 */
template<typename T>
T& Stack<T>::peek() {
	if (!isEmpty()) {
		return this->data[top];
	} else {
		throw NoSuchElementException();
	}
}

/**
 * @brief Adds the element to the top of the stack.
 * @param Element to be added.
 */
template<typename T>
void Stack<T>::push(T elem) {
	if (this->isFull()) {
		this->resize();
	}
	this->data[++top] = elem;
}

/**
 * @brief Checks whether the stack is empty.
 * @return Returns true if the stack is empty
 */
template<typename T>
bool Stack<T>::isEmpty() {
	return top == -1;
}

/**
 * @brief Resizes the stack to fit more elements.
 */
template<typename T>
void Stack<T>::resize() {
	this->capacity = this->capacity * 2 + 1;
	T* newdata = new T[this->capacity];
	for (int i = 0; i <= this->top; ++i) {
		newdata[i] = this->data[i];
	}
	delete[] this->data;
	this->data = newdata;

}

/**
 * @brief Checks whether the stacks is full.
 * @return Returns true if the stack is full.
 */
template<typename T>
bool Stack<T>::isFull() {
	return this->top >= this->capacity - 1;
}

#endif //STRUCTSCPP_STACK_H
