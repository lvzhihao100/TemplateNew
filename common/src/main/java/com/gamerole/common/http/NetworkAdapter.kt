package com.gamerole.common.http

import androidx.annotation.MainThread
import com.alibaba.android.arouter.launcher.ARouter
import com.gamerole.common.RoutePath
import com.gamerole.common.base.AppManager
import com.gamerole.common.base.BaseViewModel
import com.gamerole.common.entity.HttpData
import com.gamerole.common.util.DataStoreUtils
import com.gamerole.common.util.ToastUtil
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow


abstract class NetworkAdapter<RESULT>(var baseViewModel: BaseViewModel) {

    fun asFlow(isShowLoading: Boolean = true) = flow<RESULT> {

        if (isShowLoading) {
            baseViewModel.showLoading()
        }
        var checkInfo = fetchFromRemote()
        checkInfo
            .collect {
                if (isShowLoading) {
                    baseViewModel.hideLoading()
                }
                when (it) {
                    is Resource.Success -> {
                        if (it.data is HttpData<*>) {

                            var httpData = it.data as HttpData<*>
                            if (httpData.status == 0) {

                                ToastUtil.showShort(httpData.msg)
                            } else {
                                it.data?.let { it1 -> emit(it1) }
                            }
                        } else {
                            it.data?.let { it1 -> emit(it1) }
                        }
                    }
                    is Resource.Error -> {
                        if (it.apiResponse == null) {
                            ToastUtil.showShort("failure")
                        } else if (it.apiResponse is ApiResponse.Failure.Error) {
                            var error = it.apiResponse as ApiResponse.Failure.Error
                            if (error.statusCode.code == 401) {
                                DataStoreUtils.clearSync()
                                ARouter.getInstance().build(RoutePath.LOGIN_LOGIN).navigation()
                                AppManager.getAppManager().finishAllLogin()
                            } else {
                                ToastUtil.showShort(error.message())
                            }
                        } else {
                            var error = it.apiResponse as ApiResponse.Failure.Exception
                            ToastUtil.showShort(error.message())
                        }
                    }

                }

            }
    }.catch { e ->
        if (isShowLoading) {
            baseViewModel.hideLoading()
        }
        e.printStackTrace()
    }

    @MainThread
    protected abstract suspend fun fetchFromRemote(): Flow<Resource<RESULT>>
}