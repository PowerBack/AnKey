/*
 * Copyright (C) 2015-2016 Qiujuer-PowerBack <qiujuer@live.cn>
 * WebSite https://github.com/PowerBack/AnKey
 * Author Qiujuer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.qiujuer.powerback.ankey.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

/**
 * Collect util method for format
 */
public class FontFormat {
    private FontFormat() {
        // on use static methods
    }

    /**
     * use font to title
     *
     * @param title    to format title
     * @param fontPath the path of font which is in assert directory
     * @return a fontable title text
     */
    public static SpannableStringBuilder getTextWithFont(Context context, String title, String fontPath) {
        // init builder
        SpannableStringBuilder sBuilder = new SpannableStringBuilder();
        // Add title
        sBuilder.append(title);

        // Create the Typeface you want to apply to certain text
        Typeface typeface = TypefaceUtils.load(context.getAssets(), fontPath);
        CalligraphyTypefaceSpan typefaceSpan = new CalligraphyTypefaceSpan(typeface);
        sBuilder.setSpan(typefaceSpan, 0, sBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sBuilder;
    }

    public static SpannableStringBuilder getTextWithLobsterFont(Context context, String title) {
        return getTextWithFont(context, title, "fonts/Lobster.otf");
    }

}
