package com.example.deberzapata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deberzapata.adapter.DistributorAdapter
import com.example.deberzapata.databinding.ActivityGasPageBinding

class GasPage : AppCompatActivity() {

    private lateinit var binding: ActivityGasPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGasPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.recyclerGas.layoutManager = LinearLayoutManager(this)
        binding.recyclerGas.adapter = DistributorAdapter(DistributorProvider.distributorList)
    }
}