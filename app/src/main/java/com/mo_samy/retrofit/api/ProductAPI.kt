package com.mo_samy.retrofit.api
import com.mo_samy.retrofit.models.Data
import com.mo_samy.retrofit.models.DataModel
import com.mo_samy.retrofit.models.LoginInfo
import com.mo_samy.retrofit.models.Product
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductAPI {
    @GET("products")
    fun getProducts(): Call<DataModel>

    @GET("products/{id}")
    fun getProductById(@Path("id") productId: Int):Call<Product>

    @POST("login")
    fun login(@Body info: LoginInfo):Call<LoginInfo>

}