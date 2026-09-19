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

	// Exercício 16
    // Retorna a categoria de conexidade: 3 (C3), 2 (C2), 1 (C1) ou 0 (C0)
    public int categoriaConexidade() {
        if (this.n == 0) return 0;

        // Matrizes auxiliares para o Algoritmo de Warshall
        // 'reach' vê se existe caminho respeitando a direção
        // 'undirReach' vê se existe caminho ignorando a direção (como se fosse ND)
        boolean[][] reach = new boolean[this.n][this.n];
        boolean[][] undirReach = new boolean[this.n][this.n];

        // 1. Inicializa as matrizes com os dados das arestas diretas
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (i == j) {
                    reach[i][j] = true;
                    undirReach[i][j] = true;
                } else {
                    reach[i][j] = (this.adj[i][j] != 0);
                    undirReach[i][j] = (this.adj[i][j] != 0 || this.adj[j][i] != 0);
                }
            }
        }

        // 2. Algoritmo de Warshall: Descobre se existe caminho indireto passando por 'k'
        for (int k = 0; k < this.n; k++) {
            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < this.n; j++) {
                    reach[i][j] = reach[i][j] || (reach[i][k] && reach[k][j]);
                    undirReach[i][j] = undirReach[i][j] || (undirReach[i][k] && undirReach[k][j]);
                }
            }
        }

        // 3. Analisa as matrizes para classificar o grafo
        boolean isC3 = true;
        boolean isC2 = true;
        boolean isC1 = true;

        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (!reach[i][j]) isC3 = false; // Se faltar um caminho, não é C3
                if (!reach[i][j] && !reach[j][i]) isC2 = false; // Se não tem ida NEM volta, não é C2
                if (!undirReach[i][j]) isC1 = false; // Se ignorando direção ainda não conecta, não é C1
            }
        }

        // Retorna a maior categoria que o grafo atendeu
        if (isC3) return 3;
        if (isC2) return 2;
        if (isC1) return 1;
        return 0; // Se reprovou em todas, é C0 (desconexo)
    }
	
	// Exercício 17
    // Retorna o grafo reduzido de um grafo direcionado no formato de matriz de adjacência
    public int[][] grafoReduzido() {
        if (this.n == 0) return new int[0][0];

        // 1. Calcula a matriz de alcançabilidade (Algoritmo de Warshall)
        boolean[][] reach = new boolean[this.n][this.n];
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (i == j) reach[i][j] = true;
                else reach[i][j] = (this.adj[i][j] != 0);
            }
        }
        for (int k = 0; k < this.n; k++) {
            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < this.n; j++) {
                    reach[i][j] = reach[i][j] || (reach[i][k] && reach[k][j]);
                }
            }
        }

        // 2. Identifica os agrupamentos (Componentes Fortemente Conexos)
        int[] scc = new int[this.n]; // Guarda o ID do "super-vértice" ao qual cada vértice original pertence
        for (int i = 0; i < this.n; i++) scc[i] = -1; // -1 significa ainda não agrupado

        int numScc = 0; // Vai contar quantos "super-vértices" teremos no final
        for (int i = 0; i < this.n; i++) {
            if (scc[i] == -1) {
                scc[i] = numScc; // O vértice i forma um novo grupo
                // Procura todos os outros vértices que fecham ciclo com o 'i'
                for (int j = i + 1; j < this.n; j++) {
                    if (reach[i][j] && reach[j][i]) {
                        scc[j] = numScc; // Se vão e voltam, entram no mesmo grupo!
                    }
                }
                numScc++; // Avança para o próximo ID de agrupamento
            }
        }

        // 3. Constrói a nova matriz de adjacência do grafo reduzido
        int[][] reduzido = new int[numScc][numScc];

        // 4. Preenche as arestas do novo grafo
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (this.adj[i][j] != 0) { // Se existia aresta no grafo original
                    int origemScc = scc[i];
                    int destinoScc = scc[j];
                    
                    // Adiciona a ligação apenas se estiverem em "super-vértices" diferentes
                    if (origemScc != destinoScc) {
                        reduzido[origemScc][destinoScc] = 1;
                    }
                }
            }
        }

        return reduzido;
    }


}
