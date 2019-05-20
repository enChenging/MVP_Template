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

import com.release.mvp.dagger.base.ChildFragment;
import com.release.mvp.dagger.base.ChildFragmentScope;

import javax.inject.Inject;

/**
 * A class that does something.
 * <p>
 * This class has the {@link ChildFragmentScope} scope. This means that the child Fragmentq (a fragment
 * inside a fragment that is added using Fragmentq.getChildFragmentManager()) and all of its
 * dependencies will share the same instance of this class. However, different child fragments
 * instances will have their own instances of this class.
 * <p>
 * This is not available at the parent Fragmentq, Activity, and Application.
 */
@ChildFragmentScope
public final class ChildFragmentScopeUtil {

    private final Fragment childFragment;

    @Inject
    ChildFragmentScopeUtil(@ChildFragment Fragment childFragment) {
        this.childFragment = childFragment;
    }

    /**
     * @return the result of the work done here as a string. For this example, this returns its
     * {@link #hashCode()} and the child fragment's {@link #hashCode()}.
     */
    public String doSomething() {
        return "ChildFragmentScopeUtil: " + hashCode()
                + ", child Fragmentq: " + childFragment.hashCode();
    }
}
