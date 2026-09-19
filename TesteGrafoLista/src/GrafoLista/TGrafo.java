package GrafoLista;

import java.io.File;
import java.util.Scanner;

//definicao da classe de nós da lista
class TNo{ //define uma struct (registro)
	public	int w;  //vértice que é adjacente ao elemento da lista
	public TNo prox;
}

//definição de uma classe para armezanar um grafo
public class TGrafo{
	// atributos privados
	private	int n; // quantidade de vértices
	private	int m; // quantidade de arestas
	private	TNo adj[]; // um vetor onde cada entrada guarda o inicio de uma lista
	// métodos públicos
	// Construtor do grafo com a lista de
	// adjacência
	public TGrafo( int n ) {
	    // aloca a estrutura TGrafo
	    this.n = n;
	    this.m = 0;
	    // aloca m vetor para guardar lista de adjacencias
	    TNo adjac[] = new TNo[n];
	    // Inicia o vetor com nullL
		for(int i = 0; i< n; i++)
			adjac[i]=null;	
	    this.adj = adjac;
	};
	
	//Método que cria uma aresta v-w no grafo. O método supõe que v e w são distintos, positivos e menores que V.
	//Se o grafo já tem a aresta v-w, o método não faz nada. O método também atualiza a quantidade de arestas no grafo.
	
	public void insereA( int v, int w) {
		
	    TNo novoNo;
	    // anda na lista para chegar ao final
	    TNo no = adj[v];
	    TNo ant = null;
	    // anda na lista enquanto no != NULL E w  > no->w
	    while( no != null && w >= no.w ){
	        if( w == no.w)
	            return;
	        ant = no;
	        no = no.prox;
	    }
	    // cria o novo No para guardar w
	    novoNo = new TNo();
	    novoNo.w = w;
	    novoNo.prox = no;
	    // atualiza a lista
	    if( ant == null){
	        // insere no inicio
	        adj[v] = novoNo;
	    } else
	        // insere no final
	        ant.prox = novoNo;
	    m++;	
	}
	
	//Método que remove do grafo a aresta que tem ponta inicial v e ponta final w. O método supõe que v e w são distintos,
	//positivos e menores que V. Se não existe a aresta v-w, o método não faz nada. O método também atualiza a quantidade de arestas no grafo.
	public void removeA( int v, int w) {
	    // obtém o início da lista do vértice v
	    TNo no = adj[v];
	    TNo ant = null;
	    // percorre a lista do vértice v
	    // procurando w (se adjacente)
	    while( no != null && no.w != w ){
	    		ant = no;
	    		no = no.prox;
	    }
	    // se w é adjacente, remove da lista de v
	    if (no != null){
	    	ant.prox = no.prox;
	    	no = null;
	    	m--;
		}	
	}

	//para cada vértice v do grafo, este método imprime, em uma linha, todos os vértices adjacentes ao vértice v (vizinhos ao vértice v).
	public void show() {
	    System.out.print("n: " + n);
	    System.out.print("\nm: " + m + "\n");
	    for( int i=0; i < n; i++){
	    	System.out.print("\n" + i + ": ");
	        //percorre a lista na posição i do vetor
	        TNo no = adj[i];
	        while( no != null ){
	        	System.out.print(no.w + " ");
	            no = no.prox;
	        }
	    }
	    System.out.print("\n\nfim da impressao do grafo.\n");
	}

	public int getN() { return n; }
	public int getM() { return m; }

	//Exercício 18
	//grau de entrada de v: percorre todas as listas contando quantas vezes v aparece
	public int inDegree(int v) {
		int grau = 0;
		for (int i = 0; i < n; i++) {
			TNo no = adj[i];
			while (no != null) {
				if (no.w == v) grau++;
				no = no.prox;
			}
		}
		return grau;
	}

	//Exercício 19
	//grau de saída de v: tamanho da lista adj[v]
	public int outDegree(int v) {
		int grau = 0;
		TNo no = adj[v];
		while (no != null) {
			grau++;
			no = no.prox;
		}
		return grau;
	}

	//Exercício 20
	public int degree(int v) {
		return inDegree(v) + outDegree(v);
	}

