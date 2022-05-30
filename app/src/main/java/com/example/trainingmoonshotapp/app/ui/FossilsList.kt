import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.trainingmoonshotapp.app.ui.FossilScreensState
import com.example.trainingmoonshotapp.app.ui.FossilsViewModel
import androidx.compose.material.*
import com.example.trainingmoonshotapp.app.ui.FossilScreens
import com.example.trainingmoonshotapp.domain.Fossil
import com.example.trainingmoonshotapp.ui.theme.*

@Composable
fun FossilCard(
    navController: NavHostController,
    fossil: Fossil
) {
    val name = fossil.name
    val group = fossil.fossil_group
    val image = fossil.image_url
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = rememberAsyncImagePainter(image),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
        )
        Column(
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "Group: $group",
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
fun FossilsCards(
    navController: NavHostController,
    fossils: List<Fossil>
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    LazyColumn {
        items(fossils.size) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(all = 15.dp)
                    .clickable() {
                        selectedChipIndex = it;
                        navController.navigate(FossilScreens.FossilDetails.name + "/${fossils[it].name}")
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue
                        else DarkerButtonBlue
                    )
            )
            {
                FossilCard(navController, fossils[it])
            }
        }
    }
}

@Composable
fun Navbar(navController: NavHostController) {
    Box(modifier = Modifier
        .background(LighterBlue)) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Box(modifier = Modifier
                    .clickable() {
                        navController.navigate(FossilScreens.FossilsList.name)
                    }
                ){
                    Image(
                        painter = rememberAsyncImagePainter("" +
                                "https://dodo.ac/np/images/f/fc/Identified_Fossil_NH_Inv_Icon.png"),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
                Text(
                    text = "Animal Crossing Fossils",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier
                )
            }
        }
    }
}


@Composable
fun FossilList(
    navController: NavHostController,
    viewModel: FossilsViewModel = hiltViewModel()
){
    val state = viewModel.state.collectAsState().value

    trainingmoonshotappTheme {
        Box(modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
        ) {
            when (state) {
                FossilScreensState.Loading ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            color = DarkerButtonBlue,
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                is FossilScreensState.ShowFossilList -> {
                    Column {
                        Navbar(navController)
                        FossilsCards(navController, fossils = state.fossils)
                    }
                }
            }
        }
    }
}

