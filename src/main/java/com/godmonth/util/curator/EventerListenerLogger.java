package com.godmonth.util.curator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.state.ConnectionState;
import com.netflix.curator.framework.state.ConnectionStateListener;

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
