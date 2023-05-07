package com.example.flixsterpart2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONException

private const val tag= "MainActivity"
private const val TV_KEY="a07e22bc18f5cb106bfe4cc1f83ad8ed"
class MainActivity : AppCompatActivity() {
    private val tvShows= mutableListOf<TvShow>()
    private lateinit var tvRev: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        tvRev= findViewById<RecyclerView>(R.id.tvRev)
        //For adapter
        val adapt=TvAdapter(this,tvShows)
        tvRev.adapter=adapt

        val layoutManager=LinearLayoutManager(this)
        tvRev.layoutManager=layoutManager

        val client = AsyncHttpClient()
        client.get("https://api.themoviedb.org/3/tv/1/recommendations?api_key=$TV_KEY", object: JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(tag, "Error $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.i(tag, "Success JSON data $json")
                try{
                    val tvList=json.jsonObject.getJSONArray("results")
                    tvShows.addAll(TvShow.jsonList(tvList))
                    adapt.notifyDataSetChanged()
                    Log.i(tag, "Tv List $tvShows")
                }
                catch(except:JSONException){
                    Log.e(tag,"Exception found $except")
                }

            }

        })



    }

}