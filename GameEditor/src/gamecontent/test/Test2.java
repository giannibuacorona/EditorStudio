package gamecontent.test;

import gamecontent.DBody;
import gamecontent.DObjectEvent;
import gamecontent.DObjectListener;
import gamecontent.GameContent;

public class Test2 {

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

	public static void main(String[] args) throws InterruptedException {

		GameContent content = new GameContent();

		DObjectListener listener = new Ascoltatore();
		content.addDObjectListener(listener);

		DBody body = content.createDBody();

		System.out.println("exists? " + body.exists());

		System.out.println("destroying body ...");
		body.destroy();

		System.out.println("exists? " + body.exists());

	}

}
