@file:Suppress("DEPRECATION")

package com.example.flixsterpart2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide

private const val TAG = "Detail Activity"
class DetailActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var tvImage: ImageView
    private lateinit var tvDescribe: TextView
    private lateinit var tvRate: RatingBar
    private lateinit var tvRelease: TextView
    private lateinit var tvVotes: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        tvTitle=findViewById(R.id.showTitle)
        tvDescribe=findViewById(R.id.showDescripton)
        tvImage=findViewById(R.id.showImage)
        tvRate = findViewById(R.id.tvRating)
        tvRelease = findViewById(R.id.releaseDate)
        tvVotes = findViewById(R.id.voteCount)

        val tvPick=intent.getParcelableExtra<TvShow>(TV_DETAILS) as TvShow
        Log.i(TAG, "Tv show is $tvPick")
        tvTitle.text=tvPick.title
        //tvImage.setImageResource()=tvPick.imageUrl
        tvDescribe.text=tvPick.descripton
        Glide.with(this).load(tvPick.imageUrl).into(tvImage)
        tvRate.rating=tvPick.voteAvg.toFloat()



        tvRelease.text= "Release Date: "+ tvPick.relDate
        tvVotes.text = "Vote Count: " + tvPick.voteCount

    }
}