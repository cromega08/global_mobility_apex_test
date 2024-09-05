package cromega.studio.globalmobilityapextest.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cromega.studio.globalmobilityapextest.R

@Composable
fun DownloadIcon() =
    Icon(
        painter = painterResource(id = R.drawable.download),
        contentDescription = "Download",
        modifier = Modifier.size(24.dp)
    )

@Composable
fun SearchIcon() = Icon(imageVector = Icons.Default.Search, contentDescription = Icons.Default.Search.name)

@Composable
fun ClearIcon() = Icon(imageVector = Icons.Default.Clear, contentDescription = Icons.Default.Clear.name)

@Composable
fun WarningIcon() = Icon(imageVector = Icons.Default.Warning, contentDescription = Icons.Default.Warning.name)
