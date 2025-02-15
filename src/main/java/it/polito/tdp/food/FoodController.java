/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import it.polito.tdp.food.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCalorie"
    private TextField txtCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassi"
    private TextField txtPassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorrelate"
    private Button btnCorrelate; // Value injected by FXMLLoader

    @FXML // fx:id="btnCammino"
    private Button btnCammino; // Value injected by FXMLLoader

    @FXML // fx:id="boxPorzioni"
    private ComboBox<String> boxPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCammino(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Cerco cammino peso massimo...\n\n");
    	
    	Integer N;
    	try {
    		N = Integer.parseInt(this.txtCalorie.getText());
    	}
    	catch(NumberFormatException e) {
    		this.txtResult.appendText("Devi inserire un numero INTERO!!");
    		return;
    	}
    	
    	String porzioneScelta = this.boxPorzioni.getValue();
    	
    	this.model.cercaCammino(N, porzioneScelta);
    	
    	this.txtResult.appendText("Percorso trovato con peso "+this.model.getPesoBest()+": \n");
    	for(String s : this.model.getCamminoBest()) {
    		this.txtResult.appendText("- "+s+"\n");
    	}
    }

    @FXML
    void doCorrelate(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Cerco porzioni correlate...\n\n");
    	
    	String porzioneScelta = this.boxPorzioni.getValue();
    	if(porzioneScelta == null) {
    		this.txtResult.appendText("Devi scegliere prima una PORZIONE!!");
    		return;
    	}
    	
    	Map<String, Integer> result = this.model.getCorrelate(porzioneScelta);
    	
    	this.txtResult.appendText("Elenco delle porzioni direttamente connesse: \n");
    	for(String s : result.keySet()) {
    		
    		this.txtResult.appendText("- '"+s+"' Peso: "+result.get(s)+"\n");
    	}
    	
    	this.btnCammino.setDisable(false);
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Creazione grafo...\n\n");
    	
    	Integer C;
    	try {
    		C = Integer.parseInt(this.txtCalorie.getText());
    	}
    	catch(NumberFormatException e) {
    		this.txtResult.appendText("Devi inserire un numero INTERO!!");
    		return;
    	}
    	
    	String result = this.model.creaGrafo(C);
    	
    	this.txtResult.appendText(result);
    	this.btnCorrelate.setDisable(false);
    	
    	this.boxPorzioni.getItems().addAll(this.model.getAllVertex());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtPassi != null : "fx:id=\"txtPassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCorrelate != null : "fx:id=\"btnCorrelate\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxPorzioni != null : "fx:id=\"boxPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
