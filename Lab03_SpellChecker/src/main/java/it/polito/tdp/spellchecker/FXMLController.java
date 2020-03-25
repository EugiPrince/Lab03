/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary dictionary;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="menuBtn"
    private MenuButton menuBtn; // Value injected by FXMLLoader

    @FXML // fx:id="menuItemEnglish"
    private MenuItem menuItemEnglish; // Value injected by FXMLLoader

    @FXML // fx:id="menuItemItaliano"
    private MenuItem menuItemItaliano; // Value injected by FXMLLoader

    @FXML // fx:id="txtInput"
    private TextArea txtInput; // Value injected by FXMLLoader

    @FXML // fx:id="spellBtn"
    private Button spellBtn; // Value injected by FXMLLoader

    @FXML // fx:id="txtParoleSbagliate"
    private TextArea txtParoleSbagliate; // Value injected by FXMLLoader

    @FXML // fx:id="labelErrori"
    private Label labelErrori; // Value injected by FXMLLoader

    @FXML // fx:id="clearBtn"
    private Button clearBtn; // Value injected by FXMLLoader

    @FXML // fx:id="labelTempo"
    private Label labelTempo; // Value injected by FXMLLoader

    @FXML
    void chooseEnglish(ActionEvent event) {
    	this.menuBtn.setText("English");
    	this.dictionary.loadDictionary("English");
    }

    @FXML
    void chooseItaliano(ActionEvent event) {
    	this.menuBtn.setText("Italian");
    	this.dictionary.loadDictionary("Italian");
    }

    @FXML
    void doClear(ActionEvent event) {
    	this.txtInput.clear();
    	this.txtParoleSbagliate.clear();
    }

    @FXML
    void doSpell(ActionEvent event) {
    	//Devo leggere l'input dell'utente
    	
    	long startTime = System.nanoTime();
    	
    	String input = this.txtInput.getText();
    	input = input.replaceAll("[.,\\\\/#!$%\\\\^&\\\\*;:{}=\\\\-_`~()\\\\[\\\\]\\\"]", "");
    	input = input.toLowerCase();
    	
    	List<Word> paroleSbagliate = new ArrayList<>();
    	List <String> paroleInserite = new ArrayList<>();
    	
    	//Set<Word> paroleSbagliate2 = new HashSet<>();
    	//Set<String> paroleInserite2 = new HashSet<>();
    	
    	String[] arrayParole;
    	arrayParole = input.split(" ");
    	
    	for(String s : arrayParole)
    		paroleInserite.add(s);
    	
    	//paroleSbagliate = this.dictionary.spellCheckTest(paroleInserite);
    	//paroleSbagliate = this.dictionary.spellCheckTest(paroleInserite2);
    	
    	//Esercizio 2
    	paroleSbagliate = this.dictionary.spellCheckLinear(paroleInserite);
    	//paroleSbagliate = this.dictionary.spellCheckDichotomic(paroleInserite);
    	
    	for(Word w : paroleSbagliate)
    		this.txtParoleSbagliate.appendText(w.toString());
    	
    	long endTime = System.nanoTime();
    	
    	long elapsedTime = (endTime - startTime);
    	
    	float tempo = (float)elapsedTime/1000000000;
    	
    	this.labelErrori.setText("The text contains "+paroleSbagliate.size()+" errors.");
    	this.labelTempo.setText("Spellcheck completed in "+tempo+" seconds.");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert menuBtn != null : "fx:id=\"menuBtn\" was not injected: check your FXML file 'Scene.fxml'.";
        assert menuItemEnglish != null : "fx:id=\"menuItemEnglish\" was not injected: check your FXML file 'Scene.fxml'.";
        assert menuItemItaliano != null : "fx:id=\"menuItemItaliano\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert spellBtn != null : "fx:id=\"spellBtn\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtParoleSbagliate != null : "fx:id=\"txtParoleSbagliate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert labelErrori != null : "fx:id=\"labelErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clearBtn != null : "fx:id=\"clearBtn\" was not injected: check your FXML file 'Scene.fxml'.";
        assert labelTempo != null : "fx:id=\"labelTempo\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Dictionary dictionary) {
    	this.dictionary = dictionary;
    }
}
