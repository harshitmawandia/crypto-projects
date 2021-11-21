import HelperClasses.Pair;
import HelperClasses.sha256;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CRF extends sha256 {

    // Stores the output size of the function Fn()
    public int outputsize;

    CRF(int size) {
        outputsize = size;
        assert outputsize <= 64;
    }

    // Outputs the mapped outputSize characters long string s' for an input string s
    public String Fn(String s) {
        String shasum = encrypt(s);
        return shasum.substring(0,outputsize);
    }

    /*==========================
    |- To be done by students -|
    ==========================*/

    public Pair<String, String> FindCollDeterministic() {
        HashMap<String,String> map = new HashMap<>();
        String s = "";
        for (int i=0;i<=outputsize;i++){
            s+="0";
        }

        while (true){
            String key = Fn(s);
            if(map.get(key)==null){
                map.put(key,s);
                s=key;
            }else{
                Pair<String,String> pair = new Pair<String,String>(s,map.get(key));
                return pair;
            }
        }
//        return null;
    }

    public void FindCollRandomized() {
        String attempt_filename = "FindCollRandomizedAttempts.txt";
        String outcome_filename = "FindCollRandomizedOutcome.txt";

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(attempt_filename,false);
            PrintStream printStream =  new PrintStream(fileOutputStream);
            FileOutputStream fileOutputStream1 = new FileOutputStream(outcome_filename,false);
            PrintStream printStream1 = new PrintStream(fileOutputStream1);
            HashMap<String,String> map = new HashMap<>();
            boolean b = true;
            for(int i = 0; i < 1000* Math.sqrt(Math.pow(16,outputsize));i++){
                String string = randomString(outputsize);
                String key = Fn(string);
                printStream.println(string);
                if(map.get(key)!=null){
                    if(!map.get(key).matches(string)) {
                        printStream1.println("FOUND");
                        printStream1.println(string);
                        printStream1.println(map.get(key));
                        b = false;
                        break;
                    }
                }else{
                    map.put(key,string);
                }
            }

            if(b){
                printStream1.println("NOT FOUND");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    public String randomString(int a) {
        byte[] array = new byte[a];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        return  (generatedString);
    }
}