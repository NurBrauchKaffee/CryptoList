package ru.justneedcoffee.cryptolist.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import androidx.navigation.fragment.findNavController
import ru.justneedcoffee.cryptolist.R

class DetailFragment : Fragment(R.layout.detail_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnBack: ImageButton = view.findViewById(R.id.arrow_back)
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}