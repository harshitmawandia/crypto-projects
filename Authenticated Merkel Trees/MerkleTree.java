import Includes.*;
import java.util.*;

public class MerkleTree{
	
	// Check the TreeNode.java file for more details
	public static TreeNode rootnode;
	public int numdocs;

	public String Build(String[] documents){
		ArrayList<TreeNode> arrayList = new ArrayList<>();
		ArrayList<TreeNode> arrayList2 = new ArrayList<>();
		numdocs = documents.length;
		for(int j = 0; j < Math.log(numdocs)/Math.log(2)+1; j++){
			for(int i = 0; i < (documents.length)/(Math.pow(2,j)); i++){
				CRF crf = new CRF(64);
				TreeNode treeNode = new TreeNode();

				if(j==0){
					treeNode.val=documents[i];
					arrayList2.add(treeNode);
				}else{
					treeNode.val = crf.Fn(arrayList.get(i*2).val+"#"+ arrayList.get(i*2 +1).val);
					arrayList.get(i*2).parent = treeNode;
					arrayList.get(i*2+1).parent = treeNode;
					treeNode.left=arrayList.get(i*2);
					treeNode.right=arrayList.get(i*2+1);
					arrayList2.add(treeNode);
				}
			}
			arrayList=arrayList2;
			arrayList2= new ArrayList<TreeNode>();
		}
		rootnode = arrayList.get(0);
		return rootnode.val;
	}

	/* 
		Pair is a generic data structure defined in the Pair.java file
		It has two attributes - First and Second, that can be set either manually or
		using the constructor

		Edit: The constructor is added
	*/
		
	public List<Pair<String,String>> QueryDocument(int doc_idx){
		ArrayList<Integer> path = new ArrayList<>();
		int a = doc_idx-1;
		for(int i = 0; i < Math.log(numdocs)/Math.log(2)+1;i++){
			path.add(a);
			a=(a)/2;
		}
		ArrayList<Pair<String,String>> SCpath = new ArrayList<Pair<String, String>>();
		TreeNode currentNode = rootnode;
		for(int i= path.size()-1; i >=0; i--){
			Pair<String,String> pair;
			if(path.get(i)%2==0){
				String s1 = currentNode.val;
				String s2;
				if(currentNode.parent!=null){
					s2 = currentNode.parent.right.val;
				}else{
					s2 = null;
				}
				pair = new Pair<>(s1,s2);
			}else{
				String s2 = currentNode.val;
				String s1;
				if(currentNode.parent!=null){
					s1 = currentNode.parent.left.val;
				}else{
					s1 = null;
				}
				pair = new Pair<>(s1,s2);
			}
			if(i>0){
				if(path.get(i-1)%2==0){
					currentNode=currentNode.left;
				}else{
					currentNode=currentNode.right;
				}
			}
			SCpath.add(pair);

		}

		return reverseArrayList(SCpath);
	}

	public static boolean Authenticate(List<Pair<String,String>> path, String summary){
		CRF obj = new CRF(64);
		int c = path.size();
		if(summary.equals(path.get(c-1).First)){
			for(int i =0;i<c-2;i++){
				String check = obj.Fn((path.get(i).First+"#"+path.get(i).Second));
				if(!(check.equals(path.get(i+1).First)||check.equals(path.get(i+1).Second))){
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public String UpdateDocument(int doc_idx, String new_document){
		ArrayList<Integer> path = new ArrayList<>();
		int a = doc_idx-1;
		for(int i = 0; i < Math.log(numdocs)/Math.log(2)+1;i++){
			path.add(a);
			a=(a)/2;
		}
		ArrayList<Pair<TreeNode,TreeNode>> SCpath = new ArrayList<Pair<TreeNode, TreeNode>>();
		TreeNode currentNode = rootnode;
		for(int i= path.size()-1; i >=0; i--){
			Pair<TreeNode,TreeNode> pair;
			if(path.get(i)%2==0){
				TreeNode s1 = currentNode;
				TreeNode s2;
				if(currentNode.parent!=null){
					s2 = currentNode.parent.right;
				}else{
					s2 = null;
				}
				pair = new Pair<>(s1,s2);
			}else{
				TreeNode s2 = currentNode;
				TreeNode s1;
				if(currentNode.parent!=null){
					s1 = currentNode.parent.left;
				}else{
					s1 = null;
				}
				pair = new Pair<>(s1,s2);
			}
			SCpath.add(pair);
			if(i>0){
				if(path.get(i-1)%2==0){
					currentNode=currentNode.left;
				}else{
					currentNode=currentNode.right;
				}
			}else {
				currentNode.val=new_document;
				CRF crf = new CRF(64);
				for(int j = SCpath.size()-1;j>=0;j--){
					if(j!=0){
						SCpath.get(j).First.parent.val = crf.Fn(SCpath.get(j).First.val + "#" +SCpath.get(j).Second.val);
					}else{
						return SCpath.get(j).First.val;
					}
				}
			}


		}

		return "";
	}

	public ArrayList<Pair<String,String>> reverseArrayList(ArrayList<Pair<String,String>> alist)
	{
		ArrayList<Pair<String,String>> revArrayList = new ArrayList<Pair<String,String>>();
		for (int i = alist.size() - 1; i >= 0; i--) {
			revArrayList.add(alist.get(i));
		}
		return revArrayList;
	}

}