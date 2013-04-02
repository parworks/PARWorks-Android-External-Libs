/*******************************************************************************
 * Copyright 2013 PAR Works, Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.parworks.arcameraview;

import com.parworks.androidlibrary.ar.ARSites;

public class ARClient {
	
	private static final String API_KEY = "4d24145a-1c98-45b5-b836-b42c9827c35d";
	private static final String SECRET_KEY = "6b884bb8-4e85-4e16-bb3b-0dded1c7ea96";
	
	private static ARSites ARSITES_INSTANCE;
	
	public static ARSites getARSites() {
		if (ARSITES_INSTANCE == null) {
			ARSITES_INSTANCE = new ARSites(API_KEY, SECRET_KEY);
		}
		return ARSITES_INSTANCE;
	}

}
