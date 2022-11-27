public class StackArrayTest{
	
	public static void main(String[] args) {
		Stack stack = new StackArray(5);
		stack.push("Apples");
		stack.push("Oranges");
		stack.push(1);
		stack.push("Phone");
		stack.pop();
		stack.display();
		
	}
}
