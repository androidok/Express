package com.express.util;

import java.util.Random;

public class RandomUtil {

	public static String  getRandomNum() {
		Random random = new Random();
		int randomNumber =  (random.nextInt(89) + 10);
		String num = Integer.toString(randomNumber);
		return num;
	}
	public static void main(String[] args) {
		int i = 0;
		while (i<100) {
			i++;
			System.out.println(getRandomNum());
			
		}
		
	}
}
