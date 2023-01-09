package fr.artus.test;

import java.util.ArrayList;

public abstract class Operation implements Expression{
	
	protected final Expression fils1 ;
	protected final Expression fils2 ;
	
    public Operation(Expression fils1, Expression fils2) {
        this.fils1 = fils1;
        this.fils2 = fils2;
    }
	
	public Operation(String expression) {
		
		ArrayList<Operation> memList = new ArrayList<Operation>();
		String tmp1 = "";
		String tmp2 = "";
		Expression newTmp1 = null;
		Expression newTmp2 = null;
		
		expression = expression.replace("(","");
		expression = expression.replace(")","");
		
		int i = expression.length()-1;
		
		while(i>0) {

			while(expression.toCharArray()[i] != ' ') {

				if(tmp1 == "") {

					if (!(expression.toCharArray()[i] == '+' || expression.toCharArray()[i] == '-' || expression.toCharArray()[i] == '*' || expression.toCharArray()[i] == '/')) {
						while(expression.toCharArray()[i] != ' ') {
							tmp1 = expression.toCharArray()[i] + tmp1;
							i--;
						}
					}
					else {
						newTmp1 = memList.get(memList.size()-2);
						newTmp2 = memList.get(memList.size()-1);

						switch ( expression.toCharArray()[i] ) {
						case '+' :
							memList.add(new Addition(memList.get(memList.size()-2), memList.get(memList.size()-1)));
			  			break;
						case '-' :
							memList.add(new Soustraction(memList.get(memList.size()-2), memList.get(memList.size()-1)));
			  			break;
						case '*' :
							memList.add(new Multiplication(memList.get(memList.size()-2), memList.get(memList.size()-1)));
		  				break;
						case '/' :
							memList.add(new Division(memList.get(memList.size()-2), memList.get(memList.size()-1)));
		  				break;
						default : throw new RuntimeException("Expression mal formatée ");
						}
						memList.remove(newTmp1);
						memList.remove(newTmp2);
					}
				}
				else if(tmp2 == "") {
					while(expression.toCharArray()[i] != ' ') {
						tmp2 =  expression.toCharArray()[i]+tmp2;
						i--;
					}
				}
				else if (expression.toCharArray()[i] == '+' || expression.toCharArray()[i] == '-' || expression.toCharArray()[i] == '*' || expression.toCharArray()[i] == '/') {

					  if (Character.isDigit(tmp1.charAt(0)))
					    newTmp1 = new Constante(Double.parseDouble(tmp1));
					  else
					    newTmp1 = new Variable(tmp1);

					  if (Character.isDigit(tmp2.charAt(0)))
						    newTmp2 = new Constante(Double.parseDouble(tmp2));
					  else
						    newTmp2 = new Variable(tmp2);

					  switch ( expression.toCharArray()[i] ) {
				  		case '+' :
				  			memList.add(new Addition(newTmp2, newTmp1));
				  		break;
		                case '-' :
				  			memList.add(new Soustraction(newTmp2, newTmp1));
				  			break;
		                case '*' :
			  				memList.add(new Multiplication(newTmp2, newTmp1));
			  				break;
		                case '/' :
			  				memList.add(new Division(newTmp2, newTmp1));
			  				break;
				        default : throw new RuntimeException("Expression mal formatée ");
				     }
					 tmp1 = "";
					 tmp2 = "";
				}
				i--;
			}
	           i--;
	       	}
	    this.fils1 = memList.get(1);
	    this.fils2 = memList.get(0);
		}
}
