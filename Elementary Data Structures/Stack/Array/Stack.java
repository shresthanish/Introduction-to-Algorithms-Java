public interface Stack{
	
	//Pushes an object onto the stack.
	public void push(Object x);
	
	//Pops an object from the stack, returining the popped object.
	public Object pop();
	
	//Returns true if the stack is empty, false otherwise.
	public boolean isEmpty();
	
	//prints the content of the stack. 
	public void display();
}
