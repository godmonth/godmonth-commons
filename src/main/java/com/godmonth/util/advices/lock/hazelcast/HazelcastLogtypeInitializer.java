package com.godmonth.util.advices.lock.hazelcast;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * 
 * @author shenyue
 *
 */
public class HazelcastLogtypeInitializer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.setProperty("hazelcast.logging.type", "slf4j");

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
