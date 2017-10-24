package com.email.module;

import java.util.Random;

public class RandomInteger {

	public static int randInt(int min, int max) {
		

		// note a single Random object is reused here
		Random randomGenerator = new Random();

		int randomInt = randomGenerator.nextInt((max - min) + 1) + min;
		//log("Generated : " + randomInt);

		//log("Done.");

		return randomInt;

	}

	private static void log(String aMessage) {
		System.out.println(aMessage);
	}

}
