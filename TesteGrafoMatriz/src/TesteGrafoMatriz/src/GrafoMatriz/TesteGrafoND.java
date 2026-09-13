package GrafoMatriz;

public class TesteGrafoND {
    public static void main(String[] args) {

        System.out.println("=== Exercício 8: Grafo G4 não-dirigido ===");
        TGrafoND g = new TGrafoND(4);
        g.insereA(0, 1);
        g.insereA(0, 2);
        g.insereA(1, 2);
        g.insereA(1, 3);
        g.insereA(2, 3);
        g.show();

        System.out.println("\n--- Exercício 9: degree ---");
        for (int v = 0; v < 4; v++)
            System.out.println("degree(" + v + ") = " + g.degree(v));
    }
}
