package org.jarmoni.restxe.common;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;

public final class Item<T> {

	private List<Link> links = new ArrayList<>();

	private T data;

	private Item() {
	}

	public List<Link> getLinks() {
		return links;
	}

	public T getData() {
		return data;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(Item.class).add("links", this.links).add("data", this.data).toString();
	}

	public static <T> ItemBuilder<T> builder() {
		return new ItemBuilder<>();
	}

	public static final class ItemBuilder<T> {

		private final Item<T> item;

		private ItemBuilder() {
			this.item = new Item<>();
		}

		public ItemBuilder<T> links(final List<Link> links) {
			this.item.links.addAll(links);
			return this;
		}

		public ItemBuilder<T> link(final Link link) {
			this.item.links.add(link);
			return this;
		}

		public ItemBuilder<T> data(final T data) {
			this.item.data = data;
			return this;
		}

		public Item<T> build() {
			return this.item;
		}
	}

}
