package com.example.lastApp.ui.doctors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lastApp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorDetailsFragment : Fragment() {

    private lateinit var viewModel: DoctorDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.doctor_details_fragment, container, false)
    }

}