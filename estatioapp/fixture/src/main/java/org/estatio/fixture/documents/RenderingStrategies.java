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
package org.estatio.fixture.documents;

import javax.inject.Inject;

import org.apache.isis.applib.services.clock.ClockService;

import org.isisaddons.module.security.dom.tenancy.ApplicationTenancyRepository;

import org.incode.module.docrendering.stringinterpolator.fixture.RenderingStrategyFSForStringInterpolator;
import org.incode.module.docrendering.stringinterpolator.fixture.RenderingStrategyFSForStringInterpolatorCaptureUrl;
import org.incode.module.docrendering.stringinterpolator.fixture.RenderingStrategyFSForStringInterpolatorPreviewAndCaptureUrl;
import org.incode.module.document.dom.impl.rendering.RenderingStrategyRepository;
import org.incode.module.document.fixture.DocumentTemplateFSAbstract;
import org.incode.module.docrendering.freemarker.fixture.RenderingStrategyFSForFreemarker;

public class RenderingStrategies extends DocumentTemplateFSAbstract {

    public static final String REF_SIPC = RenderingStrategyFSForStringInterpolatorPreviewAndCaptureUrl.REF;
    public static final String REF_SINC = RenderingStrategyFSForStringInterpolatorCaptureUrl.REF;
    public static final String REF_SI = RenderingStrategyFSForStringInterpolator.REF;
    public static final String REF_FMK = RenderingStrategyFSForFreemarker.REF;


    @Override
    protected void execute(final ExecutionContext executionContext) {

        // prereqs

        executionContext.executeChild(this, new RenderingStrategyFSForStringInterpolatorPreviewAndCaptureUrl());
        executionContext.executeChild(this, new RenderingStrategyFSForStringInterpolatorCaptureUrl());
        executionContext.executeChild(this, new RenderingStrategyFSForStringInterpolator());
        executionContext.executeChild(this, new RenderingStrategyFSForFreemarker());

    }

    @Inject
    private ApplicationTenancyRepository applicationTenancyRepository;
    @Inject
    private RenderingStrategyRepository renderingStrategyRepository;
    @Inject
    private ClockService clockService;


}
