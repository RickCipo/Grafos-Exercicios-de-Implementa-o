package GrafoLista;

public class TesteExercicios18a30 {
	public static void main(String[] args) {

		System.out.println("=== Grafo G1 (0->1,0->2,0->3,2->1,2->3,1->3) ===");
		TGrafo g = new TGrafo(4);
		g.insereA(0, 1);
		g.insereA(0, 2);
		g.insereA(0, 3);
		g.insereA(2, 1);
		g.insereA(2, 3);
		g.insereA(1, 3);
		g.show();

		System.out.println("\n--- Exercício 18: inDegree ---");
		for (int v = 0; v < 4; v++)
			System.out.println("inDegree(" + v + ") = " + g.inDegree(v));

		System.out.println("\n--- Exercício 19: outDegree ---");
		for (int v = 0; v < 4; v++)
			System.out.println("outDegree(" + v + ") = " + g.outDegree(v));

		System.out.println("\n--- Exercício 20: degree ---");
		for (int v = 0; v < 4; v++)
			System.out.println("degree(" + v + ") = " + g.degree(v));

		System.out.println("\n--- Exercício 21: saoIguais ---");
		TGrafo g2 = new TGrafo(4);
		g2.insereA(0, 1); g2.insereA(0, 2); g2.insereA(0, 3);
		g2.insereA(2, 1); g2.insereA(2, 3); g2.insereA(1, 3);
		System.out.println("g igual a g2 (mesmas arestas)? " + g.saoIguais(g2));
		TGrafo g3 = new TGrafo(4);
		g3.insereA(0, 1);
		System.out.println("g igual a g3 (arestas diferentes)? " + g.saoIguais(g3));

		System.out.println("\n--- Exercício 22: converterParaLista (a partir de matriz) ---");
		int[][] matriz = {
			{0, 1, 1, 0},
			{0, 0, 0, 1},
			{0, 1, 0, 1},
			{0, 0, 0, 0}
		};
		TGrafo gConvertido = TGrafo.converterParaLista(matriz, 4);
		gConvertido.show();

		System.out.println("\n--- Exercício 23: inverterListas ---");
		System.out.println("Antes de inverter:");
		g.show();
		g.inverterListas();
		System.out.println("Depois de inverter:");
		g.show();
		g.inverterListas(); // desfaz, pra não afetar os testes seguintes

		System.out.println("\n--- Exercício 24: ehFonte ---");
		for (int v = 0; v < 4; v++)
			System.out.println("ehFonte(" + v + ") = " + g.ehFonte(v));

		System.out.println("\n--- Exercício 25: ehSorvedouro ---");
		for (int v = 0; v < 4; v++)
			System.out.println("ehSorvedouro(" + v + ") = " + g.ehSorvedouro(v));

		System.out.println("\n--- Exercício 26: ehSimetrico ---");
		System.out.println("ehSimetrico() = " + g.ehSimetrico());

		System.out.println("\n--- Exercício 27: carregarGrafo (a partir de arquivo) ---");
		TGrafo gArquivo = TGrafo.carregarGrafo("dados/grafo.txt");
		if (gArquivo != null) gArquivo.show();
		else System.out.println("Não foi possível carregar o arquivo.");

		System.out.println("\n--- Exercício 29: removerVertice (dirigido) ---");
		TGrafo gRemove = new TGrafo(4);
		gRemove.insereA(0, 1); gRemove.insereA(0, 2); gRemove.insereA(0, 3);
		gRemove.insereA(2, 1); gRemove.insereA(2, 3); gRemove.insereA(1, 3);
		TGrafo gSemVertice1 = gRemove.removerVertice(1);
		gSemVertice1.show();

		System.out.println("\n--- Exercício 30: ehCompleto ---");
		System.out.println("g (dirigido) é completo? " + g.ehCompleto(true));
		TGrafo k3 = new TGrafo(3);
		k3.insereA(0, 1); k3.insereA(0, 2);
		k3.insereA(1, 0); k3.insereA(1, 2);
		k3.insereA(2, 0); k3.insereA(2, 1);
		System.out.println("K3 dirigido é completo? " + k3.ehCompleto(true));

		System.out.println("\n=== Grafo não-dirigido (exercício 28) ===");
		TGrafoND gnd = new TGrafoND(4);
		gnd.insereA(0, 1);
		gnd.insereA(0, 2);
		gnd.insereA(1, 2);
		gnd.insereA(1, 3);
		gnd.insereA(2, 3);
		gnd.show();

		System.out.println("\n--- Exercício 28: removerVertice (não-dirigido) ---");
		TGrafoND gndSemVertice1 = gnd.removerVertice(1);
		gndSemVertice1.show();

		System.out.println("\n--- Exercício 30 (versão não-dirigida): ehCompleto ---");
		TGrafoND k4 = new TGrafoND(4);
		for (int i = 0; i < 4; i++)
			for (int j = i + 1; j < 4; j++)
				k4.insereA(i, j);
		System.out.println("K4 não-dirigido é completo? " + k4.ehCompleto());
		System.out.println("gnd é completo? " + gnd.ehCompleto());
	}
}
