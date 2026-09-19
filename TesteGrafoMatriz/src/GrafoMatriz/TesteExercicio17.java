package GrafoMatriz;

public class TesteExercicio17 {
    public static void main(String[] args) {
        System.out.println("--- Exercício 17: grafoReduzido ---");

        // Grafo com dois ciclos (duas SCCs) e uma cauda:
        // SCC1 = {0,1} (0->1, 1->0)
        // SCC2 = {2,3} (2->3, 3->2)
        // e uma aresta de saída de SCC2 para o vértice 4 (sozinho)
        TGrafo g = new TGrafo(5);
        g.insereA(0, 1);
        g.insereA(1, 0);
        g.insereA(1, 2);
        g.insereA(2, 3);
        g.insereA(3, 2);
        g.insereA(3, 4);

        System.out.println("Grafo original:");
        g.show();

        int[][] reduzido = g.grafoReduzido();

        System.out.println("\nGrafo reduzido (deve ter 3 super-vértices: {0,1}, {2,3}, {4}):");
        System.out.println("Quantidade de super-vértices: " + reduzido.length);
        for (int i = 0; i < reduzido.length; i++) {
            for (int j = 0; j < reduzido.length; j++)
                System.out.print(reduzido[i][j] + " ");
            System.out.println();
        }
    }
}
