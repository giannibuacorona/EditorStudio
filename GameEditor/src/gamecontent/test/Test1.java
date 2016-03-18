package gamecontent.test;

import gamecontent.DBody;
import gamecontent.GameContent;

public class Test1 {

	public static void main(String[] args) {

		GameContent content = new GameContent();

		DBody body = new DBody();

		System.out.println("exists? " + body.exists());

		content.add(body);
		System.out.println("Body added to content.");
		System.out.println("exists? " + body.exists());

	}

}
