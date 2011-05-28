package com.godmonth.util.wicket.ajax;

import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;

public abstract class ConfirmAjaxLink<T> extends AjaxLink<T> {
	public ConfirmAjaxLink(String id, IModel<T> model) {
		super(id, model);
	}

	public ConfirmAjaxLink(String id) {
		super(id);
	}

	@Override
	protected IAjaxCallDecorator getAjaxCallDecorator() {
		return new ConfirmAjaxCallDecorator();
	}

}
