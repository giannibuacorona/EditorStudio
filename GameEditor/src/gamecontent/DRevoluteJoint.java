package gamecontent;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

public class DRevoluteJoint extends DJoint {

	RevoluteJointDef revoluteJointDef;

	public Vec2 getLocalAnchorA() {
		return revoluteJointDef.localAnchorA;
	}

	public void setLocalAnchorA(Vec2 localAnchorA) {
		this.revoluteJointDef.localAnchorA = localAnchorA;
	}

	public Vec2 getLocalAnchorB() {
		return revoluteJointDef.localAnchorB;
	}

	public void setLocalAnchorB(Vec2 localAnchorB) {
		this.revoluteJointDef.localAnchorB = localAnchorB;
	}

	public float getReferenceAngle() {
		return revoluteJointDef.referenceAngle;
	}

	public void setReferenceAngle(float referenceAngle) {
		this.revoluteJointDef.referenceAngle = referenceAngle;
	}

	public boolean isEnableLimit() {
		return revoluteJointDef.enableLimit;
	}

	public void setEnableLimit(boolean enableLimit) {
		this.revoluteJointDef.enableLimit = enableLimit;
	}

	public float getLowerAngle() {
		return revoluteJointDef.lowerAngle;
	}

	public void setLowerAngle(float lowerAngle) {
		this.revoluteJointDef.lowerAngle = lowerAngle;
	}

	public float getUpperAngle() {
		return revoluteJointDef.upperAngle;
	}

	public void setUpperAngle(float upperAngle) {
		this.revoluteJointDef.upperAngle = upperAngle;
	}

	public boolean isEnableMotor() {
		return revoluteJointDef.enableMotor;
	}

	public void setEnableMotor(boolean enableMotor) {
		this.revoluteJointDef.enableMotor = enableMotor;
	}

	public float getMotorSpeed() {
		return revoluteJointDef.motorSpeed;
	}

	public void setMotorSpeed(float motorSpeed) {
		this.revoluteJointDef.motorSpeed = motorSpeed;
	}

	public float getMaxMotorTorque() {
		return revoluteJointDef.maxMotorTorque;
	}

	public void setMaxMotorTorque(float maxMotorTorque) {
		this.revoluteJointDef.maxMotorTorque = maxMotorTorque;
	}

}
