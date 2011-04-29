package com.godmonth.util.ehcache;

import javax.management.MBeanServer;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.management.ManagementService;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class EhcacheEnableJmx implements ApplicationContextAware {
	private MBeanServer mBeanServer;
	private CacheManager cacheManager;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ManagementService.registerMBeans(cacheManager, mBeanServer, true, true,
				true, true);
	}

	public void setmBeanServer(MBeanServer mBeanServer) {
		this.mBeanServer = mBeanServer;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

}
