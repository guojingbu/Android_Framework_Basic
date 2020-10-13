package com.yesway.common_lib.widget;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.yesway.common_lib.utils.DisplayUtil;

/**
 * @Description
 * @Author guojingbu
 * @Date 2019/10/12
 */
public class TopOffsetDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    public TopOffsetDecoration(Context context) {
        this.mContext = context;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if(position==0){
            outRect.top= DisplayUtil.dp2px(mContext,10);
        }
    }
}
