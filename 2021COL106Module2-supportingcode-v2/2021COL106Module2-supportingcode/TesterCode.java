import Includes.*;

public class TesterCode{
	public static void main(String[] args) {
		
		AuthList blockchain = new AuthList();
		Data document1 = new Data();
		document1.value = "Hello!";
		Data document2 = new Data();
		document2.value = "This is COL106";
		Data document3 = new Data();
		document3.value = "This is Lab Module 2";
		
		String proof = null;
		
		System.out.println("Auth. Lists: Testing Insert, DeleteFirst and DeleteLast\n");
		System.out.println("*****************************************************************\n\n");
		
		try{
			System.out.println("start_string: " + AuthList.start_string +"\n");
			System.out.println("You must use your entry number as start_string\n\n");
			
			System.out.println("Insert: \"Hello\" \n");
			proof = blockchain.InsertNode(document1, null);
			System.out.println("Proof after insert operation: " + proof+"\n\n");
			
			System.out.println("Insert: \"This is COL106\" \n");
			proof = blockchain.InsertNode(document2, proof);
			System.out.println("Proof after insert operation: " + proof+"\n\n");


			System.out.println("Insert: \"This is Lab Module 2\" \n");
			proof = blockchain.InsertNode(document2, proof);
			System.out.println("Proof after insert operation: " + proof+"\n\n");
		}catch(Exception e){
			System.out.println(e+ "\n");
		}
		// Delete First
		try{
			proof = blockchain.DeleteFirst(proof);
			System.out.println("Deleted First Node"+ "\n");
			System.out.println("Proof after DeleteFirst: " + proof +"\n\n");
		}catch(Exception e){
			System.out.println(e+ "\n");
		}


		// Delete Last
		try{
			proof = blockchain.DeleteLast(proof);
			System.out.println("Deleted Last Node"+ "\n");
			System.out.println("Proof after DeleteLast: " + proof +"\n\n");
		}catch(Exception e){
			System.out.println(e+ "\n");
		}


		/* The following is the driver code for the Authenticated Stack */
		
		
			System.out.println("*****************************************************************\n\n");
		
		System.out.println("Auth. Stacks: \n Push \"Hello\", \"This is COL106\", \"This is Lab Module 2\"\n");
		System.out.println("*****************************************************************\n\n");
	
		AuthStack stk = new AuthStack();

		String stackProof = null;

		try{
			stackProof = stk.push(document1, stackProof);
			stackProof = stk.push(document2, stackProof);
			stackProof = stk.push(document3, stackProof);

			System.out.println("Pushed Docuements to the stack\n");
		}catch(Exception e){
			System.out.println(e);
		}

		try{
			stackProof = stk.pop(stackProof);
			System.out.println("Popped Docuement from the stack\n");
		}catch(Exception e){
			System.out.println(e + "\n");
		}

		try{
			StackNode curr = stk.GetTop(stackProof);

			if(curr != null) System.out.println("Top of stack: " + curr.data.value+ "\n");
			else System.out.println("Unable to retrieve data"+ "\n");
		}catch(Exception e){
			System.out.println(e + "\n");
		}
		
		try{
			AuthStack.CheckStack(stk , stackProof);

			System.out.println("Stack Authenticated");
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
