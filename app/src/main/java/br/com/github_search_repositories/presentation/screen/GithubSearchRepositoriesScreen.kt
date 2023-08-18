package br.com.github_search_repositories.presentation.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import br.com.github_search_repositories.R
import br.com.github_search_repositories.domain.models.GithubSearchRepositories
import br.com.github_search_repositories.presentation.components.CardRepositoriesItem
import br.com.github_search_repositories.presentation.mock.dummyGithubSearchRepositories
import br.com.github_search_repositories.presentation.theme.GithubSearchRepositoriesTheme

@Composable
fun GithubSearchRepositoriesScreenData(
    repositories: List<GithubSearchRepositories>
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(
            items = repositories,
            itemContent = { content ->
                CardRepositoriesItem(
                    title = content.name
                ) {
                    shareRepository(content.htmlUrl, context)
                }
            }
        )
    }
}

fun shareRepository(url: String?, context: Context) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Share Project")
    startActivity(context, shareIntent, null)
}

@Composable
fun GithubSearchRepositoriesScreenError(exception: Throwable?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = exception?.message ?: stringResource(id = R.string.error_repository),
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GithubSearchRepositoriesScreenDataPreview() {
    GithubSearchRepositoriesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = stringResource(id = R.string.app_name)) },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary,
                            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                },
                content = { padding ->
                    Column(modifier = Modifier.padding(padding)) {
                        GithubSearchRepositoriesScreenData(
                            listOf(dummyGithubSearchRepositories)
                        )
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GithubSearchRepositoriesScreenErrorPreview() {
    GithubSearchRepositoriesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = stringResource(id = R.string.app_name)) },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary,
                            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                },
                content = { padding ->
                    Column(modifier = Modifier.padding(padding)) {
                        GithubSearchRepositoriesScreenError(Throwable())
                    }
                }
            )
        }
    }
}