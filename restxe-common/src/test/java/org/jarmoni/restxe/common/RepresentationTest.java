/*
 * Copyright (c) 2013. All rights reserved.
 * Original Author: ms
 * Creation Date: Feb 21, 2014
 */
package org.jarmoni.restxe.common;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class RepresentationTest {

	private final LinkFactory linkBuilder = new LinkFactory(new TestUrlResolver());

	@Test
	public void testCreateRepresentation() throws Exception {

		final Link selfLink = this.linkBuilder.createLink("self", "/self/path", HttpVerb.GET);
		final Link nextLink = this.linkBuilder.createLink("next", "/next/path", HttpVerb.PUT);

		final Link item1SelfLink = this.linkBuilder.createLink("self", "/self/path/1", HttpVerb.POST);
		final Link item1NextLink = this.linkBuilder.createLink("next", "/next/path/1", HttpVerb.DELETE);

		final Link item2SelfLink = this.linkBuilder.createLink("self", "/self/path/2", HttpVerb.GET);
		final Link item2NextLink = this.linkBuilder.createLink("next", "/next/path/2", HttpVerb.GET);

		final Item<PersonData> item1 = Item.<PersonData> builder().data(PersonData.builder().name("john").age(25).build())
				.links(Arrays.asList(item1SelfLink, item1NextLink)).build();
		final Item<PersonData> item2 = Item.<PersonData> builder().data(PersonData.builder().name("jane").age(30).build())
				.link(item2SelfLink).link(item2NextLink).build();

		final Representation<PersonData> representation = Representation.<PersonData> builder().version("1.0.0").link(selfLink)
				.links(Arrays.asList(nextLink)).item(item1).items(Arrays.asList(item2)).build();

		assertEquals("1.0.0", representation.getVersion());

		assertEquals(2, representation.getLinks().size());
		assertEquals(2, representation.getItems().size());

		assertEquals("self", representation.getLinks().get(0).getRel());
		assertEquals("http://myhost:8080/self/path", representation.getLinks().get(0).getHref());
		assertEquals("GET", representation.getLinks().get(0).getHttpVerb());
		assertEquals("next", representation.getLinks().get(1).getRel());
		assertEquals("http://myhost:8080/next/path", representation.getLinks().get(1).getHref());
		assertEquals("PUT", representation.getLinks().get(1).getHttpVerb());

		assertEquals("john", representation.getItems().get(0).getData().getName());
		assertEquals(Integer.valueOf(25), representation.getItems().get(0).getData().getAge());
		assertEquals(2, representation.getItems().get(0).getLinks().size());
		assertEquals("self", representation.getItems().get(0).getLinks().get(0).getRel());
		assertEquals("http://myhost:8080/self/path/1", representation.getItems().get(0).getLinks().get(0).getHref());
		assertEquals("POST", representation.getItems().get(0).getLinks().get(0).getHttpVerb());

		assertEquals("next", representation.getItems().get(0).getLinks().get(1).getRel());
		assertEquals("http://myhost:8080/next/path/1", representation.getItems().get(0).getLinks().get(1).getHref());
		assertEquals("DELETE", representation.getItems().get(0).getLinks().get(1).getHttpVerb());

		assertEquals("jane", representation.getItems().get(1).getData().getName());
		assertEquals(Integer.valueOf(30), representation.getItems().get(1).getData().getAge());
		assertEquals(2, representation.getItems().get(1).getLinks().size());
		assertEquals("self", representation.getItems().get(1).getLinks().get(0).getRel());
		assertEquals("http://myhost:8080/self/path/2", representation.getItems().get(1).getLinks().get(0).getHref());
		assertEquals("next", representation.getItems().get(1).getLinks().get(1).getRel());
		assertEquals("http://myhost:8080/next/path/2", representation.getItems().get(1).getLinks().get(1).getHref());
	}
}
