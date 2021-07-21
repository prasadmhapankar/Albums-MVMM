package com.jpmc.android_challenge.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jpmc.android_challenge.R
import com.jpmc.android_challenge.model.Album
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {

    private lateinit var albums : MutableList<Album>

    abstract class ViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView!!)  {
        abstract fun init(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return AlbumsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(::albums.isInitialized){
            holder.init(position)
        }
    }

    override fun getItemCount(): Int = if(::albums.isInitialized) albums.size else 0

    inner class AlbumsViewHolder(itemView : View?) : ViewHolder(itemView){
        override fun init(position: Int) {
            val albumItem = albums[position]
            itemView.tv_album_name.text = albumItem.title
        }
    }

    fun notifyAlbums(posts: List<Album>){
        this.albums = posts as MutableList<Album>
        notifyDataSetChanged()
    }

}
