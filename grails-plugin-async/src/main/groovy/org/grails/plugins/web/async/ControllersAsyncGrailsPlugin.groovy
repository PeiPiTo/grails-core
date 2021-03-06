/*
 * Copyright 2011 SpringSource
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.grails.plugins.web.async

import grails.async.Promises
import grails.util.GrailsUtil

import org.grails.plugins.web.async.mvc.AsyncActionResultTransformer
import org.springframework.context.ApplicationContext

/**
 * Async support for the Grails 2.0. Doesn't do much right now, most logic handled
 * by the compile time transform.
 *
 * @author Graeme Rocher
 * @since 2.0
 */
class ControllersAsyncGrailsPlugin {
    def version = GrailsUtil.getGrailsVersion()
    def loadAfter = ['controllers']

    def doWithSpring = {
        asyncPromiseResponseActionResultTransformer(AsyncActionResultTransformer)
    }

    def doWithDynamicMethods(ApplicationContext appCtx) {
        Promises.promiseFactory.addPromiseDecoratorLookupStrategy(new WebRequestPromiseDecoratorLookupStrategy())
    }
}
