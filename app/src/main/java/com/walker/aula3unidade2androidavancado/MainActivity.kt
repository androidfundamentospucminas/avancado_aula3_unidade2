package com.walker.aula3unidade2androidavancado

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlaylistPlay
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import com.walker.aula3unidade2androidavancado.ui.theme.Aula3Unidade2AndroidAvancadoTheme

const val MUSIC_SCREEN: String = "music_screen"
const val PLAYLIST_SCREEN: String = "playlist_screen"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Aula3Unidade2AndroidAvancadoTheme {
                MusicApp()
            }
        }
    }
}

@Composable
fun MusicApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MUSIC_SCREEN ) {
        composable(MUSIC_SCREEN) {
            MusicPlayingScreen(navController)
        }
        composable(PLAYLIST_SCREEN) {
            PlaylistScreen(navController)
        }
    }
}

@Composable
fun PlaylistScreen(navigation: NavController) {

    val context = LocalContext.current

    val playlists = listOf(
        PlaylistItemData("Chill Beats", 4.7f, 1023f),
        PlaylistItemData("Workout Mix", 4.5f, 735f),
        PlaylistItemData("90's Hits", 4.8f, 1260f),
        PlaylistItemData("Study Music", 4.6f, 942f),
        PlaylistItemData("Pop Favorites", 4.3f, 1506f),
        PlaylistItemData("Mega work Music", 4.6f, 942f),
        PlaylistItemData("Dev Favorites", 4.3f, 1506f),
        PlaylistItemData("Anime Music", 4.6f, 942f),
        PlaylistItemData("Rock Starts 70s", 4.3f, 1506f)
    )

    val gradientColors = listOf(
        Color(0xFF010211),
        Color(0xFF0E123A)
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black,
        contentColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(gradientColors))
                .padding(top = 26.dp, start = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navigation.navigateUp()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Text(text = "Playlists", style = MaterialTheme.typography.headlineSmall)
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(playlists) { playlist ->
                    PlaylistItem(item = playlist, onClickItem = {
                        Toast.makeText(context, "Clicked in ${playlist.name}", Toast.LENGTH_SHORT).show()
                    })
                }
            }
        }
    }
}

