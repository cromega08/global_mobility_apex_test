package cromega.studio.globalmobilityapextest.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

/**
 * [Composable] component to represent a search bar, modifying a [TextField].
 *
 * @param modifier [Modifier] instance to modify the TextField below
 * @param hint [String] that give a hint about the use of the search bar
 * @param query [String] that represent the content of the TextField
 * @param onQueryChange ([String]) -> [Unit] that contains the code to execute
 * when the content is modified
 * @param onSearch [KeyboardActionScope].() -> [Unit] that contains the code to execute
 * when the [KeyboardActions] configured with the [ImeAction.Search] is triggered
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: KeyboardActionScope.() -> Unit
) = TextField(
    modifier = modifier,
    value = query,
    onValueChange = onQueryChange,
    placeholder = { Text(hint) },
    trailingIcon = {
        if (!query.isNullOrBlank())
            IconButton(
                onClick = { onQueryChange("") },
                enabled = true,
                content = { ClearIcon() }
            )
        else SearchIcon()
    },
    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
    keyboardActions = KeyboardActions(onSearch = onSearch),
    singleLine = true,
    maxLines = 1,
    shape = RoundedCornerShape(50.dp),
    colors = textFieldColors(
        focusedIndicatorColor = Color.Transparent, // Remove the underline when focused
        unfocusedIndicatorColor = Color.Transparent // Remove the underline when unfocused
    )
)