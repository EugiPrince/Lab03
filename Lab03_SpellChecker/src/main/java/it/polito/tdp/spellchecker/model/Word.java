package it.polito.tdp.spellchecker.model;

public class Word{
	
	private String parola;
	private boolean corretta;

	public Word(String parola) {
		this.parola = parola;
		this.corretta = false;
	}
	
	public String getParola() {
		return this.parola;
	}
	
	public void setParola(String p) {
		this.parola = p;
	}

	/**
	 * Ritorna {@code true} se la parola inserita e' corretta, {@code false} altrimenti
	 * @return
	 */
	public boolean isCorretta() {
		return corretta;
	}

	public void setCorretta(boolean corretta) {
		this.corretta = corretta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parola == null) ? 0 : parola.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (parola == null) {
			if (other.parola != null)
				return false;
		} else if (!parola.equals(other.parola))
			return false;
		return true;
	}

	public String toString() {
		return parola+"\n";
	}
}
