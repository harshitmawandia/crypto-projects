import Includes.*;

public class DriverCode{
	public static void main(String[] args) {
		
		AuthList blockchain = new AuthList();
		Data document1 = new Data();
		document1.value = "Hello!";
		Data document2 = new Data();
		document2.value = "This is COL106";
		Data document3 = new Data();
		document3.value = "This is Lab Module 2";
		
		String proof = null;


		// Insenrting new documents in the blockchain
		try{
			proof = blockchain.InsertNode(document1, null);
			proof = blockchain.InsertNode(document2, proof);
			proof = blockchain.InsertNode(document3, proof);

			
			System.out.println("Added Documents"+ "\n");
		}catch(Exception e){
			System.out.println(e+ "\n");
		}

		// Retrieving a document from the blockchain

		try{
			Node curr = AuthList.RetrieveNode(blockchain, proof, document1);

			if(curr != null) System.out.println("Retrieved Node with data: " + curr.data.value+ "\n");
			else System.out.println("Unable to retrieve node"+ "\n");
		}catch(Exception e){
			System.out.println(e + "\n");
		}
		
		// Delete First
		try{
			proof = blockchain.DeleteFirst(proof);
			System.out.println("Deleted First Node"+ "\n");
		}catch(Exception e){
			System.out.println(e+ "\n");
		}


		// Delete Last
		try{
			proof = blockchain.DeleteLast(proof);
			System.out.println("Deleted Last Node"+ "\n");
		}catch(Exception e){
			System.out.println(e+ "\n");
		}


		/* The following is the driver code for the Authenticated Stack */

		System.out.println("Running tests for Authenticated Stacks");

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
			System.out.println("Poped Docuement from the stack\n");
		}catch(Exception e){
			System.out.println(e + "\n");
		}

		try{
			StackNode curr = stk.GetTop(stackProof);

			if(curr != null) System.out.println("Retrieved data: " + curr.data.value+ "\n");
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
