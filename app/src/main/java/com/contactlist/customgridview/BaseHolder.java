package com.contactlist.customgridview;


import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.opensource.widget.RecyclerView;

/**
 * Created by yongzhang on 2018/5/30.
 */

public abstract class BaseHolder<M> extends RecyclerView.ViewHolder {

    public BaseHolder(ViewGroup parent, @LayoutRes int resId) {
        super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
    }

    /**
     * 获取布局中的View
     * @param viewId view的Id
     * @param <T> View的类型
     * @return view
     */
    protected <T extends View>T getView(@IdRes int viewId){
        return (T) (itemView.findViewById(viewId));
    }

    /**
     * 获取Context实例
     * @return context
     */
    protected Context getContext() {
        return itemView.getContext();
    }

    /**
     * 设置数据
     * @param data 要显示的数据对象
     */
    public abstract void setData(M data);
}