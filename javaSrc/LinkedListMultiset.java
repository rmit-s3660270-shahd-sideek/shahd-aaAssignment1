import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;


public class LinkedListMultiset<T> extends Multiset<T>
{
	
	protected Node mHead;
	protected int mLength;
	
	
	public LinkedListMultiset() {
		
		mHead = null;
		mLength = 0;
		
		// Implement me!
	} // end of LinkedListMultiset()
	
	
	public void add(T item) {
		//long startTime = System.nanoTime();
		Node newNode = new Node((String) item);
		// if the start of the list is empty
		if (mHead == null)
			mHead = newNode;
		else {
			// if the list isnt empty 
			Node currentNode = mHead;
			// setting parent node to null; 
			Node parentNode = null;
			while (currentNode != null) {
				if (currentNode.getValue().equals(newNode.getValue())) {
					currentNode.addCounter();
					return;
				}
				parentNode = currentNode;
				currentNode = currentNode.getNext();
			}
			parentNode.setNext(newNode);
		}
		mLength++;
		//long endTime = System.nanoTime();
		//long result= endTime - startTime;

		//System.out.println("That took " + (result) + " milliseconds");
//		try (PrintStream out = new PrintStream(new FileOutputStream("generation/results.txt"))) {
//		    out.println(result,'a');
//		} catch (FileNotFoundException e) {
//			System.out.println("could not open file");
//			e.printStackTrace();
//		}
		
		//try(FileWriter fw = new FileWriter("linkedlist_results.txt", true);
		//	    BufferedWriter bw = new BufferedWriter(fw);
		//	    PrintWriter out = new PrintWriter(bw))
		//	{
		//	    out.println(result);
			    //more code
			   
		//	} catch (IOException e) {
			    //exception handling left as an exercise for the reader
		//	}
		
		
		// Implement me!
	} // end of add()
	
	
	public int search(T item) {
		
		Node currentNode = mHead;
		
		while (currentNode != null) {
			if (currentNode.getValue().equals((String) item)) {
				return currentNode.getCounter();
			}
			currentNode = currentNode.getNext();
		}
		
		
		return 0;
		
		//long endTime = System.nanoTime();
				//long result= endTime - startTime;

				//System.out.println("That took " + (result) + " milliseconds");
//				try (PrintStream out = new PrintStream(new FileOutputStream("generation/results.txt"))) {
//				    out.println(result,'a');
//				} catch (FileNotFoundException e) {
//					System.out.println("could not open file");
//					e.printStackTrace();
//				}
				
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
	
	
	public void removeOne(T item) {
		
		Node currentNode = mHead;
		Node lastNode = null;
		
		while (currentNode != null) {
			//if there's only one item in the list 
			if (currentNode.getValue().equals((String) item)) {
			
				currentNode.minusCounter();
				
				if (currentNode.getCounter() == 0) {
				
					if (currentNode == mHead)
					
						mHead = currentNode.getNext();
					
					else lastNode.setNext(currentNode.getNext());
					
					mLength--;
				}
				return;
			}
			lastNode = currentNode;
			currentNode = currentNode.getNext();
		}
		
		//long endTime = System.nanoTime();
				//long result= endTime - startTime;

				//System.out.println("That took " + (result) + " milliseconds");
//				try (PrintStream out = new PrintStream(new FileOutputStream("generation/results.txt"))) {
//				    out.println(result,'a');
//				} catch (FileNotFoundException e) {
//					System.out.println("could not open file");
//					e.printStackTrace();
//				}
				
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
	
	
	public void removeAll(T item) {
		Node currentNode = mHead;
		Node lastNode = null;
		
		while (currentNode != null) {
			if (currentNode.getValue().equals((String) item)) {
				if (currentNode == mHead)
					mHead = currentNode.getNext();
				else lastNode.setNext(currentNode.getNext());
				mLength--;
				return;
			}
			lastNode = currentNode;
			currentNode = currentNode.getNext();
		}
		
		//long endTime = System.nanoTime();
				//long result= endTime - startTime;

				//System.out.println("That took " + (result) + " milliseconds");
//				try (PrintStream out = new PrintStream(new FileOutputStream("generation/results.txt"))) {
//				    out.println(result,'a');
//				} catch (FileNotFoundException e) {
//					System.out.println("could not open file");
//					e.printStackTrace();
//				}
				
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
	
	public void print(PrintStream out) {
		Node currentNode = mHead;
		while (currentNode != null) {
            out.printf("%s | %d\n", currentNode.getValue()
                    , currentNode.getCounter());
            currentNode = currentNode.getNext();
		}
	} 
	
	
    private class Node {
        protected String mValue;
        protected Node mNext;

        int counter;

        public Node(String value) {
            mValue = value;
            mNext = null;
            counter = 1;
        }

        public void addCounter() {
            counter++;
        }

        public void minusCounter() {
            counter--;
        }

        public int getCounter() {
            return counter;
        }

        public String getValue() {
            return mValue;
        }

        public Node getNext() {
            return mNext;
        }

        public void setValue(String value) {
            mValue = value;
        }

        public void setNext(Node next) {
            mNext = next;
        }

    }
	
	
	
} // end of class LinkedListMultiset