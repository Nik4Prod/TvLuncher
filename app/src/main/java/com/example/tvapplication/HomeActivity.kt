package com.example.tvapplication

import android.content.ComponentName
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.example.tvapplication.ui.component.AppList
import com.example.tvapplication.ui.component.CarouselTv
import com.example.tvapplication.ui.component.MainViewModel
import com.example.tvapplication.ui.theme.TvApplicationTheme
import com.example.tvapplication.util.MakeHomeLauncher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class HomeActivity : ComponentActivity() {

    companion object{
        val imageList = listOf(
            R.drawable.mountine_1,
            R.drawable.mountine_2,
            R.drawable.mountine_3,
            R.drawable.mountine_4,
            R.drawable.mountine_5,
        )
    }
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val defaultLauncher = MakeHomeLauncher()
        defaultLauncher.setAsDefaultLauncher(applicationContext)


        val viewModel = MainViewModel(packageManager)
        setContent {

            TvApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),

                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        CarouselTv(imageList = imageList)
                    }
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {

                        AppList(viewModel)
                    }
                }
            }
        }
    }


}













