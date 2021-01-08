package com.gamerole.app.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import com.gamerole.app.repository.MainRepository
import com.gamerole.common.base.BaseViewModel

class MainViewModel @ViewModelInject constructor(private var repository: MainRepository) :
    BaseViewModel() {

}