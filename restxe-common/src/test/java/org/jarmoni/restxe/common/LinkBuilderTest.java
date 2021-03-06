/*
 * Copyright (c) 2013. All rights reserved.
 * Original Author: ms
 * Creation Date: Feb 21, 2014
 */
package org.jarmoni.restxe.common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LinkBuilderTest {

	private final LinkFactory linkFactory = new LinkFactory(new TestUrlResolver());

	@Test
	public void testCreateLink() throws Exception {

		final Link link = this.linkFactory.createLink("self", "/my/path", HttpVerb.GET);
		assertEquals("self", link.getRel());
		assertEquals("http://myhost:8080/my/path", link.getHref());
		assertEquals("GET", link.getHttpVerb());
	}

}
