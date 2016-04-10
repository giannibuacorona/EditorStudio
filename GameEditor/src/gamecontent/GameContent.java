package gamecontent;

import java.util.Collection;
import java.util.Vector;

/*
 * TODO: Lo stato GETONLY riguarda anche le modifiche strutturali? Durante una notifica cioè non si devono
 * poter creare o distruggere oggetti?
 */
/**
 * Gestisce la collezione degli oggetti del modello. notifica le modifiche di
 * creazione e distruzione di oggetti del modello. Presso un GameContent si
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
	 * @return l'oggetto creato
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
	 * @return l'oggetto creato
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
	 * @return l'oggetto creato
	 */

	public DRevoluteJoint createDRevoluteJoint() {

		DRevoluteJoint res = new DRevoluteJoint();
		add(res);
		/*
		 * TODO: metere qua la notifica agli ascolatatori in modo da notificare
		 * fireRevoluteJointCreated invece di fireObjectCreated
		 */
		return res;
	}

	/*
	 * Chiamato solo da destroy di DObject
	 */
	void destroy(DObject object) {

		object.checkStatus();

		//oggetto già distrutto
		if (!object.exists())
			throw new IllegalStateException("DObject doesn't exist!");

		DObjectEvent event = new DObjectEvent();
		event.setSource(object);

		object.gameContent = null;

		object.setGetOnly(true);

		//notifica ascoltatori registrati presso il game content
		fireObjectDestroyed(event);

		//notifica ascoltatori dell'oggetto
		object.fireObjectDestroyed(event);

		object.setGetOnly(false);

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

		//TODO: fireXxxCreated o solo fireObjectCreated?
		e.setGetOnly(true);

		for (DObjectListener dObjectListener : listeners) {

			dObjectListener.objectCreated(event);
		}

		e.setGetOnly(false);

	}

	/**
	 * Registra un ascoltatore
	 * 
	 * @param listener
	 *            l'ascoltatore
	 */
	public void addDObjectListener(DObjectListener listener) {

		if (!listeners.contains(listener))
			listeners.add(listener);

	}

	/*
	 * notifica agli ascoltatori l'evento di distruzione di un oggetto
	 */
	void fireObjectDestroyed(DObjectEvent event) {

		for (DObjectListener dObjectListener : listeners) {

			dObjectListener.objectDestroyed(event);
		}
	}

}
