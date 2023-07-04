package com.example.newsappjetpackcompose.pagination

import android.util.Log
import com.example.newsappjetpackcompose.Article
import com.example.newsappjetpackcompose.NewsResponse
import com.example.newsappjetpackcompose.repository.NewsRepository
import kotlinx.coroutines.delay

class paginatonRepository {
    private var data:NewsResponse? = null
    private val newsRepository = NewsRepository()
    suspend fun getResponse(page:Int,pageSize:Int):Result<List<Article>?>{
        if(data==null){
            data = newsRepository.getBreakingNews("in",1)
            Log.i("scroll","null")
        }
        delay(2000L)
        Log.i("scroll",data?.articles.toString())
        Log.i("scroll","$page")
        Log.i("scroll","${data?.articles?.size}")
        return if(page * pageSize + pageSize <= (data?.articles?.size ?: 1000)){
             Result.success(data?.articles?.slice(page*pageSize until  page*pageSize + pageSize))
        }else{
             Result.success(emptyList())
        }

    }
}