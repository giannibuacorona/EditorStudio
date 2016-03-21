package gamecontent;

import java.util.Vector;

/**
 * classe antenata di tutti gli oggetti del game content
 * 
 * @author gianni
 *
 */
public class DObject {

	protected GameContent gameContent;
	protected Vector<DObjectListener> dObjectListeners;
	/*
	 * Un DObject è busy durante la propagazione di un DObjectEvent, per evitare
	 * che un ascoltatore modifichi l'oggetto prima che la propagazione della
	 * modifica precedente sia ultimata.
	 */
	protected boolean busy;

	protected DObject() {
		super();
		init();
	}

	public void addDObjectListener(DObjectListener listener) {
		if (!dObjectListeners.contains(listener))
			dObjectListeners.add(listener);
	}

	public void destroy() {

		checkBusy();
		if (!exists()) {
			throw new IllegalStateException("Object doesn't exist!");
		}
		busy = true;

		DObjectEvent event = new DObjectEvent();
		event.setSource(this);
		fireObjectDestroyed(event);

		gameContent.fireObjectDestroyed(event);

		//non esiste più:
		gameContent.remove(this);
		this.gameContent = null;

		busy = false;

	}

	@Override
	public String toString() {
		return "DObject [gameContent=" + gameContent + ", dObjectListeners=" + dObjectListeners + ", busy=" + busy + ", exists()=" + exists() + "]";
	}

	protected void fireObjectDestroyed(DObjectEvent event) {
		// TODO Auto-generated method stub
		for (DObjectListener dObjectListener : dObjectListeners) {
			dObjectListener.objectDestroyed(event);
		}

	}

	protected void checkBusy() {

		if (busy)
			throw new IllegalStateException("Busy object!");
	}

	/*
	 * Un oggetto esiste se e solo se è aggiunto al game content
	 */
	public boolean exists() {

		return (gameContent != null) && gameContent.contains(this);

	}

	void setBusy(boolean busy) {

		this.busy = busy;

	}

	private void init() {

		dObjectListeners = new Vector<>();

	}

}
