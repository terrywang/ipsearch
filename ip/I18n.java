/*
 * 2004-9-1
 * Terry
 */
package ip;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * i18n
 * 
 * @author terry
 */
public class I18n {

	private static ResourceBundle resourceBundle;

	public I18n (String baseName, Locale locale, ClassLoader classloader) {
		resourceBundle = ResourceBundle.getBundle(baseName, locale, classloader);
	}

	/**
	 * 从资源文件中返回字符串 我们不希望程序崩溃，所以如果没有找到Key，就直接返回Key
	 * 
	 * @param key
	 * @return 资源
	 */
	public static String getResourceString (String key) {
		try {
			return resourceBundle.getString(key);
		}
		catch (MissingResourceException e) {
			return key;
		}
		catch (NullPointerException e) {
			return "!" + key + "!";
		}
	}
}