package com.github.mopai.jkit.lang.utils;

import com.github.mopai.jkit.lang.collects.Arrays;
import com.github.mopai.jkit.lang.collects.Lists;
import com.github.mopai.jkit.lang.constants.StringConstant;
import com.github.mopai.jkit.lang.enums.TrimMode;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

public final class StringUtils extends CharSequenceUtils implements StringConstant {

    private static final int PAD_LIMIT = 8192;

    private StringUtils() {

    }

    // region [pad]
    public static String leftPad(final String str, final int size, final char padChar) {
        if (str == null) {
            return null;
        }
        final int pads = size - str.length();
        if (pads <= 0) {
            return str;
        }
        if (pads > PAD_LIMIT) {
            return leftPad(str, size, String.valueOf(padChar));
        }
        return repeat(padChar, pads).concat(str);
    }

    public static String leftPad(final String str, final int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = SPACE;
        }
        final int strLen = str.length();
        final int padLen = padStr.length();
        final int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return leftPad(str, size, padStr.charAt(0));
        }
        if (pads == padLen) {
            return padStr.concat(str);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        } else {
            final char[] padding = new char[pads];
            final char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str);
        }

    }

    public static String rightPad(final String str, final int size, final char padChar) {
        if (str == null) {
            return null;
        }
        final int pads = size - str.length();
        if (pads <= 0) {
            return str;
        }
        if (pads > PAD_LIMIT) {
            return rightPad(str, size, String.valueOf(padChar));
        }
        return str.concat(repeat(padChar, pads));
    }

    public static String rightPad(final String str, final int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = SPACE;
        }
        final int strLen = str.length();
        final int padLen = padStr.length();
        final int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return rightPad(str, size, padStr.charAt(0));
        }
        if (pads == padLen) {
            return str.concat(padStr);
        } else if (pads < padLen) {
            return str.concat(padStr.substring(0, pads));
        } else {
            final char[] padding = new char[pads];
            final char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return str.concat(new String(padding));
        }
    }


    // endregion

    // region [repeat]
    public static String repeat(final char c, final int repeat) {
        if (repeat <= 0) {
            return EMPTY;
        }
        final char[] buf = new char[repeat];
        for (int i = repeat - 1; i >= 0; i--) {
            buf[i] = c;
        }
        return new String(buf);
    }
    // endregion

    // region [sub]
    public static String subString(final String str, int start, int end) {
        if (str == null) {
            return null;
        }
        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }
        if (end > str.length()) {
            end = str.length();
        }
        if (start > end) {
            return EMPTY;
        }
        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }
        return str.substring(start, end);
    }

    public static String left(final String str, final int len) {
        if (str == null) {
            return null;
        }
        if (len <= 0) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(0, len);
    }

    public static String right(final String str, final int len) {
        if (str == null) {
            return null;
        }
        if (len <= 0) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(str.length() - len);
    }

    public static String leftBy(final String str, final String separator) {
        if (isEmpty(str) || isEmpty(separator)) {
            return str;
        }
        final int pos = str.indexOf(separator);
        if (pos == INDEX_NOT_FOUND) {
            return str;
        }
        return str.substring(0, pos);
    }

    public static String rightBy(final String str, final String separator) {
        if (isEmpty(str) || isEmpty(separator)) {
            return str;
        }
        final int pos = str.lastIndexOf(separator);
        if (pos == INDEX_NOT_FOUND) {
            return str;
        }
        return str.substring(pos + separator.length());
    }

    public static String betweenBy(final String str, final String start, String end) {
        if (str == null || start == null || end == null) {
            return null;
        }
        final int startIdx = str.indexOf(start);
        if (startIdx == INDEX_NOT_FOUND) {
            return null;
        }
        final int endIdx = str.lastIndexOf(end);
        if (endIdx == INDEX_NOT_FOUND) {
            return null;
        }
        return str.substring(startIdx + start.length(), endIdx);
    }
    // endregion

    // region [trim]
    public static String trim(final String str, TrimMode trimMode, Predicate<Character> predicate) {
        if (str == null) {
            return null;
        }
        final int length = str.length();
        int lIdx = 0;
        int rIdx = length;
        if (trimMode.getCode() <= 0) {
            while ((lIdx < rIdx) && predicate.test(str.charAt(lIdx))) {
                lIdx++;
            }
        }
        if (trimMode.getCode() >= 0) {
            while ((lIdx < rIdx) && predicate.test(str.charAt(rIdx - 1))) {
                rIdx--;
            }
        }
        if ((lIdx > 0) || (rIdx < length)) {
            return str.substring(lIdx, rIdx);
        } else {
            return str;
        }
    }

    public static String trim(final String str) {
        return trim(str, TrimMode.ALL, Character::isWhitespace);
    }

    public static String leftTrim(final String str) {
        return trim(str, TrimMode.LEFT, Character::isWhitespace);
    }

    public static String rightTrim(final String str) {
        return trim(str, TrimMode.RIGHT, Character::isWhitespace);
    }

    // endregion

    // region [remove]
    public static String remove(final String str, final String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        return replace(str, remove, EMPTY);
    }

    public static String remove(final String str, final char remove) {
        if (isEmpty(str) || str.indexOf(remove) == INDEX_NOT_FOUND) {
            return str;
        }
        final char[] chars = str.toCharArray();
        int pos = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != remove) {
                chars[pos++] = chars[i];
            }
        }
        return new String(chars, 0, pos);
    }

    public static String removeIgnoreCase(final String str, final String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        return replaceIgnoreCase(str, remove, EMPTY);
    }

    public static String removeStart(final String str, final String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.startsWith(remove)) {
            return str.substring(remove.length());
        }
        return str;
    }

    public static String removeStartIgnoreCase(final String str, final String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (startsWithIgnoreCase(str, remove)) {
            return str.substring(remove.length());
        }
        return str;
    }

    public static String removeEnd(final String str, final String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.endsWith(remove)) {
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }

    public static String removeEndIgnoreCase(final String str, final String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (endsWithIgnoreCase(str, remove)) {
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }
    // endregion

    // region [replace]
    public static String replace(final String str, final String search, final String replacement, final boolean ignoreCase) {
        if (isEmpty(str) || isEmpty(search) || replacement == null) {
            return str;
        }
        String strText = str;
        String searchText = search;
        if (ignoreCase) {
            strText = strText.toLowerCase();
            searchText = searchText.toLowerCase();
        }
        int start = 0;
        int end = strText.indexOf(searchText, start);
        if (end == INDEX_NOT_FOUND) {
            return str;
        }
        int increase = replacement.length() - search.length();
        increase = Math.max(0, increase);
        increase *= 4;
        final StringBuilder builder = new StringBuilder(str.length() + increase);
        while (end != INDEX_NOT_FOUND) {
            builder.append(str, start, end).append(replacement);
            start = end + search.length();
            end = strText.indexOf(searchText, start);
        }
        builder.append(str, start, str.length());
        return builder.toString();
    }

    public static String replace(final String str, final String search, final String replacement) {
        return replace(str, search, replacement, false);
    }

    public static String replaceIgnoreCase(final String str, final String search, final String replacement) {
        return replace(str, search, replacement, true);
    }
    // endregion

    // region [case]
    public static String lowerCase(final String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.toLowerCase();
    }

    public static String lowerCase(final String str, final Locale locale) {
        if (isEmpty(str)) {
            return str;
        }
        return str.toLowerCase(locale);
    }

    public static String upperCase(final String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.toUpperCase();
    }

    public static String upperCase(final String str, final Locale locale) {
        if (isEmpty(str)) {
            return str;
        }
        return str.toUpperCase(locale);
    }

    public static String swapCase(final String str) {
        if (isEmpty(str)) {
            return str;
        }
        final char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            final char c = chars[i];
            if (Character.isUpperCase(c)) {
                chars[i] = Character.toLowerCase(c);
            } else if (Character.isTitleCase(c)) {
                chars[i] = Character.toLowerCase(c);
            } else if (Character.isLowerCase(c)) {
                chars[i] = Character.toUpperCase(c);
            }
        }
        return new String(chars);
    }

    public static String camelCase(final String str) {
        if (isEmpty(str)) {
            return str;
        }
        if (contains(str, CharUtils.UNDERLINE)) {
            final StringBuilder builder = new StringBuilder(str.length());
            boolean upperCase = false;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == CharUtils.UNDERLINE) {
                    upperCase = true;
                } else if (upperCase) {
                    builder.append(Character.toUpperCase(c));
                    upperCase = false;
                } else {
                    builder.append(Character.toLowerCase(c));
                }
            }
            return builder.toString();
        } else {
            return str;
        }
    }

    public static String symbolCase(final String str, char symbol) {
        if (isEmpty(str)) {
            return str;
        }
        final StringBuilder builder = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                final Character preChar = (i > 0) ? str.charAt(i - 1) : null;
                final Character nextChar = (i < str.length() - 1) ? str.charAt(i + 1) : null;
                if (preChar != null) {
                    if (symbol == preChar) {
                        // 前一个未分割符
                        if (nextChar == null || Character.isLowerCase(nextChar)) {
                            c = Character.toLowerCase(c);
                        }
                    } else if (Character.isLowerCase(preChar)) {
                        builder.append(symbol);
                        if (nextChar == null || Character.isLowerCase(nextChar)) {
                            c = Character.toLowerCase(c);
                        }
                    } else {
                        if (nextChar == null || Character.isLowerCase(nextChar)) {
                            builder.append(symbol);
                            c = Character.toLowerCase(c);
                        }
                    }
                } else {
                    if (nextChar == null || Character.isLowerCase(nextChar)) {
                        c = Character.toLowerCase(c);
                    }
                }
            }
            builder.append(c);
        }
        return builder.toString();
    }
    // endregion

    // region [split]
    public static String[] split(final String str, final char separator, final boolean ignoreEmpty, boolean isTrim) {
        if (isEmpty(str)) {
            return Arrays.EMPTY_STRING_ARRAY;
        }
        final List<String> list = Lists.newArrayList();
        int i = 0;
        int start = 0;
        boolean match = false;
        boolean lastMatch = false;
        while (i < str.length()) {
            final char c = str.charAt(i);
            if (c == separator) {
                if (match || !ignoreEmpty) {
                    if (isTrim) {
                        list.add(trim(str.substring(start, i)));
                    } else {
                        list.add(str.substring(start, i));
                    }
                    match = false;
                    lastMatch = true;
                }
                start = ++i;
                continue;
            }
            lastMatch = false;
            match = true;
            i++;
        }
        if (match || !ignoreEmpty && lastMatch) {
            if (isTrim) {
                list.add(trim(str.substring(start, i)));
            } else {
                list.add(str.substring(start, i));
            }
        }
        return list.toArray(Arrays.EMPTY_STRING_ARRAY);
    }

    public static String[] split(final String str, final char separator) {
        return split(str, separator, true, true);
    }

    public static String[] split(final String str, final String separator, final boolean ignoreEmpty, boolean isTrim) {
        if (isEmpty(str)) {
            return Arrays.EMPTY_STRING_ARRAY;
        }

        if (separator == null) {
            final List<String> list = Lists.newArrayList();
            int i = 0;
            int start = 0;
            boolean match = false;
            boolean lastMatch = false;
            while (i < str.length()) {
                final char c = str.charAt(i);
                if (Character.isWhitespace(c)) {
                    if (match || !ignoreEmpty) {
                        lastMatch = true;
                        if (isTrim) {
                            list.add(trim(str.substring(start, i)));
                        } else {
                            list.add(str.substring(start, i));
                        }
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
            if (match || !ignoreEmpty && lastMatch) {
                if (isTrim) {
                    list.add(trim(str.substring(start, i)));
                } else {
                    list.add(str.substring(start, i));
                }
            }
            return list.toArray(Arrays.EMPTY_STRING_ARRAY);
        } else if (separator.length() == 1) {
            final char separatorChar = separator.charAt(0);
            return split(str, separatorChar, ignoreEmpty, isTrim);
        } else {
            final List<String> list = Lists.newArrayList();
            int i = 0;
            int start = 0;
            boolean match = false;
            boolean lastMatch = false;
            while (i < str.length()) {
                final char c = str.charAt(i);
                if (separator.indexOf(c) >= 0) {
                    if (match || !ignoreEmpty) {
                        lastMatch = true;
                        if (isTrim) {
                            list.add(trim(str.substring(start, i)));
                        } else {
                            list.add(str.substring(start, i));
                        }
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
            if (match || !ignoreEmpty && lastMatch) {
                if (isTrim) {
                    list.add(trim(str.substring(start, i)));
                } else {
                    list.add(str.substring(start, i));
                }
            }
            return list.toArray(Arrays.EMPTY_STRING_ARRAY);
        }
    }

    public static String[] split(final String str, final String separator) {
        return split(str, separator, true, true);
    }
    // endregion

    // region [join]
    public static String join(final Object[] array, final char separator, final int startIndex, final int endIndex) {
        if (array == null) {
            return null;
        }
        final int itemCount = endIndex - startIndex;
        if (itemCount <= 0) {
            return EMPTY;
        }
        final StringBuilder builder = newStringBuilder(itemCount);
        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                builder.append(separator);
            }
            if (array[i] != null) {
                builder.append(array[i]);
            }
        }
        return builder.toString();
    }

    public static String join(final Object[] array, final char separator) {
        return join(array, separator, 0, array.length);
    }

    // endregion

    // region [format]
    public static String format(final String template, final Object... params) {
        if (isBlank(template) || Arrays.isEmpty(params)) {
            return template;
        }
        final StringBuilder builder = new StringBuilder(template.length() + 50);

        int handledPos = 0; // 已处理到的位置
        int delimIndex; // 占位符所在位置
        for (int paramIndex = 0; paramIndex < params.length; paramIndex++) {
            delimIndex = template.indexOf(EMPTY_JSON, handledPos);
            // 剩余部分无占位符
            if (delimIndex == INDEX_NOT_FOUND) {
                if (handledPos == 0) {
                    return template;
                }
                builder.append(template, handledPos, template.length());
                return builder.toString();
            }

            if (delimIndex > 0 && template.charAt(delimIndex - 1) == CharUtils.BACKSLASH) {
                if (delimIndex > 1 && template.charAt(delimIndex - 2) == CharUtils.BACKSLASH) {
                    builder.append(template, handledPos, delimIndex - 1);
                    builder.append(params[paramIndex]);
                    handledPos = delimIndex + 2;
                } else {
                    paramIndex--;
                    builder.append(template, handledPos, delimIndex - 1);
                    builder.append(CharUtils.DELTM_START);
                    handledPos = delimIndex + 1;
                }
            } else {
                builder.append(template, handledPos, delimIndex);
                builder.append(params[paramIndex]);
                handledPos = delimIndex + 2;
            }
        }
        builder.append(template, handledPos, template.length());
        return builder.toString();
    }
    // endregion

    // region [assistant]
    private static StringBuilder newStringBuilder(final int itemCount) {
        return new StringBuilder(itemCount * 16);
    }
    // endregion

}
