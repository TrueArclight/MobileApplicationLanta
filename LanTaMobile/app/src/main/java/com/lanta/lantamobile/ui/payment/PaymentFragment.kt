package com.lanta.lantamobile.ui.payment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lanta.lantamobile.R
import com.lanta.lantamobile.ui.map.MapViewModel
import com.lanta.lantamobile.ui.register.RegisterViewModel

class PaymentFragment : Fragment() {

    companion object {
        fun newInstance() = PaymentFragment()
    }

    private lateinit var paymentViewModel: PaymentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        paymentViewModel = ViewModelProvider(this)[PaymentViewModel::class.java]
        return inflater.inflate(R.layout.payment_fragment, container, false)
    }


}