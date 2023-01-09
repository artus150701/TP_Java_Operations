package fr.artus.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		String entree = null;
        Map<String, Operation> map = new HashMap<>();
        try {
            Reader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            while(entree==null||!entree.startsWith("exit")&&!entree.startsWith("stop")) {
                entree = br.readLine();
                if (entree.contains(" <- ")) {

                    if (entree.split(" <- ")[1].charAt(0) == '('){
                        switch (entree.split(" <- ")[1].charAt(1)) {
                            case '+':
                            	map.put(entree.split(" <- ")[0], new Addition(entree.split(" <- ")[1]));
                            break;
                            case '-':
                            	map.put(entree.split(" <- ")[0], new Soustraction(entree.split(" <- ")[1]));
                            break;
                            case '*':
                            	map.put(entree.split(" <- ")[0], new Multiplication(entree.split(" <- ")[1]));
                            break;
                            case '/':
                            	map.put(entree.split(" <- ")[0], new Division(entree.split(" <- ")[1]));
                            break;
                            default: throw new RuntimeException("Expression mal formatée : Operation = " + entree.split(" <- ")[1].charAt(0));
                        }
                    }

                    System.out.println(map.get(entree.split(" <- ")[0]).toString());
                } else if (entree.contains("(") && entree.contains(")") && map.containsKey(entree.substring(0, entree.indexOf("(")))) {
                    Map<String, Double> varMap =  new HashMap<>();
                    for (String varAssign:entree.substring(entree.indexOf('(')+1,entree.indexOf(')')).split(",") ) {
                        if (varAssign.contains("="))
                            varMap.put(varAssign.split("=")[0].replace(" ",""),Double.valueOf(varAssign.split("=")[1].replace(" ","")));
                    }

                    System.out.println( map.get(entree.substring(0, entree.indexOf("("))).eval(varMap));

                }
            }
        }
        catch (IOException e) {
            System.out.println("Caught IOException");
        }
	}

}
