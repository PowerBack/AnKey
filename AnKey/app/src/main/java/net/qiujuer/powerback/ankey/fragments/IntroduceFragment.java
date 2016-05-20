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
package net.qiujuer.powerback.ankey.fragments;

import android.support.annotation.StringRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.qiujuer.powerback.ankey.R;
import net.qiujuer.powerback.ankey.utils.FontFormat;
import net.qiujuer.powerback.ankey.utils.ViewPagerOnPageChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Introduce view fragment
 */
public class IntroduceFragment extends net.qiujuer.powerback.common.app.Fragment {
    private final List<View> mViews = new ArrayList<>();
    private ViewPager mViewPager;
    private IIntroduceAction mIntroduceAction;

    public IntroduceFragment() {

    }

    public static IntroduceFragment newInstance(IIntroduceAction action) {
        IntroduceFragment fragment = new IntroduceFragment();
        fragment.mIntroduceAction = action;
        return fragment;
    }

    @Override
    protected void initData() {
        mIntroduceAction.onColorChange(getResources().getColor(R.color.colorAccent));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_introduce;
    }

    private SpannableStringBuilder formatText(@StringRes int id) {
        return FontFormat.getTextWithLobsterFont(getContext(), getString(id));
    }

    @Override
    protected void initView(View view) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.frag_introduce_item, null);
        ((TextView) item.findViewById(R.id.tv_value)).
                setText(formatText(R.string.frag_introduce_friendly));
        mViews.add(item);

        item = inflater.inflate(R.layout.frag_introduce_item, null);
        ((TextView) item.findViewById(R.id.tv_value)).
                setText(formatText(R.string.frag_introduce_simple));
        mViews.add(item);

        item = inflater.inflate(R.layout.frag_introduce_item, null);
        ((TextView) item.findViewById(R.id.tv_value)).
                setText(formatText(R.string.frag_introduce_safe));
        mViews.add(item);

        mViewPager = (ViewPager) view.findViewById(R.id.lay_root);
        mViewPager.setAdapter(new IntroduceAdapter());

        mViewPager.addOnPageChangeListener(new ViewPagerOnPageChangeListener() {
            private boolean flag;

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        flag = false;
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        flag = true;
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (mViewPager.getCurrentItem() == mViewPager.getAdapter()
                                .getCount() - 1 && !flag) {
                            mIntroduceAction.onDone();
                        }
                        flag = true;
                        break;
                }
            }
        });
    }

    private class IntroduceAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View item = mViews.get(position);
            container.addView(item, 0);
            return item;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViews.get(position));
        }
    }

    public interface IIntroduceAction {
        void onDone();

        void onColorChange(int color);
    }
}
