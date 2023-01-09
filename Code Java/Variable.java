package fr.artus.test;

import static java.util.Objects.requireNonNull;
import java.util.Map;

public class Variable implements Expression{
	private String nom;
	
	public Variable(String nom){
		this.nom = requireNonNull(nom);
	}
	
    @Override
	public String toString() {
        return nom;
	}
    
    @Override
    public double eval(Map<String, Double> variable){
        if (!variable.containsKey(nom))
            throw new RuntimeException('"' + nom + '"' + "la variable n'est pas dans la Map");
        return variable.get(nom);
    }
}
