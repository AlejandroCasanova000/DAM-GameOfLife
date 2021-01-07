package JuegoDeLaVida;

/**
 * Esta clase Java define el objeto Tripleta, formado por el n�mero de generaci�n de la clase
 * Main, por el n�mero de c�lulas vivas y por la diferencia de estas con la anterior generaci�n
 * @author alexc
 *
 */

public class Tripleta {
	private int numIteracion;
	private int celulasVivas;
	private int diferenciaCelulas;
	
	/**
	 * El contructor del objeto
	 * @param numIteracion El numero de generaci�n
	 * @param celulasVivas El n�mero de c�lulas vivas
	 * @param diferenciaCelulas La diferencia de celulasVivas con las celulasVivas de la generacion
	 * 								anterior
	 */
	public Tripleta (int numIteracion , int celulasVivas , int diferenciaCelulas) {
		this.numIteracion = numIteracion;
		this.celulasVivas = celulasVivas;
		this.diferenciaCelulas = diferenciaCelulas;
	}
	
	/**
	 * Pre:Este m�todo devuelve el numero de generaci�n del objeto
	 * @return
	 */
	public int getNumIteracion() {
		return numIteracion;
	}
	
	/**
	 * Pre: Este m�todo cambia el n�mero de generaci�n por [numIteracion]
	 * @param numIteracion
	 */
	public void setNumIteracion(int numIteracion) {
		this.numIteracion = numIteracion;
	}
	
	/**
	 * Pre: Este m�todo ddevuelve el n�mero de c�lulas vivas de la generaci�n
	 * @return
	 */
	public int getCelulasVivas() {
		return celulasVivas;
	}
	
	/**
	 * Pre: Este programa cambai el numero de c�lulas vivas a [celulasVivas]
	 * @param celulasVivas
	 */
	public void setCelulasVivas(int celulasVivas) {
		this.celulasVivas = celulasVivas;
	}
	
	/**
	 * Pre: Este m�todo devuelve la diferencia entre c�lulasVivas y c�lulasVivas de la generaci�n
	 * anterior.
	 * @return
	 */
	public int getDifereciaCelulas() {
		return diferenciaCelulas;
	}
	
	/**
	 * Pre: Este m�todo cambia diferenciaCelulas a [diferenciaCelulas]
	 * @param diferenciaCelulas
	 */
	public void setDiferenciaCelulas(int diferenciaCelulas) {
		this.diferenciaCelulas = diferenciaCelulas;
	}
	
	/**
	 * Pre: Este m�todo pinta el numero de generaci�n, el de c�lulas vivas y la diferenciaCelulas
	 * de tal manera que se formateen en la tabla del main.
	 */
	public void mostrarCelula() {
		String leftAlignFormat = "| %-11s | %-13d | %-18d |%n";
		System.out.format(leftAlignFormat, numIteracion + 1 , celulasVivas , diferenciaCelulas);
	}
}
