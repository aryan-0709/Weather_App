package com.example.weatherapp.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen()
{
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {})
    {
innerPadding->
        Column(modifier = Modifier.fillMaxSize()
            .padding(innerPadding)
            .imePadding()
            .background(MaterialTheme.colorScheme.background))
        //Headder
        {
            Box(modifier = Modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(16.dp))
            {
                OutlinedTextField(value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Enter City") }

                )
            }
        }
    }
}
