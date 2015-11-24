package es.belfor.IA;

import java.util.ArrayList;

public class MiniMax {
	private static final int MIN = 1;
	private static final int MAX = 2;

	private static final int gMAX = 100;
	private static final int gMIN = -100;

	/**
	 * 
	 * @author Belfor
	 * Clase Nodo, necesaria para crear una estructura de Arbol para el algoritmo minimax
	 *
	 */
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
	/**
	 * Devuelve True si es posible hacer Columna con el valor
	 * @param tablero
	 * @param fila
	 * @param valor
	 * @return
	 */
	private boolean esPosibleColumna(ArrayList<Integer> tablero, int fila,
			int valor) {
		for (int i = 0; i < 3; i++) {
			if (tablero.get(fila + (i * 3)) != valor
					&& tablero.get(fila + (i * 3)) != 0)
				return false;
		}
		return true;
	}

	/**
	 * Devyekve True si es posible hacer Fila con el valor
	 * @param tablero
	 * @param columna
	 * @param valor
	 * @return
	 */
	private boolean esPosibleFila(ArrayList<Integer> tablero, int columna,
			int valor) {
		for (int i = 0; i < 3; i++) {
			if (tablero.get((columna * 3) + i) != valor
					&& tablero.get((columna * 3) + i) != 0)
				return false;
		}
		return true;
	}

	/**
	 * Devuelve True si puede crear la diagonal Izquierda con el valor
	 * @param tablero
	 * @param valor
	 * @return
	 */
	private boolean esPosibleDiagonalIzquierda(ArrayList<Integer> tablero,
			int valor) {
		for (int i = 0; i < 3; i++) {
			if (tablero.get(i * 4) != valor && tablero.get(i * 4) != 0)
				return false;
		}
		return true;
	}

	/**
	 * Devuelve True si puede crear la diagonal Derecha con el valor
	 * @param tablero
	 * @param valor
	 * @return
	 */
	private boolean esPosibleDiagonalDerecha(ArrayList<Integer> tablero,
			int valor) {
		for (int i = 1; i < 4; i++) {
			if (tablero.get(i * 2) != valor && tablero.get(i * 2) != 0)
				return false;
		}
		return true;
	}

	/**
	 * Devuelve la evaluacion heuristica
	 * @param tablero
	 * @return
	 */
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
	
	private boolean esVictoria(Nodo n){
		if (n.tablero.get(0) == n.tablero.get(1) && n.tablero.get(0) == n.tablero.get(2) && n.tablero.get(0)!= 0 ) return true;
		if (n.tablero.get(3) == n.tablero.get(4) && n.tablero.get(3) == n.tablero.get(5) && n.tablero.get(3)!= 0 ) return true;
		if (n.tablero.get(6) == n.tablero.get(7) && n.tablero.get(6) == n.tablero.get(8) && n.tablero.get(6)!= 0 ) return true;
		
		if (n.tablero.get(0) == n.tablero.get(3) && n.tablero.get(0) == n.tablero.get(6) && n.tablero.get(0)!= 0 ) return true;
		if (n.tablero.get(1) == n.tablero.get(4) && n.tablero.get(1) == n.tablero.get(7) && n.tablero.get(1)!= 0 ) return true;
		if (n.tablero.get(2) == n.tablero.get(5) && n.tablero.get(2) == n.tablero.get(8) && n.tablero.get(2)!= 0 ) return true;
		
		if (n.tablero.get(0) == n.tablero.get(4) && n.tablero.get(0) == n.tablero.get(8) && n.tablero.get(0)!= 0 ) return true;
		if (n.tablero.get(2) == n.tablero.get(4) && n.tablero.get(2) == n.tablero.get(6) && n.tablero.get(2)!= 0 ) return true;
		
		
		return false;
	}
	
