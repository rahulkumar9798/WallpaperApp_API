package com.example.wallpaperapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallpaperapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var apiHelper = ApiHelper.getInstance()

    var selectedColor = ""

    val arrColors = ArrayList<ColorModal>().apply {
        add(ColorModal("F57C00", R.color.orange))
        add(ColorModal("7B1FA2", R.color.purple))
        add(ColorModal("1976D2", R.color.blue))
        add(ColorModal("ACA298", R.color.grey))
        add(ColorModal("C2185B", R.color.pink))
        add(ColorModal("D32F2F", R.color.red))
        add(ColorModal("FFFFFF", R.color.white))
        add(ColorModal("000000", R.color.black))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerColor.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = RecyclerColorAdapter(this@MainActivity, arrColors)
        }


        val apiHelper = ApiHelper.getInstance()

        binding.btnSearchImg.setOnClickListener {
            if (binding.edtSearch.text.toString().isNotEmpty()){



            startActivity(Intent(this, SearchResultActivity::class.java)
                .putExtra("searchimg", binding.edtSearch.text.toString())
                .putExtra("arrColors", selectedColor))

            }
        }

        binding.textAbstrack.setOnClickListener {
            startActivity(Intent(this, SearchResultActivity::class.java)
                .putExtra("searchimg", "Abstract"))
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

    fun onColorSelected(selectedCo : String){
        selectedColor = selectedCo
        startActivity(Intent(this, SearchResultActivity::class.java)
            .putExtra("searchimg", binding.edtSearch.text.toString())
            .putExtra("arrColors", selectedColor))
    }


}