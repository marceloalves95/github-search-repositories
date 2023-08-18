package br.com.github_search_repositories.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import br.com.github_search_repositories.R
import br.com.github_search_repositories.presentation.model.GithubSearchRepositoriesUIState
import br.com.github_search_repositories.presentation.screen.GithubSearchRepositoriesScreenData
import br.com.github_search_repositories.presentation.screen.GithubSearchRepositoriesScreenError
import br.com.github_search_repositories.presentation.theme.GithubSearchRepositoriesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentScreen()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ContentScreen() {

        var textSearchValue by remember { mutableStateOf("") }
        var shouldShowResult by remember { mutableStateOf(false) }
        mainViewModel.loadAllRepositoriesByUser(textSearchValue)
        val state by mainViewModel.state.collectAsState()

        GithubSearchRepositoriesTheme {
            // A surface container using the 'background' color from the theme
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
                        ConstraintLayout(
                            modifier = Modifier
                                .padding(padding)
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            val guideLine = createGuidelineFromStart(0.60f)
                            val (searchTextField, searchButton, repositoriesText, contentScreen) = createRefs()

                            OutlinedTextField(
                                value = textSearchValue,
                                onValueChange = { textSearchValue = it },
                                modifier = Modifier
                                    .padding(start = 8.dp, top = 8.dp, end = 8.dp)
                                    .wrapContentHeight()
                                    .constrainAs(searchTextField) {
                                        width = Dimension.fillToConstraints
                                        top.linkTo(parent.top)
                                        start.linkTo(parent.start)
                                        end.linkTo(guideLine)
                                    },
                                label = { Text(text = stringResource(id = R.string.search_repository)) },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                                ),
                                trailingIcon = {
                                    when {
                                        textSearchValue.isNotEmpty() -> IconButton(onClick = {
                                            textSearchValue = ""
                                        }) {
                                            Icon(
                                                imageVector = Icons.Filled.Clear,
                                                contentDescription = "Clear"
                                            )
                                        }
                                    }
                                }
                            )

                            Button(
                                onClick = {
                                    mainViewModel.loadAllRepositoriesByUser(textSearchValue)
                                    shouldShowResult = true
                                },
                                modifier = Modifier
                                    .padding(end = 8.dp, start = 8.dp, top = 16.dp)
                                    .constrainAs(searchButton) {
                                        width = Dimension.fillToConstraints
                                        height = Dimension.fillToConstraints
                                        top.linkTo(searchTextField.top)
                                        start.linkTo(guideLine)
                                        end.linkTo(parent.end)
                                        bottom.linkTo(searchTextField.bottom)
                                    },
                                shape = CutCornerShape(5)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    text = stringResource(id = R.string.search),
                                    Modifier.padding(start = 10.dp)
                                )
                            }
                            Text(
                                text = stringResource(id = R.string.repositories),
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .padding(start = 8.dp, end = 8.dp, top = 16.dp)
                                    .constrainAs(repositoriesText) {
                                        width = Dimension.fillToConstraints
                                        top.linkTo(searchTextField.bottom)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                    }
                            )
                            Column(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .padding(top = 8.dp)
                                    .constrainAs(contentScreen) {
                                        width = Dimension.fillToConstraints
                                        top.linkTo(repositoriesText.bottom)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                    }
                            ) {
                                LoadGithubSearchRepositoriesScreen(
                                    isVisible = shouldShowResult,
                                    uiState = state
                                )
                            }
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun LoadGithubSearchRepositoriesScreen(
        isVisible: Boolean,
        uiState: GithubSearchRepositoriesUIState
    ) {
        if (isVisible) {
            when (uiState) {
                is GithubSearchRepositoriesUIState.ScreenData -> GithubSearchRepositoriesScreenData(
                    uiState.screenData
                )

                is GithubSearchRepositoriesUIState.Error -> GithubSearchRepositoriesScreenError(
                    uiState.exception
                )

                else -> Unit
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.no_repository),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}