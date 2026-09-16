package GrafoMatriz;

public class TesteExercicios11a16 {
    public static void main(String[] args) {

        System.out.println("--- Exercício 13: isCompleto (grafo dirigido) ---");
        TGrafo g1 = new TGrafo(4);
        g1.insereA(0, 1);
        g1.insereA(0, 2);
        g1.insereA(2, 1);
        g1.insereA(2, 3);
        g1.insereA(1, 3);
        g1.show();
        System.out.println("g1 é completo? " + g1.isCompleto());

        TGrafo k3 = new TGrafo(3);
        k3.insereA(0, 1); k3.insereA(0, 2);
        k3.insereA(1, 0); k3.insereA(1, 2);
        k3.insereA(2, 0); k3.insereA(2, 1);
        System.out.println("K3 dirigido é completo? " + k3.isCompleto());

        System.out.println("\n--- Exercício 16: categoriaConexidade (grafo dirigido) ---");
        System.out.println("g1 -> categoria: " + g1.categoriaConexidade());

        TGrafo ciclo = new TGrafo(3);
        ciclo.insereA(0, 1);
        ciclo.insereA(1, 2);
        ciclo.insereA(2, 0);
        System.out.println("Ciclo 0->1->2->0 -> categoria (deve ser 3, fortemente conexo): " + ciclo.categoriaConexidade());

        System.out.println("\n--- Exercício 11: removeVertice (grafo não-dirigido) ---");
        TGrafoND gnd = new TGrafoND(4);
        gnd.insereA(0, 1);
        gnd.insereA(0, 2);
        gnd.insereA(1, 2);
        gnd.insereA(1, 3);
        gnd.insereA(2, 3);
        System.out.println("Antes de remover o vértice 1:");
        gnd.show();
        gnd.removeVertice(1);
        System.out.println("Depois de remover o vértice 1:");
        gnd.show();

        System.out.println("\n--- Exercício 12: isCompleto (grafo não-dirigido) ---");
        TGrafoND k4 = new TGrafoND(4);
        for (int i = 0; i < 4; i++)
            for (int j = i + 1; j < 4; j++)
                k4.insereA(i, j);
        System.out.println("K4 é completo? " + k4.isCompleto());

        TGrafoND naoCompleto = new TGrafoND(4);
        naoCompleto.insereA(0, 1);
        naoCompleto.insereA(1, 2);
        System.out.println("Grafo esparso é completo? " + naoCompleto.isCompleto());

        System.out.println("\n--- Exercício 14: getComplemento (grafo não-dirigido) ---");
        TGrafoND g4 = new TGrafoND(4);
        g4.insereA(0, 1);
        g4.insereA(0, 2);
        g4.insereA(1, 2);
        g4.insereA(1, 3);
        g4.insereA(2, 3);
        int[][] comp = g4.getComplemento();
        System.out.println("Matriz complementar de g4:");
        for (int i = 0; i < comp.length; i++) {
            for (int j = 0; j < comp.length; j++)
                System.out.print(comp[i][j] + " ");
            System.out.println();
        }

        System.out.println("\n--- Exercício 15: tipoConexidade (grafo não-dirigido) ---");
        System.out.println("g4 (0=conexo, 1=desconexo): " + g4.tipoConexidade());

        TGrafoND desconexo = new TGrafoND(4);
        desconexo.insereA(0, 1); // vértices 2 e 3 ficam isolados
        System.out.println("Grafo com vértices isolados: " + desconexo.tipoConexidade());
    }
}