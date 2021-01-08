package com.gamerole.app.ui.fragment
import com.gamerole.app.R
import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.gamerole.common.RoutePath
import com.gamerole.common.base.BaseFragment
import com.gamerole.app.databinding.AppFragmentHomeBinding
import com.gamerole.app.viewmodel.HomeFragmentViewModel
import com.hi.dhl.binding.viewbind
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = RoutePath.APP_FRAGMENT_HOME)
class HomeFragment : BaseFragment(R.layout.app_fragment_home) {

    private val viewModelHomeFragment: HomeFragmentViewModel by viewModels()
    override fun getViewModel() = viewModelHomeFragment
    private val binding: AppFragmentHomeBinding by viewbind()

    override fun initView() {
        with(binding) {
        }
    }
    override fun initData() {

    }
}