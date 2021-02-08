package com.mughalasim.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mughalasim.R
import com.mughalasim.databinding.ActivityDescBinding
import com.mughalasim.model.DescActivityViewModel
import com.mughalasim.utils.Shared

class DescActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescBinding
    private lateinit var viewModel: DescActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_desc)
        viewModel = ViewModelProvider(this)[DescActivityViewModel::class.java]
        binding.field = viewModel
        binding.lifecycleOwner = this

        viewModel.model = intent.getParcelableExtra(Shared.INTENT_EXTRA)!!

    }
}
