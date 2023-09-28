package com.example.tvapplication.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.tv.foundation.lazy.list.TvLazyRow


@Composable
fun AppList(
    viewModel: MainViewModel,
) {

    val context = LocalContext.current

    val appList by viewModel.appList.collectAsState(initial = emptyList())
    // Fetch the list of installed apps in a coroutine
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TvLazyRow(modifier = Modifier.background(Color.Black.copy(alpha = 0.3f))){
            appList.forEach {
                item {
                    AppItem(app = it , packageManager = viewModel.packageManager){ app ->
                        viewModel.onAppClick(app, context)
                    }
                }
            }
        }
    }
}