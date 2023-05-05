package com.github.mopai.jkit.lang.annotations;

import java.lang.annotation.*;

/**
 * 守护者
 */
@Documented
@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GuardedBy {
    String value();
}
