package com.foodapp.model

class MealListModel {

    lateinit var meals: MutableList<Meal>

    class Meal{

        var strMealThumb = ""
        var strMeal = ""
        var strCategory = ""
        var strArea = ""
        var strInstructions = ""

    }

}