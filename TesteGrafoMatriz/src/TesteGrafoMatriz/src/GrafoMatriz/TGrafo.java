package GrafoMatriz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

//definição de uma estrutura Matriz de Adjacência para armezanar um grafo
public class TGrafo {
	// Atributos Privados
	private	int n; // quantidade de vértices
	private	int m; // quantidade de arestas
	private	int adj[][]; //matriz de adjacência
	// Métodos Públicos
	public TGrafo( int n) {  // construtor
	    this.n = n;
	    // No início dos tempos não há arestas
	    this.m = 0; 
	    // alocação da matriz do TGrafo
	    this.adj = new int [n][n];

	    // Inicia a matriz com zeros
		for(int i = 0; i< n; i++)
			for(int j = 0; j< n; j++)
				this.adj[i][j]=0;	
	}

	//Exercício 7 
	//Construtor que lê o grafo de um arquivo texto no formato:
	//linha 1: V (qtd de vértices), linha 2: A (qtd de arestas),
	//demais linhas: "v w" (uma aresta por linha)
	public TGrafo(String arquivo) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(arquivo));
		int v = Integer.parseInt(in.readLine().trim());
		int a = Integer.parseInt(in.readLine().trim());

		this.n = v;
		this.m = 0;
		this.adj = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				this.adj[i][j] = 0;

		for (int i = 0; i < a; i++) {
			String linha = in.readLine();
			if (linha == null) break;
			StringTokenizer st = new StringTokenizer(linha);
			int origem = Integer.parseInt(st.nextToken());
			int destino = Integer.parseInt(st.nextToken());
			insereA(origem, destino);
		}
		in.close();
	}

	// Insere uma aresta no Grafo tal que
	// v é adjacente a w
	public void insereA(int v, int w) {
	    // testa se nao temos a aresta
	    if(adj[v][w] == 0 ){
	        adj[v][w] = 1;
	        m++; // atualiza qtd arestas
	    }
	}
	
	// remove uma aresta v->w do Grafo	
	public void removeA(int v, int w) {
	    // testa se temos a aresta
	    if(adj[v][w] == 1 ){
	        adj[v][w] = 0;
	        m--; // atualiza qtd arestas
	    }
	}
	// Apresenta o Grafo contendo
	// número de vértices, arestas
	// e a matriz de adjacência obtida	
	public void show() {
	    System.out.println("n: " + n );
	    System.out.println("m: " + m );
	    for( int i=0; i < n; i++){
	    	System.out.print("\n");
	        for( int w=0; w < n; w++)
	            if(adj[i][w] == 1)
	            	System.out.print("Adj[" + i + "," + w + "]= 1" + " ");
	            else System.out.print("Adj[" + i + "," + w + "]= 0" + " ");
	    }
	    System.out.println("\n\nfim da impressao do grafo." );
	}

	//Exercício 1
	// Grau de entrada de v: total de arestas que chegam em v
	public int inDegree(int v) {
		int grau = 0;
		for (int i = 0; i < n; i++)
			if (adj[i][v] == 1)
				grau++;
		return grau;
	}

	//Exercício 2
	// Grau de saída de v: total de arestas que saem de v
	public int outDegree(int v) {
		int grau = 0;
		for (int j = 0; j < n; j++)
			if (adj[v][j] == 1)
				grau++;
		return grau;
	}

	//Exercício 3
	// Grau do vértice v (grafo dirigido) = grau de entrada + grau de saída
	public int degree(int v) {
		return inDegree(v) + outDegree(v);
	}

	//Exercício 4
	// Retorna 1 se v for fonte (outDegree>0 e inDegree==0), 0 caso contrário
	public int isFonte(int v) {
		return (outDegree(v) > 0 && inDegree(v) == 0) ? 1 : 0;
	}

	//Exercício 5
	// Retorna 1 se v for sorvedouro (inDegree>0 e outDegree==0), 0 caso contrário
	public int isSorvedouro(int v) {
		return (inDegree(v) > 0 && outDegree(v) == 0) ? 1 : 0;
	}

	//Exercício 6
	// Retorna 1 se o grafo dirigido for simétrico (adj[i][j]==adj[j][i] para todo i,j)
	public int isSimetrico() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (adj[i][j] != adj[j][i])
					return 0;
		return 1;
	}
	// Exercício 13
    // Verifica e retorna se o grafo dirigido é completo
    public boolean isCompleto() {
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                // Em grafos dirigidos completos, precisamos de arestas indo e voltando.
                // Portanto, todas as células (exceto a diagonal principal) devem ser 1.
                if (i != j && this.adj[i][j] == 0) {
                    return false; // Se faltar uma única aresta, já não é completo
                }
            }
        }
        return true; 
    }
}
