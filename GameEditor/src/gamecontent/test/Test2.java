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

		DBody body = new DBody();

		System.out.println("exists? " + body.exists());

		DObjectListener listener = new Ascoltatore();

		content.addDObjectListener(listener);
		//body.addDObjectListener(listener);

		System.out.println("adding body to content ...");
		content.add(body);

		System.out.println("destroying body ...");
		body.destroy();

		System.out.println("exists? " + body.exists());

	}

}
