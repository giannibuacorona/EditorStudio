package gamecontent;

import java.io.Serializable;

import org.jbox2d.dynamics.Filter;

public class DFixture implements Serializable {

	/*
	 * Una fixtura appartiene ad un body
	 */
	DBody owner;

	/**
	 * The shape, this must be set. The shape will be cloned, so you can create
	 * the shape on the stack.
	 */
	public DShape shape = null;

	/**
	 * Use this to store application specific fixture data.
	 */
	public Object userData;

	/**
	 * The friction coefficient, usually in the range [0,1].
	 */
	public float friction;

	/**
	 * The restitution (elasticity) usually in the range [0,1].
	 */
	public float restitution;

	/**
	 * The density, usually in kg/m^2
	 */
	public float density;

	/**
	 * A sensor shape collects contact information but never generates a
	 * collision response.
	 */
	public boolean isSensor;

	/**
	 * Contact filtering data;
	 */
	public Filter filter;

	public DShape getShape() {
		return shape;
	}

	public void setShape(DShape shape) {
		this.shape = shape;
	}

	public Object getUserData() {
		return userData;
	}

	public void setUserData(Object userData) {
		this.userData = userData;
	}

	public float getFriction() {
		return friction;
	}

	public void setFriction(float friction) {
		this.friction = friction;
	}

	public float getRestitution() {
		return restitution;
	}

	public void setRestitution(float restitution) {
		this.restitution = restitution;
	}

	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;
	}

	public boolean isSensor() {
		return isSensor;
	}

	public void setSensor(boolean isSensor) {
		this.isSensor = isSensor;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

}
