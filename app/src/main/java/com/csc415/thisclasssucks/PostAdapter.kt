package com.csc415.thisclasssucks

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(val posts: List<Post>, private val context: Context) : RecyclerView.Adapter<PostAdapter.PostHolder>()
{
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder
	{
		val view: View = LayoutInflater.from(context).inflate(R.layout.post_card, parent, false)
		return PostHolder(view)
	}

	override fun onBindViewHolder(holder: PostHolder, position: Int)
	{
		holder.title.text = posts[position].title
	}

	override fun getItemCount(): Int = posts.size

	inner class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
	{
		val title: TextView = itemView.findViewById(R.id.card_title)

		init
		{
			itemView.setOnClickListener {
				val intent = Intent(context, PostActivity::class.java).apply {
					putExtra(PostActivity.POST_ID, posts[layoutPosition].id.toString())
				}
				context.startActivity(intent)
			}
		}
	}
}