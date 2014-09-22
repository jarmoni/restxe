package org.jarmoni.restxe.common;

import java.util.Objects;

/**
 * @author ms Creates link-instances
 * 
 */
public final class LinkFactory {

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

		return Link.builder().rel(rel).href(this.urlResolver.getRootUrl() + relativeUrl).build();
	}

}
