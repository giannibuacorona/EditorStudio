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

	public void destroy() {

		checkBusy();
		busy = true;
		gameContent.remove(this);
		DObjectEvent event = new DObjectEvent();
		event.setSource(this);
		fireObjectDestroyed(event);
		busy = false;

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

	void setBusy(boolean busy) {

		this.busy = busy;

	}

}
