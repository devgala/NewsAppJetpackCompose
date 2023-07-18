package com.example.newsappjetpackcompose.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.matchParentSize
//import androidx.compose.foundation.layout.BoxScopeInstance.matchParentSize
//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.BoxScopeInstance.matchParentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsappjetpackcompose.model.weatherModel.WeatherResponse
import com.example.newsappjetpackcompose.util.LoadImageByURL
import com.example.newsappjetpackcompose.util.getImageURLFromCondition

//weatherResponse: WeatherResponse
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDisplay(weatherResponse: WeatherResponse,locationUpdate:(String)->Unit) {
    var textColor = Color.Black
    val isCelsius = remember {
        mutableStateOf(true)
    }
    val showDialog = remember {
        mutableStateOf(false)
    }
    val locationState = remember {
        mutableStateOf(weatherResponse.location.name)
    }
    if(showDialog.value){
        CustomDialog(
            location = locationState.value,
            setShowDialog = {
                            showDialog.value = it
            },
            setLocation = {
                locationState.value = it
            },
            isCelcius = isCelsius.value,
            setCelcius = {
                         isCelsius.value = it
            },
            response = {
                if(it==1){
                    locationUpdate(locationState.value)
                }
            }
        )
    }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),

            elevation = CardDefaults.cardElevation(defaultElevation = 7.dp),
            colors = CardDefaults.cardColors(Color.White)

        ) {
            Box(
                modifier = Modifier
                    //.align(Alignment.Start)

                    .fillMaxWidth()
            ) {
                val img = LoadImageByURL(
                    url = getImageURLFromCondition(
                        condition = weatherResponse.current.condition,
                        isDay = weatherResponse.current.is_day == 1
                    )
                ).value
                img?.let {
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .matchParentSize()
                            .blur(10.dp),
                        alpha = 1F
                    ); // alpha = 0.4F

                }
                //  Image(imageVector = Icons.Default.Add, contentDescription = null,modifier = Modifier.matchParentSize())

                Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()){

                    Column(

                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            //      .align(Alignment.Center)
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                            .fillMaxWidth(0.72F)
                    ) {

                        textColor = Color.White
                        Text(
                            weatherResponse.location.name + ", " + weatherResponse.location.region + ", " + weatherResponse.location.country,
                            modifier = Modifier.padding(bottom = 5.dp),
                            color = textColor,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = if (isCelsius.value)"${weatherResponse.current.temp_c}\u2103" else "${weatherResponse.current.temp_f}\u2109" ,
                            fontSize = 45.sp,
                            modifier = Modifier.padding(bottom = 5.dp),
                            color = textColor,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = "Feels Like " + if (isCelsius.value)"${weatherResponse.current.feelslike_c}\u2103" else "${weatherResponse.current.feelslike_f}\u2109",
                            fontSize = 18.sp,
                            modifier = Modifier.padding(bottom = 5.dp),
                            color = textColor,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = weatherResponse.current.condition.text,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(bottom = 5.dp),
                            color = textColor
                        )

                    }
                    Box(modifier = Modifier.fillMaxWidth(0.4F)){

                        IconButton(onClick = {
                            showDialog.value=true
                        }, modifier = Modifier.padding(5.dp)){
                            Icon(imageVector = Icons.Default.Settings, contentDescription =null, tint = Color.White)
                        }
                    }

                }


            }


        }

}


