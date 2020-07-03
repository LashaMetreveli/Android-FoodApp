package com.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import com.foodapp.model.MealListModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var adapter: MealsReyclerViewAdapter
    private var meals  = ArrayList<MealListModel.Meal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        getMeals()
    }

    private fun getMeals() {

        dashboardRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MealsReyclerViewAdapter(meals,this)
        dashboardRecyclerView.adapter = adapter

        MealDataLoader.getMeals(object : CustomCallBack {
            override fun onSuccess(result: String) {

                var model = Gson().fromJson(result, MealListModel::class.java)

                //Check
                d("total meals:", "${model.meals.size}")

                meals.addAll(model.meals)
                adapter.notifyDataSetChanged()
            }
        })
    }
}
