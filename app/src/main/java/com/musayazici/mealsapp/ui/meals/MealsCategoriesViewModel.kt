package com.musayazici.mealsapp.ui.meals

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import com.musayazici.mealsapp.model.MealsRepository
import com.musayazici.mealsapp.model.response.MealResponse
import com.musayazici.mealsapp.model.response.MealsCategoriesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Callback

class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository()): ViewModel() {

    private val mealsJob = Job()
    init {
        val scope = CoroutineScope(mealsJob + Dispatchers.IO)
        scope.launch() {
            val meals = getMeals()
            mealsState.value = meals
        }
    }

    val mealsState: MutableState<List<MealResponse>> = mutableStateOf(emptyList<MealResponse>())

    override fun onCleared() {
        super.onCleared()
        mealsJob.cancel()
    }

    private suspend fun getMeals(): List<MealResponse> {
        return repository.getMeals().categories
    }
}