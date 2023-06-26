package com.example.newsappjetpackcompose.view

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsappjetpackcompose.events.SavedScreenEvents
import com.example.newsappjetpackcompose.model.Article
import com.example.newsappjetpackcompose.model.Source
import com.example.newsappjetpackcompose.util.LoadImageByURL
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleItemSaved(
    article: Article,
    onEvent: (SavedScreenEvents)->Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 7.dp)



    ) {

    Column {
        val image = LoadImageByURL(url = article.url).value
        image?.let {
            Image(
                bitmap = it.asImageBitmap(), contentDescription =null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(225.dp)
                , contentScale = ContentScale.Crop
            )
        }
        Text(text = article.title, color = Color.Black, fontSize = 25.sp, modifier = Modifier.padding(7.dp), fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(15.dp)
                            )
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
            sourceNameDisplay(modifier = Modifier, sourceName =article.source_name)
            IconButton(onClick = {onEvent(SavedScreenEvents.onClickDelete(article))}){
                Icon(imageVector = Icons.Default.Delete, contentDescription =null, tint = Color.Red, modifier = Modifier.padding(7.dp))

            }
        }



    }


    }
}

@Composable
fun sourceNameDisplay(modifier: Modifier,sourceName : String) {
    Row(verticalAlignment = Alignment.CenterVertically){
        Icon(imageVector = Icons.Default.Edit, contentDescription =null, tint = Color.Gray, modifier = modifier.padding(7.dp))
        Text(text = sourceName, color = Color.Gray, fontSize = 15.sp)
    }
}
@Preview(showBackground = true)
@Composable
fun Preview(){
    ArticleItemSaved(article = Article(
        author = "Author",
        title = "Title",
        description = "Description",
        "www.google.com",
        "https://images.pexels.com/photos/17211591/pexels-photo-17211591/free-photo-of-bicycle-parked-under-building-painted-blue.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load",
        "This is content",
       // Source("id","Source Name")
    ), onEvent ={event -> print(event)})
}
