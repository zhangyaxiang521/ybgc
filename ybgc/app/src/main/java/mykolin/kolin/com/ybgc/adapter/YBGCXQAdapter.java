package mykolin.kolin.com.ybgc.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import mykolin.kolin.com.ybgc.R;
import mykolin.kolin.com.ybgc.bean.YBGCXQBean;

/**
 * Created by Administrator on 2020/6/5.
 */

public class YBGCXQAdapter extends BaseQuickAdapter<YBGCXQBean.ReListBean,BaseViewHolder> {
    private  List<YBGCXQBean.ReListBean> list;
    public YBGCXQAdapter(int layoutResId, @Nullable List<YBGCXQBean.ReListBean> data) {
        super(layoutResId, data);
        this.list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, YBGCXQBean.ReListBean item) {
        helper.setText(R.id.ybgcxq_mc,item.getTzkzDetailGcmc());
        helper.setText(R.id.ybgcxq_gcbh,item.getTzkzDetailGcbh());
        helper.setText(R.id.ybgcxq_qszh,item.getTzkzDetailQszh());
        helper.setText(R.id.ybgcxq_zzzh,item.getTzkzDetailZzzh());

    }
    public void addAll(List<YBGCXQBean.ReListBean> dd){
        list.addAll(dd);
        notifyDataSetChanged();
    }
    public void clear(){
        list.clear();
        notifyDataSetChanged();
    }

    public void delete(int position){
        list.remove(position);
        notifyDataSetChanged();
    }
}
