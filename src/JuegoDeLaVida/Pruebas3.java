package JuegoDeLaVida;

import java.util.ArrayList;

/**
 * Esta clase Java consiste en el llamado Juego de La vida, donde el tablero es de 
 * 4*4, y el número de generaciones que se van a simular son 30. Se rellena el tablero y se aplican
 * las reglas, si una célula tiene en sus 8 casillas vecinas más de tres células, esta muere por
 * sobrepoblación, y si hay menos de dos, muere por estar sola. Si hay exactemante tres células
 * vivas alrededor de un espacio en blanco, se genera una nueva por reproducción. Este proceso
 * se repite 30 veces.
 * @author alexc
 *
 */
public class Pruebas3{
	
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
		int celulasG0 = 0;
		Tripleta tripleta;
		int generaciones = 30;
		String[][] tablero = new String[6][6];
		tablero = generarTablero(tablero);
		System.out.println("Generación 0");
		mostrarTablero(tablero);
		celulasG0 = Main.contarCelulas(tablero);
		for (int i = 0; i < generaciones; i++) {
			System.out.println("Generación " + (i + 1));
			tablero = Main.matarPorPoblacion(tablero);
			mostrarTablero(tablero);
			celulas = Main.contarCelulas(tablero);
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
		tablero[1][1] = "*";
		tablero[1][2] = "*";
		tablero[2][1] = "*";
		tablero[2][2] = "*";
		
		tablero[3][3] = "*";
		tablero[3][4] = "*";
		tablero[4][3] = "*";
		tablero[4][4] = "*";
		return tablero;
	}
}
