package GrafoMatriz;

//Exercício 8
// Grafo NÃO-DIRECIONADO representado como matriz de adjacência
public class TGrafoND {
    private int n;      // quantidade de vértices
    private int m;      // quantidade de arestas
    private int adj[][]; // matriz de adjacência (simétrica)

    public TGrafoND(int n) {
        this.n = n;
        this.m = 0;
        this.adj = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                this.adj[i][j] = 0;
    }

    // Insere a aresta {v,w}: marca as duas posições simétricas
    public void insereA(int v, int w) {
        if (adj[v][w] == 0) {
            adj[v][w] = 1;
            adj[w][v] = 1;
            m++;
        }
    }

    // remove a aresta {v,w}
    public void removeA(int v, int w) {
        if (adj[v][w] == 1) {
            adj[v][w] = 0;
            adj[w][v] = 0;
            m--;
        }
    }

    public void show() {
        System.out.println("n: " + n);
        System.out.println("m: " + m);
        for (int i = 0; i < n; i++) {
            System.out.print("\n");
            for (int w = 0; w < n; w++)
                System.out.print("Adj[" + i + "," + w + "]= " + adj[i][w] + " ");
        }
        System.out.println("\n\nfim da impressao do grafo.");
    }

    // Exercício 9
    // Grau do vértice v em grafo não-dirigido: total de arestas incidentes em v
    public int degree(int v) {
        int grau = 0;
        for (int j = 0; j < n; j++)
            if (adj[v][j] == 1)
                grau++;
        return grau;
    }
    //Exercício 11
    public void removeVertice(int v) {
    int novoN = n - 1;
    int novoAdj[][] = new int[novoN][novoN];
    int novoM = 0;

    int linha = 0;
    for (int i = 0; i < n; i++) {
        if (i == v) continue;
        int coluna = 0;
        for (int j = 0; j < n; j++) {
            if (j == v) continue;
            novoAdj[linha][coluna] = adj[i][j];
            coluna++;
        }
        linha++;
    }

    for (int i = 0; i < novoN; i++)
        for (int j = 0; j < novoN; j++)
            if (novoAdj[i][j] == 1) novoM++;
    novoM /= 2;

    this.n = novoN;
    this.adj = novoAdj;
    this.m = novoM;
}
// Exercício 12
    // Verifica e retorna se o grafo não-dirigido é completo
    public boolean isCompleto() {
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                // Se a linha for diferente da coluna (i != j) e não houver aresta (adj[i][j] == 0)
                if (i != j && this.adj[i][j] == 0) {
                    return false; // Achou dois vértices sem conexão, então NÃO é completo
                }
            }
        }
        return true; // Se varreu a matriz inteira e não achou nenhum 0 fora da diagonal, É completo
    }
// Exercício 14
    // Retorna o grafo complementar na forma de uma matriz de adjacência
    public int[][] getComplemento() {
        // Cria uma nova matriz do mesmo tamanho do grafo original
        int[][] matrizComplementar = new int[this.n][this.n];
        
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (i == j) {
                    // A diagonal principal continua 0 (sem auto-loops)
                    matrizComplementar[i][j] = 0;
                } else {
                    // Se era 0, vira 1. Se era 1, vira 0.
                    matrizComplementar[i][j] = (this.adj[i][j] == 0) ? 1 : 0;
                }
            }
        }
        
        return matrizComplementar;
    }
// Exercício 15
    // Retorna o tipo de conexidade: 0 (conexo) ou 1 (desconexo)
    public int tipoConexidade() {
        if (this.n == 0) return 0; // Prevenção para grafo vazio
        
        boolean[] visitados = new boolean[this.n];
        
        // Inicia a busca a partir do vértice 0
        buscaEmProfundidade(0, visitados);
        
        // Verifica se algum vértice não foi visitado
        for (int i = 0; i < this.n; i++) {
            if (!visitados[i]) {
                return 1; // Se achou algum não visitado, é DESCONEXO (retorna 1)
            }
        }
        return 0; // Se todos foram visitados, é CONEXO (retorna 0)
    }

    // Método auxiliar para fazer a Busca em Profundidade (DFS)
    private void buscaEmProfundidade(int v, boolean[] visitados) {
        visitados[v] = true; // Marca o vértice atual como visitado
        for (int i = 0; i < this.n; i++) {
            // Se existe aresta e o vizinho ainda não foi visitado, avança para ele
            if (this.adj[v][i] != 0 && !visitados[i]) {
                buscaEmProfundidade(i, visitados);
            }
        }
    }
}