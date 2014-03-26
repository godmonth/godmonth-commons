package com.godmonth.util.curator.queue;

import org.apache.commons.codec.Charsets;
import org.apache.curator.framework.recipes.queue.QueueSerializer;

/**
 * utf-8 encode
 * 
 * @author shenyue
 * 
 */
public class StringQueueSerializer implements QueueSerializer<String> {

	public static final StringQueueSerializer INSTANCE = new StringQueueSerializer();

	private StringQueueSerializer() {
	}

	@Override
	public byte[] serialize(String item) {
		return item.getBytes(Charsets.UTF_8);
	}

	@Override
	public String deserialize(byte[] bytes) {
		return new String(bytes, Charsets.UTF_8);
	}

}
