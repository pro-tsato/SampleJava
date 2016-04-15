package xyz.ronrico151.samplejava;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * フィールド操作のユーティリティ.
 * <p>下記出力となる。（※インデント、改行は自身で成形）
 * <pre>
 * testPublicInteger : 1
 * testPublicString : hoge
 * testListString : [list1, list2, list3]
 * testMapStringObject : {
 * 	key1=[list1, list2, list3],
 * 	key2={map3=map_val3, map2=map_val2, map1=map_val1},
 * 	key3=[
 * 		{testPublicInteger : 1
 * 			testPublicString : hoge
 * 			testPublicInt : 1
 * 			testMapStringString : {key1=val1, key2=val2, key3=val3}
 * 		},
 * 		{testPublicInteger : 1
 * 			testPublicString : hoge
 * 			testPublicInt : 1
 * 			testMapStringString : {key1=val1, key2=val2, key3=val3}
 * 		},
 * 		{testPublicInteger : 1
 * 			testPublicString : hoge
 * 			testPublicInt : 1
 * 			testMapStringString : {key1=val1, key2=val2, key3=val3}
 * 		}
 * 	]
 * }
 * testPublicInt : 1
 * testMapStringString : {key1=val1, key2=val2, key3=val3}
 * </pre>
 * @author pro-tsato
 */
public class FieldUtility {

	/**
	 * オブジェクトのプロパティ変数名をキーとした、変数の値を保持するMapを取得する。<br />
	 * また、staticフィールドは除外設定の有無にかかわらず除外する。
	 * @param obj Map化するオブジェクト
	 * @param excludeList 除外設定（ここで指定された文字列のパラメータはMapから除外）
	 * @return Map<変数名, 変数の値>
	 */
	public static Map<String, Object> toMapExcludeStaticField(Object obj, List<String> excludeList) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		Field[] fs = obj.getClass().getFields();
		for (Field f : fs) {
			// フィールドの属性（修飾子）を取得
			int mod = f.getModifiers();

			// 静的要素は除外
			if (Modifier.isStatic(mod)) {
				continue;
			}

			try {
				String name = f.getName();

				// 除外リストに指定されたものはマップに含めない
				if (excludeList != null && excludeList.contains(name)) {
					continue;
				}

				retMap.put(name, (f.get(obj) == null) ? null : f.get(obj));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return retMap;
	}

	/**
	 * 動作確認用main
	 * @param args 
	 */
	public static void main(String[] args) {
		Map<String, Object> map = toMapExcludeStaticField(new Test(), null);
		
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();
			System.out.println(key + " : " + val.toString());
		}
	}
	
	/**
	 * 動作確認用インナークラス1.
	 * @author pro-tsato
	 */
	public static class Test {
		public static final String TEST_STATIC_1 = "TEST_STATIC_1_val";
		public static String TEST_STATIC_2       = "TEST_STATIC_2_val";
		
		public String testPublicString = "hoge";
		private String testPrivateString = "hoge";
		
		public int testPublicInt = 1;
		public Integer testPublicInteger = 1;
		
		public Map<String, String> testMapStringString = new HashMap<String, String>(){
			{
				put("key1", "val1");
				put("key2", "val2");
				put("key3", "val3");
			}
		};

		public Map<String, Object> testMapStringObject = new HashMap<String, Object>(){
			{
				List<String> list = new ArrayList<String>();
				list.add("list1");
				list.add("list2");
				list.add("list3");
				
				Map<String, String> map = new HashMap<String, String>();
				map.put("map1", "map_val1");
				map.put("map2", "map_val2");
				map.put("map3", "map_val3");
				
				List<Test2> list2 = new ArrayList<Test2>();
				list2.add(new Test2());
				list2.add(new Test2());
				list2.add(new Test2());
				
				put("key1", list);
				put("key2", map);
				put("key3", list2);
			}
		};
		
		public List<String> testListString = new ArrayList<String>(){
			{
				add("list1");
				add("list2");
				add("list3");
			}
		};
	}

	/**
	 * 動作確認用インナークラス2.
	 * @author pro-tsato
	 */
	public static class Test2 {
		public static final String TEST_STATIC_1 = "TEST_STATIC_1_val";
		public static String TEST_STATIC_2       = "TEST_STATIC_2_val";
		
		public String testPublicString = "hoge";
		private String testPrivateString = "hoge";
		
		public int testPublicInt = 1;
		public Integer testPublicInteger = 1;
		
		public Map<String, String> testMapStringString = new HashMap<String, String>(){
			{
				put("key1", "val1");
				put("key2", "val2");
				put("key3", "val3");
			}
		};
		
		@Override
		public String toString() {
			Map<String, Object> map = toMapExcludeStaticField(this, null);
			
			StringBuffer buff = new StringBuffer("{");
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				Object val = entry.getValue();
				buff.append(key + " : " + val.toString() + "\n");
			}
			buff.append("}");
			return buff.toString();
		}
	}
}
