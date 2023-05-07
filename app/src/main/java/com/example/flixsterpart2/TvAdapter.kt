package com.example.flixsterpart2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
//import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
const val TV_DETAILS= "TV_DETAILS"
class TvAdapter(private val context: Context, private val tvShows: List<TvShow>): RecyclerView.Adapter<TvAdapter.ViewHolder>() {
//    val titles=arrayOf("Title 1","Title 2","Title 3")
//    val descriptions=arrayOf("Testing 1","Testing 2","Testing 3")
    //private var images

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private val titleItem: TextView
        private val descItem: TextView
        private val imageView: ImageView
        init {
            imageView=itemView.findViewById<ImageView>(R.id.imageView)
            titleItem= itemView.findViewById<TextView>(R.id.title)
            descItem= itemView.findViewById<TextView>(R.id.description)

            itemView.setOnClickListener(this)
        }
        fun binding(tvShow:TvShow){
            titleItem.text=tvShow.title
            descItem.text=tvShow.descripton
            Glide.with(context).load(tvShow.imageUrl).into(imageView)
        }

        override fun onClick(p0: View?) {

            val tv = tvShows[adapterPosition]
            //Toast.makeText(context,tv.title,Toast.LENGTH_SHORT).show()
            val intention = Intent(context, DetailActivity :: class.java)

            intention.putExtra(TV_DETAILS, tv)


            context.startActivity(intention)
        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvAdapter.ViewHolder {

        val context=parent.context
        val inflater = LayoutInflater.from(context)
        val tvView=inflater.inflate(R.layout.tv_list,parent,false)
        return ViewHolder(tvView)
    }

    override fun getItemCount(): Int {

        return tvShows.size
    }

    override fun onBindViewHolder(holder: TvAdapter.ViewHolder, position: Int) {

            val show= tvShows[position]
        holder.binding(show)

    }

}