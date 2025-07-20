package com.pixelveda.dmovies.presentation.ui.bookMarkPage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pixelveda.dmovies.R
import com.pixelveda.dmovies.domain.model.Movie
import com.squareup.picasso.Picasso

class BookMarkAdapter(val list:List<Movie>,val listenerItemClick:BookMarkListListener,val listenerItemDelete:BookMarkListDeleteListener): RecyclerView.Adapter<BookMarkAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.book_mark_item_image)
        val title: TextView = itemView.findViewById(R.id.book_mark_item_title)
        val dec: TextView = itemView.findViewById(R.id.book_mark_item_dec)
        val delete: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.book_mark_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
            val model = list[position]
            holder.title.text = model.title
        Picasso.get()
            .load(model.poster)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imageView)

        holder.dec.text = model.genre

        holder.itemView.setOnClickListener{
            listenerItemClick.onItemClick(position)
        }

        holder.delete.setOnClickListener {
            listenerItemDelete.onDeleteIconClick(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}