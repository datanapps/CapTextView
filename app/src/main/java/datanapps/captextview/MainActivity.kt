package datanapps.captextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import datanapps.captextview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //set text value
        binding.text2.setHtmlText(getString(R.string.text2), true)

    }
}