using System.Diagnostics;

public class Program {
	// na linux-u sam nemam windows forme
	// usage: ./bin/Debug/net6.0/cs322-dz10-nikola_tasic_3698 <add|remove> <val>
	// zakomentarisani su inicijalni primeri za listom
	public static void Main(params string[] args) {
		List<int> list = new() {12, 3, 4, 5, 6, 1, 5451, 51, 41, 42};
		Stack<int> stack = new(list);
		Console.WriteLine("Data before manipulation:\n{0}", stack);
		if (args[0] == "add") {
			// arr.Add(int.Parse(args[1]));
			stack.push(int.Parse(args[1]));
		} else if (args[0] == "remove") {
			// arr.Remove(int.Parse(args[1]));
			int val = int.Parse(args[1]);
			Stack<int> helper = new Stack<int>();
			while (!stack.isEmpty()) {
				int curr = stack.pop();
				if (curr == val) {
					break;
				}
				helper.push(curr);
			}

			while (!helper.isEmpty()) {
				stack.push(helper.pop());
			}
		}
		
		Console.WriteLine("Data after manipulation:\n{0}", stack);
		
		// testiranje queue implementacije
		Queue<int> queue = new();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		while (!queue.isEmpty()) {
			Console.WriteLine("{0}", queue.poll());
		}
	}
}

public class Stack<T> {
	private T[] _data;
	private int _index;
	private int _size = 16;

	public Stack() {
		// initial size
		_data = new T[_size];
		_index = -1;
	}

	public Stack(List<T> initial) {
		// clone rather than reference
		_data = initial.ToArray().Clone() as T[] ?? new T[16];
		_index = _data.Length - 1;
		_size = _data.Length;
	}
	
	public Stack(T[] initial) {
		// clone rather than reference
		_data = initial.Clone() as T[] ?? new T[16];
		_index = _data.Length - 1;
		_size = _data.Length;
	}

	public Stack(Stack<T> stack) : this(stack._data) {
	}

	public T peek() {
		return _data[_index - 1];
	}

	public void push(T val) {
		
		if (_index + 1 == _size) {
			_size *= 2;
			T[] newArr = new T[_size];
			_data.CopyTo(newArr, 0);
			_data = newArr;
		}
		_data[++_index] = val;
	}

	public T pop() {
		return _data[_index--];
	}

	public bool isEmpty() {
		return _index == -1;
	}

	public override string ToString() {
		return string.Join(", ", _data[..(_index + 1)]);
	}
}
public class Queue<T> {
	private T[] _data;
	private int _frontIndex;
	private int _backIndex;
	private int _size = 16;

	public Queue() {
		// initial size
		_data = new T[_size];
		_frontIndex = -1;
		_backIndex = -1;
	}

	public T peekFront() {
		return _data[_frontIndex];
	}

	public void enqueue(T val) {

		if (_frontIndex + _backIndex == _size) {
			// TODO implement moving the data to the front
			// and then setting front and back indices
			// to the appropriate values
			// convert this [.......xxxxxx]
			// into this    [xxxxxx.......]
			// i know how to do it in java was too lazy for C#
		}
		
		if (_backIndex + 1 == _size) {
			_size *= 2;
			T[] newArr = new T[_size];
			_data.CopyTo(newArr, 0);
			_data = newArr;
		}
		_data[++_backIndex] = val;
	}

	public T poll() {
		return _data[++_frontIndex];
	}

	public bool isEmpty() {
		return _frontIndex == _backIndex;
	}

	public override string ToString() {
		return string.Join(", ", _data[_frontIndex.._backIndex]);
	}
}
