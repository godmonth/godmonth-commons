package com.godmonth.util.cli;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextCliStart {
	public static void main(String[] args) {
		ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext(
				args);
		while (true) {
			try {
				Thread.sleep(30000000);
			} catch (Exception e) {
			}
		}

	}
}
