package com.godmonth.util.advices.lock.lockmap;

import java.util.concurrent.locks.Lock;

public interface LockMap {
	Lock getLock(String key);
}
