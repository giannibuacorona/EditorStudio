package gamecontent;

public interface BodyListener {

	void bodyTypeChanged(BodyEvent event);

	void bodyPositionChanged(BodyEvent event);

	void bodyAngleChanged(BodyEvent event);

	void bodyLinearvelocityChanged(BodyEvent event);

	void bodyAngularVelocityChanged(BodyEvent event);

	void bodyLinearDampingChanged(BodyEvent event);

	void bodyAngularDampingChanged(BodyEvent event);

	void bodyAllowSleepChanged(BodyEvent event);

	void bodyAwakeChanged(BodyEvent event);

	void bodyFixedRotationChanged(BodyEvent event);

	void bodyBulletChanged(BodyEvent event);

	void bodyActiveChanged(BodyEvent event);

	void bodyGravityScaleChanged(BodyEvent event);

	void bodyDestroyed(BodyEvent event);

}
