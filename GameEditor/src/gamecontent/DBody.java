package gamecontent;

import java.util.Vector;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;

public class DBody {

	BodyDef bodyDef;

	/*
	 * 1 body può avere molte fixture, 1 fixtura appartiene ad un solo body
	 */
	Vector<DFixture> fixtures;

	@Override
	public int hashCode() {
		return bodyDef.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return bodyDef.equals(obj);
	}

	public BodyType getType() {
		return bodyDef.getType();
	}

	public void setType(BodyType type) {
		bodyDef.setType(type);
	}

	public Object getUserData() {
		return bodyDef.getUserData();
	}

	public void setUserData(Object userData) {
		bodyDef.setUserData(userData);
	}

	public Vec2 getPosition() {
		return bodyDef.getPosition();
	}

	public void setPosition(Vec2 position) {
		bodyDef.setPosition(position);
	}

	public float getAngle() {
		return bodyDef.getAngle();
	}

	public void setAngle(float angle) {
		bodyDef.setAngle(angle);
	}

	public Vec2 getLinearVelocity() {
		return bodyDef.getLinearVelocity();
	}

	public void setLinearVelocity(Vec2 linearVelocity) {
		bodyDef.setLinearVelocity(linearVelocity);
	}

	public float getAngularVelocity() {
		return bodyDef.getAngularVelocity();
	}

	public void setAngularVelocity(float angularVelocity) {
		bodyDef.setAngularVelocity(angularVelocity);
	}

	public float getLinearDamping() {
		return bodyDef.getLinearDamping();
	}

	public void setLinearDamping(float linearDamping) {
		bodyDef.setLinearDamping(linearDamping);
	}

	public float getAngularDamping() {
		return bodyDef.getAngularDamping();
	}

	public void setAngularDamping(float angularDamping) {
		bodyDef.setAngularDamping(angularDamping);
	}

	public boolean isAllowSleep() {
		return bodyDef.isAllowSleep();
	}

	public void setAllowSleep(boolean allowSleep) {
		bodyDef.setAllowSleep(allowSleep);
	}

	public boolean isAwake() {
		return bodyDef.isAwake();
	}

	public void setAwake(boolean awake) {
		bodyDef.setAwake(awake);
	}

	public boolean isFixedRotation() {
		return bodyDef.isFixedRotation();
	}

	public void setFixedRotation(boolean fixedRotation) {
		bodyDef.setFixedRotation(fixedRotation);
	}

	public boolean isBullet() {
		return bodyDef.isBullet();
	}

	public void setBullet(boolean bullet) {
		bodyDef.setBullet(bullet);
	}

	@Override
	public String toString() {
		return bodyDef.toString();
	}

	public boolean isActive() {
		return bodyDef.isActive();
	}

	public void setActive(boolean active) {
		bodyDef.setActive(active);
	}

	public float getGravityScale() {
		return bodyDef.getGravityScale();
	}

	public void setGravityScale(float gravityScale) {
		bodyDef.setGravityScale(gravityScale);
	}

}
