package it.polito.tdp.Anagramma.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {
	private List<String> soluzione;

	/**
	 * Genera tutti gli anagrammi della parola specificata in ingresso
	 * @param parola parola da anagrammare
	 * @return elenco di tutti gli anagrammi della parola data
	 */
	public List<String> anagrammi(String parola){
		//caso iniziale-->livello zero: preparo variabili di cui abbiamo bisogno
		this.soluzione= new ArrayList<String>();
		
		parola= parola.toUpperCase();
		
		List<Character> disponibili= new ArrayList<>();
		for(int i=0; i<parola.length(); i++) {
			disponibili.add(parola.charAt(i)); //per ciascuna iterazione prendo il carattere in posizione i-esima e lo aggiungo alla lista
		}
		
		//avvio la ricorsione
		cerca(" ", 0, disponibili);
		
		return this.soluzione;
	}
	/**
	 * Procedura ricorsiva per il calcolo dell'anagramma
	 * @param parziale parte iniziale dell'anagramma costruito fino ad ora
	 * @param livello livello della ricorsione sempre uguale a parziale.lenght()
	 * @param disponibili insieme delle lettere non ancora utilizzate
	 */
	private void cerca(String parziale, int livello, List<Character> disponibili) {
		if(disponibili.size()==0) { //oppure livello== parola.lenght()
			//caso terminale--> livello= lunghezza parola da cercare oppure se non c'è più nulla da aggiungere/ lista di caratteri vuota
			
			//if(parziale è nel dizionario) la aggiungo, altrimenti la ignoro
			//if(parziale non è presente nella soluzione) la aggiungo, altrimenti non aggiungo il duplicato
			this.soluzione.add(parziale);
		}
		
		//caso normale/generale --> provare ad aggiungere alla soluzione parziale tutti i caratteri presenti tra i disponibili
		for(Character ch: disponibili) { //non posso modificare la lista disponibili mentre la stiamo iterando
			String tentativo = parziale + ch;
			
		//	if(nel dizionario esistono delle parole che iniziano con 'tentativo'?)
		//		se si vado avanti e faccio chiamata ricorsiva
		//		altrimenti mi fermo al livello precedente
			
			List<Character> rimanenti= new ArrayList<Character>(disponibili); //creo lista uguale a disponibili e modifico questa
			rimanenti.remove(ch);
			
			cerca(tentativo, livello+1, rimanenti);
			//ad ogni iterazione provo i vari tentativi aggiungendo un carattere diverso
		}
	}
}

/*
Dato di partenza: parola da anagrammare di lunghezza N
Soluzione parziale: è una parte dell'anagramma già costruito (i primi caratteri)
Livello: numero di lettere di cui è composta la soluzione parziale
Soluzione finale: soluzione di lunghezza N -> caso terminale
Caso terminale: salvare la soluzione trovata
Generazione delle nuove soluzioni: provare ad aggiungere una lettera scegliendola tra 
									quelle che non sono ancora state usate nella soluzione parziale
*/