package JuegoDeLaVida;

import java.util.ArrayList;
/**
 * Esta clase Java consiste en el llamado Juego de La vida, donde el tablero es de 
 * 4*4, y el nï¿½mero de generaciones que se van a simular son 30. Se rellena el tablero y se aplican
 * las reglas, si una cï¿½lula tiene en sus 8 casillas vecinas mï¿½s de tres cï¿½lulas, esta muere por
 * sobrepoblaciï¿½n, y si hay menos de dos, muere por estar sola. Si hay exactemante tres cï¿½lulas
 * vivas alrededor de un espacio en blanco, se genera una nueva por reproducciï¿½n. Este proceso
 * se repite 30 veces.
 * @author alexc
 *
 */
public class Pruebas2{
	
	/**
	 * Pre: Este método se encarga de repetir el proceso de generaciones 30 veces.
	 * Post: Manda rellenar el tablero y posteriormente empieza
	 * a simular las generaciones, mostrando por pantalla el numoer de la generacion y como queda
	 * el tablero tras esta. Las iteraciones se guardan en la losta iteraciones, del tipo Tripleta,
	 * que guarda el número de generacion, el número de células vivas y la diferencia de estas con
	 * las de la iteración anterior. Luego muestra una tabla de esta lista.
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Tripleta> iteraciones = new ArrayList<Tripleta>();
		int celulas = 0;
		Tripleta tripleta;
		int generaciones = 30;
		String[][] tablero = new String[5][5];
		tablero = generarTablero(tablero);
		System.out.println("Generación 0");
		mostrarTablero(tablero);
		for (int i = 0; i < generaciones; i++) {
			System.out.println("Generación " + (i + 1));
			tablero = matarPorPoblacion(tablero);
			mostrarTablero(tablero);
			celulas = contarCelulas(tablero);
			if (i == 0) {
				tripleta = new Tripleta(i , celulas , celulas);
			} else {
				tripleta = new Tripleta(i , celulas , (celulas - 
						iteraciones.get(i - 1).getCelulasVivas()));
			}
			iteraciones.add(tripleta);
			if (celulas == 0) {
				System.out.println("Colonia extinguida");
				break;
			}
		}
		System.out.format("+-------------+---------------+--------------------+%n");
		System.out.format("| Generación  | Células Vivas | Diferencia Células |%n");
		System.out.format("+-------------+---------------+--------------------+%n");
		for(Tripleta tripletaActual : iteraciones) {
			tripletaActual.mostrarCelula();
		}
		System.out.format("+-------------+---------------+--------------------+%n");
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
	 * Pre: Este método únicamente pinta una línea "+----+" en proporcion al tablero
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
	 * Pre: Rellena el tablero de células vivas y muertas
	 * Post: Para ello se recorre asignando a cada casilla un espacio en blanco, luego ponemos las
	 * células en el patron correcto para ver si la pruba está bien hecha.
	 * @param tablero
	 * @return
	 */
	public static String[][] generarTablero (String[][] tablero) {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				tablero[i][j] = " ";
			}
		}
		tablero[2][1] = "*";
		tablero[2][2] = "*";
		tablero[2][3] = "*";
		return tablero;
	}
	
	/**
	 * Pre: Comprueba las células vivas que hay en las ocho casillas vecinas de la casilla
	 * [tablero[fila][col]]
	 * Post: Para ello se recorre el tablero entero, y cuando se está en una casilla vecina, se
	 * comprueba si hay un asterisco, y se añade 1 al contador
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
	 * Pre: Este método se encarga de matar o crear células según las reglas explicadas mas arriba
	 * Post: Para ello se recorre el tablero entero, si es una asterisco, se comprubeba el número
	 * de células vivas vecinas (comprobarVecinas()), y la célula muere o no según las reglas. Si
	 * por el contrario es un espacio vacío, se comprueban también las células vecinas, si hay
	 * exactamente 3, se genera una nueva en ese espacio.
	 * Tanto si muere una célula como si se crea, se añade al tableroNuevo, ya que si se trabaja
	 * sobre el tablero normal, la reproducción y la superpoblación no estaría bien programada.
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
	 * Pre: Este método cuenta el número de células vivas que hay en el tablero
	 * Post: Se recorre el tablero añadiendo 1 al contador cada vez que haya un asterisco.
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
}
