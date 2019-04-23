package com.release.mvp.injector.base;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author Mr.release
 * @create 2019/4/18
 * @Describe 实现全局单例
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScope {
}
