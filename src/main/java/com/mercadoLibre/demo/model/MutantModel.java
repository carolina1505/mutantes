package com.mercadoLibre.demo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mercadoLibre.demo.Entity.Dna;
import com.mercadoLibre.demo.dto.EstadisticaDTO;


/**
 * Clase donde se ejecutan los métodos con la estructura y lógica de los datos
 * 
 * @author ccardona
 * 
 * @version 1.0, 03/06/2022
 * 
 * @param objetos
 * 
 * @return Salida de métodos.
 *
 */


public class MutantModel {
	
	static Dna persona = new Dna(null, null);
	EstadisticaDTO estadistica = new EstadisticaDTO();

	
	//Funcion recuersiva buscar letra en una matriz de forma horizontal
	public static Boolean BuscarHorizontal (String letraBusc, String matriz[][]) {
		
		String letra = letraBusc;
		int contador = 0;
		String banderaMutante = "false";

		int indiceAnterior = 0;
		int indiceActual = 0;
		for (int x=0; x < matriz.length; x++) {
			for (int y=0; y < matriz[x].length; y++) {

				if (letra.compareTo(matriz[x][y]) == 0) {
					
					
					//Si se encuentra una coincidencia el indice actual es el mismo lugar donde la encontro para validar posterior a este
					indiceActual = y;					
					
					
					//Si es la primera coincidencia el indice anterior es igual al actual - 1 para establecerlo en la resta como el primero
					if (contador == 0)
					{						
						indiceAnterior = (indiceActual-1);
					}
					
					
					//Si la resta entre los dos indices es 1 indica que la coincidencia es la inmediatamente anterior
					if ((indiceActual - indiceAnterior) == 1 ) 
					{
						contador = contador + 1;	
						
					}
					//Si no son contiguos el contador se reinicia a 0 para cuando encuentre otra coincidencia cuente de nuevo
					else
					{
						contador = 0;
					}
					//Se igualan indices para validar que los posteriores sean los inmediatamente contiguos
					indiceAnterior = indiceActual;


				}   
			}
			
			//Se reinician indices y contadores al terminar de revisar las letras de la misma fila
			
			indiceAnterior = -1;
			indiceActual = 0;
			
			if (contador >= 4)
			{
				banderaMutante = "true";
				contador = 0;
			}
			else 
			{
				contador = 0;
			}
		}
		
		//Se validan al finalizar los for las banderas de mutantes para dar una respuesta.
		
		if (banderaMutante.compareTo("true") == 0) {
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Funcion recursiva buscar letra en una matriz de forma vertical
	public static Boolean BuscarVertical (String letraBusc, String matriz[][]) {
		
		String letra = letraBusc;
		int contador = 0;
		String banderaMutante = "false";

		int indiceAnterior = 0;
		int indiceActual = 0;
		for (int x=0; x < matriz.length; x++) {
			for (int y=0; y < matriz[x].length; y++) {

				//Se compara con el indice en y para hacer el recorrido en forma vertical
				if (letra.compareTo(matriz[y][x]) == 0) {
					
					indiceActual = y;
					
					//Si es la primera coincidencia el indice anterior es igual al actual - 1 para establecerlo en la resta como el primero
					if (contador == 0)
					{						
						indiceAnterior = (indiceActual-1);
					}					
					
					//Si la resta entre los dos indices es 1 indica que la coincidencia es la inmediatamente anterior
					if ((indiceActual - indiceAnterior) == 1 ) 
					{
						contador = contador + 1;
						
					}
					//Si no son contiguos el contador se reinicia a 0 para cuando encuentre otra coincidencia cuente de nuevo
					else
					{
						contador = 0;
					}
					//Se igualan indices para validar que los posteriores sean los inmediatamente contiguos
					indiceAnterior = indiceActual;

				}   
			}
			
			indiceAnterior = -1;
			indiceActual = 0;
			
			//Al finalizar los ciclos se valida el contador para marcar la bandera de mutante
			if (contador >= 4)
			{
				banderaMutante = "true";
				contador = 0;
			}
			else 
			{
				contador = 0;
			}
		}
		
		if (banderaMutante.compareTo("true") == 0) {
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Funcion recursiva buscar letra en una matriz de forma diagonal
	public static Boolean BuscarDiagonal (String letraBusc, String matriz[][]) {
		
		String letra = letraBusc;
		int contador = 0;
		String banderaMutante = "false";

		int limite = (matriz.length - 1);
		
		for (int x=0; x < matriz.length; x++) {
			for (int y=0; y < matriz[x].length; y++) {


				if (letra.compareTo(matriz[x][y]) == 0) {
					
					
					contador = contador + 1;
					
					int siguienteX = x+1;
					int siguienteY = y+1;
					
					//Si se encontro una coincidencia valido que el proximo en diagonal no sobrepase los limites de la matriz
					while ((siguienteX <= limite) && (siguienteY <= limite)) {
						
						
						if (letra.compareTo(matriz[siguienteX][siguienteY])== 0) {
							contador = contador + 1;							
							
						}
						//Se igualan indices para validar la proxima coincidencia diagonal
						siguienteX = siguienteX +1;
						siguienteY = siguienteY +1;
						
					}					
					
					
					if (contador >= 4)
					{
						banderaMutante = "true";
						contador = 0;
					}
					else 
					{
						contador = 0;
					}
					
				}   
			}
			
		}
		
		if (banderaMutante.compareTo("true") == 0) {
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Metodo para detectar si un humano es mutante segun se secuencia de ADN 
	public static boolean isMutant(List<String> dna) {
		
		String secuencia = String.join(",", dna);

		//Se recibe la lista de String y se llena una matriz
		System.out.println("The list is: " + dna);

		String matriz[][];
		matriz = new String[dna.size()][dna.size()];

		//For para separar caracteres y llenar la matriz uno a uno
		for(int i = 0; i < dna.size(); i++) {	 
			String s = dna.get(i);
			String[] strArr = s.split(""); 	      
			ArrayList<String> list = new ArrayList<String>(Arrays.asList(strArr));

			for(int j = 0; j < dna.size(); j++) {	 


				String a = list.get(j);
				matriz[i][j] = a;


			}

		}
		
		//Se buscan secuencia de cuatro letras iguales​, de forma diagonal, horizontal o vertical.
		
		boolean horizontalA = BuscarHorizontal("A", matriz);
		boolean horizontalT = BuscarHorizontal("T", matriz);
		boolean horizontalC = BuscarHorizontal("C", matriz);
		boolean horizontalG = BuscarHorizontal("G", matriz);
		boolean verticalA = BuscarVertical("A", matriz);
		boolean verticalT = BuscarVertical("T", matriz);
		boolean verticalC = BuscarVertical("C", matriz);
		boolean verticalG = BuscarVertical("G", matriz);
		boolean diagonalA = BuscarDiagonal("A", matriz);
		boolean diagonalT = BuscarDiagonal("T", matriz);
		boolean diagonalC = BuscarDiagonal("C", matriz);
		boolean diagonalG = BuscarDiagonal("G", matriz);
		
		System.out.println ("horizontalA " + horizontalA);
		System.out.println ("horizontalT " + horizontalT);
		System.out.println ("horizontalC " + horizontalC);
		System.out.println ("horizontalG " + horizontalG);
		System.out.println ("verticalA " + verticalA);
		System.out.println ("verticalT " + verticalT);
		System.out.println ("verticalC " + verticalC);
		System.out.println ("verticalG " + verticalG);
		System.out.println ("diagonalA " + diagonalA);
		System.out.println ("diagonalT " + diagonalT);
		System.out.println ("diagonalC " + diagonalC);
		System.out.println ("diagonalG " + diagonalG);
		
		
		//Si alguna secuencia resulta positiva en cualquier direccion retorna true
		
		if ((horizontalA) || (horizontalT) || (horizontalC) || (horizontalG) || 
				(verticalA) || (verticalT) || (verticalC) || (verticalG) ||
				(diagonalA) || (diagonalT) || (diagonalC) || (diagonalG)) {
			
			return true;
		}
		else
		{			
			return false;
		}
	}
	
	public static double calcularRatio(EstadisticaDTO estadistica) {
		
		//Se obtienen los datos del objeto para hacer los calculos de la estadistica
		double humanos = estadistica.getHumanos();
		double mutantes = estadistica.getMutantes();
		double ratio = 0;
		
		//Tanto el dividendo como el divisor deben ser mayores a 0 para poder hacer la division del ratio
		if (humanos > 0 && mutantes >0 )
		{
			ratio = mutantes / humanos;
		}
		
		
		
		return ratio;
		
	}

}
