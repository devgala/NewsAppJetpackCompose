package com.example.newsappjetpackcompose.uicomponents

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.events.SavedScreenEvents
import com.example.newsappjetpackcompose.util.LoadImageByURL
import com.example.newsappjetpackcompose.util.getFormattedDate
import com.example.newsappjetpackcompose.viewmodel.SavedScreenViewModel
import com.example.newsappjetpackcompose.webViewNav.Screen
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShortsCard(
    article: com.example.newsappjetpackcompose.Article,
    onEvent: (SavedScreenEvents) -> Unit,
    savedScreenViewModel: SavedScreenViewModel = hiltViewModel(),
    webNavController: NavController

) {
    var isSaved = rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = true ){
        savedScreenViewModel.viewModelScope.launch {
            isSaved.value = savedScreenViewModel.repository.isPresent(article.url)
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .clickable {
            Log.d("fromNewsCard", article.url)
            webNavController.navigate(
                Screen.WebViewScreenUI.route + "/" + URLEncoder.encode(
                    article.url,
                    StandardCharsets.UTF_8.toString()
                )
            )
        }) {
                Column {
                val image = LoadImageByURL(url = article.urlToImage, R.drawable.img).value
                if (image == null) {
                    Log.d("glide bs", "null ")
                }

                image?.let {
                    Image(
                        bitmap = it.asImageBitmap(), contentDescription = "image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(225.dp), contentScale = ContentScale.Crop
                    )
                }
                Text(
                    text = article.title,
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(7.dp),
                    fontWeight = FontWeight.Bold
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    sourceNameDisplay(
                        modifier = Modifier,
                        sourceName = article.author,
                        publishedAt = article.publishedAt
                    )
                    Text(
                        text = getFormattedDate(article.publishedAt),
                        color = Color.Gray,
                        fontSize = 15.sp
                    )
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
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )
                Text(
                    text = article.description?:"",
                    color = Color.Black,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(7.dp),
                )


            }



    }

}

@Composable
fun sourceNameDisplay(modifier: Modifier, sourceName: String?, publishedAt: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(
            painter = painterResource(id = R.drawable.newspaper_fill0_wght400_grad0_opsz48),
            contentDescription = null,
            tint = Color.Gray,
            modifier = modifier.padding(7.dp)
        )
        Text(text = sourceName?:"", color = Color.Gray, fontSize = 15.sp)

    }
}
//@Preview(showBackground = true)
//@Composable
//fun Preview(){
//    ArticleItemSaved(article = Article(
//        author = "Author",
//        title = "Title",
//        description = "Description",
//        "www.google.com",
//        "https://images.pexels.com/photos/17211591/pexels-photo-17211591/free-photo-of-bicycle-parked-under-building-painted-blue.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load",
//        "This is content",
//       // Source("id","Source Name")
//    ), onEvent ={event -> print(event)})
//}
