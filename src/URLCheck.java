
/**
 * URLチェックに関するクラス.
 * 正規表現によるURLチェックの検証
 * @author pro-tsato
 */
public class URLCheck {
	
	/**
	 * URL 正規表現チェック.
	 * <p>【参考サイト】
	 * <ul>
	 * <li>Perlメモ
	 *  | <a href="http://www.din.or.jp/~ohzaki/perl.htm#httpURL">http://www.din.or.jp/~ohzaki/perl.htm#httpURL</a></li>
	 * <li>正規表現：正しいＵＲＬかどうか調べる - phpspot
	 *  | <a href="http://phpspot.net/php/pg%E6%AD%A3%E8%A6%8F%E8%A1%A8%E7%8F%BE%EF%BC%9A%E6%AD%A3%E3%81%97%E3%81%84%EF%BC%B5%EF%BC%B2%EF%BC%AC%E3%81%8B%E3%81%A9%E3%81%86%E3%81%8B%E8%AA%BF%E3%81%B9%E3%82%8B.html">http://phpspot.net/php/pg正規表現：正しいＵＲＬかどうか調べる.html</a></li>
	 * </ul>
	 * @param url チェック対象文字列
	 * @return boolean
	 */
	public static boolean checkUrl(String url) {

		// 正規表現
		final String regularExpression = "^(https?|ftp)(:\\/\\/[-_.!~*\\'()a-zA-Z0-9;\\/?:\\@&=+\\$,%#]+)$";

		// 正規表現チェック
		if(url.matches(regularExpression)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 動作確認要main
	 * @param args 
	 */
	public static void main(String[] args) {
		String[] urlArr = new String[]{
				"http://hogehoge",
				"http://hogehoge/?test=hogehoge",
				"http://hogehoge/index.html?test=hogehoge",
				"https://www.hogehoge.co.jp/",
				"https://www.hogehoge.co.jp/?test=hogehoge",
				"https://www.hogehoge.co.jp/index.html?test=hogehoge",
				"https://日本語.jp/",
				"http://192.168.0.1/index.html",
				"http://192.168.0.1:8080/index.html",
				"http://192.168.0.1:8080/日本語.html",
				"http://192.168.0.1:8080/%E6%97%A5%E6%9C%AC%E8%AA%9E.html",
				"http://localhost/~user/index.html",
				"http://user:pass@localhost/~user/index.html",
			};
		
		for (String url : urlArr) {
			boolean bool = false;
			if (bool = checkUrl(url)) {
				System.out.println(String.format("%-5s", bool) + " : "  + url);
			} else {
				System.err.println(String.format("%-5s", bool) + " : "  + url);
			}
		}
	}
}
