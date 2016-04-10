package gamecontent.test;

import gamecontent.DBody;
import gamecontent.DBodyListener;
import gamecontent.DFixture;
import gamecontent.DObjectEvent;
import gamecontent.DObjectListener;
import gamecontent.GameContent;

public class Test3 {

	static class Ascoltatore implements DObjectListener {

		@Override
		public void objectDestroyed(DObjectEvent event) {

			System.out.println("Object destroyed");
		}

		@Override
		public void objectCreated(DObjectEvent event) {
			System.out.println("Object created");

		}

	}

	static class AscoltaBody implements DBodyListener {

		@Override
		public void bodyTypeChanged(DObjectEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void bodyPositionChanged(DObjectEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void bodyAngleChanged(DObjectEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void bodyLinearvelocityChanged(DObjectEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void bodyAngularVelocityChanged(DObjectEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void bodyLinearDampingChanged(DObjectEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void bodyAngularDampingChanged(DObjectEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void bodyAllowSleepChanged(DObjectEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void bodyAwakeChanged(DObjectEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void bodyFixedRotationChanged(DObjectEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void bodyBulletChanged(DObjectEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void bodyActiveChanged(DObjectEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void bodyGravityScaleChanged(DObjectEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void bodyDestroyed(DObjectEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void fixtureAdded(DObjectEvent event) {

			DBody body = (DBody) event.getSource();

			//body.setActive(true); //excepion (get only)
			System.out.println("ASCOLTA BODY: Fixture added: " + body.getFixtures().lastElement());

		}

	}

	public static void main(String[] args) throws InterruptedException {

		GameContent content = new GameContent();

		DObjectListener listener = new Ascoltatore();
		content.addDObjectListener(listener);

		DBody body = content.createDBody();
		DFixture fixture = content.createDFixture();

		DBodyListener listener2 = new AscoltaBody();
		body.addDBodyListener(listener2);

		System.out.println("adding fixture to body ...");
		body.addFixture(fixture);

		System.out.println("destroying body ...");
		body.destroy();

		System.out.println("destroying fixture ...");
		fixture.destroy();

	}

}
