package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	
	private FoodDao dao;
	private SimpleWeightedGraph<String, DefaultWeightedEdge> grafo;
	private List<String> camminoBest;
	private Integer pesoBest;
	
	public Model() {
		this.dao = new FoodDao();
	}
	
	public String creaGrafo(Integer C) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.dao.getVertex(C));
		
		for(Arco a : this.dao.getEdges(C)) {
			
			if(!this.grafo.containsEdge(a.getPorzione1(), a.getPorzione2())) {
				
				Graphs.addEdgeWithVertices(this.grafo, a.getPorzione1(), a.getPorzione2(), a.getPeso());
			}
		}
		
		return String.format("GRAFO CREATO!!\n\n#VERTICI: %s\n#ARCHI: %s\n", this.grafo.vertexSet().size(), this.grafo.edgeSet().size());
	}
	
	public List<String> listAllPortionsTypes(){
		return this.dao.listAllPortionsTypes();
	}
	
	public Collection<String> getAllVertex(){
		return this.grafo.vertexSet();
	}
	
	public Map<String, Integer> getCorrelate(String porzioneScelta){
		Map<String, Integer> result = new HashMap<>();
		
		for(DefaultWeightedEdge e : this.grafo.outgoingEdgesOf(porzioneScelta)) 
			result.put(Graphs.getOppositeVertex(this.grafo, e, porzioneScelta), (int)this.grafo.getEdgeWeight(e));
		
		return result;
	}

	public void cercaCammino(Integer N, String porzioneScelta) {
		this.camminoBest = new ArrayList<>();
		this.pesoBest = 0;
		
		List<String> parziale = new ArrayList<>();
		parziale.add(porzioneScelta);
		
		this.calcolaCammino(parziale, 0, N);	
	}

	private void calcolaCammino(List<String> parziale, Integer peso, Integer N) {

		if(parziale.size()==N) {
			
			if(peso>this.pesoBest) {
				this.camminoBest = new ArrayList<>(parziale);
				this.pesoBest = peso;
			}
			return;
		}
		
		for(String s : Graphs.neighborListOf(this.grafo, parziale.get(parziale.size()-1))) {
			
			if(!parziale.contains(s)) {
				DefaultWeightedEdge e = this.grafo.getEdge(parziale.get(parziale.size()-1), s);
				Integer pesoAttuale = (int)this.grafo.getEdgeWeight(e);
				
				parziale.add(s);
				peso += pesoAttuale;
				
				this.calcolaCammino(parziale, peso, N);
			
				parziale.remove(parziale.size()-1);
				peso -= pesoAttuale;
			}
		}
		
	}

	public List<String> getCamminoBest() {
		return camminoBest;
	}

	public Integer getPesoBest() {
		return pesoBest;
	}

}
