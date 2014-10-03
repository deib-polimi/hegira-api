package it.polimi.hegira.api;

import junit.framework.TestCase;

public class Test_API extends TestCase {

	public void testSayPlainTextHello() {
		API api = new API();
		assertNotNull(api.sayPlainTextHello());
	}

	public void testSayHtmlHello() {
		API api = new API();
		assertNotNull(api.sayHtmlHello());
	}

}
