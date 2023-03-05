package com.anonymous.posts.ui.theme.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anonymous.posts.R
import com.anonymous.posts.ui.theme.PostsTheme

@Composable
fun PostItem(properties: PostUI, onclick: (PostUI) -> Unit) {
    Card(border = BorderStroke(2.dp, Color.LightGray)) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Title(
                    text = properties.title, Modifier.fillMaxWidth(0.9F)
                )

                FavouriteButton(properties.isFavorite) {
                    properties.isFavorite = it
                    onclick.invoke(properties)
                }

            }
            Spacer(modifier = Modifier.height(2.dp))
            Description(text = properties.description)
        }
    }

}

@Composable
private fun Title(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onPrimary
    )
}


@Composable
private fun Description(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.onSecondary
    )
}

@Composable
private fun FavouriteButton(isFavorite: Boolean, onclick: (Boolean) -> (Unit)) {
    var isFavoriteRemember by remember {
        mutableStateOf(isFavorite)
    }
    val imageID = if (isFavoriteRemember) R.drawable.ic_favorite
    else R.drawable.ic_not_favorite
    Image(
        painterResource(imageID),
        contentDescription = "",
        modifier = Modifier
            .size(20.dp)
            .clickable {
                onclick.invoke(!isFavoriteRemember)
                isFavoriteRemember = !isFavoriteRemember
            }
    )
}


data class PostUI(
    val id: Int,
    val title: String,
    val description: String,
    var isFavorite: Boolean
)

@Preview
@Composable
fun PreviewPostItem() {
    PostsTheme() {
        PostItem(
            PostUI(
                1,
                "et ea vero quia laudantium autem",
                "delectus reiciendis molestiae occaecati non minima eveniet qui voluptatibus\\naccusamus in eum beatae sit\\nvel qui neque voluptates ut commodi qui incidunt\\nut animi commodi",
                false,
            ),
            onclick = {

            }
        )
    }
}

@Preview
@Composable
fun PreviewPostItemOnFavorite() {
    PostsTheme() {
        PostItem(
            PostUI(
                1,
                "et ea vero quia laudantium autem",
                "delectus reiciendis molestiae occaecati non minima eveniet qui voluptatibus\\naccusamus in eum beatae sit\\nvel qui neque voluptates ut commodi qui incidunt\\nut animi commodi",
                true,
            ), onclick = {

            }
        )
    }
}