package com.godmonth.util.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.EnsurePath;

public class EnsurePath2 extends EnsurePath {

	public EnsurePath2(String path) {
		super(path);
	}

	public void ensure(CuratorFramework curatorFramework) throws Exception {
		ensure(curatorFramework.getZookeeperClient());
	}

}
