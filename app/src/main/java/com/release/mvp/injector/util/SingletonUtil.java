/*
 * Copyright 2018 Vandolf Estrellado
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.release.mvp.injector.util;

import android.app.Application;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Mr.release
 * @create 2019/4/19
 * @Describe
 */
@Singleton
public final class SingletonUtil {

    private final Application application;

    @Inject
    SingletonUtil(Application application) {
        this.application = application;
    }


    public String doSomething() {
        return "SingletonUtil: " + hashCode() + ", Application: " + application.hashCode();
    }
}
