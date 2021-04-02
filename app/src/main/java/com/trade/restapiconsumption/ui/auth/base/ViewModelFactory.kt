package com.trade.restapiconsumption.ui.auth.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trade.restapiconsumption.data.repository.AuthRepository
import com.trade.restapiconsumption.data.repository.BaseRepository
import com.trade.restapiconsumption.ui.auth.AuthViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: BaseRepository) : ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

       return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            else -> throw IllegalArgumentException("Viewmodel class not found")
        }
    }
}