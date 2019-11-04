//
// Created by nik on 11/4/19.
//

#ifndef STRUCTSCPP_STACK_H
#define STRUCTSCPP_STACK_H

template<typename T>
class Stack {
public:

	inline explicit Stack() : capacity(32), top(-1) {
		this->data = new T[capacity];
	}

	inline explicit Stack(unsigned long capacity) : capacity(capacity), top(-1) {
		this->data = new T[capacity];
	}

	T pop();

	T peek();

	void push(T);

	bool isEmpty();

	bool isFull();

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

template<typename T>
T Stack<T>::pop() {
	if (!isEmpty()) {
		return this->data[top--];
	} else {
		throw NoSuchElementException();
	}
}

template<typename T>
T Stack<T>::peek() {
	if (!isEmpty()) {
		return this->data[top];
	} else {
		throw NoSuchElementException();
	}
}

template<typename T>
void Stack<T>::push(T elem) {
	if (this->isFull()) {
		this->resize();
	}
	this->data[++top] = elem;
}

template<typename T>
bool Stack<T>::isEmpty() {
	return top == -1;
}

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

template<typename T>
bool Stack<T>::isFull() {
	return this->top >= this->capacity - 1;
}

#endif //STRUCTSCPP_STACK_H
