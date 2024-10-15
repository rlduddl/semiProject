package com.semiProject.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringUtil {

	// 랜덤 문자열 생성
	public static String generateRandomString(int len) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder result = new StringBuilder(len);
		Random random = new Random();
		
		for (int i = 0; i < len; i++) {
            int index = random.nextInt(chars.length());
            result.append(chars.charAt(index));
        }
        
        return result.toString();
	}
	
	
	/**
	 * HashMap의 key, value 출력 메서드
	 * @param asTitle
	 * @param aoMap
	 */
	public static void printMap(String asTitle, Map aoMap) {
		StringBuilder loSb = new StringBuilder();
		
		loSb.append("\n===========================");
		loSb.append("\n" + asTitle);
		loSb.append("\n===========================");
		
		Iterator loIter = aoMap.keySet().iterator();
		
		while (loIter.hasNext()) {
			String lsKey = loIter.next().toString();
			loSb.append("\n").append(lsKey).append(" = ")
				.append(aoMap.get(lsKey));
		}
		loSb.append("\n===========================");
		log.info("\n\n");
		log.info(loSb.toString());
		log.info("\n\n");
	}
	
}
	