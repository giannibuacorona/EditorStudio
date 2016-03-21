package gamecontent.test;

import gamecontent.DBody;
import gamecontent.GameContent;

public class Test1 {

	public static void main(String[] args) {

		GameContent content = new GameContent();

		DBody body = content.createDBody();

		System.out.println(body);

		body.destroy();

		System.out.println(body);
	}

}
