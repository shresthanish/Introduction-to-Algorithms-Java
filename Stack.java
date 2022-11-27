public interface Stack{
	
	//Pushes an object onto the stack.
	public void push(Object x);
	
	//Pops an object from the stack, returining the popped object.
	public Object pop();
	
	//Returns true if the stack is empty, false otherwise.
	public boolean isEmpty();
	
	//displays the content of the stack on the screen. 
	public void display();
}