package GrafoMatriz;

public class TesteGrafoPonderado {
    public static void main(String[] args) {
        System.out.println("=== Exercício 10: Grafo direcionado rotulado (pesos float) ===");
        TGrafoPonderado g = new TGrafoPonderado(4);
        g.insereA(0, 1, 2.5f);
        g.insereA(0, 2, 1.0f);
        g.insereA(2, 1, 4.2f);
        g.insereA(2, 3, 7.1f);
        g.insereA(1, 3, 3.3f);
        g.show();

        System.out.println("\nexisteAresta(0,1)? " + g.existeAresta(0, 1));
        System.out.println("existeAresta(1,0)? " + g.existeAresta(1, 0) + " (não existe -> peso = infinito)");

        System.out.println("\nRemovendo a aresta (2,3):");
        g.removeA(2, 3);
        g.show();
    }
}
