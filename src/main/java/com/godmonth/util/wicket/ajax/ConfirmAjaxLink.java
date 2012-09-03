package com.godmonth.util.wicket.ajax;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;

public abstract class ConfirmAjaxLink<T> extends AjaxLink<T> {
	private String confirmMessage = "Do you really want to perform this action?";

	public ConfirmAjaxLink(String id, IModel<T> model) {
		super(id, model);
	}

	public ConfirmAjaxLink(String id) {
		super(id);
	}

	@Override
	protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
		AjaxCallListener listener = new AjaxCallListener();
		String confirm = "if (!confirm('" + confirmMessage
				+ "')) return false; ";
		listener.onPrecondition(confirm);
		attributes.getAjaxCallListeners().add(listener);
	}
}
