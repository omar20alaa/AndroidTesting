package app.android_unit_testing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.android_unit_testing.R
import app.android_unit_testing.model.Post

class PostAdapter (private var posts: List<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val bodyTextView: TextView = itemView.findViewById(R.id.bodyTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        holder.titleTextView.text = post.title
        holder.bodyTextView.text = post.body
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun setData(newPosts: List<Post>) {
        posts = newPosts
        notifyDataSetChanged()
    }
}