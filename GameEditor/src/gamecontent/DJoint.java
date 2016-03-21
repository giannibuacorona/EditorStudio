package gamecontent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jbox2d.dynamics.joints.JointType;

/**
 * joint data
 * 
 * @author gianni
 *
 */
public class DJoint extends DObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The joint type is set automatically for concrete joint types.
	 */
	public JointType type = JointType.UNKNOWN;

	/**
	 * The first attached body.
	 */
	public DBody bodyA;

	/**
	 * The second attached body.
	 */
	public DBody bodyB;

	/**
	 * Set this flag to true if the attached bodies should collide.
	 */
	public boolean collideConnected;

	/**
	 * Distrugge un DJoint. Un oggetto distrutto non deve più essere utilizzato
	 * per cui imposta a null le variabili di tipo DJoint nel tuo codice. Questo
	 * vale per tutti i sottotipi di DObject.
	 * 
	 */
	@Override
	public void destroy() {

		super.destroy();
		//impostare a null tutti i reference che puntano a this
		if (bodyA != null) {
			bodyA.remove(this);
			bodyA = null;
		}
		if (bodyB != null) {
			bodyB.remove(this);
			bodyB = null;
		}

	}

	public DBody getBodyA() {
		return bodyA;
	}

	/**
	 * 
	 * @param bodyA
	 *            null stacca il body dal giunto
	 */
	public void setBodyA(DBody bodyA) {

		if (bodyA != null) {

			if (this.bodyB == bodyA)
				throw new IllegalStateException("bodyA == bodyB");

		}

		if (this.bodyA != null) {

			//il bodyA corrente non ha più this giunto
			this.bodyA.remove(this);

		}

		this.bodyA = bodyA;

		if (bodyA != null)
			bodyA.add(this);
	}

	public DBody getBodyB() {
		return bodyB;
	}

	/**
	 * 
	 * @param bodyB
	 *            null per staccare il body corrente dal giunto
	 */
	public void setBodyB(DBody bodyB) {

		if (bodyB != null) {

			if (this.bodyB == bodyA)
				throw new IllegalStateException("bodyA == bodyB");

		}

		if (this.bodyB != null) {
			//il bodyB corrente non ha più this giunto
			this.bodyB.remove(this);
		}

		this.bodyB = bodyB;

		if (bodyB != null)
			bodyB.add(this);
	}

	void remove(DBody body) {

		if (bodyA == body) {
			bodyA = null;
		} else if (bodyB == body) {
			bodyB = null;
		} else
			throw new IllegalStateException("Mega bug! DJoint non contiene this DBody.");
	}

	public JointType getType() {
		return type;
	}

	public void setType(JointType type) {
		this.type = type;
	}

	public boolean isCollideConnected() {
		return collideConnected;
	}

	public void setCollideConnected(boolean collideConnected) {
		this.collideConnected = collideConnected;
	}

	public static void main(String[] args) {

		try {
			FileOutputStream fileOutputStream = new FileOutputStream("DJoint.dat");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			DJoint obj = new DJoint();

			objectOutputStream.writeObject(obj);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
