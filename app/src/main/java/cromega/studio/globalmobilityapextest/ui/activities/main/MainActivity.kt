package cromega.studio.globalmobilityapextest.ui.activities.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cromega.studio.globalmobilityapextest.ui.theme.GlobalMobilityApexTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GlobalMobilityApexTestTheme {
                Screen()
            }
        }
    }
}
