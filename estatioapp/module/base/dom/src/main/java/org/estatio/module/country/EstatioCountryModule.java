/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.estatio.module.country;

import java.util.Set;

import com.google.common.collect.Sets;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import org.incode.module.country.dom.CountryModule;
import org.incode.module.country.dom.impl.Country;
import org.incode.module.country.dom.impl.State;
import org.incode.module.fixturesupport.dom.scripts.TeardownFixtureAbstract;

import org.estatio.module.base.platform.applib.ModuleAbstract;
import org.estatio.module.base.platform.fixturesupport.DemoData2Persist;
import org.estatio.module.base.platform.fixturesupport.DemoData2Teardown;
import org.estatio.module.country.fixtures.enums.Country_enum;

/**
 * This is a "proxy" for the country module defined in the platform.
 */
public final class EstatioCountryModule extends ModuleAbstract {

    @Override
    public Set<Class<?>> getDependenciesAsClass() {
        return Sets.newHashSet(CountryModule.class);
    }

    @Override
    public FixtureScript getRefDataSetupFixture() {
        return new FixtureScript() {
            @Override
            protected void execute(final ExecutionContext executionContext) {
                executionContext.executeChild(this,
                        new DemoData2Persist<Country_enum, Country>(Country_enum.class) {});
            }
        };
    }

    @Override
    public FixtureScript getTeardownFixture() {
        final TeardownFixtureAbstract teardownState = new TeardownFixtureAbstract() {
            @Override
            protected void execute(final ExecutionContext executionContext) {
                deleteFrom(State.class);
            }
        };
        return Utils.allOf(teardownState, new DemoData2Teardown<>(Country_enum.class));
    }



    public abstract static class ActionDomainEvent<S>
            extends org.apache.isis.applib.services.eventbus.ActionDomainEvent<S> { }

    public abstract static class CollectionDomainEvent<S,T>
            extends org.apache.isis.applib.services.eventbus.CollectionDomainEvent<S,T> { }

    public abstract static class PropertyDomainEvent<S,T>
            extends org.apache.isis.applib.services.eventbus.PropertyDomainEvent<S,T> { }

}
