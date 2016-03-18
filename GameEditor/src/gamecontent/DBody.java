package gamecontent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

public class DBody extends DObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//BodyDef bodyDef = new BodyDef();

	/*
	 * 1 body può avere molte fixture, 1 fixtura appartiene ad un solo body
	 */
	Vector<DFixture> fixtures = new Vector<>();

	/**
	 * The body type: static, kinematic, or dynamic. Note: if a dynamic body
	 * would have zero mass, the mass is set to one.
	 */
	public BodyType type;

	/**
	 * Use this to store application specific body data.
	 */
	public Object userData;

	/**
	 * The world position of the body. Avoid creating bodies at the origin since
	 * this can lead to many overlapping shapes.
	 */
	public Vec2 position;

	/**
	 * The world angle of the body in radians.
	 */
	public float angle;

	/**
	 * The linear velocity of the body in world co-ordinates.
	 */
	public Vec2 linearVelocity;

	/**
	 * The angular velocity of the body.
	 */
	public float angularVelocity;

	/**
	 * Linear damping is use to reduce the linear velocity. The damping
	 * parameter can be larger than 1.0f but the damping effect becomes
	 * sensitive to the time step when the damping parameter is large.
	 */
	public float linearDamping;

	/**
	 * Angular damping is use to reduce the angular velocity. The damping
	 * parameter can be larger than 1.0f but the damping effect becomes
	 * sensitive to the time step when the damping parameter is large.
	 */
	public float angularDamping;

	/**
	 * Set this flag to false if this body should never fall asleep. Note that
	 * this increases CPU usage.
	 */
	public boolean allowSleep;

	/**
	 * Is this body initially sleeping?
	 */
	public boolean awake;

	/**
	 * Should this body be prevented from rotating? Useful for characters.
	 */
	public boolean fixedRotation;

	/**
	 * Is this a fast moving body that should be prevented from tunneling
	 * through other moving bodies? Note that all bodies are prevented from
	 * tunneling through kinematic and static bodies. This setting is only
	 * considered on dynamic bodies.
	 * 
	 * @warning You should use this flag sparingly since it increases processing
	 *          time.
	 */
	public boolean bullet;

	/**
	 * Does this body start out active?
	 */
	public boolean active;

	/**
	 * Experimental: scales the inertia tensor.
	 */
	public float gravityScale;

	Vector<DJoint> dJoints;

	//body listener
	Vector<DBodyListener> bodyListeners;

	public Vector<DFixture> getFixtures() {
		return fixtures;
	}

	/**
	 * quando un DBody è distrutto tutti i client dovrebbero annullare i
	 * reference
	 */
	@Override
	public void destroy() {

		//annullare i reference
		for (DFixture dFixture : fixtures) {
			dFixture.owner = null;
		}

		fixtures = null;

		for (DJoint dJoint : dJoints) {
			if (dJoint.bodyA == this) {
				dJoint.bodyA = null;
			} else if (dJoint.bodyB == this) {
				dJoint.bodyB = null;
			} else
				throw new IllegalStateException("Mega bug! DJoint non contiene this DBody.");
		}

		dJoints = null;

		super.destroy();

	}

	public BodyType getType() {
		return type;
	}

	public void setType(BodyType type) {
		checkBusy();
		this.type = type;
		DObjectEvent event = new DObjectEvent();
		event.setSource(this);
		busy = true;
		fireTypeChanged(event);
		busy = false;
	}

	public Vec2 getPosition() {
		return position;
	}

	public void setPosition(Vec2 position) {
		checkBusy();
		this.position = position;
		DObjectEvent event = new DObjectEvent();
		event.setSource(this);
		busy = true;
		firePositionChanged(event);
		busy = false;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		checkBusy();
		this.angle = angle;
		DObjectEvent event = new DObjectEvent();
		event.setSource(this);
		busy = true;
		fireAngleChanged(event);
		busy = false;
	}

	public Vec2 getLinearVelocity() {
		return linearVelocity;
	}

	public void setLinearVelocity(Vec2 linearVelocity) {
		checkBusy();
		this.linearVelocity = linearVelocity;
		DObjectEvent event = new DObjectEvent();
		event.setSource(this);
		busy = true;
		fireLinearvelocityChanged(event);
		busy = false;
	}

	public float getAngularVelocity() {
		return angularVelocity;
	}

	public void setAngularVelocity(float angularVelocity) {
		checkBusy();
		this.angularVelocity = angularVelocity;
		DObjectEvent event = new DObjectEvent();
		event.setSource(this);
		busy = true;
		fireAngularVelocityChanged(event);
		busy = false;
	}

	public float getLinearDamping() {
		return linearDamping;
	}

	public void setLinearDamping(float linearDamping) {
		checkBusy();
		this.linearDamping = linearDamping;
		DObjectEvent event = new DObjectEvent();
		event.setSource(this);
		busy = true;
		fireLinearDampingChanged(event);
		busy = false;
	}

	public float getAngularDamping() {
		return angularDamping;
	}

	public void setAngularDamping(float angularDamping) {
		checkBusy();
		this.angularDamping = angularDamping;
		DObjectEvent event = new DObjectEvent();
		event.setSource(this);
		busy = true;
		fireAngularDampingChanged(event);
		busy = false;
	}

	public boolean isAllowSleep() {
		return allowSleep;
	}

	public void setAllowSleep(boolean allowSleep) {
		checkBusy();
		this.allowSleep = allowSleep;
		DObjectEvent event = new DObjectEvent();
		event.setSource(this);
		busy = true;
		fireAllowSleepChanged(event);
		busy = false;
	}

	public boolean isAwake() {
		return awake;
	}

	public void setAwake(boolean awake) {
		checkBusy();
		this.awake = awake;
		DObjectEvent event = new DObjectEvent();
		event.setSource(this);
		busy = true;
		fireAwakeChanged(event);
		busy = false;
	}

	public boolean isFixedRotation() {
		return fixedRotation;
	}

	public void setFixedRotation(boolean fixedRotation) {
		checkBusy();
		this.fixedRotation = fixedRotation;
		DObjectEvent event = new DObjectEvent();
		event.setSource(this);
		busy = true;
		fireFixedRotationChanged(event);
		busy = false;
	}

	public boolean isBullet() {
		return bullet;
	}

	public void setBullet(boolean bullet) {
		checkBusy();
		this.bullet = bullet;
		DObjectEvent event = new DObjectEvent();
		event.setSource(this);
		busy = true;
		fireBulletChanged(event);
		busy = false;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		checkBusy();
		this.active = active;
		DObjectEvent event = new DObjectEvent();
		event.setSource(this);
		busy = true;
		fireActiveChanged(event);
		busy = false;
	}

	public float getGravityScale() {
		return gravityScale;
	}

	public void setGravityScale(float gravityScale) {
		checkBusy();
		this.gravityScale = gravityScale;
		DObjectEvent event = new DObjectEvent();
		event.setSource(this);
		busy = true;
		fireGravityScaleChanged(event);
		busy = false;
	}

	//-----------------------------------------------------------

	private void fireTypeChanged(DObjectEvent event) {

		for (DBodyListener bodyListener : bodyListeners) {
			bodyListener.bodyTypeChanged(event);
		}

	}

	private void firePositionChanged(DObjectEvent event) {
		for (DBodyListener bodyListener : bodyListeners) {
			bodyListener.bodyPositionChanged(event);
		}

	}

	private void fireAngleChanged(DObjectEvent event) {
		for (DBodyListener bodyListener : bodyListeners) {
			bodyListener.bodyAngleChanged(event);
		}

	}

	private void fireLinearvelocityChanged(DObjectEvent event) {
		for (DBodyListener bodyListener : bodyListeners) {
			bodyListener.bodyLinearvelocityChanged(event);
		}

	}

	private void fireAngularVelocityChanged(DObjectEvent event) {
		for (DBodyListener bodyListener : bodyListeners) {
			bodyListener.bodyAngularVelocityChanged(event);
		}

	}

	private void fireLinearDampingChanged(DObjectEvent event) {
		for (DBodyListener bodyListener : bodyListeners) {
			bodyListener.bodyLinearDampingChanged(event);
		}

	}

	private void fireAngularDampingChanged(DObjectEvent event) {
		for (DBodyListener bodyListener : bodyListeners) {
			bodyListener.bodyAngularDampingChanged(event);
		}

	}

	private void fireAllowSleepChanged(DObjectEvent event) {
		for (DBodyListener bodyListener : bodyListeners) {
			bodyListener.bodyAllowSleepChanged(event);
		}

	}

	private void fireAwakeChanged(DObjectEvent event) {
		for (DBodyListener bodyListener : bodyListeners) {
			bodyListener.bodyAwakeChanged(event);
		}

	}

	private void fireFixedRotationChanged(DObjectEvent event) {
		for (DBodyListener bodyListener : bodyListeners) {
			bodyListener.bodyFixedRotationChanged(event);
		}

	}

	private void fireBulletChanged(DObjectEvent event) {
		for (DBodyListener bodyListener : bodyListeners) {
			bodyListener.bodyBulletChanged(event);
		}

	}

	private void fireActiveChanged(DObjectEvent event) {
		for (DBodyListener bodyListener : bodyListeners) {
			bodyListener.bodyActiveChanged(event);
		}

	}

	private void fireGravityScaleChanged(DObjectEvent event) {
		for (DBodyListener bodyListener : bodyListeners) {
			bodyListener.bodyGravityScaleChanged(event);
		}

	}

	public static void main(String[] args) {

		try {
			FileOutputStream fileOutputStream = new FileOutputStream("DBody3.dat");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			DBody obj = new DBody();
			obj.fixtures.add(new DFixture());
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
