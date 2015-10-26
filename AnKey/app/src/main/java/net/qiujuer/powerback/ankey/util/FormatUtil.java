package net.qiujuer.powerback.ankey.util;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

/**
 * collect util method for format
 * Created by GuDong on 10/25/15 22:06.
 */
public class FormatUtil {
    /**
     * use font to title
     * @param title
     * @param fontPath the path of font which is in assert directory
     * @return a fontable title text
     */
    public static SpannableStringBuilder getTextWithFont(Context context,String title, String fontPath){
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
}
