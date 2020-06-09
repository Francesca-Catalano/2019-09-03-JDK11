/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.ResourceBundle;
import it.polito.tdp.food.model.Model;
import it.polito.tdp.food.model.Vicino;
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
    	txtResult.appendText("Cerco cammino peso massimo...\n");
    	String ss = this.txtPassi.getText();
    	if(ss==null)
    	{
    		this.txtResult.appendText("Inserire le calorie!\n");
    		return;
    	}
    	int num;
    	try {
    		
    			num= Integer.parseInt(ss);
    		
    		
    	} catch( NumberFormatException e)
    	{
    		this.txtResult.appendText("Formato non valido!\n");
    		return;	
    	}
    	String s = this.boxPorzioni.getValue();
    	if(s==null)
    	{
    		this.txtResult.appendText("Selezionare un elemento\n");
    		return;
    	}
    	
    	if(this.model.cammino(num,s)==null)
    	{
    		this.txtResult.appendText("Errore nella ricerca del cammino\n");
    		return;
    	}
    	for(String sol : this.model.cammino(num,s))
    	{
    		this.txtResult.appendText(sol+"\n");
    	}
    	this.txtResult.appendText("Peso : "+this.model.getBestPeso()+"\n");
    }

    @FXML
    void doCorrelate(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Cerco porzioni correlate...\n");
    	String s = this.boxPorzioni.getValue();
    	if(s==null)
    	{
    		this.txtResult.appendText("Selezionare un elemento\n");
    		return;
    	}
    if(	this.model.doCorrelat(s)== null)
    {
    	this.txtResult.appendText("Errore lettura vicizi\n");
		return;
    }
    this.txtResult.appendText("LIST VICINI:\n");
    for(Vicino v : this.model.doCorrelat(s))
    {
    	this.txtResult.appendText(v.toString()+"\n");
    }
    	
    this.btnCammino.setDisable(false);
	this.txtPassi.setDisable(false);
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Creazione grafo...");
    	String s = this.txtCalorie.getText();
    	if(s==null)
    	{
    		this.txtResult.appendText("Inserire le calorie!\n");
    		return;
    	}
    	double num;
    	try {
    		if(s.contains(",")|| s.contains("."))
    		{
    			num= Double.parseDouble(s);
    		}
    		else
    		{
    			num= (double) Integer.parseInt(s);
    		}
    		
    	} catch( NumberFormatException e)
    	{
    		this.txtResult.appendText("Formato non valido!\n");
    		return;	
    	}
    	
    	this.model.creaGrafo(num);
    	this.btnCorrelate.setDisable(false);
    	this.boxPorzioni.setDisable(false);
    	this.boxPorzioni.getItems().addAll(this.model.listVertex());    	
    	this.txtResult.appendText("#VERTICI: "+ this.model.listVertex().size()+"\n");
    	this.txtResult.appendText("#Archi: "+ this.model.listEdges().size()+"\n");
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
    	this.btnCammino.setDisable(true);
    	this.txtPassi.setDisable(true);
    	this.btnCorrelate.setDisable(true);
    	this.boxPorzioni.setDisable(true);
    	
    }
}
