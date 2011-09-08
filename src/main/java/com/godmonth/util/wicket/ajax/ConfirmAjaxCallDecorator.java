package com.godmonth.util.wicket.ajax;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.calldecorator.AjaxCallDecorator;

public class ConfirmAjaxCallDecorator extends AjaxCallDecorator {
	private String confirmMessage = "Do you really want to perform this action?";

	public ConfirmAjaxCallDecorator() {
	}

	public ConfirmAjaxCallDecorator(String confirmMessage) {
		this.confirmMessage = confirmMessage;
	}

	@Override
	public CharSequence decorateScript(Component c, CharSequence script) {
		String confirm = "if (!confirm('" + confirmMessage
				+ "')) return false; ";
		return confirm + ObjectUtils.defaultIfNull(script, "");
	}

}
