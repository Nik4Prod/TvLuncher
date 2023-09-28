package com.example.tvapplication.ui.component

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun AppItem(
    app: ApplicationInfo,
    packageManager: PackageManager,
    onAppClick: (ApplicationInfo)-> Unit
) {
    val label = app.loadLabel(packageManager).toString()
    val icon = app.loadIcon(packageManager).toBitmap().asImageBitmap()
//        val banner = app.loadBanner(packageManager).toBitmap().asImageBitmap()

    var color by remember {  mutableStateOf(Color.Transparent) }

//    Box(Modifier
//        .clickable {
//            onAppClick(app)
//        }

//    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(10))
                .onFocusChanged {
                    color = if(it.isFocused) Color.White else Color.Transparent
                }
                .border(
                    2.dp,
                    color,
                    shape = RoundedCornerShape(10)
                )
                .clickable {
                    onAppClick(app)
                }

                ,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.width(8.dp))
            Image(
                bitmap = icon,
                contentDescription = "",
                modifier = Modifier.size(70.dp),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

//    }
}
