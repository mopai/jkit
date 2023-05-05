package com.github.mopai.jkit.lang.annotations;

import java.lang.annotation.*;

/**
 * 不可变的
 */
@Documented
@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Immutable {
}
