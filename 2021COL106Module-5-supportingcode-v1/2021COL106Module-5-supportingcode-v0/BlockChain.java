import Includes.*;
import java.util.*;

public class BlockChain{
	public static final String start_string = "LabModule5";
	public Block firstblock;
	public Block lastblock;

	public String InsertBlock(List<Pair<String,Integer>> Documents, int inputyear){
		CRF crf = new CRF(64);

		Block block = new Block();
		MerkleTree merkleTree = new MerkleTree();
		merkleTree.Build(Documents);
		block.mtree = merkleTree;
		block.year = inputyear;
		block.previous = lastblock;

		if(firstblock==null){
			firstblock = block;
			block.value = merkleTree.rootnode.val+ '_' + merkleTree.rootnode.maxleafval;
			block.dgst = crf.Fn(start_string+"#"+block.value);
		}else{
			lastblock.next = block;
			block.value = merkleTree.rootnode.val+ '_' + merkleTree.rootnode.maxleafval;
			block.dgst = crf.Fn(lastblock.dgst+"#"+block.value);
		}

		lastblock = block;

		return lastblock.dgst;
	}

	public Pair<List<Pair<String,String>>, List<Pair<String,String>>> ProofofScore(int student_id, int year){
		Block currentBlock = firstblock;
		ArrayList<Pair<String,String>> secondList = new ArrayList<Pair<String, String>>();
		List<Pair<String,String>> SCP = null;
		boolean b = false;
		while (currentBlock!=null){

			if(currentBlock.year == year){
				SCP = currentBlock.mtree.QueryDocument(student_id);
				b = true;
			}

			if(b){
				Pair<String,String> pair = new Pair<>();
				pair.First = currentBlock.value;
				pair.Second = currentBlock.dgst;
				secondList.add(pair);
			}

			currentBlock= currentBlock.next;
		}

		if(SCP != null){
			Pair<List<Pair<String,String>>, List<Pair<String,String>>> pair = new Pair<>();
			pair.First = SCP;
			pair.Second = secondList;
			return  pair;
		}

		return null;
	}
}
