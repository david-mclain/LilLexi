/**
 * package LilLexi contains all components for WYSIWYG text editor
 * 
 * @author David McLain
 *
 * @param <T> - Generic type to make stack
 */

@SuppressWarnings("unchecked")
public class Stack <T> {
	private final static int DEFAULT_SIZE = 10;
	private int back;
	private T[] stack;
	/**
	 * Calls other constructor with DEFAULT_SIZE
	 */
	public Stack() {
		this(DEFAULT_SIZE);
	}
	/**
	 * Instantiates new stack with size
	 * @param size - size to create stack
	 */
	public Stack(int size) {
		stack = (T[]) new Object[size];
	}
	/**
	 * Pushes new element onto the stack
	 * @param element - element to push on the stack
	 */
	public void push(T element) {
		if (this.back >= stack.length) {
			resize(stack.length * 2);
		}
		this.stack[back] = element;
		back++;
	}
	/**
	 * Pops element off stack
	 * @return element popped off stack
	 */
	public T pop() {
		back--;
		T ret = this.stack[back];
		if (back <= stack.length / 4 && stack.length / 4 >= 10) {
			resize(stack.length / 4);
		}
		return ret;
	}
	/**
	 * Returns true if stack is empty
	 * @return true if stack is empty
	 */
	public boolean isEmpty() {
		return this.back == 0;
	}
	/**
	 * Returns size of stack
	 * @return size of stack
	 */
	public int size() {  return back;  }
	/**
	 * Clears stack
	 */
	public void clear() {
		stack = (T[]) new Object[DEFAULT_SIZE];
		this.back = 0;
	}
	/**
	 * Resizes array containing stack if needed
	 * @param newSize - new size to make array
	 */
	private void resize(int newSize) {
		T[] array = (T[]) new Object[newSize];
		for (int i = 0; i < back; i++) {
			array[i] = stack[i];
		}
		stack = array;
	}
}
