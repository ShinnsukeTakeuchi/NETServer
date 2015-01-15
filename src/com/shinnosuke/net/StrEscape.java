package com.shinnosuke.net;

/**
 * 文字列エスケープクラス
 *
 * @author Shinnosuke
 *
 */
public class StrEscape {
	/**
	 * コンストラクタ
	 */
	public StrEscape() {
	}

	/**
	 * HTMLエスケープ
	 *
	 * @param str
	 * @return String 変換後文字
	 */
	public String htmlEscape(String str) {
		String strOut = "";
		for (int i = 0; i < str.length(); i++) {
			Character c = new Character(str.charAt(i));
			switch (c.charValue()) {
			case '&' :
				strOut = strOut.concat("&amp;");
				break;
			case '<' :
				strOut = strOut.concat("&lt;");
				break;
			case '>' :
				strOut = strOut.concat("&gt;");
				break;
			case '"' :
				strOut = strOut.concat("&quot;");
				break;
			case '\'' :
				strOut = strOut.concat("&#39;");
				break;
			default :
				strOut = strOut.concat(c.toString());
				break;
			}
		}
		return strOut;
	}

	/**
	 * 全て全角に変換
	 *
	 * @param str
	 * @return String 変換後文字
	 */
	public String 全て全角(String str) {
		// エスケープ
		return str.toUpperCase();
	}

	/**
	 * 全て半角
	 *
	 * @param str
	 * @return String 変換後文字
	 */
	public String 全て半角(String str) {
		// エスケープ
		return str.toLowerCase();
	}
}
