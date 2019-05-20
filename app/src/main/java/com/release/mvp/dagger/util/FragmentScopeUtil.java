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

package com.release.mvp.dagger.util;

import androidx.fragment.app.Fragment;

import com.release.mvp.dagger.base.FragmentScope;
import com.release.mvp.dagger.base.Fragmentq;

import javax.inject.Inject;

/**
 * A class that does something.
 * <p>
 * This class has the {@link FragmentScope} scope. This means that the Fragmentq and all of its child
 * fragments and their dependencies will share the same instance of this class. However, different
 * fragment instances will have their own instances.
 * <p>
 * This is not available at the Activity and Application.
 */
@FragmentScope
public final class FragmentScopeUtil {

    private final Fragment fragment;

    @Inject
    FragmentScopeUtil(@Fragmentq Fragment fragment) {
        this.fragment = fragment;
    }

    /**
     * @return the result of the work done here as a string. For this example, this returns its
     * {@link #hashCode()} and the fragment's {@link #hashCode()}.
     */
    public String doSomething() {
        return "FragmentScopeUtil: " + hashCode() + ", Fragmentq: " + fragment.hashCode();
    }
}
