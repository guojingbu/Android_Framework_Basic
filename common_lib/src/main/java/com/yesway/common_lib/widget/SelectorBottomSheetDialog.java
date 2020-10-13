package com.yesway.common_lib.widget;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.yesway.common_lib.R;
import com.yesway.common_lib.utils.DisplayUtil;

import java.util.List;

/**
 * @Description
 * @Author guojingbu
 * @Date 2019/3/15
 */
public class SelectorBottomSheetDialog extends BottomSheetDialog {
    private ListView list_source;
    private Button btn_cancel;
    private OnItemSelectedListener mItemSelectedListener;
    private View mTitleDivider;

    public SelectorBottomSheetDialog(@NonNull Context context) {
        super(context);
        initView();
    }

    public SelectorBottomSheetDialog(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
        initView();
    }

    protected SelectorBottomSheetDialog(@NonNull Context context, boolean cancelable,
                                        DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    @Override
    public void onStart() {
        super.onStart();
        getWindow().setLayout(DisplayUtil.getScreenWidth(getContext()) - DisplayUtil.dpToPx(18) * 2, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setWindowAnimations(R.style.BottomDialogAnimation);
    }

    /**
     * 设置Dialog高度,单位DP,默认200dp
     *
     * @param heightDp
     */
    public void setDialogHeight(int heightDp) {

    }

    /**
     * 设置Dialog显示全部内容的高度,该方法必须在设置内容后调用才会生效
     */
    public void setDialogPullHeight() {
        setPullLvHeight(list_source);
    }

    private void initView() {
        setContentView(R.layout.dialog_bottom_sheet_selector);
        getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        list_source = (ListView) findViewById(R.id.list_source);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        list_source.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    /**
     * 设置默认选择项, 通过标识KEY,该方法一定要在设置setSelectorList内容后触发,不然无效
     *
     * @param key
     */
    public void setDefaultChecked(String key) {
        if (list_source == null || list_source.getAdapter() == null
                || list_source.getAdapter().getCount() <= 0) {
            return;
        }
        // 从当前的列表中,查找对应的ID
        for (int i = 0; i < list_source.getAdapter().getCount(); i++) {
            Object itemObj = list_source.getAdapter().getItem(i);
            if (itemObj instanceof BaseSelectorItemBean) {
                if (key.equals(((BaseSelectorItemBean) itemObj).id)) {
                    list_source.setItemChecked(i, true);
                    list_source.setSelection(i);
                    return;
                }
            }
        }
    }

    /**
     * 根据名称显示选中项
     *
     * @param itemName
     */
    public void setDefaultCheckedByName(String itemName) {
        if (list_source == null || list_source.getAdapter() == null
                || list_source.getAdapter().getCount() <= 0) {
            return;
        }
        // 从当前的列表中,查找对应的ID
        for (int i = 0; i < list_source.getAdapter().getCount(); i++) {
            Object itemObj = list_source.getAdapter().getItem(i);
            if (itemObj instanceof BaseSelectorItemBean) {
                if (itemName.equals(((BaseSelectorItemBean) itemObj).itemName)) {
                    list_source.setItemChecked(i, true);
                    list_source.setSelection(i);
                    return;
                }
            }
        }
    }

    /**
     * 设置选择项的内容
     *
     * @param itemList
     */
    public void setSelectorList(@NonNull List<BaseSelectorItemBean> itemList) {
        ArrayAdapter adapter = new ArrayAdapter<BaseSelectorItemBean>(getContext(),
                R.layout.item_selector_dialog_bottom, itemList) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ChoiceItemView view;
                if (convertView == null) {
                    view = new ChoiceItemView(getContext());
                } else {
                    view = (ChoiceItemView) convertView;
                }

                BaseSelectorItemBean itemBean = getItem(position);
                if (itemBean == null) {
                    return view;
                }

                view.setTxtName(itemBean.itemName);
                if (itemBean.logoBitmap != null) {
                    view.setIcoBitmep(itemBean.logoBitmap);
                }

                return view;
            }
        };
        list_source.setAdapter(adapter);
        list_source.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mItemSelectedListener != null) {
                    BaseSelectorItemBean item = (BaseSelectorItemBean) parent.getAdapter().getItem(position);
                    mItemSelectedListener.onItemSelected(item.id, item);
                }
                dismiss();
            }
        });
    }

    /**
     * 设置选择监听
     *
     * @param listener
     */
    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        this.mItemSelectedListener = listener;
    }

    /**
     * 列表中的itemView 这个View 实现了Checkable接口,使Listview支持单选并兼容RadioButton
     */
    class ChoiceItemView extends FrameLayout implements Checkable {

        private ImageView img_ico;
        private TextView txt_name;
        private RadioButton radio_btn;

        public ChoiceItemView(@NonNull Context context) {
            super(context);

            View.inflate(context, R.layout.item_selector_dialog_bottom, this);

            img_ico = (ImageView) findViewById(R.id.img_ico);
            txt_name = (TextView) findViewById(R.id.txt_name);
            radio_btn = (RadioButton) findViewById(R.id.radio_btn);
        }

        public void setTxtName(String txt) {
            txt_name.setText(txt);
        }

        public void setIcoBitmep(Bitmap bitmep) {
            img_ico.setImageBitmap(bitmep);
        }

        @Override
        public void setChecked(boolean checked) {
            radio_btn.setChecked(checked);
        }

        @Override
        public boolean isChecked() {
            return radio_btn.isChecked();
        }

        @Override
        public void toggle() {
            radio_btn.toggle();
        }
    }

    /**
     * 选择Item监听器
     */
    public interface OnItemSelectedListener {
        void onItemSelected(String key, BaseSelectorItemBean itemBean);
    }

    /**
     * 动态改变listView的高度
     *
     * @param pull
     */
    private void setPullLvHeight(@NonNull ListView pull) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = pull.getAdapter();
        if (listAdapter == null) {
            return;
        }
        ViewGroup.LayoutParams params = pull.getLayoutParams();
        if (listAdapter.getCount() <= 6) {
            int totalHeight = 0;
            for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
                View listItem = listAdapter.getView(i, null, pull);
                listItem.measure(0, 0); // 计算子项View 的宽高
                totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
            }
            params.height = totalHeight + (pull.getDividerHeight() * (pull.getCount() - 1));
        } else {
            params.height = (int) ((6+0.5)* DisplayUtil.dp2px(getContext(), 50));
        }
        pull.setLayoutParams(params);
    }

    /**
     * @Description
     * @Author guojingbu
     * @Date 2019/3/15
     */
    public static class BaseSelectorItemBean {
        public String id;
        public String itemName;
        public Bitmap logoBitmap;
        public String[] devicetypes;//设备类型

        public BaseSelectorItemBean(String id, String itemName, Bitmap logoBitmap) {
            this.id = id;
            this.itemName = itemName;
            this.logoBitmap = logoBitmap;
        }

        public BaseSelectorItemBean(String id, String itemName, Bitmap logoBitmap, String[] devicetypes) {
            this.id = id;
            this.itemName = itemName;
            this.logoBitmap = logoBitmap;
            this.devicetypes = devicetypes;
        }
    }

}
