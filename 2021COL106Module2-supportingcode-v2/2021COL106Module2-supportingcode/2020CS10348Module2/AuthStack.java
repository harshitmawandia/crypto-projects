import Includes.*;

public class AuthStack{
	// PLEASE USE YOUR ENTRY NUMBER AS THE START STRING
	private static final String start_string = "2020CS10348";
	private StackNode top;

	/*
		Note that the Exceptions have already been defined for you in the includes file,
		you just have to raise them accordingly

	*/

	/* 
	Notice that this function is static, the reason why this is static is that we don't want this to be tied with
	an object of the class. 	
	*/

	public static boolean CheckStack(AuthStack current, String proof) throws AuthenticationFailedException{
		CRF obj = new CRF(64);
		StackNode curr = current.top;
		boolean top = true;
		while(curr != null){
			if(top){
				if(curr.previous!=null) {
					String hsh = obj.Fn(curr.previous.dgst + "#" + curr.data.value);
					if (!curr.dgst.equals(hsh) || !curr.dgst.equals(proof)) {
						throw new AuthenticationFailedException();
					}
					curr = curr.previous;
					top = false;
				}else{
					String hsh = obj.Fn(AuthStack.start_string + "#" + curr.data.value);

					if(!curr.dgst.equals(hsh)) {
						throw new AuthenticationFailedException();
					}
					top = false;
					curr=curr.previous;
				}
			}else if(curr.previous!=null){
				String hsh = obj.Fn(curr.previous.dgst + "#" + curr.data.value);
				if(!curr.dgst.equals(hsh))  {
					throw new AuthenticationFailedException();
				}
				curr=curr.previous;
			}else{
				String hsh = obj.Fn(AuthList.start_string + "#" + curr.data.value);

				if(!curr.dgst.equals(hsh)) {
					throw new AuthenticationFailedException();
				}
				curr=curr.previous;
			}
		}

		return true;
	}

	public String push(Data datainsert, String proof)throws AuthenticationFailedException{
		CRF crf = new CRF(64);
		try{
			CheckStack(this, proof);
			StackNode stackNode = new StackNode();
			stackNode.data=datainsert;
			stackNode.previous=top;
			if(stackNode.previous!=null){
				stackNode.dgst = crf.Fn(top.dgst + "#" + datainsert.value);
			}else{
				stackNode.dgst = crf.Fn(start_string + "#" + datainsert.value);
			}
			top= stackNode;
		}catch (Exception e ){
			throw e;
		}
		return top.dgst;
	}

	public String pop(String proof)throws AuthenticationFailedException, EmptyStackException{

		try {
			CheckStack(this,proof);
			if (top != null) {
				top=top.previous;
				return top.dgst;
			}else{
				throw new EmptyStackException();
			}
		}catch (Exception e){
			throw e;
		}
	}

	public StackNode GetTop(String proof)throws AuthenticationFailedException, EmptyStackException{
		try {
			CheckStack(this,proof);
			if(top!=null) {
				return top;
			}else{
				throw new EmptyStackException();
			}
		}catch (Exception e){
			throw e;
		}
	}
}