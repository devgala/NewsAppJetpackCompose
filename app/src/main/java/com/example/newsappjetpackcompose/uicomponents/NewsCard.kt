package com.example.newsappjetpackcompose.uicomponents

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsappjetpackcompose.Article
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.Screen
import com.example.newsappjetpackcompose.events.SavedScreenEvents

@Composable
fun NewsCard(article: Article, navController: NavController){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                Log.d("fromNewsCard", article.url)
                navController.navigate(Screen.WebViewScreenUI.route + "/" + article.url)
            }
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
                    Image(painter = painterResource(id = R.drawable.img),
                        contentDescription = "",
                        contentScale = ContentScale.Crop)
                }
                Text(text = article.source.name, modifier = Modifier.wrapContentHeight(), maxLines = 1)
                Text(text = article.publishedAt, modifier = Modifier.wrapContentHeight(), maxLines = 1)
            }
            Column(
                modifier = Modifier
                    .weight(5f)
                    .wrapContentHeight(), verticalArrangement = Arrangement.SpaceBetween) {
                Text(text = article.title, modifier = Modifier.fillMaxWidth().wrapContentHeight(),maxLines = 3, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Text(text = article.title, modifier = Modifier.fillMaxWidth().wrapContentHeight(), maxLines = 1)
            }

        }
    }
}
