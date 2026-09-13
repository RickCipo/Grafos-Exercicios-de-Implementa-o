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
}
