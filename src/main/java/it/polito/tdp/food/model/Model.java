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

	
	
	
	
	
	
}
