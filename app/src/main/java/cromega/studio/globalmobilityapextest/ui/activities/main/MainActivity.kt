package cromega.studio.globalmobilityapextest.ui.activities.main

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cromega.studio.globalmobilityapextest.ui.theme.GlobalMobilityApexTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val mainViewModel: MainViewModel =
            MainViewModel(
                connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            )

        setContent {
            GlobalMobilityApexTestTheme {
                Screen(mainViewModel)
            }
        }
    }
}
