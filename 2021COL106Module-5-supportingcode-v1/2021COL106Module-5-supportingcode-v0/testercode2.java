import Includes.*;
import java.util.*;
import java.io.*;

public class testercode2{
	public static void main(String[] args) {
		List<Pair<String,Integer>> documents = new ArrayList<Pair<String,Integer>> ();
		List<Pair<String,Integer>> documents2 = new ArrayList<Pair<String,Integer>> ();
		List<Pair<String,Integer>> documents3 = new ArrayList<Pair<String,Integer>> ();
		documents.add(new Pair <String,Integer> ("Himanshu",98));
		documents.add(new Pair <String,Integer> ("Aditya",41));
		documents.add(new Pair <String,Integer> ("Rishabh",63));
		documents.add(new Pair <String,Integer> ("Garima",11));
		documents.add(new Pair <String,Integer> ("Ramesh",91));
		documents.add(new Pair <String,Integer> ("Vaibhav",0));
		documents.add(new Pair <String,Integer> ("Ritika",63));
		documents.add(new Pair <String,Integer> ("Saiyam",100));
		documents.add(new Pair <String,Integer> ("Himadfgnshu",22));
		documents.add(new Pair <String,Integer> ("Aditefwwfewefya",68));
		documents.add(new Pair <String,Integer> ("Rishabewffewfewh",23));
		documents.add(new Pair <String,Integer> ("Gafdsrima",74));
		documents.add(new Pair <String,Integer> ("Rathrthmesh",71));
		documents.add(new Pair <String,Integer> ("Vadsvvdsvibhav",23));
		documents.add(new Pair <String,Integer> ("Rihdghgdfghtika",63));
		documents.add(new Pair <String,Integer> ("Saighhgsfghyam",43));

		documents2.add(new Pair <String,Integer> ("Dhruv",2));
		documents2.add(new Pair <String,Integer> ("Adit",91));
		documents2.add(new Pair <String,Integer> ("Risa",13));
		documents2.add(new Pair <String,Integer> ("Gama",31));

		documents3.add(new Pair <String,Integer> ("Resh",12));
		documents3.add(new Pair <String,Integer> ("Divyanis",-20));
		documents3.add(new Pair <String,Integer> ("BajajMotorWorks",69));
		documents3.add(new Pair <String,Integer> ("Saiyafewfm",74));
		documents3.add(new Pair <String,Integer> ("Redwfsh",12));
		documents3.add(new Pair <String,Integer> ("Divyanwfefeis",-30));
		documents3.add(new Pair <String,Integer> ("BajajMotowffeerWorks",56));
		documents3.add(new Pair <String,Integer> ("Saiwerryam",78));

		BlockChain al = new BlockChain();
		System.out.println("Making BlockChain...");
		try
		{
			String proof = "LabModule5";
			String proof1 = al.InsertBlock(documents, 2021);
			System.out.println(proof1);
			proof1 = al.InsertBlock(documents2, 2020);
			System.out.println(proof1);
			proof1 = al.InsertBlock(documents3, 2019);
			System.out.println(proof1);
			proof1=al.firstblock.mtree.UpdateDocument(0, 34);
			System.out.println(proof1);
			proof1=al.firstblock.mtree.UpdateDocument(1, 45);
			System.out.println(proof1);
			proof1=al.firstblock.next.mtree.UpdateDocument(2, 56);
			System.out.println(proof1);
			proof1=al.firstblock.next.next.mtree.UpdateDocument(3, 19);
			System.out.println(proof1);
		}
		catch (Exception e)
		{
			System.out.println("ERROR: An exception was thrown");
		}

	}
}
