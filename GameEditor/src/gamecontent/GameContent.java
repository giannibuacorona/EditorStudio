package gamecontent;

import java.util.Collection;
import java.util.Vector;

public class GameContent {

	Collection<DObject> collection;

	Vector<DObjectListener> listeners;

	public GameContent() {
		super();
		init();
	}

	boolean remove(Object o) {

		return collection.remove(o);
	}

	public boolean contains(Object o) {
		return collection.contains(o);
	}

	/*
	 * Un oggetto esiste per il game content quando viene aggiunto al game
	 * content; "add equivale a created"
	 */
	public boolean add(DObject e) {

		e.checkBusy();

		if (e.gameContent != null)
			throw new IllegalStateException("e.gameContent not null!");

		if (collection.contains(e))
			throw new IllegalStateException("Object already added!");

		boolean res = collection.add(e);
		e.gameContent = this;

		DObjectEvent event = new DObjectEvent();

		event.setSource(e);

		e.setBusy(true);

		for (DObjectListener dObjectListener : listeners) {

			dObjectListener.objectCreated(event);
		}

		e.setBusy(false);

		return res;
	}

	public void addDObjectListener(DObjectListener listener) {

		if (!listeners.contains(listener))
			listeners.add(listener);

	}

	private void init() {

		collection = new Vector<>();
		listeners = new Vector<>();

	}

	void fireObjectDestroyed(DObjectEvent event) {

		for (DObjectListener dObjectListener : listeners) {

			dObjectListener.objectDestroyed(event);
		}
	}

}
