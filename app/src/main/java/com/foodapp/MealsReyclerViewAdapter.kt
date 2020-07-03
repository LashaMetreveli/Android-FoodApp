package com.foodapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foodapp.model.MealListModel
import kotlinx.android.synthetic.main.item_meals_recyclerview_layout.view.*

class MealsReyclerViewAdapter(private val meals:ArrayList<MealListModel.Meal>, private val activity: DashboardActivity) : RecyclerView.Adapter<MealsReyclerViewAdapter.ViewHolder>() {

    override fun getItemCount() = meals.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_meals_recyclerview_layout,parent,false))
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private lateinit var model:MealListModel.Meal
        fun onBind(){

            model = meals[adapterPosition]

            Glide.with(activity).load(model.strMealThumb).into(itemView.mealImage)

            itemView.mealNameTextView.text = model.strMeal
            itemView.categoryTextView.text = "コ "+model.strCategory
            itemView.areaTextView.text = "旦 "+model.strArea
            itemView.instructionsTextView.text = model.strInstructions

        }
    }
}