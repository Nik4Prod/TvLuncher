package com.example.tvapplication.ui.component

import android.content.Context
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    val packageManager: PackageManager
): ViewModel(){
    private val _appList = MutableStateFlow<List<ApplicationInfo>>(emptyList())
    val appList: StateFlow<List<ApplicationInfo>>
        get() = _appList

    private val TAG = "ViewModel"

    init {
        onStart()
    }

    private fun onStart(){
        loadApplications()
    }

    private fun loadApplications(){
        // Get a list of all installed applications
        viewModelScope.launch(Dispatchers.IO) {
            _appList.value = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
//            _appList.value.forEach {
//                Log.d(TAG, (it ?: "Null").toString() + "||" + it.packageName ?: "Null")
//            }

            // Filter out system apps if needed
            val userInstalledApps = appList.value.filter {
                it.flags and ApplicationInfo.FLAG_SYSTEM == 0
            }
            val list = appList.value

            val settings = list.find { it.packageName == "com.android.tv.settings" }
            val youtube = list.find { it.packageName == "com.google.android.youtube.tv" }
            val filesApp = list.find { it.packageName == "com.android.documentsui" }
            val playMarket = list.find { it.packageName == "com.android.vending" }
            val webView = list.find { it.packageName == "com.android.vebView" }
            val galery = list.find { it.packageName == "com.android.gallery3d" }
            val memoryManager = list.find { it.packageName == "com.android.storagemanager" }


            val resultList: MutableList<ApplicationInfo> = userInstalledApps.toMutableList()

            settings?.let { resultList.add(it) }
            filesApp?.let { resultList.add(it) }
            youtube?.let { resultList.add(it) }
            playMarket?.let { resultList.add(it) }
            webView?.let { resultList.add(it) }
            galery?.let { resultList.add(it) }
            memoryManager?.let { resultList.add(it) }

            _appList.value = resultList
        }

    }
    fun onAppClick(app: ApplicationInfo, context: Context){
        val intent = packageManager.getLaunchIntentForPackage(app.packageName)
        intent?.apply {
            flags = FLAG_ACTIVITY_NEW_TASK
        }

        if (intent != null) {
            // Start the app using the intent
            context.startActivity(intent)
        } else {
            Log.d("TV APP", "Nothing to open")
            // The app is not installed on the device
            // You can handle this case as needed
        }
    }

}