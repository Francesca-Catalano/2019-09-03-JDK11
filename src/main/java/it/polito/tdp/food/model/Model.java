package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.sql.rowset.spi.SyncFactory;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	private Graph<String, DefaultWeightedEdge> graph;
	private FoodDao dao;
	private double bestPeso;
	private List<String> bestSOlution;
	public Model() {
	
		this.dao= new FoodDao();
		
	}

	public void creaGrafo(double num) {
		this.graph= new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		if(this.dao.listPortionsName(num)==null)
		{
			System.out.println("Errore lettura vertici\n");
			return;
		}
		Graphs.addAllVertices(this.graph, this.dao.listPortionsName(num));
		System.out.print(this.dao.listPortionsName(num).size());
		
		  if(this.dao.listAdiacenze(num)==null) {
		  System.out.println("Errore lettura archi\n"); return; } 
		  for(Adiacenza a :this.dao.listAdiacenze(num) )
		  { 
			  if(this.graph.containsVertex(a.getP1()) && this.graph.containsVertex(a.getP2()))
			  {
				  DefaultWeightedEdge e = this.graph.getEdge(a.getP1(), a.getP2()); 
				  if(e==null) {
			  Graphs.addEdge(this.graph, a.getP1(), a.getP2(), a.getPeso());
			  }
		
		  }
		  
		  }
		 
		
	}
	
	public Set<String> listVertex()
	{
		return this.graph.vertexSet();
	}

	public Set<DefaultWeightedEdge> listEdges() {
	
		return this.graph.edgeSet();
	}

	public List<Vicino> doCorrelat(String s) {
		List<Vicino> vicino = new ArrayList<>();

		for(String v : Graphs.neighborListOf(this.graph, s))
		{
			vicino.add(new Vicino(v, this.graph.getEdgeWeight(this.graph.getEdge(s, v))));
		}
		return vicino;
	}

	public List<String> cammino(int num, String source) {
		this.bestSOlution=new ArrayList<>();
		this.bestPeso=0;
		List<String> parziale = new ArrayList<>();
		parziale.add(source);
		System.out.print(num+"\n");
		System.out.print(source+"\n");
		
		System.out.print(parziale);
		ricorsivo(num,parziale,source,1);
		
		return this.bestSOlution;
				
	}

	private void ricorsivo(int num, List<String> parziale, String source,int livello) {
		//if(parziale.size()==num)
		if(livello==num+1)
		{
			if(peso(parziale,source)>bestPeso)
			{
				this.bestPeso=peso(parziale,source);
				this.bestSOlution= new ArrayList<>(parziale);
			}
			return;
		}
		
		for(String v : Graphs.neighborListOf(this.graph, parziale.get(livello-1)))
		{
			if(!parziale.contains(v))
			{
				parziale.add(v);
				ricorsivo(num,parziale,source,livello+1);
				parziale.remove(parziale.size()-1);
			}
		}
				
	}



	private double peso(List<String> parziale,String source) {
		double peso=0.0;
		/*
		 * for(String p : parziale) { peso+=
		 * this.graph.getEdgeWeight(this.graph.getEdge(source, p)); }
		 */
		
		for(int i=1; i<parziale.size(); i++) {
			double p = this.graph.getEdgeWeight(this.graph.getEdge(parziale.get(i-1), parziale.get(i))) ;
			peso += p ;
		}
		return peso;
	}

	public double getBestPeso() {
		return bestPeso;
	}


	
	
	
	
	
	
}
