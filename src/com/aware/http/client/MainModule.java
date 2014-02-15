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

package com.aware.http.client;

import com.aware.db.dao.ClientDao;
import com.aware.db.dao.ContactDao;
import com.aware.db.dao.FriendsDao;
import com.aware.service.ClientService;
import com.aware.service.FriendsService;
import com.aware.service.SendingService;
import com.google.inject.AbstractModule;

public class MainModule extends AbstractModule {

    @Override
    protected void configure() {
//        bind(PeanutButter.class).to(OrganicCrunchyValenciaPeanutButter.class).in(Scopes.SINGLETON);

//        bind(MainServletModule.class);
        bind(FriendsService.class);
        bind(SendingService.class);
        
        bind(ClientDao.class);
        bind(FriendsDao.class);
        bind(ContactDao.class);
        
        bind(PositionManager.class);

    }
}
