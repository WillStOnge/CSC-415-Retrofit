package com.csc415.thisclasssucks

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
			.addConverterFactory(GsonConverterFactory.create()).build()
			.create(PostService::class.java)

		retrofit.getPosts().enqueue(PostCallback())
	}

	inner class PostCallback : Callback<List<Post>>
	{
		override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>)
		{
			if (response.isSuccessful)
			{
				findViewById<RecyclerView>(R.id.recycler_view).apply {
					adapter = PostAdapter(response.body()!!, this@MainActivity)
					layoutManager = LinearLayoutManager(this@MainActivity)
				}
			}
			else
			{
				Toast.makeText(
					applicationContext,
					"Something went wrong fetching the data. Please try again later.",
					Toast.LENGTH_LONG
				).show()
			}
		}

		override fun onFailure(call: Call<List<Post>>, t: Throwable)
		{
			Toast.makeText(
				applicationContext,
				"Something went wrong fetching the data. Make sure you have a good Internet connection.",
				Toast.LENGTH_LONG
			).show()
			Log.e("test", t.message, t)
		}
	}
}