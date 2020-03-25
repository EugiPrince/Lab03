package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dictionary {
	
	private List<String> parole; // = new ArrayList<>();
	private Set<String> parole2;
	
	public Dictionary() {
		parole = new ArrayList<String>();
		parole2 = new HashSet<String>();
	}

	/**
	 * Carica il dizionario della lingua selezionata, passata come parametro
	 * @param language (English o Italian)
	 */
	public void loadDictionary(String language) {
		parole.clear();
		parole2.clear();
		if(language.compareTo("English")==0) {
			 try {
	             FileReader fr = new FileReader("src/main/resources/English.txt");
	             BufferedReader br = new BufferedReader(fr);
	             String word;
	             while ((word = br.readLine()) != null) {
	            	 parole.add(word);
	            	 //parole2.add(word);
	             }
	             br.close();
	             
	        } catch (IOException e){
	             System.out.println("Errore nella lettura del file");
	        }
	          
		}
		else if(language.compareTo("Italian")==0) {
			try {
	             FileReader fr = new FileReader("src/main/resources/Italian.txt");
	             BufferedReader br = new BufferedReader(fr);
	             String word;
	             while ((word = br.readLine()) != null) {
	            	parole.add(word);
	            	//parole2.add(word);
	             }
	             br.close();
	             
	        } catch (IOException e){
	             System.out.println("Errore nella lettura del file");
	        }
	          
		}
	}
	
	public List<Word> spellCheckTest(List<String> inputTextList) {
		List<Word> output = new ArrayList<>();
		
		for(String s : inputTextList) {
			if(!parole2.contains(s)) {
				Word wTemp = new Word(s);
				output.add(wTemp);
			}
		}
		return output;
	}
	
	public List<Word> spellCheckLinear(List<String> inputTextList) {
		List<Word> output = new ArrayList<>();
		List<Word> listaVera = new ArrayList<>();
		
		for(String s : inputTextList) {
			Word wTemp = new Word(s);
			listaVera.add(wTemp);
		}
		
		for(int i=0; i<listaVera.size(); i++) {
			for(int k=0; k<parole.size(); k++) {
				if(listaVera.get(i).getParola().equals(parole.get(k))) {
					listaVera.get(i).setCorretta(true);
				}
			}
		}
			
		for(Word w : listaVera) {
			if(w.isCorretta()==false)
				output.add(w);
		}
		return output;
	}
	
	public List<Word> spellCheckDichotomic(List<String> inputTextList) {
		List<Word> output = new ArrayList<>();
		List<Word> listaVera = new ArrayList<>();
		
		for(String s : inputTextList) {
			Word wTemp = new Word(s);
			listaVera.add(wTemp);
		}
		
		int partenza = parole.size()/2;
		
		for(int i=0; i<listaVera.size(); i++) {
			if(listaVera.get(i).getParola().compareTo(parole.get(partenza))==0) {
				listaVera.get(i).setCorretta(true);
			}
			else if(listaVera.get(i).getParola().compareTo(parole.get(partenza))>0) {
				for(int k=0; k<partenza; k++) {
					if(listaVera.get(i).getParola().equals(parole.get(k)))
						listaVera.get(i).setCorretta(true);
				}
			}
			else {
				for(int k=partenza; k<parole.size(); k++) {
					if(listaVera.get(i).getParola().equals(parole.get(k)))
						listaVera.get(i).setCorretta(true);
				}
			}
		}
		
		for(Word w : listaVera) {
			if(w.isCorretta()==false)
				output.add(w);
		}
		return output;
	}
}
