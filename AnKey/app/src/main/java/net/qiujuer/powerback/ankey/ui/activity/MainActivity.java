package net.qiujuer.powerback.ankey.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import net.qiujuer.genius.ui.widget.FloatActionButton;
import net.qiujuer.genius.ui.widget.Loading;
import net.qiujuer.powerback.ankey.R;
import net.qiujuer.powerback.ankey.ui.SuperActivity;
import net.qiujuer.powerback.ankey.ui.adapter.InfoListAdapter;
import net.qiujuer.powerback.ankey.ui.adapter.callback.InfoListAdapterCallback;
import net.qiujuer.powerback.ankey.ui.view.DetailView;
import net.qiujuer.powerback.ankey.widget.decoration.EdgeItemDecoration;
import net.qiujuer.powerback.ankey.widget.drawable.Drawables;

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
        FloatActionButton addButton = (FloatActionButton) findViewById(R.id.action_add);
        Drawable drawable = Drawables.getCreateDrawable(getResources());
        addButton.setImageDrawable(drawable);
        addButton.setOnClickListener(this);
    }

    private void initRecyclerView() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(layoutManager);

        mInfoListAdapter = new InfoListAdapter(this);
        mRecycler.setAdapter(mInfoListAdapter);

        mRecycler.addItemDecoration(new EdgeItemDecoration(getResources(), 16, 16, 16, 16, 8));
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
    public void onItemSelected(final UUID id) {
        DetailView view = (DetailView) View.inflate(this, R.layout.lay_detail, null);
        view.setInfoId(id);

        final AlertDialog dialog = createDialog(view);
        view.setOnEditListener(new DetailView.OnEditClickListener() {
            @Override
            public void onClick(DetailView view) {
                dialog.dismiss();
                EditActivity.show(MainActivity.this, view.getInfoId());
            }
        });

        dialog.show();
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
