package com.app.incroyable.videocall_prank.apihelper

import com.app.incroyable.videocall_prank.model.MultipleResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIInterface {

    @GET
    fun doGetListResources(@Url url: String): Call<MultipleResource>
}