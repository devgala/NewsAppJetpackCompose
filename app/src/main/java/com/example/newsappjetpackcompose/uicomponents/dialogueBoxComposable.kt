package com.example.newsappjetpackcompose.uicomponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun CustomDialog(
    location: String,
    setShowDialog: (Boolean) -> Unit,
    setLocation: (String) -> Unit,
    isCelcius: Boolean,
    setCelcius: (Boolean) -> Unit,
    response: (Int) -> Unit
) {
    Dialog(onDismissRequest = { setShowDialog(false) }) {
        var resp = 0;
        val locationText = remember {
            mutableStateOf(location)
        }
        val toggleState = remember {
            mutableStateOf(isCelcius)
        }
        Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp), shadowElevation = 5.dp, tonalElevation = 5.dp) {
            Column {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)) {
                    Text(
                        text = "Weather Settings",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(
                            Alignment.Center
                        ),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                }
                Box(modifier = Modifier.fillMaxWidth()) {

                    OutlinedTextField(
                        value = locationText.value,
                        onValueChange = {
                            locationText.value = it
                        },
                        label = { Text(text = "Location") },
                        placeholder = { Text("Enter Location") },
                        modifier = Modifier
                            .align(
                                Alignment.CenterStart
                            )
                            .padding(horizontal = 20.dp, vertical = 5.dp)
                            .fillMaxWidth()
                    )
                }
                Box(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(15.dp, 5.dp)
                            .fillMaxWidth()
                    ) {
                        Text("Toggle Units:", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        Switch(
                            checked = toggleState.value,
                            onCheckedChange = {
                                toggleState.value = !toggleState.value
                            },
                            thumbContent = {
                                Text(text =
                                    if (toggleState.value) {
                                        "\u2103"
                                    } else {
                                        "\u2109"
                                    },
                                    color = Color.Black

                                )
                            },
                            modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 5.dp),
                            colors = SwitchDefaults.colors(

                                checkedTrackColor = Color.Blue,
                                uncheckedTrackColor = Color(52,199,89),
                                uncheckedThumbColor = Color.White,
                                checkedBorderColor = Color.Transparent,
                                uncheckedBorderColor = Color.Transparent
                            )

                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.End, modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    TextButton(onClick = {
                        setShowDialog(false)
                    }) {
                        Text(text = "Cancel")
                    }
                    TextButton(onClick = {
                        if (locationText.value.isNotEmpty()) {

                            setLocation(locationText.value)
                            resp = 1
                        }

                        setCelcius(toggleState.value)
                        response(resp)
                        setShowDialog(false)


                    }) {
                        Text(text = "Confirm")
                    }
                }
            }
        }
    }
}

@Composable
fun FormIp() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)) {
                Text(
                    text = "Weather Settings",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(
                        Alignment.Center
                    ),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                )
            }
            Box(modifier = Modifier.fillMaxWidth()) {

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text(text = "Location") },
                    placeholder = { Text("Enter Location") },
                    modifier = Modifier
                        .align(
                            Alignment.CenterStart
                        )
                        .padding(horizontal = 20.dp, vertical = 5.dp)
                        .fillMaxWidth()
                )
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(15.dp, 5.dp)
                        .fillMaxWidth()
                ) {
                    Text("Toggle Units:", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Switch(
                        checked = true,
                        onCheckedChange = {},
                        thumbContent = { Text("℃", color = Color.Black) },
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = Color.Blue,
                            uncheckedTrackColor = Color(52,199,89),
                            uncheckedThumbColor = Color.White,
                            checkedBorderColor = Color.Transparent,
                            uncheckedBorderColor = Color.Transparent,
                            
                        )

                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.End, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Cancel")
                }
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Confirm")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Previw() {
    FormIp()
}