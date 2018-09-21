import java.io.PrintStream;
import java.util.*;

public class BstMultiset<T> extends Multiset<T>
{
	
	protected Node rootNode;
	
	public BstMultiset() {
		
		
		rootNode = null;
	} // end of BstMultiset()

	public void add(T item) {
		// Implement me!
		Node newNode = new Node();
		newNode.setValue(item);
		newNode.setNumber(1);


		//If a root doesn't exist, makes the new node the root. 
		if(rootNode == null)
		{
			rootNode = newNode;
			rootNode.setNumber(1);
			return;
		}

      // the parent Node 
		Node parentNode = rootNode;

		while(true)
		{
//			If the nodes have the same value, the number increments by 1

			if(compareVal(parentNode, newNode) == 0)
			{
				parentNode.setNumber(parentNode.getNumber() + 1);
				break;
			}
//		 if the new node is less than the parent node 
			else if(parentNode.getValue().toString().compareTo(newNode.getValue().toString()) > 0)
			{
//				If there is no node on the left, insert the new node
				if(parentNode.leftNode == null)
				{
					parentNode.leftNode = newNode;
					break;
				}
				
				else
					parentNode = parentNode.leftNode;
			}
      		//Value of the new node is greater than the old node 
			else
			{
				if(parentNode.rightNode == null)
				{
					parentNode.rightNode = newNode;
					break;
				}
				else
					parentNode = parentNode.rightNode;
			}
		}

		return;
		
	} // end of add()
	
	private int compareVal(Node val1, Node val2)

	{
		return val1.getValue().toString().compareTo(val2.getValue().toString());
	}
	
	private int compareVal(Node node, T item)
	{
		return node.getValue().toString().compareTo(item.toString());
	}

	public int search(T item) {
		// Implement me!
		
		Node currentNode = rootNode;
		
		while(currentNode != null)
		{
			if(compareVal(currentNode, item) ==0 )
				return currentNode.getNumber();
			
			else if(compareVal(currentNode, item) > 0)
				currentNode = currentNode.leftNode;
			
			else
				currentNode = currentNode.rightNode;
		}

		// default return, please override when you implement this method
		return 0;
	} // end of add()


	public void removeOne(T item) {
		// Implement me!
		
		int number = search(item);
		
		if(number == 0)
			return;
		
		else if(number ==1 ) {
			removeAll(item);
			return;
		}
		
		else
		{
			Node currentNode = rootNode;
			while(currentNode != null)
			{
				if(currentNode.getValue().toString().compareTo(item.toString()) == 0)
				{
					currentNode.setNumber(currentNode.getNumber() -1);
					return;
				}
				
				else if(currentNode.getValue().toString().compareTo(item.toString()) > 0)
					currentNode = currentNode.leftNode;
				
				else
					
					currentNode = currentNode.rightNode;
			}
		}
		
		
		
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement me!
		
		int number = search(item);
		
		if(number == 0)
			return;
		
		rootNode = delete(rootNode, item);
	} // end of removeAll()
	
	private Node delete(Node parentNode, T item)
	{
		//If the parent node doesn't exist then error is thrown
		if (parentNode == null)
			throw new RuntimeException("Error, Item can't be deleted");

		// If the parent node is less than the item value, parent node is set to the left
		// and deleted. 
		else if (parentNode.getValue().toString().compareTo(item.toString()) > 0)
			parentNode.leftNode = delete (parentNode.leftNode, item);

//		same function as above but this time parent node is set to the right. 
		else if (parentNode.getValue().toString().compareTo(item.toString())  < 0)
			parentNode.rightNode = delete (parentNode.rightNode, item);

		
		else
		{
			if (parentNode.leftNode == null) return parentNode.rightNode;
			else
			if (parentNode.rightNode == null) return parentNode.leftNode;
			else
			{
				parentNode.setNumber(retrieveNumber(parentNode.leftNode));
			//taken from geeksforgeeks.org binary search tree 
				parentNode.setValue(retrieveValue(parentNode.leftNode));
//				Delete the node from left subtree 
				parentNode.leftNode =  delete(parentNode.leftNode, parentNode.getValue()) ;
			}
		}
		return parentNode;
	}
	
	private T retrieveValue(Node parentNode)
	{
		while (parentNode.rightNode != null) parentNode = parentNode.rightNode;
		return parentNode.getValue();
	}
	
	private int retrieveNumber(Node parentNode)
	{
		while (parentNode.rightNode != null) parentNode = parentNode.rightNode;
		
		return parentNode.getNumber();
	}
	

	public void print(PrintStream out) {
		// Implement me!
		print(rootNode, out);
	} // end of print()
	
	private void print(Node parent, PrintStream out)
	{
		if(parent != null) {
			out.println(parent.getValue() + " | " + parent.getNumber());
			print(parent.leftNode, out);
			print(parent.rightNode, out);
		}
	}
	
	protected class Node
	{
		private T value;
		private int number = 0;

		private Node leftNode;
		private Node rightNode;

		public Node(){}

		public boolean isLeaf()
		{
			return (leftNode == null && rightNode == null);
		}

		public void setValue(T value)
		{
			this.value = value;
		}

		public void setNumber(int number)
		{
			this.number = number;
		}

		public T getValue()
		{
			return value;
		}

		public int getNumber()
		{
			return number;
		}

	}
	
	

} // end of class BstMultiset
