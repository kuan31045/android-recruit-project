package `in`.hahow.androidrecruitproject.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import `in`.hahow.androidrecruitproject.domain.model.Course
import `in`.hahow.androidrecruitproject.ui.navigation.Screen
import `in`.hahow.androidrecruitproject.util.LoadingStatus
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import `in`.hahow.android_recruit_project.R
import `in`.hahow.androidrecruitproject.domain.model.CourseStatus
import `in`.hahow.androidrecruitproject.util.PreviewData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    val loadingState = viewModel.loadingState

    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TopAppBar(modifier = modifier, title = {
            Text(text = stringResource(id = Screen.HOME.titleRes))
        })
    }) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (loadingState) {
                is LoadingStatus.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is LoadingStatus.Done -> {
                    CourseColumn(
                        modifier = Modifier.fillMaxSize(), courses = uiState.value.courses
                    )
                }

                is LoadingStatus.Error -> {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(id = loadingState.stringRes)
                    )
                }
            }
        }
    }
}

@Composable
fun CourseColumn(
    modifier: Modifier = Modifier, courses: List<Course>
) {
    LazyColumn(
        modifier = modifier.padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items = courses) { course ->
            CourseItem(
                modifier = Modifier.fillMaxSize(), course = course
            )
        }
    }
}

@Composable
fun CourseItem(
    modifier: Modifier = Modifier, course: Course
) {
    val courseColor = if (course.status == CourseStatus.PUBLISHED) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondary
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .padding(16.dp)
        ) {
            CourseCoverLayout(
                modifier = Modifier
                    .weight(0.35f)
                    .aspectRatio(1.6f)
                    .clip(RoundedCornerShape(8.dp)),
                imageUrl = course.coverImageUrl,
                statusTag = stringResource(id = course.status.stringRes),
                color = courseColor
            )

            Box(
                modifier = Modifier
                    .weight(0.65f)
                    .fillMaxHeight()
                    .padding(start = 16.dp)
            ) {
                //-----Title-----
                Text(
                    modifier = Modifier.align(Alignment.TopStart),
                    text = course.title,
                    maxLines = 2,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )

                //-----Progress Status-----
                Column(modifier = Modifier.align(Alignment.BottomStart)) {
                    Text(
                        color = MaterialTheme.colorScheme.outline,
                        text = when (course.status) {
                            CourseStatus.PUBLISHED -> {
                                stringResource(
                                    id = R.string.progress_published,
                                    course.calProgress()
                                )
                            }

                            CourseStatus.INCUBATING -> {
                                stringResource(
                                    id = R.string.progress_incubating,
                                    course.numSoldTickets, course.successCriteria.numSoldTickets
                                )
                            }

                            CourseStatus.SUCCESS -> {
                                stringResource(id = R.string.progress_success, course.calProgress())
                            }
                        },
                        style = MaterialTheme.typography.labelMedium
                    )
                    LinearProgressIndicator(
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .width(75.dp)
                            .clip(RoundedCornerShape(99.dp)),
                        progress = course.calProgress() / 100f,
                        color = courseColor
                    )
                }

                //-----Count Down-----
                if (course.proposalDueTime != null) {
                    Row(modifier = Modifier.align(Alignment.BottomEnd), verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.size(20.dp).padding(end = 2.dp) ,
                            painter = painterResource(id = R.drawable.ic_timer),
                            contentDescription = null, tint = MaterialTheme.colorScheme.outline)
                        Text(
                            text =
                            if (course.calCountDownDay() < 1) {
                                stringResource(id = R.string.coming_soon)
                            } else {
                                stringResource(id = R.string.count_down, course.calCountDownDay())
                            },
                            color = MaterialTheme.colorScheme.outline,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CourseCoverLayout(
    modifier: Modifier = Modifier, imageUrl: String, statusTag: String, color: Color
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current).data(imageUrl)
                .crossfade(true).build(),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img)
        )

        Surface(
            modifier = Modifier.align(Alignment.BottomEnd),
            shape = RoundedCornerShape(topStart = 8.dp),
            color = color
        ) {
            Text(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                text = statusTag,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 13.sp
            )
        }
    }
}

@Preview
@Composable
fun CourseItemPreview() {
    CourseItem(course = PreviewData.courses.first())
}