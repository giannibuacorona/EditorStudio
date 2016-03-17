package gamecontent;

import org.jbox2d.common.Vec2;

public class DRevoluteJoint extends DJoint {

	/**
	 * The local anchor point relative to body1's origin.
	 */
	public Vec2 localAnchorA;

	/**
	 * The local anchor point relative to body2's origin.
	 */
	public Vec2 localAnchorB;

	/**
	 * The body2 angle minus body1 angle in the reference state (radians).
	 */
	public float referenceAngle;

	/**
	 * A flag to enable joint limits.
	 */
	public boolean enableLimit;

	/**
	 * The lower angle for the joint limit (radians).
	 */
	public float lowerAngle;

	/**
	 * The upper angle for the joint limit (radians).
	 */
	public float upperAngle;

	/**
	 * A flag to enable the joint motor.
	 */
	public boolean enableMotor;

	/**
	 * The desired motor speed. Usually in radians per second.
	 */
	public float motorSpeed;

	/**
	 * The maximum motor torque used to achieve the desired motor speed. Usually
	 * in N-m.
	 */
	public float maxMotorTorque;

	public Vec2 getLocalAnchorA() {
		return localAnchorA;
	}

	public void setLocalAnchorA(Vec2 localAnchorA) {
		this.localAnchorA = localAnchorA;
	}

	public Vec2 getLocalAnchorB() {
		return localAnchorB;
	}

	public void setLocalAnchorB(Vec2 localAnchorB) {
		this.localAnchorB = localAnchorB;
	}

	public float getReferenceAngle() {
		return referenceAngle;
	}

	public void setReferenceAngle(float referenceAngle) {
		this.referenceAngle = referenceAngle;
	}

	public boolean isEnableLimit() {
		return enableLimit;
	}

	public void setEnableLimit(boolean enableLimit) {
		this.enableLimit = enableLimit;
	}

	public float getLowerAngle() {
		return lowerAngle;
	}

	public void setLowerAngle(float lowerAngle) {
		this.lowerAngle = lowerAngle;
	}

	public float getUpperAngle() {
		return upperAngle;
	}

	public void setUpperAngle(float upperAngle) {
		this.upperAngle = upperAngle;
	}

	public boolean isEnableMotor() {
		return enableMotor;
	}

	public void setEnableMotor(boolean enableMotor) {
		this.enableMotor = enableMotor;
	}

	public float getMotorSpeed() {
		return motorSpeed;
	}

	public void setMotorSpeed(float motorSpeed) {
		this.motorSpeed = motorSpeed;
	}

	public float getMaxMotorTorque() {
		return maxMotorTorque;
	}

	public void setMaxMotorTorque(float maxMotorTorque) {
		this.maxMotorTorque = maxMotorTorque;
	}

}
