package com.lls.lemon.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

/************************************
 * StringUtils
 * @author liliangshan
 * @date 2019-03-27
 ************************************/
public class StringUtils {

  /**
   *
   * @param str
   * @return
   */
  public static boolean isNotEmpty(String str) {
    return (str != null && !str.isEmpty());
  }

  /**
   *
   * @param str
   * @return
   */
  public static boolean isNotBlank(String str) {
    return (isNotEmpty(str) && contains(str));
  }


  private static boolean contains(CharSequence str) {
    int strLen = str.length();
    for (int i = 0; i < strLen; i++) {
      if (!Character.isWhitespace(str.charAt(i))) {
        return true;
      }
    }
    return false;
  }

  public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {

    if (str == null) {
      return new String[0];
    }

    StringTokenizer st = new StringTokenizer(str, delimiters);
    List<String> tokens = new ArrayList<>();
    while (st.hasMoreTokens()) {
      String token = st.nextToken();
      if (trimTokens) {
        token = token.trim();
      }
      if (!ignoreEmptyTokens || token.length() > 0) {
        tokens.add(token);
      }
    }
    return toStringArray(tokens);
  }

  private static String[] toStringArray(Collection<String> collection) {
    if (collection == null) {
      return null;
    }
    return collection.toArray(new String[collection.size()]);
  }

}
