package com.example.newsappjetpackcompose.uicomponents

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappjetpackcompose.Article
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.events.NewsScreenEvents
import com.example.newsappjetpackcompose.events.SavedScreenEvents
import com.example.newsappjetpackcompose.util.LoadImageByURL
import com.example.newsappjetpackcompose.viewmodel.SavedScreenViewModel
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun NewsCard(article: Article,onEvent:(SavedScreenEvents)->Unit,savedScreenViewModel: SavedScreenViewModel= hiltViewModel()){
    var isSaved = rememberSaveable {
        mutableStateOf(false)
    }
   LaunchedEffect(key1 = true ){
       savedScreenViewModel.viewModelScope.launch {
           isSaved.value = savedScreenViewModel.repository.isPresent(article.url)
       }
   }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
                ){
            Column(
                modifier = Modifier
                    .weight(2f)
                    .wrapContentHeight()) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)) {
                    if(article.urlToImage==null){
                        Image(
                            painter = painterResource(id = R.drawable.img), contentDescription ="image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(225.dp)
                            , contentScale = ContentScale.Crop
                        )
                    }
                    else{
                        val image = LoadImageByURL(url = article.urlToImage, R.drawable.img).value
                        if(image==null){
                            Log.d("glide bs", "null ")
                        }

                        image?.let {
                            Image(
                                bitmap = it.asImageBitmap(), contentDescription ="image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(225.dp)
                                , contentScale = ContentScale.Crop
                            )
                        }
                    }
                    article.urlToImage?.let {
                    
                    }

                }

                Text(text = article.source.name, modifier = Modifier.wrapContentHeight(), maxLines = 1)
                Text(text = getDateFormat(article.publishedAt.substring(0,9)), modifier = Modifier.wrapContentHeight(), maxLines = 1)
            }
            Column(
                modifier = Modifier
                    .weight(5f)
                    .wrapContentHeight(), verticalArrangement = Arrangement.SpaceBetween) {
                Text(text = article.title, modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),maxLines = 3, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Text(text = article.title, modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), maxLines = 1)
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()){

                    IconButton(onClick = {
                        val inserted = com.example.newsappjetpackcompose.model.Article(
                            article.author?:"Not Available",
                            article.title?:"Not Available",
                            article.description?:"desc",
                            article.url?:"Not available",
                            article.urlToImage?:"Not available",
                            article.content?:"Not available",
                            article.publishedAt?:"Not available"

                        )
                        inserted.source_name = article.source.name?:"Not Available"
                        if(!isSaved.value){

                            onEvent(SavedScreenEvents.onClickAdd(inserted))
                            isSaved.value = true
                        }else {
                            onEvent(SavedScreenEvents.onClickDelete(inserted))
                            isSaved.value = false
                        }


                    }) {
//                        savedScreenViewModel.viewModelScope.launch {
//                            isSaved.value = savedScreenViewModel.repository.isPresent(article.url)
//                        }
                        if(isSaved.value){
                            Icon(imageVector = Icons.Default.Check , contentDescription ="download", modifier = Modifier.size(25.dp) )

                        }else{

                            Icon(painter = painterResource(id = R.drawable.bookmark_fill0_wght400_grad0_opsz48) , contentDescription ="download", modifier = Modifier.size(25.dp) )
                        }

                    }
                }
            }

        }
    }
}


@SuppressLint("SimpleDateFormat")
fun getDateFormat(date:String):String{
 val input = SimpleDateFormat("yyyy-MM-dd")
    val output = SimpleDateFormat("dd/MM/yyyy")
var d = Date()
    try {
        d = input.parse(date)
    }catch (e:ParseException){
        println(e)
    }
return output.format(d)
}
