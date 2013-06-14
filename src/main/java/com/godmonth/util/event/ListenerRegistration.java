package com.godmonth.util.event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class ListenerRegistration<T> implements InitializingBean,
		DisposableBean {

	private Map<ListenersHolder<T>, Collection<T>> multiListenerHolder = new HashMap<ListenersHolder<T>, Collection<T>>();
	private Map<SingleListenerHolder<T>, T> singleListenerHolder = new HashMap<SingleListenerHolder<T>, T>();

	public void afterPropertiesSet() throws Exception {
		for (ListenersHolder<T> lr : multiListenerHolder.keySet()) {
			if (lr != null) {
				for (T object : multiListenerHolder.get(lr)) {
					lr.addListener(object);
				}

			}
		}
		for (SingleListenerHolder<T> slr : singleListenerHolder.keySet()) {
			if (slr != null) {
				slr.setListener(singleListenerHolder.get(slr));
			}
		}
	}

	public void destroy() throws Exception {
		for (ListenersHolder<T> lr : multiListenerHolder.keySet()) {
			if (lr != null) {
				lr.removeAllListener();
			}
		}
		for (SingleListenerHolder<T> slr : singleListenerHolder.keySet()) {
			if (slr != null) {
				slr.setListener(null);
			}
		}

	}

	public Map<ListenersHolder<T>, Collection<T>> getMultiListenerHolder() {
		return multiListenerHolder;
	}

	public void setMultiListenerHolder(
			Map<ListenersHolder<T>, Collection<T>> multiListenerHolder) {
		this.multiListenerHolder = multiListenerHolder;
	}

	public Map<SingleListenerHolder<T>, T> getSingleListenerHolder() {
		return singleListenerHolder;
	}

	public void setSingleListenerHolder(
			Map<SingleListenerHolder<T>, T> singleListenerHolder) {
		this.singleListenerHolder = singleListenerHolder;
	}

}
