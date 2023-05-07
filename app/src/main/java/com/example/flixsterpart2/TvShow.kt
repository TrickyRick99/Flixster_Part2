package com.example.flixsterpart2

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.json.JSONArray

@Parcelize
data class TvShow(
    val title:String,
    val descripton: String,
    private val image: String,
    val voteAvg:Double,
    val relDate: String,
    val voteCount: Int
): Parcelable {
    @IgnoredOnParcel
    val imageUrl="https://image.tmdb.org/t/p/w500/$image"

    companion object{
        fun jsonList(tvList: JSONArray):List<TvShow> {
            val tvShows= mutableListOf<TvShow>()

            for (x in 0 until tvList.length()){
                val tvJson = tvList.getJSONObject(x)
                tvShows.add(
                    TvShow(
                        tvJson.getString("name"),
                        tvJson.getString("overview"),
                        tvJson.getString("poster_path"),
                        tvJson.getDouble("vote_average"),
                        tvJson.getString("first_air_date"),
                        tvJson.getInt("vote_count")


                    )
                )
            }
            return tvShows
        }
    }
}