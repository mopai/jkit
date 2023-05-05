package com.github.mopai.jkit.lang.utils;

import com.github.mopai.jkit.lang.collects.Lists;
import com.github.mopai.jkit.lang.collects.Maps;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexUtils {
    private static final Map<String, Pattern> REGEX_CACHE = Maps.newConcurrentMap();

    private RegexUtils() {
    }

    // region [match]
    public static boolean match(final String content, final String regexp) {
        if (StringUtils.isEmpty(content) || StringUtils.isEmpty(regexp)) {
            return false;
        }
        Pattern pattern = buildIfAbsent(regexp);
        return pattern.matcher(content).matches();
    }

    public static boolean matchAny(final String content, final String... regexps) {
        for (String regexp : regexps) {
            if (match(content, regexp)) {
                return true;
            }
        }
        return false;
    }
    // endregion

    //region [group]
    public static String group(final Integer groupIndex, final String content, final String regexp) {
        if (groupIndex == null || groupIndex < 0) {
            return null;
        }
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        if (StringUtils.isEmpty(regexp)) {
            return null;
        }
        Pattern pattern = buildIfAbsent(regexp);
        Matcher matcher = pattern.matcher(content);
        if (!matcher.find()) {
            return null;
        }
        if (groupIndex > matcher.groupCount()) {
            return null;
        }
        return matcher.group(groupIndex);
    }

    public static String group(final Integer groupIndex, final String content, final String... regexps) {
        for (String regexp : regexps) {
            String result = group(groupIndex, content, regexp);
            if (StringUtils.isNotEmpty(result)) {
                return result;
            }
        }
        return null;
    }
    // endregion

    // region [extract]
    public static String extract(final String content, final String regex, final String template) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        if (StringUtils.isEmpty(regex)) {
            return null;
        }
        Pattern pattern = buildIfAbsent(regex);
        Matcher matcher = pattern.matcher(content);
        if (!matcher.find()) {
            return null;
        }
        String result = template;
        for (int i = 0; i <= matcher.groupCount(); i++) {
            result = StringUtils.replace(result, "$" + i, matcher.group(i));
        }
        return result;
    }

    public static List<String> extractAll(final String content, final String regex, final String template) {
        List<String> results = Lists.newArrayList();
        if (StringUtils.isEmpty(content)) {
            return results;
        }
        if (StringUtils.isEmpty(regex)) {
            return results;
        }
        Pattern pattern = buildIfAbsent(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            String result = template;
            for (int i = 0; i <= matcher.groupCount(); i++) {
                result = StringUtils.replace(result, "$" + i, matcher.group(i));
            }
            results.add(result);
        }
        return results;
    }
    // endregion

    // region [assistant]
    private static Pattern buildIfAbsent(String regex) {
        return REGEX_CACHE.computeIfAbsent(regex, (key) -> Pattern.compile(regex));
    }
    // endregion
}
