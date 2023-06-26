package com.example.newsappjetpackcompose.uicomponents

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.newsappjetpackcompose.Article
import com.example.newsappjetpackcompose.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun NewsCard(article: Article){
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
                    Image(painter = painterResource(id = R.drawable.img),
                        contentDescription = "",
                        contentScale = ContentScale.Crop)
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
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription ="download" )
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
