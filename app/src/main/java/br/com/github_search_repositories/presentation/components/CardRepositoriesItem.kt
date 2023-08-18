package br.com.github_search_repositories.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import br.com.github_search_repositories.presentation.mock.dummyGithubSearchRepositories

@Composable
fun CardRepositoriesItem(
    title: String?,
    shareRepository: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White),
        ) {

            val (titleText, shareIcon) = createRefs()

            Text(
                text = title ?: "",
                modifier = Modifier
                    .constrainAs(titleText) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(shareIcon.start)
                        width = Dimension.fillToConstraints
                    }
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    )
                    .wrapContentHeight()
            )

            IconButton(
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .padding(top = 16.dp, bottom = 16.dp, end = 16.dp)
                    .constrainAs(shareIcon) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                onClick = {
                    shareRepository.invoke()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun CardRepositoriesItemPreview() {
    CardRepositoriesItem(
        title = dummyGithubSearchRepositories.name,
        shareRepository = {

        }
    )
}