@Composable
fun PlaylistItem(item: PlaylistItemData, onClickItem: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClickItem)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.PlaylistPlay,
                contentDescription = "Playlist Icon",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = item.name, style = MaterialTheme.typography.headlineSmall, color = Color.White)
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star Icon",
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = item.rating.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Icon(
                        imageVector = Icons.Default.Headphones,
                        contentDescription = "Listener Icon",
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = item.listenersCount.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )

                }
            }
        }
        Divider(
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MusicPlayingScreen(navController: NavController) {

    var isPlaying by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    var isFowarding by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val imageScale by animateDpAsState(targetValue = if (scrollState.value > 0) 90.dp else 240.dp)

    val items = listOf(
        ArtistSong(
            "https://images.pexels.com/photos/154147/pexels-photo-154147.jpeg",
            "Artista Nome 1",
            "Nome dessa Música é 1",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."),
        ArtistSong(
            "https://images.pexels.com/photos/3693108/pexels-photo-3693108.jpeg",
            "Nome 3 Artista",
            "Nome dessa Música é 2",
            "Suspendisse non ipsum nisl. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Proin pellentesque id dui ut bibendum. Pellentesque porta justo eget justo feugiat, quis accumsan metus porta. Quisque consequat eu nisi et molestie. Nunc fringilla fringilla orci, dapibus tempor dolor volutpat eget. Sed at erat quis odio hendrerit volutpat. Donec et rutrum urna. Cras commodo ultricies pellentesque. Nulla suscipit ipsum eu bibendum cursus."),
        ArtistSong(
            "https://images.pexels.com/photos/4406759/pexels-photo-4406759.jpeg",
            "Nome Artista 3",
            "Nome dessa Música é 3",
            "Duis efficitur ante sit amet ante ullamcorper volutpat. Nam finibus dolor at magna condimentum molestie. Nullam cursus ac ex at viverra. Nullam ac justo rutrum, aliquam orci vitae, pulvinar urna. Sed sodales tempus orci, quis interdum odio eleifend non. Quisque dignissim convallis ultrices. Nulla a sollicitudin quam. Donec purus odio, convallis at sollicitudin ut, ultrices non odio. Etiam vehicula turpis in erat blandit, ut eleifend nisi accumsan. Maecenas vel facilisis nibh. Mauris sit amet nunc vel dolor scelerisque egestas. Integer tincidunt odio et rhoncus luctus."),
        ArtistSong(
            "https://images.pexels.com/photos/1714354/pexels-photo-1714354.jpeg",
            "4 Artista Nome",
            "Nome dessa Música é 4",
            "Quisque vehicula a felis sed molestie. Proin non pharetra magna. Pellentesque et gravida eros, non tincidunt elit. Donec vitae massa sem. Sed venenatis sagittis convallis. Aenean quis nunc vel mauris semper faucibus. Fusce eget vulputate mi, eget consequat turpis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Duis ornare eros aliquam, pretium purus sed, condimentum turpis. Sed laoreet efficitur tincidunt."),
        )

    val gradientColors = listOf(
        Color(0xFF01234A),
        Color(0xFF673AB7)
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black,
        contentColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(gradientColors)
                )
                .padding(16.dp)
        ) {
            MenuBar(navController)

            Spacer(modifier = Modifier.height(16.dp))


            AnimatedContent(
                targetState = selectedIndex,
                transitionSpec = {
                    slideInHorizontally(
                        initialOffsetX = { if (isFowarding) 1000 else -1000 },
                        animationSpec = tween(durationMillis = 500)
                    ) with slideOutHorizontally(
                        targetOffsetX = { if (isFowarding) -1000 else 1000 },
                        animationSpec = tween(durationMillis = 500)
                    )
                }
            ) {
                ArtistSection(artistSong = items[selectedIndex], imageScale = imageScale)
            }

            Spacer(modifier = Modifier.height(32.dp))

            SongMenu(
                onPreviousSong = {
                    isFowarding = false
                    if (selectedIndex == 0) {
                        selectedIndex = items.size - 1
                    } else {
                        selectedIndex--
                    }
                },
                onNextSong = {
                    isFowarding = true
                    if (selectedIndex == items.size - 1) {
                        selectedIndex = 0
                    } else {
                        selectedIndex++
                    }
                },
                onPlayPause = {
                    isPlaying = !isPlaying
                },
                isPlaying
            )

            Spacer(modifier = Modifier.height(32.dp))

            LyricsSection(items[selectedIndex].songLyrics, scrollState)
        }
    }
}

@Composable
fun LyricsSection(songLyrics: String, scrollState: ScrollState) {
    Text(
        text = songLyrics,
        style = MaterialTheme.typography.headlineLarge,
        color = Color.White,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState)
    )
}

@Composable 
fun SongMenu(
    onPreviousSong: () -> Unit,
    onNextSong: () -> Unit,
    onPlayPause: () -> Unit,
    isPlaying: Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = {
                onPreviousSong()
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Previous")
            }

            IconButton(onClick = {
                onPlayPause()
            }) {
                Icon(
                    imageVector = if (isPlaying) Icons.Default.PlayArrow else Icons.Default.Pause,
                    contentDescription = if (isPlaying) "Playing" else "Pause")
            }

            IconButton(onClick = {
                onNextSong()
            }) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Next")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LinearProgressIndicator(
            progress = 0.5f,
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
        )
    }
}

@Composable
fun ArtistSection(artistSong: ArtistSong, imageScale: Dp) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SubcomposeAsyncImage(
            model = artistSong.imageUrl,
            contentDescription = "image_description",
            contentScale = ContentScale.Crop,
            loading = {
                CircularProgressIndicator()
            },
            modifier = Modifier
                .size(imageScale)
                .align(Alignment.CenterHorizontally)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = artistSong.songTitle,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = artistSong.artistName,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun MenuBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Music Player", style = MaterialTheme.typography.headlineSmall)
        IconButton(onClick = { navController.navigate(PLAYLIST_SCREEN) }) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "Playlist_Icon_Menu")
        }
    }
}