	//Exercício 21
	//verifica se dois grafos direcionados (em lista de adjacência) são iguais
	public boolean saoIguais(TGrafo g2) {
		if (this.n != g2.n || this.m != g2.m) return false;
		for (int i = 0; i < n; i++) {
			TNo no1 = this.adj[i];
			TNo no2 = g2.adj[i];
			while (no1 != null && no2 != null) {
				if (no1.w != no2.w) return false;
				no1 = no1.prox;
				no2 = no2.prox;
			}
			if (no1 != null || no2 != null) return false;
		}
		return true;
	}

	//Exercício 22
	//converte um grafo armazenado como matriz de adjacência (int[n][n]) em um grafo representado como lista de adjacência.
	public static TGrafo converterParaLista(int[][] matrizAdj, int n) {
		TGrafo gList = new TGrafo(n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (matrizAdj[i][j] == 1) {
					gList.insereA(i, j);
				}
			}
		}
		return gList;
	}

	//Exercício 23
	//inverte a ordem de todas as listas de adjacência do grafo
	public void inverterListas() {
		for (int i = 0; i < n; i++) {
			TNo atual = adj[i];
			TNo anterior = null;
			TNo proximo = null;
			while (atual != null) {
				proximo = atual.prox;
				atual.prox = anterior;
				anterior = atual;
				atual = proximo;
			}
			adj[i] = anterior;
		}
	}

	//Exercício 24
	//v é fonte se tem grau de saída > 0 e grau de entrada == 0
	public boolean ehFonte(int v) {
		if (adj[v] == null) return false; // sem arestas saindo -> não é fonte
		for (int i = 0; i < n; i++) {
			TNo aux = adj[i];
			while (aux != null) {
				if (aux.w == v) return false; // alguém chega em v -> não é fonte
				aux = aux.prox;
			}
		}
		return true;
	}

	//Exercício 25
	//v é sorvedouro se tem grau de saída == 0 e grau de entrada > 0
	public boolean ehSorvedouro(int v) {
		if (adj[v] != null) return false; // tem arestas saindo -> não é sorvedouro
		int grauEntrada = 0;
		for (int i = 0; i < n; i++) {
			TNo aux = adj[i];
			while (aux != null) {
				if (aux.w == v) {
					grauEntrada++;
					break;
				}
				aux = aux.prox;
			}
		}
		return grauEntrada > 0;
	}

	//Exercício 26
	//grafo simétrico: para toda aresta i->j existe também j->i
	public boolean ehSimetrico() {
		for (int i = 0; i < n; i++) {
			TNo no = adj[i];
			while (no != null) {
				int vizinho = no.w;
				boolean temVolta = false;
				TNo aux = adj[vizinho];
				while (aux != null) {
					if (aux.w == i) {
						temVolta = true;
						break;
					}
					aux = aux.prox;
				}
				if (!temVolta) return false;
				no = no.prox;
			}
		}
		return true;
	}

	//Exercício 27
	// Constrói um grafo (lista de adjacência) a partir de um arquivo no formato: linha 1: V, linha 2: A, demais linhas: "v w"
	public static TGrafo carregarGrafo(String nomeArquivo) {
		try (Scanner sc = new Scanner(new File(nomeArquivo))) {
			if (!sc.hasNextInt()) return null;
			int vertices = sc.nextInt();
			int arestas = sc.nextInt();
			TGrafo g = new TGrafo(vertices);
			for (int i = 0; i < arestas; i++) {
				g.insereA(sc.nextInt(), sc.nextInt());
			}
			return g;
		} catch (Exception e) {
			return null;
		}
	}

	//Exercício 29
	//remove um vértice v (grafo dirigido) e retorna um NOVO grafo já sem v e com os vértices seguintes reindexados.
	public TGrafo removerVertice(int v) {
		TGrafo novoGrafo = new TGrafo(n - 1);
		for (int i = 0; i < n; i++) {
			if (i == v) continue;
			int novoI = (i > v) ? i - 1 : i;
			TNo no = adj[i];
			while (no != null) {
				if (no.w != v) {
					int novoW = (no.w > v) ? no.w - 1 : no.w;
					novoGrafo.insereA(novoI, novoW);
				}
				no = no.prox;
			}
		}
		return novoGrafo;
	}

	//Exercício 30
	//verifica se o grafo é completo. Para dirigido, o máximo de arestas é n*(n-1); para não-dirigido seria a metade disso.
	public boolean ehCompleto(boolean ehDirigido) {
		int arestasMaximas = n * (n - 1);
		if (!ehDirigido) {
			arestasMaximas /= 2;
		}
		return this.m == arestasMaximas;
	}
}
