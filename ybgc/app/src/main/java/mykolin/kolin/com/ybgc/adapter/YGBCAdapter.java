package mykolin.kolin.com.ybgc.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import mykolin.kolin.com.ybgc.R;
import mykolin.kolin.com.ybgc.bean.YBGCBean;

/**
 * Created by Administrator on 2020/6/4.
 */

public class YGBCAdapter extends BaseQuickAdapter<YBGCBean.ReListBean,BaseViewHolder> {

    public YGBCAdapter(int layoutResId, @Nullable List<YBGCBean.ReListBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, YBGCBean.ReListBean item) {
        helper.setText(R.id.ybgc_home_tv,item.getTZKZ_NAME());
    }
}
