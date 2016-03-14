package gamecontent;

import org.jbox2d.dynamics.joints.JointDef;
import org.jbox2d.dynamics.joints.JointType;

/**
 * joint data
 * 
 * @author gianni
 *
 */
public class DJoint {

	JointDef jointDef;

	DBody bodyA, bodyB;
	/**
	 * The joint type is set automatically for concrete joint types.
	 */
	public JointType type;

	/**
	 * Use this to attach application specific data to your joints.
	 */
	public Object userData;

	public DBody getBodyA() {
		return bodyA;
	}

	public void setBodyA(DBody bodyA) {
		this.bodyA = bodyA;
	}

	public DBody getBodyB() {
		return bodyB;
	}

	public void setBodyB(DBody bodyB) {
		this.bodyB = bodyB;
	}

	/**
	 * Set this flag to true if the attached bodies should collide.
	 */
	public boolean collideConnected;

	public JointType getType() {
		return jointDef.type;
	}

	public void setType(JointType type) {
		this.jointDef.type = type;
	}

	public Object getUserData() {
		return jointDef.userData;
	}

	public void setUserData(Object userData) {
		this.jointDef.userData = userData;
	}

	public boolean isCollideConnected() {
		return jointDef.collideConnected;
	}

	public void setCollideConnected(boolean collideConnected) {
		this.jointDef.collideConnected = collideConnected;
	}

}
