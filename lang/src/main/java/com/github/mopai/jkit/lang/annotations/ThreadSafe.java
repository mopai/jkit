package com.github.mopai.jkit.lang.annotations;

import java.lang.annotation.*;

/**
 * 线程安全的
 */
@Documented
@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ThreadSafe {
}
