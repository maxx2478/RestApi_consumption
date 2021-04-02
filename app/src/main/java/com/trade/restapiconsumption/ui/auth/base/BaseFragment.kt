package com.trade.restapiconsumption.ui.auth.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.trade.restapiconsumption.data.UserPreferences
import com.trade.restapiconsumption.data.network.RemoteDataSource
import com.trade.restapiconsumption.data.repository.BaseRepository

abstract class BaseFragment<VM: ViewModel, B: ViewBinding, R:BaseRepository> : Fragment()
{

    protected lateinit var userPreferences: UserPreferences
    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        userPreferences = UserPreferences(requireContext())
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getviewModel()) //to get the viewmodel
        return binding.root
    }

    abstract fun getviewModel(): Class<VM> // returns the viewmodel

    abstract fun getFragmentBinding(inflater: LayoutInflater,
    container: ViewGroup?): B //return the view binding

    abstract fun getFragmentRepository(): R //returns the fragment repository


}