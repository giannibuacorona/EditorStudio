package gamecontent;

import java.util.Vector;

/*
 * TODO: fire-event contengono solo il loop di notifica: incorporare la gestione dello stato GET_ONLY
 * in modo che chi volesse propagare l'evento possa farlo senza preoccuparsi del problema dello stato.
 * 
 */

/**
 * classe antenata di tutti gli oggetti del game content
 * 
 * GET ONLY <br/>
 * Durante una notifica agli ascoltatori (fire-event) gli oggetti del game
 * content sono in stato GET_ONLY. Non è possibile effettuare nessuna modifica
 * al modello, ne di dati ne strutturale. Alla fine del loop di notifica gli
 * oggetti rientrano nello stato normale.
 * 
 * @author gianni
 *
 */
public class DObject {

	protected GameContent gameContent;
	protected Vector<DObjectListener> dObjectListeners;
	/*
	 * Un DObject è GET_ONLY durante la propagazione di un DObjectEvent, per
	 * evitare che un ascoltatore modifichi l'oggetto prima che la propagazione
	 * della modifica precedente sia ultimata.
	 */
	protected boolean getOnly;

	DObject() {
		super();
		dObjectListeners = new Vector<>();
	}

	public void addDObjectListener(DObjectListener listener) {
		if (!dObjectListeners.contains(listener))
			dObjectListeners.add(listener);
	}

	public void destroy() {

		if (exists())
			gameContent.destroy(this);

	}

	@Override
	public String toString() {
		return "DObject [gameContent=" + gameContent + ", dObjectListeners=" + dObjectListeners + ", busy=" + getOnly + ", exists()=" + exists() + "]";
	}

	protected void fireObjectDestroyed(DObjectEvent event) {

		for (DObjectListener dObjectListener : dObjectListeners) {
			dObjectListener.objectDestroyed(event);
		}

	}

	protected void checkStatus() {

		if (getOnly)
			throw new IllegalStateException("Status: GET_ONLY");
	}

	/**
	 * Un oggetto esiste se e solo se è contenuto nel game content
	 */
	public boolean exists() {

		return (gameContent != null) && gameContent.contains(this);

	}

	void setGetOnly(boolean getOnly) {

		this.getOnly = getOnly;

	}

}
