package project4;
/**
 * Represents a last-in-first-out Reference-based stack that holds generic objects 
 * @author helenchang
 * @version Nov 17, 2015 
 * @param <E>
 */

public class MyStack <E> {
	
	private MyNode<E> head = null; 
	/**
	 * Returns the head object in stack
	 * @return a MyNode object representing the last in item in the stack
	 */
	public MyNode<E> getHead(){
		return this.head;
	}
	/**
	 * Sets the head object in a stack
	 * @param MyNode object to be set as the last in/first out item in the stack
	 */
	public void setHead(MyNode<E> head){
		this.head = head;
	}
	
	/**
	 * Calls the default MyStack constructor
	 * @return new MyStack object that is an empty stack
	 */
	public MyStack<E> Stack(){
		return new MyStack<E>();
	}
	/**
	 * Default constructor that creates an empty stack
	 */
	public MyStack(){
		head = null;
	}

	/**
	 * Checks if the stack is empty
	 * @return boolean value representing if stack is empty
	 */
	public boolean empty(){
		if (this.getHead()==null){
			return true;
		}
		return false;
	}

	/**
	 * Returns object at the top of the stack without removing it
	 * @return data value of object at the top of the stack
	 */
	public E peek(){
		return this.getHead().getData();
	}

	/**
	 * Removes and returns object at the top of the stack
	 * @return data value of object at the top of the stack
	 */
	public E pop(){
		E toReturn = this.getHead().getData();
		this.setHead(this.getHead().getNext());
		return toReturn;
	}

		
	/**
	 * Adds new item to top of stack
	 * @param data to be added to stack
	 * @return the item added
	 */
	public E push(E data){
		MyNode<E> toAdd = new MyNode<E>(data);
		toAdd.setNext(this.getHead());
		this.setHead(toAdd);
		return data;
	}

	/**
	 * Searches an object in a stack
	 * @param object to be found
	 * @return int length from head where object is located
	 */
	public int search(Object o){
		int counter = 0;
		MyNode<E> current = this.getHead();
		while (current != null){
			if (current.getData().equals(o)){
				return counter;
			}
			counter++;
			current = current.getNext();
		}
		return -1;
	}

	@Override
	/**
	 * Returns entire stack as String
	 * @return string of items in stack with one item per line
	 */
	public String toString(){
		MyNode<E> current = this.getHead();
		String string = "";
		while(current != null){
			string += current.getData() + "\n";
			current = current.getNext();
		}
		return string;
	}
}

/**
 * Represents a node object that links to another node object (linked list)
 * and holds a generic data type
 * @author helenchang
 * @version Nov 17, 2015 
 * @param <E> Generic data type
 */
class MyNode <E>{
	public static int size;
	private E data;
	private MyNode<E> next;

	/**
	 * Getter method for data
	 * @return generic object stored
	 */
	public E getData(){
		return (E) this.data;
	}
	/**
	 * Setter method for data
	 * @param generic data to be set as value of node
	 */
	public void setData(E data){
		this.data = data;
	}

	/**
	 * getter method for next node in linked list
	 * @return next node in linked list
	 */
	public MyNode<E> getNext(){
		return this.next;
	}
	/**
	 * Setter method for next node in linked list
	 * @param next node to be set in linked list
	 */
	public void setNext(MyNode<E> next){
		this.next = next;
	}

	/**
	 * default constructor creates an empty linked list
	 */
	public MyNode(){
		setData(null);
		setNext(null);
	}
	/**
	 * Creates an object that holds the data
	 * points the next value to null
	 * @param data to be stored in node
	 */
	public MyNode(E data){
		setData(data);
		setNext(null);
	}
}