	private boolean esVictoria(Nodo n, int valor){
		if (n.tablero.get(0) == n.tablero.get(1) && n.tablero.get(0) == n.tablero.get(2) && n.tablero.get(0) == valor ) return true;
		if (n.tablero.get(3) == n.tablero.get(4) && n.tablero.get(3) == n.tablero.get(5) && n.tablero.get(3) == valor ) return true;
		if (n.tablero.get(6) == n.tablero.get(7) && n.tablero.get(6) == n.tablero.get(8) && n.tablero.get(6) == valor ) return true;
		
		if (n.tablero.get(0) == n.tablero.get(3) && n.tablero.get(0) == n.tablero.get(6) && n.tablero.get(0) == valor ) return true;
		if (n.tablero.get(1) == n.tablero.get(4) && n.tablero.get(1) == n.tablero.get(7) && n.tablero.get(1) == valor ) return true;
		if (n.tablero.get(2) == n.tablero.get(5) && n.tablero.get(2) == n.tablero.get(8) && n.tablero.get(2) == valor ) return true;
		
		if (n.tablero.get(0) == n.tablero.get(4) && n.tablero.get(0) == n.tablero.get(8) && n.tablero.get(0) == valor ) return true;
		if (n.tablero.get(2) == n.tablero.get(4) && n.tablero.get(2) == n.tablero.get(6) && n.tablero.get(2) == valor ) return true;
		
		
		return false;
	}
	public boolean esVictoria(ArrayList<Integer> tablero){
		return (esVictoria(new Nodo(tablero)));
	}


	/**
	 * Expande los nodos hijos indicandole el valor
	 * @param raiz
	 * @param valor
	 * @return
	 */
	private Nodo expandirNodo(Nodo raiz, int valor) {
		Nodo n;
		ArrayList<Integer> tablero = new ArrayList<Integer>(raiz.tablero);
		for (int i = 0; i < tablero.size(); i++) {
			if (tablero.get(i) == 0) {
				tablero.set(i, valor);
				ArrayList<Integer> aux = new ArrayList<Integer>(tablero);
				tablero.set(i, 0);
				n = new Nodo(aux);
				if (esVictoria(n,MAX))
					n.evaluacion = gMAX;
					else if (esVictoria(n,MIN))
						n.evaluacion = gMIN;
					else
					n.evaluacion = evaluacion(aux);
				raiz.hijos.add(n);
			}
		}
		return raiz;
	}

	/**
	 * Devuelve el minimo de los Nodos min
	 * @param min
	 * @return
	 */
	private Nodo Min(ArrayList<Nodo> min) {
		Nodo minimo = min.get(0);

		for (int i = 1; i < min.size(); i++) {
			if (min.get(i).evaluacion < minimo.evaluacion)
				minimo = min.get(i);

		}
		return minimo;
	}

	/**
	 * Devuelve el maximo de los nodos Max
	 * @param max
	 * @return
	 */
	private Nodo Max(ArrayList<Nodo> max) {
		Nodo maximo = max.get(0);

		for (int i = 1; i < max.size(); i++) {
			if (max.get(i).evaluacion > maximo.evaluacion)
				maximo = max.get(i);

		}
		return maximo;
	}
	
	/**
	 * Calcula a traves del algoritmo MiniMax la mejor Jugada
	 * @param tablero
	 * @return
	 */
	public ArrayList<Integer> mueve(ArrayList<Integer> tablero) {

		Nodo raiz = new Nodo(tablero);
		// Expandimos los nodos
		raiz = expandirNodo(raiz, MAX);
		for (int i = 0; i < raiz.hijos.size(); i++) 	
			expandirNodo(raiz.hijos.get(i), MIN);
		
		// Calculamos solucion
		for (int i = 0; i < raiz.hijos.size(); i++) {
			if (raiz.hijos.get(i).hijos.size() != 0)
				raiz.hijos.get(i).evaluacion = Min(raiz.hijos.get(i).hijos).evaluacion;
		}

		Nodo n = Max(raiz.hijos);

		
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
