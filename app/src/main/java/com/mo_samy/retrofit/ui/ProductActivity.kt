package com.mo_samy.retrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.mo_samy.retrofit.R
import com.mo_samy.retrofit.api.RetrofitFactory
import com.mo_samy.retrofit.models.Data
import com.mo_samy.retrofit.models.DataModel
import com.mo_samy.retrofit.models.Product
import retrofit2.Call
import retrofit2.Response

class ProductActivity : AppCompatActivity() {
    private lateinit var img:ImageView
    private lateinit var tvName:TextView
    private lateinit var tvPrice:TextView
    private lateinit var tvQuantity:TextView
    private lateinit var tvRestaurantId:TextView
    private lateinit var progress :ProgressBar
    private lateinit var cl :ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        initViews()
        if (intent!= null){
            val id = intent.getIntExtra("id",-1)
            val retrofit = RetrofitFactory().productApi()
            if (id!= -1){
                val call = retrofit.getProductById(id)
                call.enqueue(object :retrofit2.Callback<Product>{
                    override fun onResponse(call: Call<Product>?, response: Response<Product>?) {
                        initData(response!!.body())
                    }

                    override fun onFailure(call: Call<Product>?, t: Throwable?) {
                        Toast.makeText(this@ProductActivity, "${t!!.localizedMessage}" , Toast.LENGTH_LONG).show()
                        onBackPressed()

                    }
                })
            }
        }


    }

    private fun initViews() {
        img = findViewById(R.id.imageView)
        tvName = findViewById(R.id.details_name)
        tvPrice = findViewById(R.id.details_price)
        tvQuantity = findViewById(R.id.quantity)
        tvRestaurantId = findViewById(R.id.restaurant_id)
        progress = findViewById(R.id.progressBar)
        cl = findViewById(R.id.cl_product)
    }
    fun initData(product:Product){
        Glide.with(this)
            .asBitmap()
            .load(product.data.image)
            .placeholder(R.drawable.ic_launcher_background)
            .into(img)
        tvName.text = product.data.name
        tvPrice.text = "${product.data.price} $"
        tvQuantity.text = "${product.data.quantity}"
        tvRestaurantId.text = "${product.data.restaurant_id}"
        progress.visibility = View.GONE
        cl.visibility = View.VISIBLE
    }
}