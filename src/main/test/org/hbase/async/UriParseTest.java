/**
 * 
 */
package org.hbase.async;

import static org.junit.Assert.*;

import java.net.URI;

import org.junit.Test;

/**
 * @author drew
 * 
 */
public class UriParseTest {

	@Test
	public void testUri1() {
		URI u = URI.create("accumulo://test:one@localhost:1234/blah");
		assertEquals("localhost", u.getHost());
		assertEquals(1234, u.getPort());
		assertEquals("/blah", u.getPath());
		assertEquals("test:one", u.getUserInfo());
		assertEquals("test", u.getUserInfo().split(":")[0]);
		assertEquals("one", u.getUserInfo().split(":")[1]);
	}

	@Test
	public void testUri2() {
		URI u = URI.create("accumulo://localhost:1234/blah");
		assertEquals("localhost", u.getHost());
		assertEquals(1234, u.getPort());
		assertEquals("/blah", u.getPath());
		assertNull(u.getUserInfo());
	}

	@Test
	public void testUri3() {
		URI u = URI.create("accumulo://localhost/blah");
		assertEquals("localhost", u.getHost());
		assertEquals(-1, u.getPort());
		assertEquals("/blah", u.getPath());
		assertNull(u.getUserInfo());
	}

}
