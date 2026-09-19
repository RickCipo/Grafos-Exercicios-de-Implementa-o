package GrafoLista;

public class TesteGrafoLista {

	public static void main(String[] args) {
	    TGrafo g = new TGrafo(4);
	    g.insereA(0,1);
	    g.insereA(0,2);
	    g.insereA(0,3);
	    g.insereA(2,1);
	    g.insereA(2,3);
	    g.insereA(1,3);
	    g.show();
	    g.removeA(0,3);
	    System.out.print("\nDepois da remoção da aresta (0,3)\n");
	    g.show();
	}
}
