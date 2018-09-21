
import java.io.*;
import java.util.*;

/**
 * 
 * @author jefcha
 * modified by Shahd Sideek 
 */
public class PerformanceAnalysis
{
    protected static final String progName = "PerformanceAnalysis";
    protected static final int EXP_NUMBER = 10;

    /** Different modes that program can run. */
    /*public enum Mode {
      	BUB,
      	SELECT
    }*/

    
    /**
     * Print usage information.
     * 
     * @param progName Program name.
     */
    protected static void printUsage(String progName) {
      	System.err.println("USAGE: " + progName + " [data structure] [input file]");
      	System.err.println("  data structure [linkedlist, sortedlinkedlist, bst, hash]");
      	System.err.println("EXAMPLE: " + progName + " hash random.txt");
    } // end of printUsage()


    /**
     * Main method.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            // not enough arguments
            if (args.length != 3) {
                printUsage(progName);
                System.exit(1);
            }
            
            // data structure to be used
            String dataUsed = args[0];
            Multiset<String> implementType = null;
                        
            //args[1] = fix data size
            //args[2] = operation size
            String title = "FixData" + args[1] + "_OpSize" + args[2];
            String fileName = null;
            String recordFileName = dataUsed + "_" + title + ".txt";
            String[] dataOrder = {"random", "order", "reverse"}; 
            int testingCases = 11;
            
            // construct in and output streams/writers/readers, then process each operation.
            long startTime = 0;
            long endTime = 0;
            double timeElapsed = 0;
            // record 10 times experiments
            double[] recordList = new double[EXP_NUMBER];
            
            PrintWriter filehOutWriter = new PrintWriter(recordFileName);
            for(int i=0 ; i<dataOrder.length ; i++)
            {
            	// figure out which data structure we are using    		
	            implementType = null;
	            switch(dataUsed) {
		            case "linkedlist":
		            	implementType = new LinkedListMultiset<String>();
						break;
					case "sortedlinkedlist":
						implementType = new SortedLinkedListMultiset<String>();
						break;
					case "bst":
						implementType = new BstMultiset<String>();
						break;
					case "hash":
						implementType = new HashMultiset<String>();
						break;
					case "baltree":
						implementType = new BalTreeMultiset<String>();
						break;
					default:
						System.err.println("Unknown implmementation type.");
	                    printUsage(progName);
	                    System.exit(1);
	            }
	            
            	for(int j=1 ; j<=testingCases ; j++)
                {    	            
            		fileName = title + "_Test" + Integer.toString(j) + "_" + dataOrder[i] + ".txt";
            		
            		try {
            			FileReader in = new FileReader(new File(fileName));
            		    BufferedReader inReader = new BufferedReader(in);
            			PrintWriter searchOutWriter = new PrintWriter(System.out, true);
            			
            			int curExp = 0;
            			while (curExp < EXP_NUMBER)
            			{            				
            				startTime = System.nanoTime();
                			// do the experiment (depends on text file)
                			// process the operations
                			MultisetTester.processOperations(inReader, searchOutWriter, implementType);
                			endTime = System.nanoTime();
                			
                			timeElapsed = (endTime - startTime) / Math.pow(10, 9);
                			recordList[curExp] = timeElapsed;
                			filehOutWriter.println(String.format("Time elapsed (secs): %.10f", timeElapsed));
                            System.out.printf("Time elapsed (secs): %.10f\n", timeElapsed);
                			curExp++;
            			}
            			
            			// calculate the average
            			int index = 0;
            			double sum = 0;
            			
            			while(index<recordList.length)
            			{
            				sum += recordList[index];
            				index++;
            			}
            			
            			double average = sum / EXP_NUMBER ;
            			filehOutWriter.println("DataStructure:" + dataUsed + " Testing file: " + fileName);
            			filehOutWriter.println(String.format("Average time performance (secs): %.10f\n", average));
            			System.out.println("DataStructure:" + dataUsed + " Testing file: " + fileName);
            			System.out.printf("Average time performance (secs): %.10f\n", average);
            		} 
            		catch (IOException e) {
            			System.err.println(e.getMessage());
            		}
                }
            }
            filehOutWriter.close();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            printUsage(progName);
        }

    } // end of main()
    	
} // end of class SortDemo1