package fr.artus.test;

import java.util.Map;

public class Constante implements Expression {
		private double value;
		
		public Constante(double value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
	        return String.valueOf(value);
		}
		
		@Override
	    public double eval(Map<String, Double> variable){
			return value;
	    }
		
}


