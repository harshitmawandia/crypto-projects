import Includes.*;
import java.util.*;

public class tester {

	public static List<Pair<String, Integer>> doc(int a) {

		List<Pair<String, Integer>> documents = new ArrayList<Pair<String, Integer>>();
		for (int i = 0; i < 64; i++) {

			documents.add(new Pair<String, Integer>("String_" + i, i * a));

		}

		return documents;

	}

	public static void main(String[] args) {
		List<Pair<String, Integer>> documents = doc(1);

		System.out.println("Building Merkle Tree...");
		MerkleTree tree = new MerkleTree();

		try {

			tree.Build(documents);
			System.out.println(tree.rootnode.val);
			System.out.println();// F7EDA57BD63F08D92D1E8E09816F1A91FE119BA4371BCD75A5ECFB004B41B636

		} catch (Exception e) {
			System.out.println("error");
		}

		System.out.println("Updating Documents...");

		try {

			for (int i = 0; i < 64; i += 2) {
				tree.UpdateDocument(i, i + 10);
			}

			System.out.println(tree.rootnode.val);
			System.out.println();// 43772DD32AFEA04CAB73D4AC687A69C9F8E3A574FBA3103D566BA76694AB3F0A

		} catch (Exception e) {
			System.out.println("error");
		}

		System.out.println("Building Block Chain...");

		BlockChain al = new BlockChain();
		try {

			for (int i = 0; i < 10; i++) {
				List<Pair<String, Integer>> dok = doc(i);
				al.InsertBlock(dok, i + 2000);
			}

			System.out.println(al.lastblock.dgst);
			System.out.println();// A9C64F88A562B30C24CF6E7CEFA16ABFAB3D0C58F80562B0702395A4488F58FA

		} catch (Exception e) {
			System.out.println("error");
		}

		System.out.println("Checking ProofofScore...");

		try {

			CRF crf = new CRF(64);
			String a = "";

			for (int i = 0; i < 10; i++) {

				Pair<List<Pair<String, String>>, List<Pair<String, String>>> obt_path = al.ProofofScore(5, i + 2000);
				List<Pair<String, String>> nodepath = obt_path.First;
				List<Pair<String, String>> blockpath = obt_path.Second;

				String b = "";
				String c = "";
				String d = "";
				String e = "";
				for (int j = 0; j < nodepath.size(); j++) {
					b += nodepath.get(j).First;
					c += nodepath.get(j).Second;
				}
				for (int k = 0; k < blockpath.size(); k++) {
					d += blockpath.get(k).First;
					e += blockpath.get(k).Second;
				}

				a = crf.Fn(a + b + c + d + e);

			}

			System.out.println(a);
			System.out.println();// BB3BD1B58DA3D8662E1AE64BD57BFE335A61D5FE712D31746A9AF0ED5F7C7B49

		} catch (Exception e) {
			System.out.println("error");
		}

	}

}
