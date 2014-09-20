package org.jarmoni.restxe.common;

import com.google.common.base.MoreObjects;

public final class Link {

	private String rel;
	private String href;

	private Link() {
	}

	public String getRel() {
		return rel;
	}

	public String getHref() {
		return href;
	}

	public static LinkBuilder builder() {
		return new LinkBuilder();
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(Link.class).add("rel", this.rel).add("href", this.href).toString();
	}

	public static final class LinkBuilder {

		private final Link link;

		private LinkBuilder() {
			this.link = new Link();
		}

		public LinkBuilder rel(final String rel) {
			this.link.rel = rel;
			return this;
		}

		public LinkBuilder href(final String href) {
			this.link.href = href;
			return this;
		}

		public Link build() {
			return this.link;
		}
	}
}
