package com.gamerole.app.repository

import com.gamerole.app.service.HttpService
import javax.inject.Inject
import com.skydoves.sandwich.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.gamerole.common.http.Resource
class HomeFragmentRepository @Inject constructor(private var httpService: HttpService) {

}