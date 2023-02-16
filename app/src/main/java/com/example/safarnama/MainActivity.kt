package com.example.safarnama

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.safarnama.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        //Now creating mainViewModel instance by following syntax
        mainViewModel=ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)

        //Handling mainViewModel directlt in layout itself.
//        mainViewModel.quoteLive.observe(this,{
//            binding.quote=mainViewModel.quoteLive.value
//        })
        binding.mainViewModel=mainViewModel
        binding.lifecycleOwner=this
    }

    fun onShare(view: View) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,"${mainViewModel.getQuote().text} \n~${mainViewModel.getQuote().author}")
        startActivity(intent)
    }
}