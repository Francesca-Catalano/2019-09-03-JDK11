package it.polito.tdp.food.model;

public class Adiacenza {
	private String p1;
	private String p2;
	private Double peso;
	public Adiacenza(String p1, String p2, Double peso) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.peso = peso;
	}
	public String getP1() {
		return p1;
	}
	public String getP2() {
		return p2;
	}
	public Double getPeso() {
		return peso;
	}

}
