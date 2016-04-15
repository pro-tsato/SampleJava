package xyz.ronrico151.samplejava;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * トレース情報取得のユーティリティ.
 * @author pro-tsato
 */
public class TraceUtility {

	/**
	 * トレース用：例外発生箇所を出力する
	 * @param throwable Throwable
	 * @return
	 */
	public static String printTrace(Throwable throwable) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);
		pw.flush();
		return sw.toString();
	}

	/**
	 * 動作確認用main
	 * @param args 
	 */
	public static void main(String[] args) {
		try {
			test(5);
		} catch (Exception e) {
			System.err.println(printTrace(e));
			
			try {
				throw new Exception("例外が発生しました。", e);
			} catch (Exception ex) {
				System.err.println(printTrace(ex));
			}
		}
	}
	
	/**
	 * テストメソッド
	 * @param i 再起回数
	 * @throws Exception 例外
	 */
	public static void test(int i) throws Exception {
		System.out.println(i);
		if (i <= 0) {
			throw new Exception("例外発生！！：i=" + i);
		} else {
			test(--i);
		}
	}
}
