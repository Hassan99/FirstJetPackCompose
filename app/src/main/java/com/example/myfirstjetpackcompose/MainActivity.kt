package com.example.myfirstjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myfirstjetpackcompose.viewmodel.ListViewModel
import com.example.myfirstjetpackcompose.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartUp()
        }
    }
}

@Composable
fun StartUp() {
    createListOfEmployee(ListViewModel())
//    initiateOnLaunch(MainViewModel())
}

@Composable
fun initiateOnLaunch(viewModel: MainViewModel) {
    viewModel.counter.observeAsState().value
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier = Modifier.background(color = Color.White)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,

                ) {
                Button(onClick = { viewModel.decrement.invoke() }) {
                    Text("decrement")
                }
                Text(text = viewModel.counter.value.toString())
                Button(onClick = { viewModel.increment.invoke() }) {
                    Text("increment")
                }

            }
        }

    }
}@Composable
fun createListOfEmployee(viewModel: ListViewModel) {
    val listOfEmployees = viewModel.listOfEmployees.observeAsState(emptyList())
    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between TextField and Button
            ) {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Employee Name") },
                    placeholder = { Text("Type a name...") },
                    modifier = Modifier
                        .weight(1f), // Make the TextField take the remaining space
                    singleLine = true
                )

                Button(
                    onClick = {
                        viewModel.addEmployee(text)
                        text = "" // Clear the TextField after adding
                    },
                    enabled = text.isNotBlank() // Disable the Button if text is blank
                ) {
                    Text("Add")
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listOfEmployees.value) { item ->
                    Text(
                        text = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .padding(16.dp), // Inner padding for better text spacing
                        color = Color.Black
                    )
                }
            }
        }
    }
}

