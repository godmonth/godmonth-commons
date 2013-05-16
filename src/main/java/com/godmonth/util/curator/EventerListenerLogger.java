package com.godmonth.util.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shenyue
 */
public class EventerListenerLogger implements ConnectionStateListener {

	private static final Logger logger = LoggerFactory.getLogger(EventerListenerLogger.class);

	@Override
	public void stateChanged(CuratorFramework client, ConnectionState newState) {
		logger.info("stateChanged:{}", newState);
	}

}
