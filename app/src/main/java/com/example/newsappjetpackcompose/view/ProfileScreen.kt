package com.example.newsappjetpackcompose.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.util.Languages
import com.example.newsappjetpackcompose.util.LoadImageByURL


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val isExpanded = remember {
        mutableStateOf(false)
    }
    val languageField = remember {
        mutableStateOf("ENGLISH")

    }
    val languageCode = remember {
        mutableStateOf("en")
    }
    Column(/*modifier = Modifier.fillMaxSize()*/
    ) {

        Box(
            modifier = Modifier

                .fillMaxWidth()
                .padding(top = 20.dp)
        )
        {
            val image = LoadImageByURL(
                url = "",
                defaultImg = R.drawable.person_fill0_wght400_grad0_opsz48
            ).value
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.07F)
                )
                image?.let {

                    Image(
                        bitmap = it.asImageBitmap(), contentDescription = null,
                        modifier = Modifier
                            //.align(Alignment.Center)
                            .padding(20.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = CircleShape
                            ),

                        contentScale = ContentScale.Crop,

                        )
                }
                Text(text = "THIS IS NAME", fontWeight = FontWeight.ExtraBold, fontSize = 28.sp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painter = painterResource(
                            id = R.drawable.alternate_email_fill0_wght400_grad0_opsz48
                        ),
                        modifier = Modifier
                            .padding(5.dp)
                            .height(25.dp)
                            .width(25.dp),

                        contentDescription = null, tint = Color.DarkGray
                    )
                    OutlinedTextField(
                        value = "THIS IS EMAIL",
                        onValueChange = {},
                        readOnly = true,
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )

                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Select Language:",
                        modifier = Modifier.padding(
                            top = 10.dp,
                            bottom = 10.dp,
                            start = 10.dp,
                            end = 5.dp
                        )
                    )
                    ExposedDropdownMenuBox(
                        expanded = isExpanded.value, onExpandedChange = {
                            isExpanded.value = it
                        },
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 10.dp, start = 5.dp, end = 10.dp)
                            .fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = languageField.value, onValueChange = {}, readOnly = true,
                            enabled = false,
                        )

                        ExposedDropdownMenu(
                            expanded = isExpanded.value,
                            onDismissRequest = { isExpanded.value = false }) {
                            Languages.languageList.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(text = option.language) },
                                    onClick = {
                                        languageField.value = option.language
                                        languageCode.value = option.code
                                        isExpanded.value = false
                                    })
                            }

                        }
                    }
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5F)
                )

                ElevatedButton(
                    onClick = {
                        //logout logic
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.padding(vertical = 10.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp, pressedElevation = 0.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Sign Out",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color.White,
                            modifier = Modifier.padding(10.dp)
                        )
                        Icon(imageVector = Icons.Default.ExitToApp, contentDescription = null)

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview() {
    ProfileScreen()
}