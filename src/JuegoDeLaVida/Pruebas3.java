package JuegoDeLaVida;

import java.util.ArrayList;

/**
 * Esta clase Java consiste en el llamado Juego de La vida, donde el tablero es de 
 * 4*4, y el n�mero de generaciones que se van a simular son 30. Se rellena el tablero y se aplican
 * las reglas, si una c�lula tiene en sus 8 casillas vecinas m�s de tres c�lulas, esta muere por
 * sobrepoblaci�n, y si hay menos de dos, muere por estar sola. Si hay exactemante tres c�lulas
 * vivas alrededor de un espacio en blanco, se genera una nueva por reproducci�n. Este proceso
 * se repite 30 veces.
 * @author alexc
 *
 */
public class Pruebas3{
	
	/**
	 * Pre: Este m�todo se encarga de repetir el proceso de generaciones 30 veces.
	 * Post: Manda rellenar el tablero y posteriormente empieza
	 * a simular las generaciones, mostrando por pantalla el numoer de la generacion y como queda
	 * el tablero tras esta. Las iteraciones se guardan en la losta iteraciones, del tipo Tripleta,
	 * que guarda el n�mero de generacion, el n�mero de c�lulas vivas y la diferencia de estas con
	 * las de la iteraci�n anterior. Luego muestra una tabla de esta lista.
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
		System.out.println("Generaci�n 0");
		mostrarTablero(tablero);
		celulasG0 = Main.contarCelulas(tablero);
		for (int i = 0; i < generaciones; i++) {
			System.out.println("Generaci�n " + (i + 1));
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
		System.out.format("| Generaci�n  | C�lulas Vivas | Diferencia C�lulas |%n");
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
	 * Post: Para ello se recorre asignando a cada casilla un espacio en blanco, luego ponemos las
	 * c�lulas en el patron correcto para ver si la pruba est� bien hecha.
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
