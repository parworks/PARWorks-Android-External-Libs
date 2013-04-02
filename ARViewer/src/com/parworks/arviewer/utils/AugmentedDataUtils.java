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
package com.parworks.arviewer.utils;

import java.util.ArrayList;
import java.util.List;

import com.parworks.androidlibrary.ar.AugmentedData;
import com.parworks.androidlibrary.ar.Overlay;
import com.parworks.androidlibrary.ar.OverlayImpl;
import com.parworks.androidlibrary.ar.Vertex;
import com.parworks.androidlibrary.response.AugmentImageResultResponse;
import com.parworks.androidlibrary.response.OverlayAugmentResponse;

public class AugmentedDataUtils {

	public static AugmentedData convertAugmentResultResponse(String imgId,
			AugmentImageResultResponse result) {
		List<OverlayAugmentResponse> overlayResponses = result.getOverlays();
		List<Overlay> overlays = new ArrayList<Overlay>();

		for (OverlayAugmentResponse overlayResponse : overlayResponses) {
			overlays.add(makeOverlay(overlayResponse, imgId));
		}

		AugmentedData augmentedData = new AugmentedData(result.getFov(),
				result.getFocalLength(), result.getScore(),
				result.isLocalization(), overlays);
		return augmentedData;
	}
	
	private static Overlay makeOverlay(OverlayAugmentResponse overlayResponse,
			String imgId) {
		Overlay overlay = new OverlayImpl(imgId, overlayResponse.getName(),
				overlayResponse.getDescription(),
				parseVertices(overlayResponse.getVertices()));
		return overlay;
	}

	private static List<Vertex> parseVertices(String serverOutput) {
		String[] points = serverOutput.split(",");

		List<Vertex> vertices = new ArrayList<Vertex>();
		for (int i = 0; i < points.length; i += 3) {
			float xCoord = Float.parseFloat(points[i]);
			float yCoord = Float.parseFloat(points[i + 1]);
			float zCoord = Float.parseFloat(points[i + 2]);
			vertices.add(new Vertex(xCoord, yCoord, zCoord));
		}
		return vertices;
	}
}
