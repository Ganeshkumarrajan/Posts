package com.anonymous.posts.ui.theme.component.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anonymous.posts.ui.theme.component.items.properties.CommentItemProperties

@Composable
fun CommentItem(properties: CommentItemProperties) {
    Card(border = BorderStroke(0.5.dp, Color.LightGray)) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Title(text = properties.name)
            Email(text = properties.email)
            Spacer(modifier = Modifier.height(3.dp))
            Body(text = properties.body)
        }
    }
}

@Composable
private fun Title(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onSecondary
    )
}

@Composable
private fun Email(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.onSecondary
    )
}

@Composable
private fun Body(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.caption,
        color = MaterialTheme.colors.onSecondary
    )
}

@Preview
@Composable
fun PreviewCommentItem() {
    MaterialTheme() {
        CommentItem(properties = CommentItemProperties(name = "name", "email@com.com", "this is test description line no1 and oline no2"))
    }
}
