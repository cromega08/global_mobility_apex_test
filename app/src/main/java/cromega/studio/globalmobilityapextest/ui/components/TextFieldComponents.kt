package cromega.studio.globalmobilityapextest.ui.components

import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

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
    maxLines = 1
)