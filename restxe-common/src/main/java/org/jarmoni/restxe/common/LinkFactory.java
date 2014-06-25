package org.jarmoni.restxe.common;

import java.util.Objects;

/**
 * @author ms Creates link-instances
 * 
 */
public final class LinkFactory {

	public static final String SELF_REF = "self";

	private final IUrlResolver urlResolver;

	public LinkFactory(final IUrlResolver urlResolver) {
		this.urlResolver = Objects.requireNonNull(urlResolver);
	}

	/**
	 * @param rel
	 *            type of relation (self, next,...)
	 * @param relativeUrl
	 *            relative part of url starting with '/' (/my/path,...)
	 * @return
	 */
	public Link createLink(final String rel, final String relativeUrl) {

		return new Link().builder().rel(rel).href(this.urlResolver.getRootUrl() + relativeUrl).build();
	}

}
