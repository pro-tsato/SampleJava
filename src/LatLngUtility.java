
/**
 * 緯度経度に関するユーティリティクラス.
 * <p>【参考資料】
 * <ul>
 * <li>距離1kmあたりの緯度・経度の度数を計算（日本・北緯35度） | EasyRamble
 *  - http://easyramble.com/latitude-and-longitude-per-kilometer.html</li>
 * </ul>
 * 
 * <p>【動作確認用ツール】
 * <ul>
 * <li>緯度経度から地図化|Google Maps API v3を使ったポイント地図化|谷謙二研究室
 *  - http://ktgis.net/gcode/lonlatmapping.html</li>
 * <li>ShowGoogleMap
 *  - http://www.motohasi.net/GPS/ShowGoogleMap.php</li>
 * <li>２地点間の距離と方位角 - 高精度計算サイト
 *  - http://keisan.casio.jp/exec/system/1257670779</li>
 * <li>住所や地名から緯度・経度を一括で取得するツール | ScrapEngineer
 *  - http://scrap.php.xdomain.jp/create_tool/get_lanlng_get_address/</li>
 * </ul>
 * 
 * @author pro-tsato
 */
public class LatLngUtility {
	
	/** 地球半径：6378.137 （出典：２地点間の距離と方位角 - 高精度計算サイト - http://keisan.casio.jp/exec/system/1257670779） */
	public static final double EARTH_DIAMETER = 6378.137;

	/**
	 * 指定緯度（lat）と、距離（distance）より、距離あたりの度数（緯度）を返す。
	 * <p>【参考サイト】
	 * <ul>
	 * <li>距離1kmあたりの緯度・経度の度数を計算（日本・北緯35度） | EasyRamble
	 *  - http://easyramble.com/latitude-and-longitude-per-kilometer.html</li>
	 * </ul>
	 * @param lat 緯度
	 * @param distance 距離（km）
	 * @return 距離あたりの度数（緯度）
	 */
	public static double getLatDistanceAngle(double lat, int distance) {
		
		// 円周＝半径×２×π
		double cir = EARTH_DIAMETER * (double)2 * Math.PI;
		
		// １度あたりの距離＝円周÷360
		double dis = cir / (double)360;
		
		// 引数distance距離あたりの度数
		double angle = distance / dis;
		
		return angle;
	}
	
	/**
	 * 現在緯度（lat）と、経度（lng）と、距離（distance）より、距離あたりの度数（経度）を返す。
	 * <p>【参考サイト】
	 * <ul>
	 * <li>距離1kmあたりの緯度・経度の度数を計算（日本・北緯35度） | EasyRamble
	 *  - http://easyramble.com/latitude-and-longitude-per-kilometer.html</li>
	 * </ul>
	 * @param lat 緯度
	 * @param lng 経度
	 * @param distance 距離（km）
	 * @return 距離あたりの度数（経度）
	 */
	public static double getLngDistanceAngle(double lat, double lng, int distance) {
		
		// 任意の緯度あたりの円周＝任意の緯度の半径（cos×半径）×２×π
		double cir = (Math.cos(lat * Math.PI / (double)180) * EARTH_DIAMETER) * (double)2 * Math.PI;
		
		// １度あたりの距離＝円周÷360
		double dis = cir / (double)360;
		
		// 引数distance距離あたりの度数
		double angle = distance / dis;
		
		return angle;
	}

	/**
	 * 動作確認要main
	 * @param args 
	 */
	public static void main(String[] args) {
		
		// 現在緯度と、距離より、緯度を出す。
		System.out.println(getLatDistanceAngle(26.212432, 1));
		
		// 現在緯度、経度と距離より±経度を出す。
		System.out.println(getLatDistanceAngle(26.212432, 1) + "," + getLngDistanceAngle(26.212432, 127.679200, 1));
		
		// 現在緯度と、距離より、緯度を出す。
		System.out.println(getLatDistanceAngle(69.126739, 1));
		
		// 現在緯度、経度と距離より±経度を出す。
		System.out.println(getLatDistanceAngle(69.126739, 1) + "," + getLngDistanceAngle(69.126739, 141.687967, 1));
	}
}
