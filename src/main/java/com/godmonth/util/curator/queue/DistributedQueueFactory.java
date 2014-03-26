package com.godmonth.util.curator.queue;

import java.util.concurrent.Executor;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.queue.DistributedDelayQueue;
import org.apache.curator.framework.recipes.queue.QueueBuilder;
import org.apache.curator.framework.recipes.queue.QueueConsumer;
import org.apache.curator.framework.recipes.queue.QueueSerializer;
import org.springframework.beans.factory.annotation.Required;

public class DistributedQueueFactory<T> {
	private CuratorFramework curatorFramework;
	private QueueConsumer<T> queueConsumer;
	private QueueSerializer<T> queueSerializer;
	private String path;
	private Integer maxItems;
	private Executor executor;

	public DistributedDelayQueue<T> create() {
		QueueBuilder<T> builder = QueueBuilder.builder(curatorFramework, queueConsumer, queueSerializer, path);
		if (maxItems != null) {
			builder = builder.maxItems(maxItems);
		}
		if (executor != null) {
			builder = builder.executor(executor);
		}
		return builder.buildDelayQueue();
	}

	@Required
	public void setCuratorFramework(CuratorFramework curatorFramework) {
		this.curatorFramework = curatorFramework;
	}

	@Required
	public void setQueueConsumer(QueueConsumer<T> queueConsumer) {
		this.queueConsumer = queueConsumer;
	}

	@Required
	public void setQueueSerializer(QueueSerializer<T> queueSerializer) {
		this.queueSerializer = queueSerializer;
	}

	@Required
	public void setPath(String path) {
		this.path = path;
	}

	public void setMaxItems(Integer maxItems) {
		this.maxItems = maxItems;
	}

	public void setExecutor(Executor executor) {
		this.executor = executor;
	}

}
