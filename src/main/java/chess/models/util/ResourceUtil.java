package main.java.chess.models.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ResourceUtil {
	
	private ResourceUtil() {
	}

	public static Map<String, String> getFenFromOpenings() {
		Map<String, String> openingsMap = new HashMap<>();
		try (InputStream inputStream = ResourceUtil.class.getResourceAsStream("openings/list_of_fen.txt");
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			String openingName = null;
			StringBuilder fenBuilder = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				if (line.equals("start") || line.equals("english") || line.equals("queen's pawn")
						|| line.equals("king's pawn")) {
					if (openingName != null) {
						openingsMap.put(openingName, fenBuilder.toString());
						fenBuilder = new StringBuilder();
					}
					openingName = line;
				} else {
					fenBuilder.append(line);
				}
			}

			if (openingName != null) {
				openingsMap.put(openingName, fenBuilder.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return openingsMap;
	}
}
