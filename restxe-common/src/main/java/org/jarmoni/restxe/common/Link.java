package org.jarmoni.restxe.common;

import com.google.common.base.Objects;

public class Link {

	private String rel;
	private String href;

	public Link() {
	}

	public String getRel() {
		return rel;
	}

	public String getHref() {
		return href;
	}

	public LinkBuilder builder() {
		return new LinkBuilder();
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(Link.class).add("rel", this.rel).add("href", this.href).toString();
	}

	public static final class LinkBuilder {

		private Link link;

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
