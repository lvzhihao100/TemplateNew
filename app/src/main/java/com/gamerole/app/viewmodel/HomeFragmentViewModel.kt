package com.gamerole.app.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import com.gamerole.common.base.BaseViewModel
import com.gamerole.app.repository.HomeFragmentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gamerole.common.entity.HttpData
import com.gamerole.common.http.NetworkAdapter
import com.gamerole.common.http.Resource

class HomeFragmentViewModel @ViewModelInject constructor(private var repository: HomeFragmentRepository) :
    BaseViewModel() {

}