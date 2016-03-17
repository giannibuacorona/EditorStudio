package gamecontent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

public class DBody implements Serializable {

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

	Vector<BodyListener> bodyListeners;
	Vector<DJoint> dJoints;

	public Vector<DFixture> getFixtures() {
		return fixtures;
	}

	/**
	 * quando un DBody è distrutto tutti i client dovrebbero annullare i
	 * reference
	 */
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

		BodyEvent event = new BodyEvent();
		event.setSource(this);
		fireDestroyed(event);

	}

	public BodyType getType() {
		return type;
	}

	public void setType(BodyType type) {
		this.type = type;
		BodyEvent event = new BodyEvent();
		event.setSource(this);
		fireTypeChanged(event);
	}

	public Vec2 getPosition() {
		return position;
	}

	public void setPosition(Vec2 position) {
		this.position = position;
		BodyEvent event = new BodyEvent();
		event.setSource(this);
		firePositionChanged(event);
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
		BodyEvent event = new BodyEvent();
		event.setSource(this);
		fireAngleChanged(event);
	}

	public Vec2 getLinearVelocity() {
		return linearVelocity;
	}

	public void setLinearVelocity(Vec2 linearVelocity) {
		this.linearVelocity = linearVelocity;
		BodyEvent event = new BodyEvent();
		event.setSource(this);
		fireLinearvelocityChanged(event);
	}

	public float getAngularVelocity() {
		return angularVelocity;
	}

	public void setAngularVelocity(float angularVelocity) {
		this.angularVelocity = angularVelocity;
		BodyEvent event = new BodyEvent();
		event.setSource(this);
		fireAngularVelocityChanged(event);
	}

	public float getLinearDamping() {
		return linearDamping;
	}

	public void setLinearDamping(float linearDamping) {
		this.linearDamping = linearDamping;
		BodyEvent event = new BodyEvent();
		event.setSource(this);
		fireLinearDampingChanged(event);
	}

	public float getAngularDamping() {
		return angularDamping;
	}

	public void setAngularDamping(float angularDamping) {
		this.angularDamping = angularDamping;
		BodyEvent event = new BodyEvent();
		event.setSource(this);
		fireAngularDampingChanged(event);
	}

	public boolean isAllowSleep() {
		return allowSleep;
	}

	public void setAllowSleep(boolean allowSleep) {
		this.allowSleep = allowSleep;
		BodyEvent event = new BodyEvent();
		event.setSource(this);
		fireAllowSleepChanged(event);
	}

	public boolean isAwake() {
		return awake;
	}

	public void setAwake(boolean awake) {
		this.awake = awake;
		BodyEvent event = new BodyEvent();
		event.setSource(this);
		fireAwakeChanged(event);
	}

	public boolean isFixedRotation() {
		return fixedRotation;
	}

	public void setFixedRotation(boolean fixedRotation) {
		this.fixedRotation = fixedRotation;
		BodyEvent event = new BodyEvent();
		event.setSource(this);
		fireFixedRotationChanged(event);
	}

	public boolean isBullet() {
		return bullet;
	}

	public void setBullet(boolean bullet) {
		this.bullet = bullet;
		BodyEvent event = new BodyEvent();
		event.setSource(this);
		fireBulletChanged(event);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
		BodyEvent event = new BodyEvent();
		event.setSource(this);
		fireActiveChanged(event);
	}

	public float getGravityScale() {
		return gravityScale;
	}

	public void setGravityScale(float gravityScale) {
		this.gravityScale = gravityScale;
		BodyEvent event = new BodyEvent();
		event.setSource(this);
		fireGravityScaleChanged(event);
	}

	//-----------------------------------------------------------

	private void fireDestroyed(BodyEvent event) {
		for (BodyListener bodyListener : bodyListeners) {
			bodyListener.bodyDestroyed(event);
		}

	}

	private void fireTypeChanged(BodyEvent event) {

		for (BodyListener bodyListener : bodyListeners) {
			bodyListener.bodyTypeChanged(event);
		}

	}

	private void firePositionChanged(BodyEvent event) {
		for (BodyListener bodyListener : bodyListeners) {
			bodyListener.bodyPositionChanged(event);
		}

	}

	private void fireAngleChanged(BodyEvent event) {
		for (BodyListener bodyListener : bodyListeners) {
			bodyListener.bodyAngleChanged(event);
		}

	}

	private void fireLinearvelocityChanged(BodyEvent event) {
		for (BodyListener bodyListener : bodyListeners) {
			bodyListener.bodyLinearvelocityChanged(event);
		}

	}

	private void fireAngularVelocityChanged(BodyEvent event) {
		for (BodyListener bodyListener : bodyListeners) {
			bodyListener.bodyAngularVelocityChanged(event);
		}

	}

	private void fireLinearDampingChanged(BodyEvent event) {
		for (BodyListener bodyListener : bodyListeners) {
			bodyListener.bodyLinearDampingChanged(event);
		}

	}

	private void fireAngularDampingChanged(BodyEvent event) {
		for (BodyListener bodyListener : bodyListeners) {
			bodyListener.bodyAngularDampingChanged(event);
		}

	}

	private void fireAllowSleepChanged(BodyEvent event) {
		for (BodyListener bodyListener : bodyListeners) {
			bodyListener.bodyAllowSleepChanged(event);
		}

	}

	private void fireAwakeChanged(BodyEvent event) {
		for (BodyListener bodyListener : bodyListeners) {
			bodyListener.bodyAwakeChanged(event);
		}

	}

	private void fireFixedRotationChanged(BodyEvent event) {
		for (BodyListener bodyListener : bodyListeners) {
			bodyListener.bodyFixedRotationChanged(event);
		}

	}

	private void fireBulletChanged(BodyEvent event) {
		for (BodyListener bodyListener : bodyListeners) {
			bodyListener.bodyBulletChanged(event);
		}

	}

	private void fireActiveChanged(BodyEvent event) {
		for (BodyListener bodyListener : bodyListeners) {
			bodyListener.bodyActiveChanged(event);
		}

	}

	private void fireGravityScaleChanged(BodyEvent event) {
		for (BodyListener bodyListener : bodyListeners) {
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
