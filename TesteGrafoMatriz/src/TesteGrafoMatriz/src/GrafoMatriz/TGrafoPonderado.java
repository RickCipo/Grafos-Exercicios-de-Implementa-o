package GrafoMatriz;

//Exercício 10
// Grafo DIRECIONADO ROTULADO (arestas com peso float).
// Ausência de aresta é representada por Float.POSITIVE_INFINITY.
public class TGrafoPonderado {
    private int n;          // quantidade de vértices
    private int m;          // quantidade de arestas
    private float adj[][];  // matriz de adjacência com os pesos

    public TGrafoPonderado(int n) {
        this.n = n;
        this.m = 0;
        this.adj = new float[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                this.adj[i][j] = Float.POSITIVE_INFINITY; // sem aresta
    }

    // Insere uma aresta v->w com peso "peso"
    public void insereA(int v, int w, float peso) {
        if (adj[v][w] == Float.POSITIVE_INFINITY) {
            m++; // nova aresta
        }
        adj[v][w] = peso;
    }

    // remove a aresta v->w (volta a ser "infinito")
    public void removeA(int v, int w) {
        if (adj[v][w] != Float.POSITIVE_INFINITY) {
            adj[v][w] = Float.POSITIVE_INFINITY;
            m--;
        }
    }

    // Verifica se existe aresta v->w
    public boolean existeAresta(int v, int w) {
        return adj[v][w] != Float.POSITIVE_INFINITY;
    }

    public void show() {
        System.out.println("n: " + n);
        System.out.println("m: " + m);
        for (int i = 0; i < n; i++) {
            System.out.print("\n");
            for (int w = 0; w < n; w++) {
                if (adj[i][w] == Float.POSITIVE_INFINITY)
                    System.out.print("Adj[" + i + "," + w + "]= inf   ");
                else
                    System.out.print("Adj[" + i + "," + w + "]= " + adj[i][w] + "   ");
            }
        }
        System.out.println("\n\nfim da impressao do grafo.");
    }
}
