package JuegoDeLaVida;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Esta clase Java consiste en el llamado Juego de La vida, donde el usuario introduce el n�mero
 * de filas y columnas del tablero, y el n�mero de generaciones que se van a simular. Cada casilla
 * del tabelro se rellena con un 20% de probabilidad de c�lula viva(*), y si no con un espacio en
 * blanco, por cada generaci�n, si una c�lula esta rodeada por dos o tres (en sus ocho casillas
 * vecinas), no pasa nada, si son mas de tres, muere por sobrepoblaci�n, y si hay menos de dos,
 * muere por estar sola. Si hay exactamente tres celulas vecinas de un espacio en blanco, se genera
 * una c�lula nueva por reproducci�n. Este proceso se repite cadda vez por generaci�n.
 * @author alexc
 *
 */

public class Main {
	
	/**
	 * Pre: Este m�todo se encarga de pedir al usuario las filas y las columnas y las 
	 * generaciones.
	 * Post: Despues de pedir los datos, manda rellenar el tablero y posteriormente empieza
	 * a simular las generaciones, mostrando por pantalla el numero de la generacion y como queda
	 * el tablero tras esta. Las iteraciones se guardan en la losta iteraciones, del tipo Tripleta,
	 * que guarda el n�mero de generacion, el n�mero de c�lulas vivas y la diferencia de estas con
	 * las de la iteraci�n anterior. Luego muestra una tabla de esta lista.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		ArrayList<Tripleta> iteraciones = new ArrayList<Tripleta>();
		int celulas = 0;
		int celulasG0 = 0;
		int n = -3;
		while (n < 0) {
			System.out.print("Introduce el n�mero de filas");
			n = entrada.nextInt();
		}
		int m = -3;
		while (m < 0) {
			System.out.print("Introduce el n�mero de columnas");
			m = entrada.nextInt();
		}
		int generaciones = -3;
		while (generaciones < 0) {
			System.out.print("Introduce el n�mero de generaciones a simular");
			generaciones = entrada.nextInt();
		}
		String[][] tablero = new String[n][m];
		tablero = generarTablero(tablero);
		System.out.println("Generaci�n 0");
		String respuesta = "N";
		mostrarTablero(tablero);
		respuesta = esperarUsuario(entrada);
		Tripleta tripleta;
		celulasG0 = contarCelulas(tablero);
		for (int i = 0; i < generaciones; i++) {
			System.out.println("Generacion " + (i + 1));
			tablero = matarPorPoblacion(tablero);
			mostrarTablero(tablero);
			celulas = contarCelulas(tablero);
			if (i == 0) {
				tripleta = new Tripleta(i , celulas , celulas - celulasG0);
			} else {
				tripleta = new Tripleta(i , celulas , (celulas - 
						iteraciones.get(i - 1).getCelulasVivas()));
			}
			iteraciones.add(tripleta);
			if (celulas == 0) {
				System.out.println("Colonia extinguida");
				break;
			} 
			if (i == generaciones - 1) {
				System.out.println("Han sobrevivido " + iteraciones.get(i).getCelulasVivas() + 
						" c�lulas");
			} else if (respuesta.equals("N")) {
				respuesta = esperarUsuario(entrada);
			}
			
		}
		System.out.format("+-------------+---------------+--------------------+%n");
		System.out.format("| Generaci�n  | C�lulas Vivas | Diferencia C�lulas |%n");
		System.out.format("+-------------+---------------+--------------------+%n");
		for(Tripleta tripletaActual : iteraciones) {
			tripletaActual.mostrarCelula();
		}
		System.out.format("+-------------+---------------+--------------------+%n");
		entrada.close();
	}
	
	/**
	 * Pre: Muestra el tablero
	 * Post: Para ello se recoore pintando casilla por casilla
	 * @param tablero
	 */
	public static void mostrarTablero(String[][] tablero) {
		lineaTablero(tablero[0].length);
		System.out.println();
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (j == 0) {
					System.out.print("|" + tablero[i][j] + " ");
				} else if(j == tablero[i].length - 1) {
					System.out.print(tablero[i][j] + "|");
				} else {
					System.out.print(tablero[i][j] + " ");	
				}
			}
			System.out.println();
		}
		lineaTablero(tablero[0].length);
		System.out.println();
	}
	
	/**
	 * Pre: Este m�todo �nicamente pinta una l�nea "+----+" en proporcion al tablero
	 * @param longitud
	 */
	public static void lineaTablero (int longitud) {
		System.out.print("+");
		for (int i = 0; i < longitud; i++) {
			if (i != longitud - 1) {
				System.out.print("--");	
			} else {
				System.out.print("-+");
			}
		}
	}
	
	/**
	 * Pre: Rellena el tablero de c�lulas vivas y muertas
	 * Post: Para ello se recorre sacando un n�mero aleatorio por casilla entre 0 y 100, si este
	 * n�mero es menor oo igual que 20 (20%), se rellena con "*" (c�lula viva), si no con " ".
	 * @param tablero
	 * @return
	 */
	public static String[][] generarTablero (String[][] tablero) {
		int random = 0;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				random = (int)(Math.random() * 100);
				if (random <= 20) {
					tablero[i][j] = "*";
				} else {
					tablero[i][j] = " ";
				}
			}
		}
		return tablero;
	}
	
	/**
	 * Pre: Comprueba las c�lulas vivas que hay en las ocho casillas vecinas de la casilla
	 * [tablero[fila][col]]
	 * Post: Para ello se recorre el tablero entero, y cuando se est� en una casilla vecina, se
	 * comprueba si hay un asterisco, y se a�ade 1 al contador
	 * @param tab El tabelro
	 * @param fila La fila de la casilla
	 * @param col La columna de la casilla
	 * @return
	 */
	public static int comprobarVecinas (String[][] tab , int fila , int col) {
		int contador = 0;
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				if (i == fila - 1 || i == fila || i == fila + 1) {
					if (i == fila - 1 && j == col - 1 && tab[i][j].equals("*")) {
						contador++;
					}
					if (i == fila - 1 && j == col && tab[i][j].equals("*")) {
						contador++;
					}
					if (i == fila - 1 && j == col + 1 && tab[i][j].equals("*")) {
						contador++;
					}
					if (i == fila && j == col + 1 && tab[i][j].equals("*")) {
						contador++;
					}
					if (i == fila && j == col - 1 && tab[i][j].equals("*")) {
						contador++;
					}
					if (i == fila + 1 && j == col + 1 && tab[i][j].equals("*")) {
						contador++;
					}
					if (i == fila + 1 && j == col && tab[i][j].equals("*")) {
						contador++;
					}
					if (i == fila + 1 && j == col - 1 && tab[i][j].equals("*")) {
						contador++;
					}
				} else {
					break;
				}
			}
		}
		return contador;
	}
	
	/**
	 * Pre: Este m�todo se encarga de matar o crear c�lulas seg�n las reglas explicadas mas arriba
	 * Post: Para ello se recorre el tablero entero, si es una asterisco, se comprubeba el n�mero
	 * de c�lulas vivas vecinas (comprobarVecinas()), y la c�lula muere o no seg�n las reglas. Si
	 * por el contrario es un espacio vac�o, se comprueban tambi�n las c�lulas vecinas, si hay
	 * exactamente 3, se genera una nueva en ese espacio.
	 * Tanto si muere una c�lula como si se crea, se a�ade al tableroNuevo, ya que si se trabaja
	 * sobre el tablero normal, la reproducci�n y la superpoblaci�n no estar�a bien programada.
	 * @param tablero El tablero
	 * @return
	 */
	public static String[][] matarPorPoblacion(String[][] tablero) {
		String[][] tableroNuevo = new String[tablero.length][tablero[0].length];
		int vecinas = 0;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				tableroNuevo[i][j] = tablero[i][j];
			}
		}
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j].equals("*")) {
					vecinas = comprobarVecinas(tablero , i , j);
					if (vecinas < 2) {
						tableroNuevo[i][j] = " ";
					} else if (vecinas > 3) {
						tableroNuevo[i][j] = " ";
					}
				} else {
					vecinas = comprobarVecinas(tablero , i , j);
					if (vecinas == 3) {
						tableroNuevo[i][j] = "*";
					}
				}
			}
		}
		return tableroNuevo;
	}
	
	/**
	 * Pre: Este m�todo cuenta el n�mero de c�lulas vivas que hay en el tablero
	 * Post: Se recorre el tablero a�adiendo 1 al contador cada vez que haya un asterisco.
	 * @param tablero
	 * @return
	 */
	public static int contarCelulas(String[][] tablero) {
		int contador = 0;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j].equals("*")) {
					contador++;
				}
			}
		}
		return contador;
	}

	/**
	 * Pre: Este m�todo pide al usuario una F o una N para mostrar todas las generaciones de golpe
	 * o una a una
	 * @param entrada Clase Scanner para poder pedir por teclado
	 * @return devuelve N o F
	 */
	public static String esperarUsuario(Scanner entrada) {
		String respuesta = "";
		while (!respuesta.equals("N") && !respuesta.equals("F")) {
			System.out.println("Introduce N para la siguiente generaci�n o F para ir hasta el "
					+ "final");
			respuesta = entrada.next();
		}
		return respuesta;
	}
}
