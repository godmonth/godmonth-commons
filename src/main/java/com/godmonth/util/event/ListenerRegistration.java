package com.godmonth.util.event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@SuppressWarnings("unchecked")
public class ListenerRegistration implements InitializingBean, DisposableBean {

	private Map<ListenersHolder, Collection> multiListenerHolder = new HashMap<ListenersHolder, Collection>();
	private Map<SingleListenerHolder, Object> singleListenerHolder = new HashMap<SingleListenerHolder, Object>();

	public void afterPropertiesSet() throws Exception {
		for (ListenersHolder lr : multiListenerHolder.keySet()) {
			if (lr != null) {
				for (Object object : multiListenerHolder.get(lr)) {
					lr.addListener(object);
				}

			}
		}
		for (SingleListenerHolder slr : singleListenerHolder.keySet()) {
			if (slr != null) {
				slr.setListener(singleListenerHolder.get(slr));
			}
		}
	}

	public void destroy() throws Exception {
		for (ListenersHolder lr : multiListenerHolder.keySet()) {
			if (lr != null) {
				lr.removeAllListener();
			}
		}
		for (SingleListenerHolder slr : singleListenerHolder.keySet()) {
			if (slr != null) {
				slr.setListener(null);
			}
		}

	}

	public Map<ListenersHolder, Collection> getMultiListenerHolder() {
		return multiListenerHolder;
	}

	public void setMultiListenerHolder(
			Map<ListenersHolder, Collection> multiListenerHolder) {
		this.multiListenerHolder = multiListenerHolder;
	}

	public Map<SingleListenerHolder, Object> getSingleListenerHolder() {
		return singleListenerHolder;
	}

	public void setSingleListenerHolder(
			Map<SingleListenerHolder, Object> singleListenerHolder) {
		this.singleListenerHolder = singleListenerHolder;
	}

}
