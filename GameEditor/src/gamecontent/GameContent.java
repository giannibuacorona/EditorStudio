package gamecontent;

import java.util.Collection;
import java.util.Vector;

public class GameContent {

	Collection<DObject> collection;

	Vector<DObjectListener> listeners;

	boolean remove(Object o) {

		return collection.remove(o);
	}

	/*
	 * Un oggetto esiste per il game content quando viene aggiunto al game
	 * content; "add equivale a created"
	 */
	public boolean add(DObject e) {

		e.checkBusy();

		if (collection.contains(e))
			throw new IllegalStateException("Object already added!");

		boolean res = collection.add(e);

		DObjectEvent event = new DObjectEvent();

		event.setSource(e);

		e.setBusy(true);
		for (DObjectListener dObjectListener : listeners) {

			dObjectListener.objectCreated(event);
		}
		e.setBusy(false);

		return res;
	}

}
