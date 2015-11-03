package net.qiujuer.powerback.ankey.ui.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import net.qiujuer.genius.ui.widget.FloatActionButton;
import net.qiujuer.genius.ui.widget.Loading;
import net.qiujuer.powerback.ankey.R;
import net.qiujuer.powerback.ankey.ui.SuperActivity;
import net.qiujuer.powerback.ankey.ui.adapter.InfoListAdapter;
import net.qiujuer.powerback.ankey.ui.adapter.callback.InfoListAdapterCallback;
import net.qiujuer.powerback.ankey.widget.drawable.CrossLineShape;

import java.util.UUID;

public class MainActivity extends SuperActivity implements View.OnClickListener, InfoListAdapterCallback {
    private View mStatus;
    private Loading mLoading;
    private RecyclerView mRecycler;
    private InfoListAdapter mInfoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCenterTitle(true);

        mStatus = findViewById(R.id.text_status);
        mLoading = (Loading) findViewById(R.id.loading);
        mRecycler = (RecyclerView) findViewById(R.id.recycler);

        initFloatActionButton();
        initRecyclerView();

    }

    private void initFloatActionButton() {
        final float density = getResources().getDisplayMetrics().density;
        FloatActionButton addButton = (FloatActionButton) findViewById(R.id.action_add);
        CrossLineShape shape = new CrossLineShape();
        ShapeDrawable drawable = new ShapeDrawable(shape);
        Paint paint = drawable.getPaint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(0xc0ffffff);
        paint.setStrokeWidth(2 * density);
        paint.setStrokeCap(Paint.Cap.ROUND);
        drawable.setIntrinsicWidth(100);
        drawable.setIntrinsicHeight(100);
        addButton.setImageDrawable(drawable);
        addButton.setOnClickListener(this);
    }

    private void initRecyclerView() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(layoutManager);

        mInfoListAdapter = new InfoListAdapter(this);
        mRecycler.setAdapter(mInfoListAdapter);
    }

    @Override
    protected String getTitleFont() {
        return "fonts/Lobster.otf";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mInfoListAdapter.destroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mInfoListAdapter.refresh();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.action_add) {
            Intent intent = new Intent(MainActivity.this, CreateActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(UUID id) {

    }

    @Override
    public void showLoading() {
        mLoading.setVisibility(View.VISIBLE);
        mLoading.start();
    }

    @Override
    public void hideLoading() {
        mLoading.setVisibility(View.GONE);
        mLoading.stop();
    }

    @Override
    public void showNull() {
        mStatus.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNull() {
        mStatus.setVisibility(View.GONE);
    }
}
