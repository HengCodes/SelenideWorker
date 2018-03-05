package nh.ui.automation.tools.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jayway.jsonpath.JsonPath;

public class JsonUtils {
	/**
	 * 
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	private static String readAll(Reader reader) throws IOException {
		StringBuffer sBuffer = new StringBuffer();
		int cp;
		while ((cp = reader.read()) != -1) {
			sBuffer.append((char) cp);
		}
		return sBuffer.toString();
	}

	/**
	 * 从url获取jsonObject
	 * @param url
	 * @return
	 */
	public static JSONObject readJsonFromUrl(String url) {
		InputStream iStream = null;
		BufferedReader bufferedReader = null;
		JSONObject jsonObject = null;

		try {
			iStream = new URL(url).openStream();
			bufferedReader = new BufferedReader(new InputStreamReader(iStream, Charset.forName("UTF-8")));
			String jsonData = readAll(bufferedReader);
			jsonData = jsonData.replace("[", "").replace("]", "").trim();
			jsonObject = new JSONObject(jsonData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return jsonObject;
	}
	
	
	public static JSONArray readJsonArrFromUrl(String url) {
		InputStream iStream = null;
		BufferedReader bufferedReader = null;
		JSONArray jsonObject = null;

		try {
			iStream = new URL(url).openStream();
			bufferedReader = new BufferedReader(new InputStreamReader(iStream, Charset.forName("UTF-8")));
			String jsonData = readAll(bufferedReader);
			jsonObject = new JSONArray(jsonData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return jsonObject;
	}
	
	
	/**
	 * 通过jsonpath解析并获取值
	 * @param json
	 * @param jsonPath
	 * @return
	 */
	public static String getJsonString(String json, String jsonPath) {
		return JsonPath.read(json, jsonPath).toString();
	}

	/**
	 * 通过jsonpath解析并获取值
	 * @param json
	 * @param jsonPath
	 * @return
	 */
	public static List<String> getJsonStringList(String json, String jsonPath) {
		List<Object> read = JsonPath.read(json, jsonPath);
		List<String> jsonValueList = new ArrayList<>();
		for (Object object : read) {
			jsonValueList.add(object.toString());
		}
		return jsonValueList;
	}

	/**
	 * 通过jsonpath解析并获取值
	 * @param json
	 * @param jsonPath
	 * @return
	 */
	public static Map<String, String> getJsonMap(String json, String jsonPath) {
		Map<String, Object> jsonObjmap = JsonPath.read(json, jsonPath);
		Map<String, String> jsonStrMap = new HashMap<String, String>();

		Iterator<Entry<String, Object>> entry = jsonObjmap.entrySet().iterator();
		while (entry.hasNext()) {
			Entry<String, Object> e = entry.next();
			jsonStrMap.put(e.getKey(), e.getValue() == null ? "" : e.getValue().toString());
		}
		return jsonStrMap;
	}
	
	/**
	 * 获取jsonpath获取值的size
	 * @param json
	 * @param jsonPath
	 * @return
	 */
	public static int getJsonStringSize(String json, String jsonPath) {
		return getJsonStringList(json, jsonPath).size();
	}
}
