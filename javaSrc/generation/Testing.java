package generation;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;





public class Testing {

	// private static long startTime = 0;

	//static multiset  ll = new LinkedListMultiset<String>();
	
	public static void main(String[] args) throws java.io.IOException {

		{
			
			 ArrayList<String> list = new ArrayList<String>();
		        
			// The name of the file to open.
	        String fileName = "javasrc/generation/250with.txt";

	        // This will reference one line at a time
	        String line = null;

	        try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = 
	                new FileReader(fileName);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);

	            while((line = bufferedReader.readLine()) != null) {
	            	
			            list.add(line);
			          
	                System.out.println(line);
	            }   

	            // Always close files.
	            bufferedReader.close();         
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" + 
	                fileName + "'");                
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error reading file '" 
	                + fileName + "'");                  
	            // Or we could just do this: 
	            // ex.printStackTrace();
	            
	             
	            
	            String add = "A";
	            
	            for (int i=0; i<list.size(); i++) {
	    			
	            	((Appendable) list).append(add);
	    		}
	        }
	    }
		
		
		
		


}
}
