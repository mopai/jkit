package com.github.mopai.jkit.lang.utils;

import com.github.mopai.jkit.lang.collects.Arrays;

public class CharSequenceUtils {
    public static final int INDEX_NOT_FOUND = -1;

    // region [indexOf]
    public static int indexOf(final CharSequence cs, final CharSequence findPart, final int start) {
        if (cs == null || findPart == null) {
            return INDEX_NOT_FOUND;
        }
        return cs.toString().indexOf(findPart.toString(), start);
    }

    public static int indexOf(final CharSequence cs, final CharSequence findPart) {
        return indexOf(cs, findPart, 0);
    }

    public static int indexOf(final CharSequence cs, final char findChar) {
        if (cs == null) {
            return INDEX_NOT_FOUND;
        }
        return cs.toString().indexOf(findChar);
    }

    public static int lastIndexOf(final CharSequence cs, final CharSequence findPart, final int start) {
        if (cs == null || findPart == null) {
            return INDEX_NOT_FOUND;
        }
        return cs.toString().lastIndexOf(findPart.toString(), start);
    }

    public static int lastIndexOf(final CharSequence cs, final CharSequence findPart) {
        return lastIndexOf(cs, findPart, 0);
    }

    public static int lastIndexOf(final CharSequence cs, final char findChar) {
        if (cs == null) {
            return INDEX_NOT_FOUND;
        }
        return cs.toString().lastIndexOf(findChar);
    }
    // endregion

    // region [length]
    public static int length(final CharSequence cs) {
        if (cs == null) {
            return 0;
        }
        return cs.length();
    }
    // endregion

    // region [count]
    public static int count(CharSequence content, CharSequence sub) {
        if (isAnyEmpty(content, sub) || sub.length() > content.length()) {
            return 0;
        }
        int count = 0;
        int idx = 0;
        final String strContent = content.toString();
        final String strSub = sub.toString();
        while ((idx = strContent.indexOf(strSub, idx)) > -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }

    public static int count(CharSequence content, char sub) {
        if (isEmpty(content)) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < content.length(); i++) {
            if (sub == content.charAt(i)) {
                count++;
            }
        }
        return count;
    }
    // endregion

    // region [compare]
    public static int compare(final CharSequence cs1, final CharSequence cs2, final boolean nullIsLess) {
        if (cs1 == cs2) {
            return 0;
        }
        if (cs1 == null) {
            return nullIsLess ? -1 : 1;
        }
        if (cs2 == null) {
            return nullIsLess ? 1 : -1;
        }
        return cs1.toString().compareTo(cs2.toString());
    }

    public static int compareIgnoreCase(final CharSequence cs1, final CharSequence cs2, final boolean nullIsLess) {
        if (cs1 == cs2) {
            return 0;
        }
        if (cs1 == null) {
            return nullIsLess ? -1 : 1;
        }
        if (cs2 == null) {
            return nullIsLess ? 1 : -1;
        }
        return cs1.toString().compareToIgnoreCase(cs2.toString());
    }
    // endregion

