package com.foodapp

import android.util.Log.d
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


object MealDataLoader {

    private var mealsUrl = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .build()

    private var service = mealsUrl.create(MealController::class.java)

    fun getMeals(customCallBack: CustomCallBack) {
        val call = service.getMeals()
        call.enqueue(callback(customCallBack))
    }


    private fun callback(customCallBack: CustomCallBack) =
        object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                d("getRequest", "${t.message}")
                customCallBack.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                d("getRequest", "${response.body()}")
                customCallBack.onSuccess(response.body().toString())
            }
        }

}


interface MealController {

    @GET("search.php?s=ge")
    fun getMeals(): Call<String>
}
