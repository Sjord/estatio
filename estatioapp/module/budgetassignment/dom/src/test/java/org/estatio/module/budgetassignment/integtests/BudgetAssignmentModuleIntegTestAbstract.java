/*
 *
 *  Copyright 2012-2014 Eurocommercial Properties NV
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
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
package org.estatio.module.budgetassignment.integtests;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import org.apache.isis.applib.AppManifestAbstract;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.core.integtestsupport.IntegrationTestAbstract2;

import org.isisaddons.module.fakedata.FakeDataModule;
import org.isisaddons.module.fakedata.dom.FakeDataService;
import org.isisaddons.module.security.SecurityModule;

import org.incode.module.fixturesupport.dom.scripts.TeardownFixtureAbstract;

import org.estatio.module.budgetassignment.EstatioBudgetAssignmentModule;

public abstract class BudgetAssignmentModuleIntegTestAbstract extends IntegrationTestAbstract2 {

    @BeforeClass
    public static void initClass() {
        bootstrapUsing(AppManifestAbstract.Builder.forModules(
                EstatioBudgetAssignmentModule.class,
                SecurityModule.class,
                FakeDataModule.class));
    }

    @Before
    public void setup() {
        runFixtureScript(new EstatioBudgetAssignmentModule.Setup());
        runFixtureScript(new FixtureScript() {
            @Override
            protected void execute(final ExecutionContext executionContext) {
            }
        });
    }

    @After
    public void tearDown() {
        runFixtureScript(new TeardownFixtureAbstract() {
            @Override
            protected void execute(final ExecutionContext executionContext) {
            }
        });
        runFixtureScript(new EstatioBudgetAssignmentModule.Teardown());
    }

    @Inject
    protected FakeDataService fakeDataService;

}