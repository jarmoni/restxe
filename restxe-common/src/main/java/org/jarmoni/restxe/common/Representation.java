package org.jarmoni.restxe.common;

import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

public final class Representation<T> {

	private String version;

	private final List<Link> links = Lists.newArrayList();

	private final List<Item<T>> items = Lists.newArrayList();

	private String errorMessage;

	private Representation() {
	}

	public String getVersion() {
		return version;
	}

	public List<Link> getLinks() {
		return links;
	}

	public List<Item<T>> getItems() {
		return items;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(Representation.class).add("links", this.links).add("items", this.items).toString();
	}

	public static <T> RepresentationBuilder<T> builder() {
		return new RepresentationBuilder<>();
	}

	public static final class RepresentationBuilder<T> {

		private final Representation<T> representation;

		private RepresentationBuilder() {
			this.representation = new Representation<>();
		}

		public Representation<T> build() {
			return this.representation;
		}

		public RepresentationBuilder<T> version(final String version) {
			this.representation.version = version;
			return this;
		}

		public RepresentationBuilder<T> links(final List<Link> links) {
			this.representation.links.addAll(links);
			return this;
		}

		public RepresentationBuilder<T> link(final Link link) {
			this.representation.links.add(link);
			return this;
		}

		public RepresentationBuilder<T> items(final List<Item<T>> items) {
			this.representation.items.addAll(items);
			return this;
		}

		public RepresentationBuilder<T> item(final Item<T> item) {
			this.representation.items.add(item);
			return this;
		}

		public RepresentationBuilder<T> errorMessage(final String errorMessage) {
			this.representation.errorMessage = errorMessage;
			return this;
		}
	}
}
