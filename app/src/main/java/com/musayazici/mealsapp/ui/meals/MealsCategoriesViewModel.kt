package com.musayazici.mealsapp.ui.meals

import androidx.lifecycle.ViewModel
import com.musayazici.mealsapp.model.MealsRepository
import com.musayazici.mealsapp.model.response.MealResponse
import com.musayazici.mealsapp.model.response.MealsCategoriesResponse
import retrofit2.Callback

class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository()): ViewModel() {
    fun getMeals(successCallback: (response: MealsCategoriesResponse?) -> Unit) {
        repository.getMeals { response ->
            successCallback(response)
        }
    }
}