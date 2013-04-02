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
package com.koushikdutta.urlimageviewhelper;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class WrapperDrawable extends Drawable {
    public WrapperDrawable(BitmapDrawable drawable) {
        mDrawable = drawable;
    }
    
    BitmapDrawable mDrawable;
    
    public WrapperDrawable(WrapperDrawable drawable) {
        this(drawable.mDrawable);
    }

    @Override
    public void draw(Canvas canvas) {
        mDrawable.draw(canvas);
    }

    @Override
    public int getOpacity() {
        return mDrawable.getOpacity();
    }

    @Override
    public void setAlpha(int alpha) {
        mDrawable.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mDrawable.setColorFilter(cf);
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        mDrawable.setBounds(left, top, right, bottom);
        super.setBounds(left, top, right, bottom);
    }
    
    @Override
    public void setBounds(Rect r) {
        mDrawable.setBounds(r);
        super.setBounds(r);
    }
    
    @Override
    public int getIntrinsicHeight() {
        return mDrawable.getIntrinsicHeight();
    }
    
    @Override
    public int getIntrinsicWidth() {
        return mDrawable.getIntrinsicWidth();
    }
    
    /**
     * Returns the underlying {@link BitmapDrawable}.
     * @return An instance of {@link BitmapDrawable}
     */
    @Deprecated
    public BitmapDrawable toBitmapDrawable() {
        return mDrawable;
    }  
    
    /**
     * Returns the underlying {@link BitmapDrawable}.
     * @return An instance of {@link BitmapDrawable}
     */
    public BitmapDrawable getBitmapDrawable() {
        return mDrawable;
    }
}
