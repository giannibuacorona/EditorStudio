package gamecontent;

import java.util.Collection;
import java.util.Vector;

/**
 * Fornisce una descrizione dei contenuti del gioco e notifica le modifiche di
 * creazione e distruzione che avvengono ai contenuti. Presso un GameContent si
 * possono registrare più ascoltatori.
 * 
 * @author gianni
 *
 */
public class GameContent {

	Collection<DObject> collection;

	Vector<DObjectListener> listeners;

	/**
	 * Crea un GameContent senza contenuti
	 */
	public GameContent() {
		super();
		collection = new Vector<>();
		listeners = new Vector<>();
	}

	/**
	 * Crea un DBody e notifica agli ascoltatori la creazione del nuovo oggetto
	 * 
	 * @return
	 */

	public DBody createDBody() {

		DBody res = new DBody();
		add(res);
		return res;

	}

	/**
	 * Crea un DFixture e notifica agli ascoltatori la creazione del nuovo
	 * oggetto
	 * 
	 * @return
	 */

	public DFixture createDFixture() {

		DFixture res = new DFixture();
		add(res);
		return res;

	}

	/**
	 * Crea un DRevoluteJoint e notifica agli ascoltatori la creazione del nuovo
	 * oggetto
	 * 
	 * @return
	 */

	public DRevoluteJoint createDRevoluteJoint() {

		DRevoluteJoint res = new DRevoluteJoint();
		add(res);
		return res;
	}

	/*
	 * Chiamato solo da destroy di DObject
	 */
	void destroy(DObject object) {

		object.checkBusy();

		//oggetto già distrutto
		if (!object.exists())
			throw new IllegalStateException("DObject doesn't exist!");

		DObjectEvent event = new DObjectEvent();
		event.setSource(object);

		object.gameContent = null;

		object.setBusy(true);

		fireObjectDestroyed(event);

		object.fireObjectDestroyed(event);

		object.setBusy(false);

		collection.remove(object);
	}

	public boolean contains(Object o) {
		return collection.contains(o);
	}

	/*
	 * Chiamato solo dal metodo factory
	 */
	private void add(DObject e) {

		collection.add(e);

		e.gameContent = this;

		DObjectEvent event = new DObjectEvent();

		event.setSource(e);

		e.setBusy(true);

		for (DObjectListener dObjectListener : listeners) {

			dObjectListener.objectCreated(event);
		}

		e.setBusy(false);

	}

	public void addDObjectListener(DObjectListener listener) {

		if (!listeners.contains(listener))
			listeners.add(listener);

	}

	void fireObjectDestroyed(DObjectEvent event) {

		for (DObjectListener dObjectListener : listeners) {

			dObjectListener.objectDestroyed(event);
		}
	}

}
