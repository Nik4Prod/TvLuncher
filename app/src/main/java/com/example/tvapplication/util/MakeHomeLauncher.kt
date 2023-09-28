package com.example.tvapplication.util

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.example.tvapplication.HomeActivity

class MakeHomeLauncher {

    fun setAsDefaultLauncher(context: Context) {
        val packageManager = context.packageManager
        val componentName = ComponentName(context.packageName, HomeActivity::class.java.name)

        packageManager.setComponentEnabledSetting(
            componentName,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        val selector = Intent(Intent.ACTION_MAIN)
        selector.addCategory(Intent.CATEGORY_HOME).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(selector)

        packageManager.setComponentEnabledSetting(
            componentName,
            PackageManager.COMPONENT_ENABLED_STATE_DEFAULT,
            PackageManager.DONT_KILL_APP
        )
    }
}