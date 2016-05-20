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

import android.graphics.Rect;
import android.text.Editable;
import android.text.GetChars;
import android.text.Spannable;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.view.View;

/**
 * Created by qiujuer
 * on 15/11/2.
 *
 * This not't done
 */
public class AnKeyTransformationMethod implements TransformationMethod {
    public static AnKeyTransformationMethod getInstance() {
        if (sInstance != null)
            return sInstance;

        sInstance = new AnKeyTransformationMethod();
        return sInstance;
    }

    private static AnKeyTransformationMethod sInstance;

    private static char[] ORIGINAL = new char[]{'\n', '\r'};
    private static char[] REPLACEMENT = new char[]{' ', '\uFEFF'};

    /**
     * Returns the list of characters that are to be replaced by other
     * characters when displayed.
     */
    protected char[] getOriginal() {
        return ORIGINAL;
    }

    /**
     * Returns a parallel array of replacement characters for the ones
     * that are to be replaced.
     */
    protected char[] getReplacement() {
        return REPLACEMENT;
    }


    @Override
    public void onFocusChanged(View view, CharSequence sourceText, boolean focused, int direction, Rect previouslyFocusedRect) {

    }

    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        char[] original = getOriginal();
        char[] replacement = getReplacement();

        /*
         * Short circuit for faster display if the text will never change.
         */
        if (!(source instanceof Editable)) {
            /*
             * Check whether the text does not contain any of the
             * source characters so can be used unchanged.
             */
            boolean doNothing = true;
            int n = original.length;
            for (char anOriginal : original) {
                if (TextUtils.indexOf(source, anOriginal) >= 0) {
                    doNothing = false;
                    break;
                }
            }
            if (doNothing) {
                return source;
            }

            if (!(source instanceof Spannable)) {
                /*
                 * The text contains some of the source characters,
                 * but they can be flattened out now instead of
                 * at display time.
                 */
                if (source instanceof Spanned) {
                    return new SpannedString(new SpannedReplacementCharSequence(
                            (Spanned) source,
                            original, replacement));
                } else {
                    return new ReplacementCharSequence(source,
                            original,
                            replacement).toString();
                }
            }
        }

        if (source instanceof Spanned) {
            return new SpannedReplacementCharSequence((Spanned) source,
                    original, replacement);
        } else {
            return new ReplacementCharSequence(source, original, replacement);
        }
    }


    private static class ReplacementCharSequence
            implements CharSequence, GetChars {
        private char[] mOriginal, mReplacement;

        public ReplacementCharSequence(CharSequence source, char[] original,
                                       char[] replacement) {
            mSource = source;
            mOriginal = original;
            mReplacement = replacement;
        }

        public int length() {
            return mSource.length();
        }

        public char charAt(int i) {
            char c = mSource.charAt(i);

            int n = mOriginal.length;
            for (int j = 0; j < n; j++) {
                if (c == mOriginal[j]) {
                    c = mReplacement[j];
                }
            }

            return c;
        }

        public CharSequence subSequence(int start, int end) {
            char[] c = new char[end - start];

            getChars(start, end, c, 0);
            return new String(c);
        }

        public String toString() {
            char[] c = new char[length()];

            getChars(0, length(), c, 0);
            return new String(c);
        }

        public void getChars(int start, int end, char[] dest, int off) {
            TextUtils.getChars(mSource, start, end, dest, off);
            int offend = end - start + off;
            int n = mOriginal.length;

            for (int i = off; i < offend; i++) {
                char c = dest[i];

                for (int j = 0; j < n; j++) {
                    if (c == mOriginal[j]) {
                        dest[i] = mReplacement[j];
                    }
                }

                if (isChinese(c)) {
                    dest[i] = '\uFEFF';
                }
            }
        }

        public static boolean isChinese(char c) {
            return c >= 0x4E00 && c <= 0x9FA5;
        }

        private CharSequence mSource;
    }

    private static class SpannedReplacementCharSequence
            extends ReplacementCharSequence
            implements Spanned {
        public SpannedReplacementCharSequence(Spanned source, char[] original,
                                              char[] replacement) {
            super(source, original, replacement);
            mSpanned = source;
        }

        public CharSequence subSequence(int start, int end) {
            return new SpannedString(this).subSequence(start, end);
        }

        public <T> T[] getSpans(int start, int end, Class<T> type) {
            return mSpanned.getSpans(start, end, type);
        }

        public int getSpanStart(Object tag) {
            return mSpanned.getSpanStart(tag);
        }

        public int getSpanEnd(Object tag) {
            return mSpanned.getSpanEnd(tag);
        }

        public int getSpanFlags(Object tag) {
            return mSpanned.getSpanFlags(tag);
        }

        public int nextSpanTransition(int start, int end, Class type) {
            return mSpanned.nextSpanTransition(start, end, type);
        }

        private Spanned mSpanned;
    }
}
