package com.calyrsoft.ucbp1.features.movie.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    vm: MoviesViewModel = koinViewModel()
) {
    val state by vm.state.collectAsState()

    // (init ya llama observeLocal + refresh)
    // Si quisieras forzar de nuevo:
    // LaunchedEffect(Unit) { vm.refresh() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { vm.refresh() }
        ) { Text("Refrescar (API → DB)") }

        Spacer(Modifier.height(12.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            when (val s = state) {
                is MoviesViewModel.UiState.Init ->
                    Text("Init", modifier = Modifier.align(Alignment.Center))
                is MoviesViewModel.UiState.Loading ->
                    Text("Cargando...", modifier = Modifier.align(Alignment.Center))
                is MoviesViewModel.UiState.Error ->
                    Text("Error: ${s.message}", modifier = Modifier.align(Alignment.Center))
                is MoviesViewModel.UiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(s.movies) { movie ->
                            Card(
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(Modifier.fillMaxWidth().padding(12.dp)) {
                                    AsyncImage(
                                        model = movie.imageUrl,
                                        contentDescription = movie.title,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(140.dp)
                                    )
                                    Spacer(Modifier.height(8.dp))
                                    Text(
                                        text = movie.title,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Start
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
