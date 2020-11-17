import gurobi.*; //Importando a classe Gurobi, foi usado o asterico(*) para importar também todas as interfaces e classes filhas

public class Questao4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("\tQuestão 4");
		System.out.println();
		try {
			GRBEnv env = new GRBEnv(true);// Instanciando uma classe GRBEnv para obter um ambiente Gurobi 
			env.set("logFile", "questao4.log");//Criamos um arquivo .log que sera usado para salvar os dados gerados pelo Gurobi
			env.start();//Iniciamos o ambiente, com o metodo start(), alem que também iremos gravar os dados no arquivo .log criado
			
			GRBModel model = new GRBModel(env);// Criação do modelo vazio. No construtor do GRModel temos o ambiente criado acima
			
		    GRBVar x = model.addVar(0.0, 1.0, 0.0, GRB.BINARY, "x"); //Criação da variavel x = quantidade de M1 / dia, o metodo addVar(), o primeiro argumento usamos os limites inferiores (0.0) e o segundo argumento usamos para limites superiores (1.0). O terceiro argumento é o coeficiente objetivo linear(0.0). O quarto argumento é o tipo da variavel (GRB BINARY). O quinto argumento é o nome da variavel.   
		    GRBVar y = model.addVar(0.0, 1.0, 0.0, GRB.BINARY, "y"); //Criação de variavel y = quantidade de M2 / dia, o metodo addVar(), o primeiro argumento usamos os limites inferiores (0.0) e o segundo argumento usamos para limites superiores (1.0). O terceiro argumento é o coeficiente objetivo linear(0.0). O quarto argumento é o tipo da variavel (GRB BINARY). O quinto argumento é o nome da variavel. 
		    
		    // A expressão de maximização é 4x + 3y
		    GRBLinExpr expr = new GRBLinExpr(); // Instanciamos um classe para função linear vazia para ser adicionar as variaveis.
		    expr.addTerm(4.0, x); //O metodo .addTerm() é para colocar o valor da constante da variavel x (M1).
		    expr.addTerm(3.0, y); //O metodo .addTerm() é para colocar o valor da constante da variavel y (M2).
		    model.setObjective(expr, GRB.MAXIMIZE); //O metodo setObjective() é para definirmos qual o sentido para otimizar, preceba que usamos o objetivo de Maximizar(MAXMIZE) para o problema.
		    
		    // A primeira restrição é do volume 2x + y <= 1000
		    expr = new GRBLinExpr(); // Instanciamos a classe para a função linear.
		    expr.addTerm(2.0, x); // O metodo .addTerm() é para colocarmos o valor da constante de x (M1) das restrição.
		    expr.addTerm(1.0, y); // O metodo .addTerm() é para colocarmos o valor da constante de y (M2) das restrição.
		    model.addConstr(expr, GRB.LESS_EQUAL, 1000.0, "c0"); // O metodo .addConstr() para adicionarmos a restrição a um modelo espercifico. O primeiro argumento é o lado esquerdo da função (o que vem antes do operador de maior, menor ou igual). O segundo argumento é qual o sentido da restrição, aqui usamos o GRB_LESS_EQUAL (<=). O terceiro argumento é o lado direito da função.
		    
		    // A segunda restrição é da capacidade maxima de produção x + y <= 800
		    expr = new GRBLinExpr(); // Instanciamos a classe para a função linear.
		    expr.addTerm(1.0, x); // O metodo .addTerm() é para colocarmos o valor da constante de x (M1) das restrição.
		    expr.addTerm(1.0, y); // O metodo .addTerm() é para colocarmos o valor da constante de y (M2) das restrição.
		    model.addConstr(expr, GRB.LESS_EQUAL, 800.0, "c1"); // O metodo .addConstr() para adicionarmos a restrição a um modelo espercifico. O primeiro argumento é o lado esquerdo da função (o que vem antes do operador de maior, menor ou igual). O segundo argumento é qual o sentido da restrição, aqui usamos o GRB_LESS_EQUAL (<=). O terceiro argumento é o lado direito da função.
		    
		    // A terceira restrição é das fivelas para M1 x <= 400
		    expr = new GRBLinExpr(); // Instanciamos a classe para a função linear.
		    expr.addTerm(1.0, x); // O metodo .addTerm() é para colocarmos o valor da constante de x (M1) das restrição.
		    model.addConstr(expr, GRB.LESS_EQUAL, 400.0, "c2"); // O metodo .addConstr() para adicionarmos a restrição a um modelo espercifico. O primeiro argumento é o lado esquerdo da função (o que vem antes do operador de maior, menor ou igual). O segundo argumento é qual o sentido da restrição, aqui usamos o GRB_LESS_EQUAL (<=). O terceiro argumento é o lado direito da função.
		    
		    // A quarta restrição é das fivelas para M2 y <= 700
		    expr = new GRBLinExpr(); // Instanciamos a classe para a função linear.
		    expr.addTerm(1.0, y); // O metodo .addTerm() é para colocarmos o valor da constante de x (M2) das restrição.
		    model.addConstr(expr, GRB.LESS_EQUAL, 700.0, "c3"); // O metodo .addConstr() para adicionarmos a restrição a um modelo espercifico. O primeiro argumento é o lado esquerdo da função (o que vem antes do operador de maior, menor ou igual). O segundo argumento é qual o sentido da restrição, aqui usamos o GRB_LESS_EQUAL (<=). O terceiro argumento é o lado direito da função.
		    
		    model.optimize();// O modelo já foi construido. Com isso, usando o metodo .optimize() vai realizar a rotina de otimização e usar os valores dos atributos preenchidos (incluido qual o tipo de otimização).

		    //Parte que exibe os resultados.
		      System.out.println(x.get(GRB.StringAttr.VarName)
		                         + " " +x.get(GRB.DoubleAttr.X));
		      System.out.println(y.get(GRB.StringAttr.VarName)
		                         + " " +y.get(GRB.DoubleAttr.X));
		    
		      System.out.println("Obj: " + model.get(GRB.DoubleAttr.ObjVal));

		      //Esses metodos finais, são para fechar o ambiente para não haver e carregar lixo.
		      model.dispose();
		      env.dispose();
		} catch (GRBException e) {//Tratamento e exibição caso ocorra erro.
			System.out.println("Error code: " + e.getErrorCode() + ". " + e.getMessage());
		}
	}

}
