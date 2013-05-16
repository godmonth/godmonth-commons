package com.godmonth.util.curator;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.CuratorFrameworkFactory.Builder;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryOneTime;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author shenyue
 */
public class CuratorFrameworkFactoryBuilder implements InitializingBean {
	private CuratorFramework curatorFramework;
	private String connectString;
	private RetryPolicy retryPolicy = new RetryOneTime(1000);
	private int sessionTimeoutSecond;
	private List<ConnectionStateListener> connectionStateListeners;

	@Override
	public void afterPropertiesSet() throws Exception {
		Builder builder = CuratorFrameworkFactory.builder().connectString(connectString).retryPolicy(retryPolicy);
		if (sessionTimeoutSecond > 0) {
			builder.sessionTimeoutMs(sessionTimeoutSecond * 1000);
		}
		curatorFramework = builder.build();
		if (CollectionUtils.isNotEmpty(connectionStateListeners)) {
			for (ConnectionStateListener connectionStateListener : connectionStateListeners) {
				curatorFramework.getConnectionStateListenable().addListener(connectionStateListener);
			}
		}
	}

	public CuratorFramework build() throws Exception {
		return curatorFramework;
	}

	public void setConnectString(String connectString) {
		this.connectString = connectString;
	}

	public void setSessionTimeoutSecond(int sessionTimeoutSecond) {
		this.sessionTimeoutSecond = sessionTimeoutSecond;
	}

	public void setConnectionStateListeners(List<ConnectionStateListener> connectionStateListeners) {
		this.connectionStateListeners = connectionStateListeners;
	}

	public void setRetryPolicy(RetryPolicy retryPolicy) {
		this.retryPolicy = retryPolicy;
	}
}
