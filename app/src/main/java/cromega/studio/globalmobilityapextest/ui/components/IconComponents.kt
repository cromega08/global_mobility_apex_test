package cromega.studio.globalmobilityapextest.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

/**
 * [Icon] that render the [Icons.Default].Search image
 */
@Composable
fun SearchIcon() = Icon(imageVector = Icons.Default.Search, contentDescription = Icons.Default.Search.name)

/**
 * [Icon] that render the [Icons.Default].Clear image
 */
@Composable
fun ClearIcon() = Icon(imageVector = Icons.Default.Clear, contentDescription = Icons.Default.Clear.name)

/**
 * [Icon] that render the [Icons.Default].Warning image
 */
@Composable
fun WarningIcon() = Icon(imageVector = Icons.Default.Warning, contentDescription = Icons.Default.Warning.name)
