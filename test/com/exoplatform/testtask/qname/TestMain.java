package com.exoplatform.testtask.qname;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
				
		Result result = JUnitCore.runClasses(ParserTest.class);
		System.out.println("number of tests to be run = " + result.getRunCount());
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
	}

}
