package net.qiujuer.powerback.ankey.ui.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import net.qiujuer.genius.ui.widget.FloatActionButton;
import net.qiujuer.powerback.ankey.R;
import net.qiujuer.powerback.ankey.ui.SuperActivity;
import net.qiujuer.powerback.ankey.widget.drawable.CrossLineShape;

public class MainActivity extends SuperActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFloatActionButton();
    }

    @Override
    protected String getTitleFont() {
        return "fonts/Lobster.otf";
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onInflateMenu(Toolbar toolbar) {

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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.action_add) {
            Intent intent = new Intent(MainActivity.this, CreateActivity.class);
            startActivity(intent);
        }
    }
}
