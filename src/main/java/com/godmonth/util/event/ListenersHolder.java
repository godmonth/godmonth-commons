package com.godmonth.util.event;

import java.util.Iterator;

public interface ListenersHolder<T> {
	void addListener(T listener);

	void removeListener(T listener);

	Iterator<T> iterator();

	void removeAllListener();
}
