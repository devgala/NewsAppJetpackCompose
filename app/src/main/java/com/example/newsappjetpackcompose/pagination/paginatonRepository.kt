package com.example.newsappjetpackcompose.pagination

import android.util.Log
import com.example.newsappjetpackcompose.Article
import com.example.newsappjetpackcompose.NewsResponse
import com.example.newsappjetpackcompose.repository.NewsRepository
import kotlinx.coroutines.delay
import retrofit2.http.Query

class paginatonRepository {
    private var newsData:NewsResponse? = null
    private val newsRepository = NewsRepository()
    private var searchData: NewsResponse? = null
    suspend fun getResponse(page:Int,pageSize:Int):Result<List<Article>?>{
//        if(data==null){
//            data = newsRepository.getBreakingNews("in",1)
//            Log.i("scroll","null")
//        }
//        delay(2000L)
//        Log.i("scroll",data?.articles.toString())
//        Log.i("scroll","$page")
//        Log.i("scroll","${data?.articles?.size}")
//        return if(page * pageSize + pageSize <= (data?.articles?.size ?: 1000)){
//             Result.success(data?.articles?.slice(page*pageSize until  page*pageSize + pageSize))
//        }else{
//             Result.success(emptyList())
//        }
        newsData = newsRepository.getBreakingNews("in",page);
        Log.d("response", if(newsData?.articles==emptyList<Article>()){
            "NULL or empty"
        }else{
            newsData?.articles?.size.toString()
        })
        newsData?.let{

            return Result.success(newsData?.articles)
        }
    return  Result.success(emptyList())

    }


    suspend fun getSearchResponse(page:Int,query: String):Result<List<Article>?>{
        searchData = newsRepository.getSearchedNews(query,page)

        searchData?.let {
            return Result.success(searchData?.articles)
        }
        return Result.success(emptyList())
    }
}