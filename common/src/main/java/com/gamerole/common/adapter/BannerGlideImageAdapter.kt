package com.gamerole.common.adapter

import com.bumptech.glide.Glide
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder

class BannerGlideImageAdapter constructor(mData: MutableList<String>?):
    BannerImageAdapter<String>(mData) {
    override fun onBindView(holder: BannerImageHolder?, data: String?, position: Int, size: Int) {
        Glide.with(holder!!.itemView)
            .load(data)
            .into(holder.imageView)
    }
}