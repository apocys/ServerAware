/*
 * Copyright © 2011. Team Lazer Beez (http://teamlazerbeez.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aware.sandwich;

import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@ThreadSafe
@Path("/sandwich/create")
public class SandwichMakerResource {

    private final SandwichMaker sandwichMaker;


    @Inject
    SandwichMakerResource(SandwichMaker sandwichMaker) {
        this.sandwichMaker = sandwichMaker;
    }

    @GET
    public Sandwich makeSandwich(@QueryParam("jam") @DefaultValue("100") int gramsOfJam,
                                 @QueryParam("peanutButter") @DefaultValue("200") int gramsOfPeanutButter) {
        Sandwich sandwich = sandwichMaker.makeSandwich(gramsOfPeanutButter, gramsOfJam);

        return sandwich;
    }
}
