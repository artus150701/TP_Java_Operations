package fr.artus.test;

import java.util.Map;

public class Multiplication extends Operation{
	public Multiplication(String str) {
			super(str);
		}
	
    public Multiplication(Expression fils1, Expression fils2) {
    	super(fils1,fils2);
    }
    
	@Override
	public String toString() {
        return ("(" + fils1 + " * " + fils2 + ")");
	}
	
	@Override
    public double eval(Map<String, Double> variable){
		return (fils1.eval(variable)*fils2.eval(variable));
    }
}
