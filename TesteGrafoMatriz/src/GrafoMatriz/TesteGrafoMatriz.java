package GrafoMatriz;

import java.io.IOException;

public class TesteGrafoMatriz {
	public static void main(String args[]) throws IOException {
		//  chama o construtor para criar um grafo 4x4
		TGrafo g = new TGrafo(4);
		//insere as arestas do grafo
		//A={(0,1),(0,2),(2,1),(2,3),(1,3)}
		g.insereA(0 , 1);
		g.insereA(0,2);
		g.insereA(2,1);
		g.insereA(2,3);
		g.insereA(1,3);
		// mostra o grafo preenchido
		g.show();

		System.out.println("\n--- Exercício 1: inDegree ---");
		for (int v = 0; v < 4; v++)
			System.out.println("inDegree(" + v + ") = " + g.inDegree(v));

		System.out.println("\n--- Exercício 2: outDegree ---");
		for (int v = 0; v < 4; v++)
			System.out.println("outDegree(" + v + ") = " + g.outDegree(v));

		System.out.println("\n--- Exercício 3: degree ---");
		for (int v = 0; v < 4; v++)
			System.out.println("degree(" + v + ") = " + g.degree(v));

		System.out.println("\n--- Exercício 4: isFonte ---");
		for (int v = 0; v < 4; v++)
			System.out.println("isFonte(" + v + ") = " + g.isFonte(v));

		System.out.println("\n--- Exercício 5: isSorvedouro ---");
		for (int v = 0; v < 4; v++)
			System.out.println("isSorvedouro(" + v + ") = " + g.isSorvedouro(v));

		System.out.println("\n--- Exercício 6: isSimetrico ---");
		System.out.println("isSimetrico() = " + g.isSimetrico());

		System.out.println("\n--- Exercício 7: construir grafo a partir de arquivo ---");
		TGrafo gArquivo = new TGrafo("dados/grafo.txt");
		gArquivo.show();
	}
}
