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
package com.parworks.arviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

public class ARImageView extends ImageView {
	// Image bitmap
	private Bitmap imageBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);

	// Parameters for pan/drag and pinch-zoom
	private float scaleFactor = 1f;
	private PointF translate = new PointF();

	public ARImageView(Context context) {
		super(context);
	}

	public ARImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ARImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.save();
		canvas.scale(scaleFactor, scaleFactor);
		canvas.translate(translate.x, translate.y);		
		//canvas.drawBitmap(imageBitmap, null, null);
		canvas.drawBitmap(imageBitmap, 0, 0, null);
		canvas.restore();
	}
	
	private Display getTheDisplay() {
	    return ((WindowManager) getContext().getSystemService(
	            Context.WINDOW_SERVICE)).getDefaultDisplay();
	}

	public int[] getBitmapOffset() {
		double bitmapRatio = ((double) imageBitmap.getWidth()) / imageBitmap.getHeight();
		double imageViewRatio = 0;
		
		if (getWidth() > 0) {
			imageViewRatio = ((double) getWidth()) / getHeight();
		} else {
			Display d = getTheDisplay();
			imageViewRatio = ((double)d.getWidth()) / d.getHeight();
		}

		double drawLeft, drawTop, drawHeight, drawWidth = 0;

		if (bitmapRatio > imageViewRatio) {
			drawLeft = 0;
		} else {
			drawTop = 0;
		}

		if (bitmapRatio > imageViewRatio) {
			drawLeft = 0;
			drawHeight = (imageViewRatio / bitmapRatio) * getHeight();
		} else {
			drawTop = 0;
			drawWidth = (bitmapRatio / imageViewRatio) * getWidth();
		}

		if (bitmapRatio > imageViewRatio) {
			drawLeft = 0;
			drawHeight = (imageViewRatio / bitmapRatio) * getHeight();
			drawTop = (getHeight() - drawHeight) / 2;
		} else {
			drawTop = 0;
			drawWidth = (bitmapRatio / imageViewRatio) * getWidth();
			drawLeft = (getWidth() - drawWidth) / 2;
		}

		return new int[] { (int) Math.rint(drawLeft), (int) Math.rint(drawTop) };
	}

	public void setBitmap(Bitmap bitmap) {
		imageBitmap = bitmap;
	}

	public void setTranslation(float s, PointF t) {
		scaleFactor = s;
		translate.set(t);
		translate.x /= scaleFactor;
		translate.y /= scaleFactor;
	}
}
