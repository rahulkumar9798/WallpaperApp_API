package com.example.wallpaperapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallpaperapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val apiHelper = ApiHelper.getInstance()

        binding.btnSearchImg.setOnClickListener {
            startActivity(Intent(this, SearchResultActivity::class.java)
                .putExtra("searchimg", binding.edtSearch.text.toString()))

        }

        apiHelper.getcuratedWallpaper("dxOcX6Vnx8G4a59ltaDGGTLcnuHWzR6wWfT3aPovmD3MvdkjDXTRcl1G","curated").enqueue(object : Callback<WallpaperModal>{
                override fun onResponse(
                    call: Call<WallpaperModal>,
                    response: Response<WallpaperModal>
                ) {
                    Log.d("Respose", response.message().toString())
                    if (response.code()==200){
                        Log.d("Respose", response.body().toString())

                        binding.recyclerBestMonth.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                        binding.recyclerBestMonth.adapter = CuratedRecyclerAdpter(this@MainActivity, response.body()!!.photos as List<PhotoModal> )

                    }

                }

                override fun onFailure(call: Call<WallpaperModal>, t: Throwable) {
                    Log.d("Network Error", "Can't hit Api due to ${t.message}")
                    t.printStackTrace()
                }

            })

    }
}