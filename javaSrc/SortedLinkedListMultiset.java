import java.io.PrintStream;
import java.util.*;

//some aspects taken from geeksforgeeks.org website and lecuture slides
//as well as help from friends. 

public class SortedLinkedListMultiset<T> extends Multiset<T>
{
	
	protected Node mHead;
	protected int mLength;
	
	public SortedLinkedListMultiset() {
		// Implement me!
		
		this.mLength = 0;
	} // end of SortedLinkedListMultiset()
	
	
	public void add(T item) {
		// Implement me!
		
Node newNode = new Node(item.toString());
		
		// if the list is empty
		if (mHead == null) {
			newNode.setNext(null);
			mHead = newNode;
			
			mLength++;
		}
		else {
			Node currentNode = mHead;
			boolean findRightPos = true;
			
			// if values currently exist , break;
			while (currentNode != null) {
				if (item.toString().equals(currentNode.getElement())) {
					currentNode.incrementCount();
					findRightPos = false;
					
					break;
				}
				
				currentNode = currentNode.getNext();
			}
			
			// if the element doesn't, find the right position to insert element
			if (findRightPos) {
				currentNode = mHead;
				
				Node temp = currentNode;
				Node last = null;
				
				while (temp != null) {
					if (item.toString().compareTo(temp.getElement()) > 0)
					{
						last = temp;
					}
					
					temp = temp.getNext();
				}
				
				// if there's only one element in the list
				if (last == null) {
					newNode.setNext(mHead);
					mHead = newNode;
				} else {
					newNode.setNext(last.getNext());
					last.setNext(newNode);
				}
			}
		}
		
	} // end of add()
	
	
	public int search(T item) {
		// Implement me!	
		//long startTime = System.nanoTime();
		Node currentNode = mHead;
		
		while (currentNode != null) {
			if (currentNode.getElement().compareTo(item.toString()) > 0) {
				break;
			}
			
			if (item.toString().equals(currentNode.getElement())) {
				return currentNode.getCount();
			}
			
			currentNode = currentNode.getNext();
			
		}
		
		// default return, please override when you implement this method
		return 0;
	} // end of add()
	
	
	public void removeOne(T item) {
		// Implement me!
		Node currentNode = mHead;
		
		while (currentNode != null) {
			// breaks if the desired item is smaller than the current node
			if(currentNode.getElement().compareTo(item.toString()) > 0) {
				break;
			}
			
			if(currentNode.getCount() > 1 && currentNode.getElement().equals(item.toString())) {
				currentNode.decrementCount();
				break;
			}
			if(currentNode.getCount() == 1 && currentNode.getElement().equals(item.toString())) {
				removeAll(item);
				
				break;
			}
			
			currentNode = currentNode.getNext();
		}
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement me!
		Node currentNode = mHead;
		
		//long startTime = System.nanoTime();
		
		while (currentNode != null) {
			if(currentNode.getElement().compareTo(item.toString()) > 0) {
				break;
			}
			//if there's only 1 item in the list 
			if(currentNode.getNext() == null && currentNode.getElement().equals(item.toString())) {
				mHead = null;
			}
			
			if (currentNode.getNext() != null && mHead.getElement().equals(item.toString())) {
				mHead = currentNode.getNext();
			}
			
			if(currentNode.getNext() != null && currentNode.getNext().getNext() == null && currentNode.getNext().getElement().equals(item.toString())) {
				currentNode.setNext(null);
			}
			
			if(currentNode.getNext() != null && currentNode.getNext().getNext() != null && 
					currentNode.getNext().getElement().equals(item.toString())) {
				currentNode.mNext = currentNode.getNext().getNext();
			}
			
			currentNode = currentNode.getNext();
			
			//long result= endTime - startTime;

			//System.out.println("That took " + (result) + " milliseconds");
//			try (PrintStream out = new PrintStream(new FileOutputStream("generation/results.txt"))) {
//			    out.println(result,'a');
//			} catch (FileNotFoundException e) {
//				System.out.println("could not open file");
//				e.printStackTrace();
//			}
			
			//try(FileWriter fw = new FileWriter("linkedlist_results.txt", true);
			//	    BufferedWriter bw = new BufferedWriter(fw);
			//	    PrintWriter out = new PrintWriter(bw))
			//	{
			//	    out.println(result);
				    //more code
				   
			//	} catch (IOException e) {
				    //exception handling left as an exercise for the reader
			//	}
		}
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		// Implement me!
		Node currentNode = mHead;
		
		while(currentNode != null) {
			out.println(currentNode.getElement() + printDelim + currentNode.getCount());
			currentNode = currentNode.getNext();
		}
		
	} // end of print()
	
	private class Node
    {
        
        protected String mElement;
    
        protected int mCount;
        
        protected Node mNext;

        public Node(String value) {
            mElement = value;
            mCount = 1;
            mNext = null;
        }

        public String getElement() {
            return mElement;
        }
        
        public int getCount() {
        	return mCount;
        }
        
        public Node getNext() {
            return mNext;
        }

        public void incrementCount() {
        	this.mCount++;
        }
        
        public void decrementCount() {
        	this.mCount--;
        }
        
        public void setNext(Node next) {
            mNext = next;
        }
    }
	
} // end of class SortedLinkedListMultiset