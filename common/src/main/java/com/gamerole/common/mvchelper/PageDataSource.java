package com.gamerole.common.mvchelper;


import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.shizhefei.mvc.IAsyncDataSource;
import com.shizhefei.mvc.RequestHandle;
import com.shizhefei.mvc.ResponseSender;

import java.util.List;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-07-27 08:27
 * 下拉刷新，展示各种状态界面，请求数据
 */
public class PageDataSource<T> implements IAsyncDataSource<List<T>> {

    private ResponseSender sender;
    private OnLoadSource onLoadSource;

    private int page = 1;
    private int pageSize ;
    private boolean hasMore = true;
    private MutableLiveData<List<T>> mutableLiveData;
    private LifecycleOwner lifecycleOwner;

    public interface OnLoadSource<T> {
        void loadData(int page);
    }

    public PageDataSource(LifecycleOwner lifecycleOwner, MutableLiveData<List<T>> mutableLiveData, OnLoadSource onLoadSource) {
        this(lifecycleOwner,mutableLiveData,onLoadSource, 10);
    }

    public PageDataSource(LifecycleOwner lifecycleOwner, MutableLiveData<List<T>> mutableLiveData,OnLoadSource onLoadSource, int pageSize) {
        this.pageSize = pageSize;
        this.mutableLiveData = mutableLiveData;
        this.lifecycleOwner = lifecycleOwner;

        this.onLoadSource = onLoadSource;
        mutableLiveData.observe(lifecycleOwner, data -> {
            if (data.size() >= pageSize) {
                hasMore = true;
            } else {
                hasMore = false;
            }
            if (sender != null) {
                sender.sendData(data);
            }
        });
    }

    @Override
    public RequestHandle refresh(ResponseSender<List<T>> sender) throws Exception {
        page = 1;
        return loadData(sender);

    }

    @Override
    public RequestHandle loadMore(ResponseSender<List<T>> sender) throws Exception {
        page++;
        return loadData(sender);
    }

    private RequestHandle loadData(ResponseSender<List<T>> sender) {
        this.sender = sender;
        onLoadSource.loadData(page);
        return new RequestHandle() {
            @Override
            public void cancle() {

            }

            @Override
            public boolean isRunning() {
                return false;
            }
        };
    }

    @Override
    public boolean hasMore() {
        return hasMore;
    }

}
