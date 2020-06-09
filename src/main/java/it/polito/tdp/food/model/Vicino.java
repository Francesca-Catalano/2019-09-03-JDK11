package it.polito.tdp.food.model;

public class Vicino {
	private String p2;
	private Double peso;
	public String getP2() {
		return p2;
	}
	public Double getPeso() {
		return peso;
	}
	public Vicino(String p2, Double peso) {
		super();
		this.p2 = p2;
		this.peso = peso;
	}
	@Override
	public String toString() {
		return p2 + " " + peso ;
	}
	

}
