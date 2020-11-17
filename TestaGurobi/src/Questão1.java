import gurobi.*; //Importando a classe Gurobi, foi usado o asterico(*) para importar tamb�m todas as interfaces e classes filhas

public class Quest�o1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("\tQuest�o 1 - Problema do sapateiro");
		System.out.println();
		try {
			GRBEnv env = new GRBEnv(true);// Instanciando uma classe GRBEnv para obter um ambiente Gurobi 
			env.set("logFile", "questao1.log");//Criamos um arquivo .log que sera usado para salvar os dados gerados pelo Gurobi
			env.start();//Iniciamos o ambiente, com o metodo start(), alem que tamb�m iremos gravar os dados no arquivo .log criado
			
			GRBModel model = new GRBModel(env);// Cria��o do modelo vazio. No construtor do GRModel temos o ambiente criado acima
			
		    GRBVar x = model.addVar(0.0, 1.0, 0.0, GRB.BINARY, "x"); //Cria��o da variavel x = quantidade de sapatos feitos por hora, o metodo addVar(), o primeiro argumento usamos os limites inferiores (0.0) e o segundo argumento usamos para limites superiores (1.0). O terceiro argumento � o coeficiente objetivo linear(0.0). O quarto argumento � o tipo da variavel (GRB BINARY). O quinto argumento � o nome da variavel.   
		    GRBVar y = model.addVar(0.0, 1.0, 0.0, GRB.BINARY, "y"); //Cria��o de variavel y = quantidade de cintos feitos por hora, o metodo addVar(), o primeiro argumento usamos os limites inferiores (0.0) e o segundo argumento usamos para limites superiores (1.0). O terceiro argumento � o coeficiente objetivo linear(0.0). O quarto argumento � o tipo da variavel (GRB BINARY). O quinto argumento � o nome da variavel. 
		    
		    // A express�o de maximiza��o � 5x + 2y
		    GRBLinExpr expr = new GRBLinExpr(); // Instanciamos um classe para fun��o linear vazia para ser adicionar as variaveis.
		    expr.addTerm(5.0, x); //O metodo .addTerm() � para colocar o valor da constante da variavel x (sapato).
		    expr.addTerm(2.0, y); //O metodo .addTerm() � para colocar o valor da constante da variavel y (cinto).
		    model.setObjective(expr, GRB.MAXIMIZE); //O metodo setObjective() � para definirmos qual o sentido para otimizar, preceba que usamos o objetivo de Maximizar(MAXMIZE) para o problema.
		    
		    // A primeira restri��o � do tempo 10x + 12y <= 60
		    expr = new GRBLinExpr(); // Instanciamos a classe para a fun��o linear.
		    expr.addTerm(10.0, x); // O metodo .addTerm() � para colocarmos o valor da constante de x (sapato) das restri��o.
		    expr.addTerm(12.0, y); // O metodo .addTerm() � para colocarmos o valor da constante de y (cinto) das restri��o.
		    model.addConstr(expr, GRB.LESS_EQUAL, 60.0, "c0"); // O metodo .addConstr() para adicionarmos a restri��o a um modelo espercifico. O primeiro argumento � o lado esquerdo da fun��o (o que vem antes do operador de maior, menor ou igual). O segundo argumento � qual o sentido da restri��o, aqui usamos o GRB_LESS_EQUAL (<=). O terceiro argumento � o lado direito da fun��o.
		    
		    // A segunda restri��o � do material (couro) 2x + y <= 6
		    expr = new GRBLinExpr(); // Instanciamos a classe para a fun��o linear.
		    expr.addTerm(2.0, x); // O metodo .addTerm() � para colocarmos o valor da constante de x (sapato) das restri��o.
		    expr.addTerm(1.0, y); // O metodo .addTerm() � para colocarmos o valor da constante de y (cinto) das restri��o.
		    model.addConstr(expr, GRB.LESS_EQUAL, 6.0, "c1"); // O metodo .addConstr() para adicionarmos a restri��o a um modelo espercifico. O primeiro argumento � o lado esquerdo da fun��o (o que vem antes do operador de maior, menor ou igual). O segundo argumento � qual o sentido da restri��o, aqui usamos o GRB_LESS_EQUAL (<=). O terceiro argumento � o lado direito da fun��o.
		    
		    model.optimize();// O modelo j� foi construido. Com isso, usando o metodo .optimize() vai realizar a rotina de otimiza��o e usar os valores dos atributos preenchidos (incluido qual o tipo de otimiza��o).

		    //Parte que exibe os resultados.
		      System.out.println(x.get(GRB.StringAttr.VarName)
		                         + " " +x.get(GRB.DoubleAttr.X));
		      System.out.println(y.get(GRB.StringAttr.VarName)
		                         + " " +y.get(GRB.DoubleAttr.X));
		    
		      System.out.println("Obj: " + model.get(GRB.DoubleAttr.ObjVal));

		      //Esses metodos finais, s�o para fechar o ambiente para n�o haver e carregar lixo.
		      model.dispose();
		      env.dispose();
		} catch (GRBException e) {//Tratamento e exibi��o caso ocorra erro.
			System.out.println("Error code: " + e.getErrorCode() + ". " + e.getMessage());
		}
	}

}
