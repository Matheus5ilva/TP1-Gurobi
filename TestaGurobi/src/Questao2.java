import gurobi.*; //Importando a classe Gurobi, foi usado o asterico(*) para importar também todas as interfaces e classes filhas

public class Questao2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("\tQuestão 2");
		System.out.println();
		try {
			GRBEnv env = new GRBEnv(true);// Instanciando uma classe GRBEnv para obter um ambiente Gurobi 
			env.set("logFile", "questao2.log");//Criamos um arquivo .log que sera usado para salvar os dados gerados pelo Gurobi
			env.start();//Iniciamos o ambiente, com o metodo start(), alem que também iremos gravar os dados no arquivo .log criado
			
			GRBModel model = new GRBModel(env);// Criação do modelo vazio. No construtor do GRModel temos o ambiente criado acima
			
		    GRBVar x = model.addVar(0.0, 1.0, 0.0, GRB.BINARY, "x"); //Criação da variavel x = quantidade de P1, o metodo addVar(), o primeiro argumento usamos os limites inferiores (0.0) e o segundo argumento usamos para limites superiores (1.0). O terceiro argumento é o coeficiente objetivo linear(0.0). O quarto argumento é o tipo da variavel (GRB BINARY). O quinto argumento é o nome da variavel.   
		    GRBVar y = model.addVar(0.0, 1.0, 0.0, GRB.BINARY, "y"); //Criação de variavel y = quantidade de P2, o metodo addVar(), o primeiro argumento usamos os limites inferiores (0.0) e o segundo argumento usamos para limites superiores (1.0). O terceiro argumento é o coeficiente objetivo linear(0.0). O quarto argumento é o tipo da variavel (GRB BINARY). O quinto argumento é o nome da variavel. 
		    
		    // A expressão de maximização é 100x + 150y
		    GRBLinExpr expr = new GRBLinExpr(); // Instanciamos um classe para função linear vazia para ser adicionar as variaveis.
		    expr.addTerm(100.0, x); //O metodo .addTerm() é para colocar o valor da constante da variavel x (P1).
		    expr.addTerm(150.0, y); //O metodo .addTerm() é para colocar o valor da constante da variavel y (P2).
		    model.setObjective(expr, GRB.MAXIMIZE); //O metodo setObjective() é para definirmos qual o sentido para otimizar, preceba que usamos o objetivo de Maximizar(MAXMIZE) para o problema.
		    
		    // A primeira restrição é do tempo 2x + 3y <= 120
		    expr = new GRBLinExpr(); // Instanciamos a classe para a função linear.
		    expr.addTerm(2.0, x); // O metodo .addTerm() é para colocarmos o valor da constante de x (P1) das restrição.
		    expr.addTerm(3.0, y); // O metodo .addTerm() é para colocarmos o valor da constante de y (P2) das restrição.
		    model.addConstr(expr, GRB.LESS_EQUAL, 120.0, "c0"); // O metodo .addConstr() para adicionarmos a restrição a um modelo espercifico. O primeiro argumento é o lado esquerdo da função (o que vem antes do operador de maior, menor ou igual). O segundo argumento é qual o sentido da restrição, aqui usamos o GRB_LESS_EQUAL (<=). O terceiro argumento é o lado direito da função.
		    
		    // A segunda restrição é da produção x <= 40
		    expr = new GRBLinExpr(); // Instanciamos a classe para a função linear.
		    expr.addTerm(1.0, x); // O metodo .addTerm() é para colocarmos o valor da constante de x (P1) das restrição.
		    model.addConstr(expr, GRB.LESS_EQUAL, 40.0, "c1"); // O metodo .addConstr() para adicionarmos a restrição a um modelo espercifico. O primeiro argumento é o lado esquerdo da função (o que vem antes do operador de maior, menor ou igual). O segundo argumento é qual o sentido da restrição, aqui usamos o GRB_LESS_EQUAL (<=). O terceiro argumento é o lado direito da função.
		    
		    // A terceira restrição é da produção y <= 30
		    expr = new GRBLinExpr(); // Instanciamos a classe para a função linear.
		    expr.addTerm(1.0, y); // O metodo .addTerm() é para colocarmos o valor da constante de y (P2) das restrição.
		    model.addConstr(expr, GRB.LESS_EQUAL, 30.0, "c1"); // O metodo .addConstr() para adicionarmos a restrição a um modelo espercifico. O primeiro argumento é o lado esquerdo da função (o que vem antes do operador de maior, menor ou igual). O segundo argumento é qual o sentido da restrição, aqui usamos o GRB_LESS_EQUAL (<=). O terceiro argumento é o lado direito da função.
		    
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
