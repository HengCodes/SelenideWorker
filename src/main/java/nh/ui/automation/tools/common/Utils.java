package nh.ui.automation.tools.common;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Utils {
	/**
	 * 等待时间
	 * @param waitTime
	 */
	public static void sleep(int waitTime){
		try {
			Thread.sleep(waitTime*1000L);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T extends Comparable<T>> boolean listCompara(List<T> a,List<T> b){
		if (a.size() != b.size()) {
			return false;
		}
		Collections.sort(a);
		Collections.sort(b);
		for (int i = 0; i < a.size(); i++) {
			if (!a.get(i).equals(b.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param actualList
	 * @param expectList
	 * @return
	 */
	public static Boolean listContains(List<String> actualList,List<String> expectList){
		if (actualList.size() > expectList.size()) {
			return false;
		}
		for(String str: actualList){
			if (expectList.indexOf(str) == -1) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 获取得到String的时间时，进行排序
	 * @param dateList
	 * @param dateFormat
	 * @return
	 */
	public static List<String> dateListSort(List<String> dateList,String dateFormat){
		final SimpleDateFormat simpleDateFormat  = new SimpleDateFormat(dateFormat);
		
		Collections.sort(dateList,new Comparator<String>() {

			@Override
			public int compare(String d1, String d2) {
				try {
					Date date1 = simpleDateFormat.parse(d1);
					Date date2 = simpleDateFormat.parse(d2);
					if (date1.getTime() < date2.getTime()) {
						return -1;
					}
					if (date1.getTime() > date2.getTime()) {
						return 1;
					}
					if (date1.getTime() == date2.getTime()) {
						return 0;
					} 
				} catch (Exception e) {
					e.printStackTrace();
				}
				return 1;
			}
			
		});
		
		return dateList;
	}
}
