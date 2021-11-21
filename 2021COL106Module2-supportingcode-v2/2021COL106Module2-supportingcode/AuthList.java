import Includes.*;

public class AuthList{
	// PLEASE USE YOUR ENTRY NUMBER AS THE START STRING
	public static final String start_string = "2020CS10348";
	public Node firstNode;
	public Node lastNode;

	/*
		Note that the Exceptions have already been defined for you in the includes file,
		you just have to raise them accordingly

	*/

	/* 
	Notice that this function is static, the reason why this is static is that we don't want this to be tied with
	an object of the class AuthList. 	
	*/
	public static boolean CheckList(AuthList current, String proof) throws AuthenticationFailedException {
		CRF obj = new CRF(64);
		Node curr = current.firstNode;
		boolean initial = true;
		while(curr != null){
			if(initial){
				String hsh = obj.Fn(AuthList.start_string + "#" + curr.data.value);

				if(!curr.dgst.equals(hsh)) {
					throw new AuthenticationFailedException();
				}
				initial = false;
				curr = curr.next;
			}else if(curr == current.lastNode){
				String hsh = obj.Fn(curr.previous.dgst + "#" + curr.data.value);
				if(!curr.dgst.equals(hsh) || !curr.dgst.equals(proof)) {

					throw new AuthenticationFailedException();
				}
				curr = curr.next;
			}else{
				String hsh = obj.Fn(curr.previous.dgst + "#" + curr.data.value);

				if(!curr.dgst.equals(hsh))  {
					throw new AuthenticationFailedException();
				}
				curr = curr.next;
			}
		}
		return true;
	}


	public String InsertNode(Data datainsert, String proof) throws AuthenticationFailedException {
		CRF crf = new CRF(64);
		String s ="";
		try {
			if (CheckList(this, proof)) {
				Node node = new Node();
				node.previous = lastNode;
				node.data = datainsert;
				if(firstNode==null){
					firstNode=node;
					node.dgst = crf.Fn(start_string +"#"+ datainsert.value);
					s= node.dgst;
				}else {
					lastNode.next = node;
					node.dgst = crf.Fn(proof +"#"+ datainsert.value);
					s=node.dgst;
				}
				lastNode = node;
			}
		}catch (Exception e){
			throw e;
		}
		return s;
	}

	public String DeleteFirst(String proof) throws AuthenticationFailedException, EmptyListException {
		try {
			CheckList(this, proof);
			if(firstNode!=null) {
				firstNode = firstNode.next;
				Node curr = this.firstNode;
				CRF crf = new CRF(64);
				String proof1 = crf.Fn(start_string+"#"+curr.data.value);
				curr.dgst = proof1;
				while (curr.next!=null){
					curr=curr.next;
					proof1 = crf.Fn(proof1+"#"+curr.data.value);
					curr.dgst = proof1;
				}
				return proof1;
			}else{
				throw new EmptyListException();
			}
		}catch (AuthenticationFailedException e ){
			throw e;
		}

//		return null;
	}


	public String DeleteLast(String proof) throws AuthenticationFailedException, EmptyListException {

		try {
			CheckList(this, proof);
			if(lastNode!=null) {
				lastNode = lastNode.previous;
				return lastNode.dgst;
			}else{
				throw new EmptyListException();
			}
		}catch (AuthenticationFailedException e ){
			throw e;
		}

//		return null;
	}

	/* 
	Notice that this function is static, the reason why this is static is that we don't want this to be tied with
	an object of the class AuthList. 	
	*/
	public static Node RetrieveNode(AuthList current, String proof, Data data) throws AuthenticationFailedException, DocumentNotFoundException{

		try {
			CheckList(current,proof);
			Node curr = current.firstNode;
//			if(curr!=null){
//
//			}else{
//				throw new DocumentNotFoundException();
//			}
			while (curr!=null){
				if(curr.data.value.matches(data.value)){
					return curr;
				}else{
					curr=curr.next;
				}
			}

			throw new DocumentNotFoundException();

		}catch (Exception e){
			throw e;
		}

	}

	public void AttackList(AuthList current, String new_data)throws EmptyListException{
		/*
			Implement Code here
		*/
	}

}
