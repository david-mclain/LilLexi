package LilLexi;
public class Stack <T> {
	private final static int DEFAULT_SIZE = 10;
	private int back;
	private T[] stack;
	
	public Stack() {
		this(DEFAULT_SIZE);
	}
	
	public Stack(int size) {
		stack = (T[]) new Object[size];
	}
	
	public void push(T element) {
		if (this.back >= stack.length) {
			resize(stack.length * 2);
		}
		this.stack[back] = element;
		back++;
	}
	
	public T pop() {
		back--;
		T ret = this.stack[back];
		if (back <= stack.length / 4 && stack.length / 4 >= 10) {
			resize(stack.length / 4);
		}
		return ret;
	}
	
	public boolean isEmpty() {
		return this.back == 0;
	}
	
	public int size() {  return back;  }
	
	public void clear() {
		stack = (T[]) new Object[DEFAULT_SIZE];
	}
	
	private void resize(int newSize) {
		T[] array = (T[]) new Object[newSize];
		for (int i = 0; i < back; i++) {
			array[i] = stack[i];
		}
		stack = array;
	}
}
