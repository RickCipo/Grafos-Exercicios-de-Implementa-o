package GrafoLista;

// definição da classe de nós da lista (própria desta classe, não-dirigida)
class TNoND {
	public int w;
	public TNoND prox;
}

// Grafo NÃO-DIRECIONADO representado como lista de adjacência
public class TGrafoND {
	private int n;
	private int m;
	private TNoND adj[];

	public TGrafoND(int n) {
		this.n = n;
		this.m = 0;
		TNoND adjac[] = new TNoND[n];
		for (int i = 0; i < n; i++)
			adjac[i] = null;
		this.adj = adjac;
	}

	public int getN() { return n; }
	public int getM() { return m; }

	// insere w na lista de v mantendo ordem crescente, sem duplicar
	private void insereNaLista(int v, int w) {
		TNoND novoNo;
		TNoND no = adj[v];
		TNoND ant = null;
		while (no != null && w >= no.w) {
			if (w == no.w) return;
			ant = no;
			no = no.prox;
		}
		novoNo = new TNoND();
		novoNo.w = w;
		novoNo.prox = no;
		if (ant == null) adj[v] = novoNo;
		else ant.prox = novoNo;
	}

	private void removeDaLista(int v, int w) {
		TNoND no = adj[v];
		TNoND ant = null;
		while (no != null && no.w != w) {
			ant = no;
			no = no.prox;
		}
		if (no != null) {
			if (ant == null) adj[v] = no.prox;
			else ant.prox = no.prox;
		}
	}

	// Insere a aresta não-dirigida {v,w}
	public void insereA(int v, int w) {
		boolean jaExiste = false;
		TNoND no = adj[v];
		while (no != null) {
			if (no.w == w) { jaExiste = true; break; }
			no = no.prox;
		}
		if (!jaExiste) {
			insereNaLista(v, w);
			insereNaLista(w, v);
			m++;
		}
	}

	// Remove a aresta não-dirigida {v,w}
	public void removeA(int v, int w) {
		boolean existe = false;
		TNoND no = adj[v];
		while (no != null) {
			if (no.w == w) { existe = true; break; }
			no = no.prox;
		}
		if (existe) {
			removeDaLista(v, w);
			removeDaLista(w, v);
			m--;
		}
	}

	public void show() {
		System.out.print("n: " + n);
		System.out.print("\nm: " + m + "\n");
		for (int i = 0; i < n; i++) {
			System.out.print("\n" + i + ": ");
			TNoND no = adj[i];
			while (no != null) {
				System.out.print(no.w + " ");
				no = no.prox;
			}
		}
		System.out.print("\n\nfim da impressao do grafo.\n");
	}

	public int degree(int v) {
		int grau = 0;
		TNoND no = adj[v];
		while (no != null) { grau++; no = no.prox; }
		return grau;
	}

	// ---------- Exercício 28 ----------
	// Remove um vértice v (grafo não-dirigido) e retorna um NOVO grafo já sem v
	// e com os vértices seguintes reindexados.
	public TGrafoND removerVertice(int v) {
		TGrafoND novoGrafo = new TGrafoND(n - 1);
		for (int i = 0; i < n; i++) {
			if (i == v) continue;
			int novoI = (i > v) ? i - 1 : i;
			TNoND no = adj[i];
			while (no != null) {
				// como cada aresta {i,w} aparece duas vezes (em adj[i] e adj[w]),
				// só inserimos quando i < w para não duplicar a inserção
				if (no.w != v && i < no.w) {
					int novoW = (no.w > v) ? no.w - 1 : no.w;
					novoGrafo.insereA(novoI, novoW);
				}
				no = no.prox;
			}
		}
		return novoGrafo;
	}

	// ---------- Exercício 30 (versão não-dirigida) ----------
	public boolean ehCompleto() {
		int arestasMaximas = (n * (n - 1)) / 2;
		return this.m == arestasMaximas;
	}
}
