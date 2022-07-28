package com.mo_samy.retrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mo_samy.retrofit.R
import com.mo_samy.retrofit.adapters.ProductsAdapter
import com.mo_samy.retrofit.api.RetrofitFactory
import com.mo_samy.retrofit.models.Data
import com.mo_samy.retrofit.models.DataModel
import retrofit2.Call
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    lateinit var rv :RecyclerView
    lateinit var adapter: ProductsAdapter
    lateinit var product: MutableList<Data>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        rv  = findViewById(R.id.rv_products)
        val retrofit =RetrofitFactory().productApi()
        val call = retrofit.getProducts()
        call.enqueue(object :retrofit2.Callback<DataModel>{
            override fun onResponse(call: Call<DataModel>?, response: Response<DataModel>?) {
                val model = response!!.body()
                product = model.data as MutableList<Data>
                initRv(product)
            }
            override fun onFailure(call: Call<DataModel>?, t: Throwable?) {
                TODO("Not yet implemented")
            }
        })

    }
    fun initRv(products : MutableList<Data>){
        adapter= ProductsAdapter(this, products)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }
}