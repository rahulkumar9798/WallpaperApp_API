package com.example.wallpaperapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wallpaperapi.databinding.ActivityImagesDownloadBinding

class ImagesDownloadActivity : AppCompatActivity() {
    lateinit var binding: ActivityImagesDownloadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagesDownloadBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}