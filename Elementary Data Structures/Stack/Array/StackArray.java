//Since Stack is an interface, we need to implement methods on the Stack.
public class StackArray implements Stack{
	
	//The index of the top of the stack.
	protected int top;
	
	//Creating object array such that we can use integers, or arrays or whatever.
	//protected creates and returns a copy of the object.
	
	//Array implementing the stack.
	protected Object[] stack;
	
	//Index of the top when the stack is empty.
	protected static final int EMPTY = -1;
	
	//Makes an empty stack with 1 slot.
	public StackArray(){
		top = EMPTY;
		stack = new Object[1]; //arbitrary initial size.
	}
	
	//Makes an empty stack with a given number of size. 
	public StackArray(int size){
		top = EMPTY;
		stack = new Object[size];
	}
	
	//Returns true if the stack is empty, false otherwise. 
	public boolean isEmpty(){
		return top <= EMPTY;
	}
	
	//Pushes an object onto the stack. If the stack is full, first doubles its size. 
	//@param x Object to be pushed. 
	public void push(Object x){
		top++; //go to the next position.
		
		//is the stack full?
		if (top >=stack.length) {
			
			//allocate a new array and copy the content of the old in it.
			Object[] temp = new Object[top*2];
			for (int i = 0; i < stack.length; i++) {
				temp[i] = stack[i];
				stack = temp; //We then use the new array.
			}
		}
		
		stack[top] = x; //Save the object. 
		
	}
	
	//Pops an object form the stack, returning the popped object.
	
	//Throws StackUnderFlowException if the stack was already empty.
	public Object pop(){
		if (isEmpty()) {
			throw new StackUnderflowException();
		}
		else {
			top--;
			return stack[top+1];
		}
	}
	
	public void display(){
		for (int i = top; i>=0; i--) {
			System.out.println(stack[i]);
		}
	}
	
	
	
}
