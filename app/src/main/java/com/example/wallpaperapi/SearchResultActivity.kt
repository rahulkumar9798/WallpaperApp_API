package com.example.wallpaperapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wallpaperapi.databinding.ActivitySearchResultBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchResultBinding
    var apiHelper = ApiHelper.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getColorWallpaper()


//        val searchImg = intent.getStringExtra("searchimg")
//
//        binding.textSer.text = searchImg



        /*apiHelper.getSraechEallpaper("dxOcX6Vnx8G4a59ltaDGGTLcnuHWzR6wWfT3aPovmD3MvdkjDXTRcl1G", "${searchImg}", 15, ).enqueue(object : Callback<WallpaperModal> {
                override fun onResponse(
                    call: Call<WallpaperModal>,
                    response: Response<WallpaperModal>
                ) {
                    //Log.d("Respose", response.message().toString())
                    if (response.code()==200){
                        Log.d("Respose", response.body().toString())

                        binding.recyclerSearchResult.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        binding.recyclerSearchResult.adapter = SearchResultAdpter(this@SearchResultActivity, response.body()!!.photos as List<PhotoModal> )

                    }

                }

                override fun onFailure(call: Call<WallpaperModal>, t: Throwable) {
                    Log.d("Network Error", "Can't hit Api due to ${t.message}")
                    t.printStackTrace()
                }

        })*/


    }

    fun getColorWallpaper(){
        var searchImg = intent.getStringExtra("searchimg")
        var arrColor = intent.getStringExtra("arrColors")



        binding.textSer.text = searchImg

        Log.d("value", "$searchImg,$arrColor")
        apiHelper.getSraechEallpaper("dxOcX6Vnx8G4a59ltaDGGTLcnuHWzR6wWfT3aPovmD3MvdkjDXTRcl1G", "${searchImg}", 15, arrColor!!).enqueue(object : Callback<WallpaperModal> {
            override fun onResponse(
                call: Call<WallpaperModal>,
                response: Response<WallpaperModal>
            ) {
                //Log.d("Respose", response.message().toString())
                if (response.code()==200){
                    Log.d("Respose", response.body().toString())

//                    binding.recyclerSearchResult.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    binding.recyclerSearchResult.layoutManager = GridLayoutManager(this@SearchResultActivity, 2)
                    binding.recyclerSearchResult.adapter = SearchResultAdpter(this@SearchResultActivity, response.body()!!.photos as List<PhotoModal> )

                }

            }

            override fun onFailure(call: Call<WallpaperModal>, t: Throwable) {
                Log.d("Network Error", "Can't hit Api due to ${t.message}")
                t.printStackTrace()
            }

        })
    }
}