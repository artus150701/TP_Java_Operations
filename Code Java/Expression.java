package fr.artus.test;

import java.util.Map;

public interface Expression {
	double eval(Map<String, Double> variable);
}
