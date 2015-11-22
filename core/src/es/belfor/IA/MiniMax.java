package es.belfor.IA;

import java.util.ArrayList;

public class MiniMax {
	private static final int MIN = 1;
	private static final int MAX = 2;

	private static final int gMAX = 100;
	private static final int gMIN = -100;

	class Nodo {

		ArrayList<Integer> tablero;

		int evaluacion;

		ArrayList<Nodo> hijos;

		public Nodo() {
			tablero = new ArrayList<Integer>(8);
			for (int i = 0; i < 9; i++)
				tablero.add(0);

			hijos = new ArrayList<Nodo>();
		}

		public Nodo(ArrayList<Integer> tablero) {
			this.tablero = tablero;
			hijos = new ArrayList<Nodo>();
		}
	}

	public MiniMax() {

	}

	private boolean esPosibleColumna(ArrayList<Integer> tablero, int fila,
			int valor) {
		for (int i = 0; i < 3; i++) {
			if (tablero.get(fila + (i * 3)) != valor
					&& tablero.get(fila + (i * 3)) != 0)
				return false;
		}
		return true;
	}

	private boolean esPosibleFila(ArrayList<Integer> tablero, int columna,
			int valor) {
		for (int i = 0; i < 3; i++) {
			if (tablero.get((columna * 3) + i) != valor
					&& tablero.get((columna * 3) + i) != 0)
				return false;
		}
		return true;
	}

	private boolean esPosibleDiagonalIzquierda(ArrayList<Integer> tablero,
			int valor) {
		for (int i = 0; i < 3; i++) {
			if (tablero.get(i * 4) != valor && tablero.get(i * 4) != 0)
				return false;
		}
		return true;
	}

	private boolean esPosibleDiagonalDerecha(ArrayList<Integer> tablero,
			int valor) {
		for (int i = 1; i < 4; i++) {
			if (tablero.get(i * 2) != valor && tablero.get(i * 2) != 0)
				return false;
		}
		return true;
	}

	private int evaluacion(ArrayList<Integer> tablero) {
		int m = 0;
		int n = 0;
		for (int i = 0; i < 3; i++) {
			if (esPosibleColumna(tablero, i, MAX))
				m++;
			if (esPosibleFila(tablero, i, MAX))
				m++;
			if (esPosibleColumna(tablero, i, MIN))
				n++;
			if (esPosibleFila(tablero, i, MIN))
				n++;
		}
		if (esPosibleDiagonalDerecha(tablero, MAX))
			m++;
		if (esPosibleDiagonalIzquierda(tablero, MAX))
			m++;
		if (esPosibleDiagonalDerecha(tablero, MIN))
			n++;
		if (esPosibleDiagonalIzquierda(tablero, MIN))
			n++;

		return m - n;
	}

	private Nodo expandirNodo(Nodo raiz, int valor) {
		Nodo n;
		ArrayList<Integer> tablero = new ArrayList<Integer>(raiz.tablero);
		for (int i = 0; i < tablero.size(); i++) {
			if (tablero.get(i) == 0) {
				tablero.set(i, valor);
				ArrayList<Integer> aux = new ArrayList<Integer>(tablero);
				tablero.set(i, 0);
				n = new Nodo(aux);
				n.evaluacion = evaluacion(aux);
				raiz.hijos.add(n);
			}
		}
		return raiz;
	}

	private Nodo Min(ArrayList<Nodo> min) {
		Nodo minimo = min.get(0);

		for (int i = 1; i < min.size(); i++) {
			if (min.get(i).evaluacion < minimo.evaluacion)
				minimo = min.get(i);

		}
		return minimo;
	}

	private Nodo Max(ArrayList<Nodo> max) {
		Nodo maximo = max.get(0);

		for (int i = 1; i < max.size(); i++) {
			if (max.get(i).evaluacion > maximo.evaluacion)
				maximo = max.get(i);

		}
		return maximo;
	}

	public ArrayList<Integer> mueve() {

		Nodo raiz = new Nodo();
		// Expandimos los nodos
		raiz = expandirNodo(raiz, MAX);
		for (int i = 0; i < raiz.hijos.size(); i++) {
			raiz.hijos.set(i, expandirNodo(raiz.hijos.get(i), MIN));
		}
		// Calculamos solucion
		for (int i = 0; i < raiz.hijos.size(); i++) {
			raiz.hijos.get(i).evaluacion = Min(raiz.hijos.get(i).hijos).evaluacion;
		}

		Nodo n = Max(raiz.hijos);

		mostrarNodo(n);
		
		return n.tablero;
	}
	
	public ArrayList<Integer> mueve(ArrayList<Integer> tablero) {

		Nodo raiz = new Nodo(tablero);
		// Expandimos los nodos
		raiz = expandirNodo(raiz, MAX);
		for (int i = 0; i < raiz.hijos.size(); i++) {
			raiz.hijos.set(i, expandirNodo(raiz.hijos.get(i), MIN));
		}
		// Calculamos solucion
		for (int i = 0; i < raiz.hijos.size(); i++) {
			raiz.hijos.get(i).evaluacion = Min(raiz.hijos.get(i).hijos).evaluacion;
		}

		Nodo n = Max(raiz.hijos);

		mostrarNodo(n);
		
		return n.tablero;
	}

	private void mostrarNodo(Nodo n) {
		for (int i = 0; i < n.tablero.size(); i++) {
			System.out.print(n.tablero.get(i) + " ");
			if (i == 2 || i == 5 || i == 8)
				System.out.println();
		}
		System.out.print("\n\n");
	}

}
