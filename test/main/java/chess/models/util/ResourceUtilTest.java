package main.java.chess.models.util;

import java.util.Map;

import org.junit.jupiter.api.Test;

class ResourceUtilTest {

	@Test
	void testGetFenFromOpenings() {
		Map<String, String> openingsMap = ResourceUtil.getFenFromOpenings();
		for (Map.Entry<String, String> entry : openingsMap.entrySet()) {
			System.out.println("Opening Name: " + entry.getKey());
			System.out.println("FEN Notation: " + entry.getValue());
			System.out.println();
		}
	}

}
