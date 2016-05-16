package net.qiujuer.powerback.common.widget.decoration;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by qiujuer
 * on 15/11/11.
 */
public class EdgeItemDecoration extends RecyclerView.ItemDecoration {
    private int mEdgeTop;
    private int mEdgeRight;
    private int mEdgeLeft;
    private int mEdgeBottom;
    private int mSpace;

    public EdgeItemDecoration(Resources resources, float left, float top, float right, float bottom, float normal) {
        float density = resources.getDisplayMetrics().density;

        this.mEdgeLeft = (int) (left * density);
        this.mEdgeTop = (int) (top * density);
        this.mEdgeRight = (int) (right * density);
        this.mEdgeBottom = (int) (bottom * density);
        this.mSpace = (int) ((normal * density) / 2f);
    }

    private int getSpanCount(RecyclerView parent) {
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        } else if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    private boolean isFirstRaw(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();

            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if (pos < spanCount)
                    return true;
            } else {
                if ((pos) % spanCount == 0) {
                    return true;
                }
            }
        } else if (layoutManager instanceof GridLayoutManager) {
            return true;
        }
        return false;
    }

    private boolean isFirstColumn(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos) % spanCount == 0) {
                    return true;
                }
            } else {
                if (pos < spanCount)
                    return true;
            }
        } else if (layoutManager instanceof GridLayoutManager) {
            if ((pos) % spanCount == 0) {
                return true;
            }
        }
        return false;
    }


    private boolean isLastColumn(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)
                    return true;
            }
        } else if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)
                    return true;
            } else {
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        } else if (layoutManager instanceof GridLayoutManager) {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount)
                return true;
        }
        return false;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();

        outRect.set(mSpace, mSpace, mSpace, mSpace);

        if (isFirstColumn(parent, itemPosition, spanCount, childCount)) {
            outRect.set(mEdgeLeft, outRect.top, outRect.right, outRect.bottom);
        }

        if (isFirstRaw(parent, itemPosition, spanCount, childCount)) {
            outRect.set(outRect.left, mEdgeTop, outRect.right, outRect.bottom);
        }

        if (isLastColumn(parent, itemPosition, spanCount, childCount)) {
            outRect.set(outRect.left, outRect.top, mEdgeRight, outRect.bottom);
        }

        if (isLastRaw(parent, itemPosition, spanCount, childCount)) {
            outRect.set(outRect.left, outRect.top, outRect.right, mEdgeBottom);
        }
    }

}
