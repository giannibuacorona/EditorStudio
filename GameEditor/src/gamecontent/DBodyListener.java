package gamecontent;

public interface DBodyListener {

	void bodyTypeChanged(DObjectEvent event);

	void bodyPositionChanged(DObjectEvent event);

	void bodyAngleChanged(DObjectEvent event);

	void bodyLinearvelocityChanged(DObjectEvent event);

	void bodyAngularVelocityChanged(DObjectEvent event);

	void bodyLinearDampingChanged(DObjectEvent event);

	void bodyAngularDampingChanged(DObjectEvent event);

	void bodyAllowSleepChanged(DObjectEvent event);

	void bodyAwakeChanged(DObjectEvent event);

	void bodyFixedRotationChanged(DObjectEvent event);

	void bodyBulletChanged(DObjectEvent event);

	void bodyActiveChanged(DObjectEvent event);

	void bodyGravityScaleChanged(DObjectEvent event);

	void bodyDestroyed(DObjectEvent event);

}
