package io.github.zane.recyclerview_frame;

import android.view.View;

/**
 * 自定义点击事件
 * Created by Zane on 2017/9/20.
 */

public interface OnItemClickListener {
    void onItemClick(View view, int position);
    void onItemLongClick(View view, int position);
}
