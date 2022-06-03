package com.mercadoLibre.demo.dto;

/**
 * Clase DTO donde se define el objeto de transferencia de datos entre el cliente y el servidor
 * 
 * @author ccardona
 * 
 * @version 1.0, 03/06/2022
 * 
 *
 */

public class EstadisticaDTO {
	
	private int humanos;
	private int mutantes;
	private double ratio;
	
	
	
	public int getHumanos() {
		return humanos;
	}
	public void setHumanos(int humanos) {
		this.humanos = humanos;
	}
	public int getMutantes() {
		return mutantes;
	}
	public void setMutantes(int mutantes) {
		this.mutantes = mutantes;
	}
	public double getRatio() {
		return ratio;
	}
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}





	@Override
	public String toString() {
		return "{" +
                "\"count_mutant_dna\":" + getMutantes() +
                ",\"count_human_dna\":" + getHumanos() +
                ",\"ratio\":" + getRatio() +
                '}';
	}
	

}