    // region [empty]
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isAnyEmpty(final CharSequence... css) {
        if (Arrays.isEmpty(css)) {
            return false;
        }
        for (CharSequence cs : css) {
            if (isEmpty(cs)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNoneEmpty(final CharSequence... css) {
        return !isAnyEmpty(css);
    }

    public static boolean isAllEmpty(final CharSequence... css) {
        if (Arrays.isEmpty(css)) {
            return true;
        }
        for (CharSequence cs : css) {
            if (isNotEmpty(cs)) {
                return false;
            }
        }
        return true;
    }
    // endregion

    // region [blank]
    public static boolean isBlank(final CharSequence cs) {
        if (cs == null || cs.length() == 0) {
            return true;
        }
        for (int i = 0; i < cs.length(); i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    public static boolean isAnyBlank(final CharSequence... css) {
        if (Arrays.isEmpty(css)) {
            return false;
        }
        for (CharSequence cs : css) {
            if (isBlank(cs)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNoneBlank(final CharSequence... css) {
        return !isAnyBlank(css);
    }

    public static boolean isAllBlank(final CharSequence... css) {
        if (Arrays.isEmpty(css)) {
            return true;
        }
        for (CharSequence cs : css) {
            if (isNotBlank(cs)) {
                return false;
            }
        }
        return true;
    }

    // endregion

    // region [with]
    public static boolean startsWith(final CharSequence cs, final CharSequence prefix, final boolean ignoreCase) {
        if (null == cs || null == prefix) {
            return null == cs && null == prefix;
        }
        if (ignoreCase) {
            return cs.toString().toLowerCase().startsWith(prefix.toString().toLowerCase());
        }
        return cs.toString().startsWith(prefix.toString());
    }

    public static boolean startsWith(final CharSequence cs, final CharSequence prefix) {
        return startsWith(cs, prefix, false);
    }

    public static boolean startsWithIgnoreCase(final CharSequence cs, final CharSequence prefix) {
        return startsWith(cs, prefix, true);
    }

    public static boolean startsWithAny(final CharSequence cs, final CharSequence... prefixes) {
        if (Arrays.isEmpty(prefixes)) {
            return false;
        }
        for (CharSequence prefix : prefixes) {
            if (startsWith(cs, prefix)) {
                return true;
            }
        }
        return false;
    }

    public static boolean startsWithAnyIgnoreCase(final CharSequence cs, final CharSequence... prefixes) {
        if (Arrays.isEmpty(prefixes)) {
            return false;
        }
        for (CharSequence prefix : prefixes) {
            if (startsWithIgnoreCase(cs, prefix)) {
                return true;
            }
        }
        return false;
    }


    public static boolean endsWith(final CharSequence cs, final CharSequence suffix, final boolean ignoreCase) {
        if (null == cs || null == suffix) {
            return null == cs && null == suffix;
        }
        if (ignoreCase) {
            return cs.toString().toLowerCase().endsWith(suffix.toString().toLowerCase());
        }
        return cs.toString().endsWith(suffix.toString());
    }

    public static boolean endsWith(final CharSequence cs, final CharSequence suffix) {
        return endsWith(cs, suffix, false);
    }

    public static boolean endsWithIgnoreCase(final CharSequence cs, final CharSequence suffix) {
        return endsWith(cs, suffix, true);
    }

    public static boolean endsWithAny(final CharSequence cs, final CharSequence... suffixes) {
        if (Arrays.isEmpty(suffixes)) {
            return false;
        }
        for (CharSequence suffix : suffixes) {
            if (endsWith(cs, suffix)) {
                return true;
            }
        }
        return false;
    }

    public static boolean endsWithAnyIgnoreCase(final CharSequence cs, final CharSequence... suffixes) {
        if (Arrays.isEmpty(suffixes)) {
            return false;
        }
        for (CharSequence suffix : suffixes) {
            if (endsWithIgnoreCase(cs, suffix)) {
                return true;
            }
        }
        return false;
    }
    // endregion

    // region [contains]
    public static boolean contains(final CharSequence cs, final char searchChar) {
        return indexOf(cs, searchChar) > -1;
    }

    public static boolean contains(final CharSequence cs, final CharSequence search, final boolean ignoreCase) {
        if (null == cs || null == search) {
            return null == cs && null == search;
        }
        if (ignoreCase) {
            return cs.toString().toLowerCase().contains(search.toString().toLowerCase());
        }
        return cs.toString().contains(search.toString());
    }

    public static boolean contains(final CharSequence cs, final CharSequence search) {
        return contains(cs, search, false);
    }

    public static boolean containsIgnoreCase(final CharSequence cs, final CharSequence search) {
        return contains(cs, search, true);
    }

    public static boolean containsAny(final CharSequence cs, final CharSequence... searches) {
        if (Arrays.isEmpty(searches)) {
            return false;
        }
        for (CharSequence search : searches) {
            if (contains(cs, search)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsAnyIgnoreCase(final CharSequence cs, final CharSequence... searches) {
        if (Arrays.isEmpty(searches)) {
            return false;
        }
        for (CharSequence search : searches) {
            if (containsIgnoreCase(cs, search)) {
                return true;
            }
        }
        return false;
    }
    // endregion
}
