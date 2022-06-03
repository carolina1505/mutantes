package com.mercadoLibre.demo.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Clase Entidad donde se define el objeto a regresar y a mapear en la base de datos
 * 
 * @author ccardona
 * 
 * @version 1.0, 03/06/2022
 * 
 *
 */


@Entity
@Table(name = "dna")
public class Dna {
	
	@Id
    @Column(name = "dna")
	private String adn;
	
	
    @Column(name = "tipo")
	private String tipo;
    
    
	private List<String> dna;
	
	
	public Dna(String adn, String tipo) {
		super();
		this.adn = adn;
		this.tipo = tipo;
	}
	
	public String getAdn() {
		return adn;
	}
	public void setAdn(String adn) {
		this.adn = adn;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public List<String> getDna() {
		return dna;
	}
	public void setDna(List<String> dna) {
		this.dna = dna;
	}	
	
	
	

}
