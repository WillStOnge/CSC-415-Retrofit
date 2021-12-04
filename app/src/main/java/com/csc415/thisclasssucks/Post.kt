package com.csc415.thisclasssucks

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

data class Post(val id: Int, val userId: Int, val title: String, val body: String)

interface PostService
{
	@GET("/posts")
	fun getPosts(): Call<List<Post>>

	@GET("/posts")
	fun getPost(@Query("id") id: Int): Call<List<Post>>
}