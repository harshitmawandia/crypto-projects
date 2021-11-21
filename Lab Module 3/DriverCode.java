import Includes.*;
import java.util.*;
import java.io.*;

public class DriverCode{
	public static void main(String[] args) {
		String[] documents = {"Hello!", "This is Lab Module 3", 
									"COL 106", "Data Structures and Algorithms"};
		String summary = null;

		MerkleTree tree = new MerkleTree();

		// Building the MerkleTree
		System.out.println("Testing the Build function");
		summary = tree.Build(documents);

		System.out.println("The summary after building the MerkleTree is " + summary);
		System.out.println("The summary expected after building the MerkleTree is E1619AF4F3289277C173C6714926C31E93230663B7FA1EECD7FD5378BBDB67E2");
		
		System.out.println();
		System.out.println("---------------------------------------------------------");
		// Getting the Sibling-Coupled Path to Root

		String[] strlist = new String[3];
		try{
			FileReader fr=new FileReader("output.txt");
			BufferedReader br=new BufferedReader(fr);
			String line;
			int i = 0;
			while((line=br.readLine())!=null){
				strlist[i++]= line;
			}
			br.close();
			fr.close();  
		}catch(Exception e){
			System.out.println("Output file not present");
		}
		

		System.out.println("Testing the QueryDocument function");
		List<Pair<String,String>> path = tree.QueryDocument(3);
		try{
			int length = path.size();
			for(int i = 0; i < length; i++){
				Pair<String,String> temp = path.get(i);
				String node_one = temp.First;
				String node_two = temp.Second;
				System.out.println("The value at position " + (i + 1) + " is " + node_one + " and " + node_two);
				if(i < 3)System.out.println(strlist[i]);
				else{
					System.out.println("ERROR: Path contains more elements than expected");
				}
			}
		}catch(Exception e){
			System.out.println("Failed to get path to document!");
		}
		System.out.println();
		System.out.println("---------------------------------------------------------");

		//Authenticate List function
		System.out.println("Testing the Authenticate function");
		System.out.println("Authenticating path...");
		System.out.println("The result is "+ MerkleTree.Authenticate(path,summary));
		System.out.println();
		System.out.println("---------------------------------------------------------");

		// Updating a document
		System.out.println("Testing the Update function");
		summary = tree.UpdateDocument(2, "Lab Module - 3");

		System.out.println("The summary after updating the MerkleTree is " + summary);
		System.out.println("The expected summary after updating the MerkleTree is C9E6DCE395DDE0B7A218EC0CFF78A5A369501DCEDAE36230CB16F4097C11CF67");
	}
}