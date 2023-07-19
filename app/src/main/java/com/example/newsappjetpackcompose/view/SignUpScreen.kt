package com.example.newsappjetpackcompose.view

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsappjetpackcompose.PreferencesManager
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.ui.theme.RegularFont
import com.example.newsappjetpackcompose.ui.theme.lightBlue
import com.example.newsappjetpackcompose.util.Constants
import com.example.newsappjetpackcompose.util.Languages
import com.example.newsappjetpackcompose.viewmodel.SignUpViewModel
import com.example.newsappjetpackcompose.webViewNav.Screen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
) {

    val db = Firebase.firestore

    val googleAccount = remember { mutableStateOf<GoogleSignInAccount?>(null) }
    val googleSignInState = viewModel.googleState.value

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                googleAccount.value = result
                val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                viewModel.googleSignIn(credentials)
            } catch (it: ApiException) {
                print(it)
            }
        }

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var country by rememberSaveable { mutableStateOf("") }
    var language by rememberSaveable { mutableStateOf("") }
    val isExpanded = remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    val state = viewModel.signUpState.collectAsState(initial = null)

    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    val spName = remember { mutableStateOf(preferencesManager.getData("name","")) }
    val spLanguage = remember { mutableStateOf(preferencesManager.getData("language","English")) }
    val spCountry = remember { mutableStateOf(preferencesManager.getData("country","India")) }
    val spEmail = remember { mutableStateOf(preferencesManager.getData("email","")) }
    val spPassword = remember { mutableStateOf(preferencesManager.getData("password","")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 30.dp,
                end = 30.dp
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 5.dp),
            text = "Create Account",
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            fontFamily = RegularFont,
        )
        Text(
            text = "Enter your credentials to register",
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = Color.Gray,
            fontFamily = RegularFont,

            )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                name = it
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            placeholder = {
                Text(text = "Name")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = country,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                country = it
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            placeholder = {
                Text(text = "Country")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = isExpanded.value, onExpandedChange = {
                isExpanded.value = it
            },
            modifier = Modifier
                //.padding(top = 10.dp, bottom = 10.dp, start = 5.dp, end = 10.dp)
                .fillMaxWidth()
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = language,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = lightBlue,
                    cursorColor = Color.Black,
                    disabledLabelColor = lightBlue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                   // country = it
                },
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                placeholder = {
                    Text(text = "Language")
                },
                readOnly = true,
            )

            ExposedDropdownMenu(
                expanded = isExpanded.value,
                onDismissRequest = { isExpanded.value = false }) {

                Languages.languageCodeMap.forEach { (key,_) ->
                    DropdownMenuItem(
                        text = { Text(text = key) },
                        onClick = {
                            language = key
                            //languageCode.value = option.code
                            isExpanded.value = false
                        })
                }

            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                email = it
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            placeholder = {
                Text(text = "Email")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                password = it
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            placeholder = {
                Text(text = "Password")
            }
        )
        Button(
            onClick = {
                scope.launch {
                    viewModel.registerUser(email, password)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 20.dp,
                    start = 30.dp,
                    end = 30.dp
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "Sign Up",
                color = Color.White,
                modifier = Modifier
                    .padding(7.dp)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            if (state.value?.isLoading == true) {
                CircularProgressIndicator()
            }
        }
        Text(
            modifier = Modifier
                .padding(top = 15.dp)
                .clickable {
                    navController.navigate(Screen.LoginScreen.route)
                },
            text = "Already Have an account? Login",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontFamily = RegularFont
        )
        Text(
            modifier = Modifier
                .padding(top = 10.dp),
            text = "Or connect with",
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestIdToken(Constants.SERVER_CLIENT)
                    .build()

                val googleSignInClient = GoogleSignIn.getClient(context, gso)

                launcher.launch(googleSignInClient.signInIntent)
            }) {
                Icon(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google Icon",
                    tint = Color.Unspecified
                )
            }


            LaunchedEffect(key1 = state.value?.isSuccess) {
                scope.launch {
                    if (state.value?.isSuccess?.isNotEmpty() == true) {
                        val success = state.value?.isSuccess
                        Toast.makeText(context, "$success", Toast.LENGTH_LONG).show()

                        val uid = FirebaseAuth.getInstance().uid
                        val user = hashMapOf(
                            "userID" to uid,
                            "name" to name,
                            "country" to country,
                            "language" to language,
                            "email" to email,
                            "password" to password
                        )

                        db.collection("users")
                            .add(user)
                            .addOnSuccessListener { documentReference ->
                                Log.d("fireStore entry created", "User added with id: ${documentReference.id}")
                            }
                            .addOnFailureListener { e ->
                                Log.d("fireStore error", e.toString())
                            }

                        navController.navigate(Screen.LoginScreen.route)
                    }
                }
            }
            LaunchedEffect(key1 = state.value?.isError) {
                scope.launch {
                    if (state.value?.isError?.isNotBlank() == true) {
                        val error = state.value?.isError
                        Toast.makeText(context, "$error", Toast.LENGTH_LONG).show()
                    }
                }
            }
            LaunchedEffect(key1 = googleSignInState.success) {
                scope.launch {
                    if (googleSignInState.success != null) {
                        Toast.makeText(context, "Sign In Success", Toast.LENGTH_LONG).show()
                        preferencesManager.saveData("name", googleAccount.value?.displayName!!)
                        spName.value = googleAccount.value?.displayName!!
                        preferencesManager.saveData("email", googleAccount.value?.email!!)
                        spEmail.value = googleAccount.value?.email!!

                        navController.navigate(Screen.BottomScreenNav.route)
                    }
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (googleSignInState.loading) {
                CircularProgressIndicator()
            }
        }

    }
}