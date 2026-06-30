package com.example.task_2_android_basic.tutorial

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// * Main Dashboard combining basic layouts, modifiers, state and common components.
// ? Also provides tabs to switch between different lesson views.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningDashboardApp() {
    // * State management for navigation tabs
    var currentTab by remember { mutableStateOf(0) }

    // * State management for dark theme
    var isDarkMode by remember { mutableStateOf(false) }

    val backgroundColor by animateColorAsState(
        targetValue = if (isDarkMode) Color(0xFF121212) else Color(0xFFF7F9FC),
        animationSpec = tween(durationMillis = 300),
        label = "bgTransition"
    )
    val textColor by animateColorAsState(
        targetValue = if (isDarkMode) Color.White else Color(0xFF1E293B),
        animationSpec = tween(durationMillis = 300),
        label = "textTransition"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Compose Academy",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = if (isDarkMode) Color.White else Color.Black
                    )
                },
                navigationIcon = {
                    if (currentTab != 0) {
                        IconButton(onClick = { currentTab = 0 }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = if (isDarkMode) Color.White else Color.Black
                            )
                        }
                    } else {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = if (isDarkMode) Color.White else Color.Black
                            )
                        }
                    }
                },
                actions = {
                    // * Toggle button for theme selection
                    Text(
                        text = if (isDarkMode) "Tối" else "Sáng",
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isDarkMode) Color(0xFF334155) else Color(0xFFE2E8F0))
                            .clickable { isDarkMode = !isDarkMode }
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isDarkMode) Color.White else Color(0xFF334155)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = if (isDarkMode) Color(0xFF1E1E1E) else Color.White
                )
            )
        },
        bottomBar = {
            // * Bottom navigation bar
            NavigationBar(
                containerColor = if (isDarkMode) Color(0xFF1E1E1E) else Color.White
            ) {
                NavigationBarItem(
                    selected = currentTab == 0,
                    onClick = { currentTab = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Profile", fontSize = 11.sp) }
                )
                NavigationBarItem(
                    selected = currentTab == 1,
                    onClick = { currentTab = 1 },
                    icon = { Icon(Icons.Default.Info, contentDescription = null) },
                    label = { Text("L1: Layout", fontSize = 11.sp) }
                )
                NavigationBarItem(
                    selected = currentTab == 2,
                    onClick = { currentTab = 2 },
                    icon = { Icon(Icons.Default.Create, contentDescription = null) },
                    label = { Text("L2: Modifier", fontSize = 11.sp) }
                )
                NavigationBarItem(
                    selected = currentTab == 3,
                    onClick = { currentTab = 3 },
                    icon = { Icon(Icons.Default.Star, contentDescription = null) },
                    label = { Text("L3: State", fontSize = 11.sp) }
                )
                NavigationBarItem(
                    selected = currentTab == 4,
                    onClick = { currentTab = 4 },
                    icon = { Icon(Icons.Default.Notifications, contentDescription = null) },
                    label = { Text("L4: Comp", fontSize = 11.sp) }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(innerPadding)
        ) {
            when (currentTab) {
                0 -> MainDemoProfileScreen(isDarkMode = isDarkMode, textColor = textColor)
                1 -> Lesson1LayoutsScreen()
                2 -> ModifierBasicsScreen()
                3 -> StateBasicsScreen()
                4 -> ComponentsBasicsScreen()
            }
        }
    }
}

// * Profile screen UI representation
@Composable
fun MainDemoProfileScreen(isDarkMode: Boolean, textColor: Color) {
    // ? Tracks follow state
    var isFollowing by remember { mutableStateOf(false) }
    val followersCount = if (isFollowing) 1248 else 1247

    // ? Tracks skill tag inputs
    var tagInputText by remember { mutableStateOf("") }
    val tagsList = remember {
        mutableStateListOf("Jetpack Compose", "Android", "Kotlin", "Flutter", "UI/UX")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(androidx.compose.foundation.rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // * Profile Header Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 6.dp, shape = RoundedCornerShape(24.dp)),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isDarkMode) Color(0xFF1E293B) else Color.White
            )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(Color(0xFF3F51B5), Color(0xFF00BCD4))
                            )
                        )
                ) {
                    Text(
                        text = "NEWBIE DEVELOPER",
                        color = Color.White.copy(alpha = 0.6f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                ) {
                    // * Circular Avatar
                    Box(
                        modifier = Modifier
                            .offset(y = (-50).dp)
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(Color(0xFFFF4081), Color(0xFFFF9100))
                                )
                            )
                            .border(width = 4.dp, color = if (isDarkMode) Color(0xFF1E293B) else Color.White, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "K", // * Initials
                            color = Color.White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // * User Name & Description
                    Text(
                        text = "Nguyễn Quốc Khánh",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor,
                        modifier = Modifier.offset(y = (-40).dp)
                    )

                    Text(
                        text = "Học viên Android Basic & Jetpack Compose",
                        fontSize = 14.sp,
                        color = if (isDarkMode) Color.LightGray else Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .offset(y = (-35).dp)
                            .padding(horizontal = 24.dp)
                    )

                    // * Statistics Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .offset(y = (-20).dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatItem(number = "$followersCount", label = "Followers", textColor = textColor)
                        StatItem(number = "42", label = "Bài học", textColor = textColor)
                        StatItem(number = "9.8", label = "Điểm số", textColor = textColor)
                    }

                    // * Actions Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .offset(y = (-10).dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = { isFollowing = !isFollowing },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isFollowing) Color(0xFF4CAF50) else Color(0xFF3F51B5)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            if (isFollowing) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Đã Follow")
                            } else {
                                Text("Follow")
                            }
                        }

                        OutlinedButton(
                            onClick = { },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = if (isDarkMode) Color.White else Color(0xFF3F51B5)
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Liên hệ")
                        }
                    }
                }
            }
        }

        SpacerHeight(24)

        // * Skills Section Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Kỹ năng & Sở thích",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = textColor
            )
            Text(
                text = "${tagsList.size} Tags",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        SpacerHeight(8)

        // * LazyRow for Skill tags
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tagsList) { tag ->
                TagChip(text = tag, isDarkMode = isDarkMode)
            }
        }

        SpacerHeight(16)

        // * Dynamic tag input card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isDarkMode) Color(0xFF334155).copy(alpha = 0.3f) else Color(0xFFE2E8F0).copy(alpha = 0.4f)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = tagInputText,
                    onValueChange = { tagInputText = it },
                    placeholder = { Text("Thêm kỹ năng...", fontSize = 14.sp) },
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = textColor,
                        unfocusedTextColor = textColor
                    )
                )

                Spacer(modifier = Modifier.width(12.dp))

                IconButton(
                    onClick = {
                        if (tagInputText.isNotBlank()) {
                            tagsList.add(tagInputText.trim())
                            tagInputText = ""
                        }
                    },
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0xFF00BCD4), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Thêm tag",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun StatItem(number: String, label: String, textColor: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = number,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun TagChip(text: String, isDarkMode: Boolean) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(if (isDarkMode) Color(0xFF334155) else Color(0xFFE2E8F0))
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = if (isDarkMode) Color.White else Color(0xFF1E293B)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainDemoPreview() {
    LearningDashboardApp()
}
