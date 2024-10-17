package com.example.signupauthentificationui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(navController: NavController){
    var name by remember{
        mutableStateOf("")
    }
    var age by remember {
        mutableStateOf("")
    }
    var selectedSex by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    val sexList = listOf("M", "F")
    var isExtended by remember {
        mutableStateOf(false)
    }

    var errorMessage by remember {
        mutableStateOf("")
    }

    Scaffold(
        content = { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = name,
                onValueChange = {name = it},
                label = {Text("Name")},
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = age,
                onValueChange = {age = it},
                label = { Text(text = "Age") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            /*TextField(
                value = sex,
                onValueChange = {sex = it},
                label = { Text("Sex")},
                modifier = Modifier.fillMaxWidth()
            )*/
            ExposedDropdownMenuBox(
                expanded = isExtended,
                onExpandedChange = {isExtended = !isExtended},
            ) {
                TextField(
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    value = selectedSex,
                    onValueChange = {},
                    readOnly = true,
                    label = {Text("Select your gender")}
                )
                ExposedDropdownMenu(
                    expanded = isExtended,
                    onDismissRequest = {isExtended = false}
                ) {
                    sexList.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            text = { Text(text)},
                            onClick = {
                                selectedSex = sexList[index]
                                isExtended = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = email,
                onValueChange = {email = it},
                label = { Text("Email")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (errorMessage.isNotEmpty()){
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
                Spacer (modifier = Modifier.height(8.dp))
            }
            Button(
                onClick = {
                    if (name.isNotEmpty() &&
                        age.isNotEmpty() &&
                        selectedSex.isNotEmpty() &&
                        email.isNotEmpty()){
                        /*if(isValidName(name) && isValidAge(age) && isValidEmail(email)){
                            navController.navigate("detail_screen/$name/$age/$selectedSex/$email")
                        }*/

                        when{
                            !isValidName(name) -> errorMessage = "Invalid name. Please enter a valid name."
                            !isValidAge(age) -> errorMessage = "Invalid age. Please enter a valid age."
                            !isValidEmail(email) -> errorMessage = "Invalid email. Please enter a valid email."
                            else -> {
                                //errorMessage = ""
                                navController.navigate("detail_screen/$name/$age/$selectedSex/$email")
                            }
                        }

                        //errorMessage = "Please fill in all fields the correct way"
                    }
                    else{
                        errorMessage = "Please fill in all fields"
                    }
                }
            ) {
                Text("Register")
            }
        }
    }
    )
}

fun isValidName(name:String): Boolean{
    val nameRegex = "^[A-Za-z ]{2,}\$".toRegex()
    return nameRegex.matches(name)
}

fun isValidAge(age:String):Boolean{
    return age.toInt() > 0
}

fun isValidEmail(email:String):Boolean{
    val emailRegex = "[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex()
    return emailRegex.matches(email)
}

