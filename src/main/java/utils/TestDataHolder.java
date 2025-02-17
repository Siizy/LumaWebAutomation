package utils;

import java.util.HashMap;
import java.util.Map;

public class TestDataHolder {
	
	private static final ThreadLocal<Map<String, String>> testData = new ThreadLocal<>();

	public static void setTestData(Map<String, String> data) {
		testData.set(new HashMap<>(data));
	}

	public static Map<String, String> getTestData() {
		return testData.get();
	}

	public static void clear() {
		testData.remove();
	}
}
