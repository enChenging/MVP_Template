package com.release.mvp.injector.base;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author Mr.release
 * @create 2019/4/20
 * @Describe
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ChildFragmentScope {
}
