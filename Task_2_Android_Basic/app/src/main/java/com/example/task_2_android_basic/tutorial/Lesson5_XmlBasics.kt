package com.example.task_2_android_basic.tutorial

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task_2_android_basic.R
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// ==========================================
// DATA MODELS FOR INTENT SIMULATION
// ==========================================
data class UserParcelable(val name: String, val age: Int) : Parcelable {
    constructor(parcel: android.os.Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: android.os.Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<UserParcelable> {
        override fun createFromParcel(parcel: android.os.Parcel): UserParcelable = UserParcelable(parcel)
        override fun newArray(size: Int): Array<UserParcelable?> = arrayOfNulls(size)
    }
}

data class UserSerializable(val name: String, val age: Int) : Serializable

// ==========================================
// RECYCLERVIEW DATA & ADAPTER
// ==========================================
data class XmlLessonItem(val title: String, val desc: String, val iconRes: Int)

class XmlLessonAdapter(
    private val items: List<XmlLessonItem>,
    private val onItemClick: (XmlLessonItem) -> Unit
) : RecyclerView.Adapter<XmlLessonAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvItemTitle)
        val desc: TextView = view.findViewById(R.id.tvItemDesc)
        val icon: ImageView = view.findViewById(R.id.ivItemIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lesson5_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        holder.desc.text = item.desc
        holder.icon.setImageResource(item.iconRes)
        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount() = items.size
}

// ==========================================
// MAIN TUTORIAL SCREEN
// ==========================================
@Composable
fun Lesson5XmlBasicsScreen() {
    var selectedSubTab by remember { mutableStateOf(0) }
    val subTabs = listOf("Lifecycle", "Layouts", "Views", "Intents", "Events")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text(
            text = "Lesson 5: Android XML & View System",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp),
            color = MaterialTheme.colorScheme.primary
        )

        // Sub-tabs navigation bar
        ScrollableTabRow(
            selectedTabIndex = selectedSubTab,
            edgePadding = 0.dp,
            containerColor = Color.Transparent,
            modifier = Modifier.fillMaxWidth()
        ) {
            subTabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedSubTab == index,
                    onClick = { selectedSubTab = index },
                    text = { Text(title, fontSize = 13.sp, fontWeight = FontWeight.Bold) }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Tab views switcher
        Box(modifier = Modifier.weight(1f)) {
            when (selectedSubTab) {
                0 -> LifecycleSimulatorView()
                1 -> LayoutSandboxView()
                2 -> ViewsDemoView()
                3 -> IntentSimulatorView()
                4 -> EventHandlingView()
            }
        }
    }
}

// ==========================================
// 1. LIFECYCLE SIMULATOR VIEW
// ==========================================
@Composable
fun LifecycleSimulatorView() {
    var isActivityMode by remember { mutableStateOf(true) }
    val logs = remember { mutableStateListOf<String>() }
    val timeFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())

    fun addLog(msg: String) {
        val time = timeFormat.format(Date())
        logs.add(0, "[$time] $msg")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Mode Selector (Activity vs Fragment)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    isActivityMode = true
                    logs.clear()
                    addLog("--- Bắt đầu giám sát Activity Lifecycle ---")
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isActivityMode) MaterialTheme.colorScheme.primary else Color.Gray
                )
            ) {
                Text("Activity Lifecycle")
            }
            Button(
                onClick = {
                    isActivityMode = false
                    logs.clear()
                    addLog("--- Bắt đầu giám sát Fragment Lifecycle ---")
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!isActivityMode) MaterialTheme.colorScheme.primary else Color.Gray
                )
            ) {
                Text("Fragment Lifecycle")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Visual Lifecycle Diagram Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isActivityMode) "Sơ đồ vòng đời Activity" else "Sơ đồ vòng đời Fragment",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                if (isActivityMode) {
                    LifecycleStep("onCreate()", "Khởi tạo View, khôi phục trạng thái (Bundle)")
                    LifecycleArrow()
                    LifecycleStep("onStart()", "Màn hình chuẩn bị hiển thị với người dùng")
                    LifecycleArrow()
                    LifecycleStep("onResume()", "Màn hình bắt đầu chạy, sẵn sàng nhận tương tác")
                    LifecycleArrow()
                    LifecycleStep("onPause()", "Mất tiêu điểm (bị che một phần, vd: Dialog)")
                    LifecycleArrow()
                    LifecycleStep("onStop()", "Bị che khuất hoàn toàn (sang màn hình khác, Home)")
                    LifecycleArrow()
                    LifecycleStep("onDestroy()", "Giải phóng tài nguyên, hủy bỏ hoàn toàn")
                } else {
                    LifecycleStep("onAttach()", "Fragment được gắn kết vào Host Activity")
                    LifecycleArrow()
                    LifecycleStep("onCreateView()", "Inflate XML layout và tạo cây View")
                    LifecycleArrow()
                    LifecycleStep("onViewCreated()", "View được tạo thành công, thiết lập listeners")
                    LifecycleArrow()
                    LifecycleStep("onStart() / onResume()", "Fragment hiển thị và sẵn sàng hoạt động")
                    LifecycleArrow()
                    LifecycleStep("onPause() / onStop()", "Fragment đi vào background")
                    LifecycleArrow()
                    LifecycleStep("onDestroyView()", "Hủy cây View liên kết với Fragment")
                    LifecycleArrow()
                    LifecycleStep("onDetach()", "Gỡ Fragment hoàn toàn khỏi Activity")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Interactive Simulation Controls
        Text(
            text = "Giả lập Sự Kiện Hệ Thống:",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = {
                if (isActivityMode) {
                    addLog("onCreate() -> onStart() -> onResume()")
                } else {
                    addLog("onAttach() -> onCreate() -> onCreateView() -> onViewCreated() -> onStart() -> onResume()")
                }
            }) {
                Text("Mở Screen", fontSize = 12.sp)
            }

            Button(onClick = {
                if (isActivityMode) {
                    addLog("onPause() -> onStop()")
                } else {
                    addLog("onPause() -> onStop()")
                }
            }) {
                Text("Home (Background)", fontSize = 12.sp)
            }

            Button(onClick = {
                if (isActivityMode) {
                    addLog("onRestart() -> onStart() -> onResume()")
                } else {
                    addLog("onStart() -> onResume()")
                }
            }) {
                Text("Quay lại App", fontSize = 12.sp)
            }

            Button(onClick = {
                addLog("onPause() -> onStop() -> onDestroy() -> [Recreate] -> onCreate() -> onStart() -> onResume()")
            }) {
                Text("Xoay màn hình", fontSize = 12.sp)
            }

            Button(onClick = {
                addLog("onPause()  [Màn hình mờ đi]")
            }) {
                Text("Hiện Dialog", fontSize = 12.sp)
            }

            Button(onClick = {
                if (isActivityMode) {
                    addLog("onPause() -> onStop() -> onDestroy()")
                } else {
                    addLog("onPause() -> onStop() -> onDestroyView() -> onDestroy() -> onDetach()")
                }
            }) {
                Text("Đóng Screen", fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Realtime Console Logs
        Text(
            text = "Nhật ký giả lập (Logs):",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF1E1E1E))
                .border(1.dp, Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            if (logs.isEmpty()) {
                Text(
                    "Hãy nhấn các nút sự kiện bên trên để xem log...",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(logs) { log ->
                        val color = when {
                            log.contains("create", true) || log.contains("attach", true) -> Color(0xFF4CAF50)
                            log.contains("resume", true) || log.contains("start", true) -> Color(0xFF00BCD4)
                            log.contains("pause", true) || log.contains("stop", true) -> Color(0xFFFFC107)
                            log.contains("destroy", true) || log.contains("detach", true) -> Color(0xFFF44336)
                            else -> Color.White
                        }
                        Text(
                            text = log,
                            color = color,
                            fontSize = 12.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LifecycleStep(name: String, desc: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(110.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.colorScheme.primary)
                .padding(vertical = 6.dp, horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = desc,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun LifecycleArrow() {
    Box(
        modifier = Modifier
            .padding(vertical = 2.dp)
            .width(2.dp)
            .height(12.dp)
            .background(Color.Gray.copy(alpha = 0.5f))
    )
}

// ==========================================
// 2. LAYOUT SANDBOX VIEW
// ==========================================
@Composable
fun LayoutSandboxView() {
    var selectedLayoutType by remember { mutableStateOf("LinearLayout (Dọc)") }
    val layoutTypes = listOf("LinearLayout (Dọc)", "LinearLayout (Ngang)", "FrameLayout", "ConstraintLayout")

    val xmlCode = when (selectedLayoutType) {
        "LinearLayout (Dọc)" -> """
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp">
    
    <TextView ... android:text="Item 1" android:background="#FFBB86FC"/>
    <TextView ... android:text="Item 2" android:background="#FF6200EE"/>
    <TextView ... android:text="Item 3" android:background="#FF03DAC5"/>
</LinearLayout>
        """.trimIndent()
        "LinearLayout (Ngang)" -> """
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:padding="16dp">
    
    <TextView ... android:layout_weight="1" android:text="Item 1"/>
    <TextView ... android:layout_weight="1" android:text="Item 2"/>
    <TextView ... android:layout_weight="1" android:text="Item 3"/>
</LinearLayout>
        """.trimIndent()
        "FrameLayout" -> """
<FrameLayout
    android:layout_width="match_parent"
    android:layout_height="200dp"
    android:padding="16dp">
    
    <View ... android:layout_width="match_parent" android:background="#FF3700B3"/>
    <View ... android:layout_width="150dp" android:layout_gravity="center" android:background="#FF03DAC5"/>
    <TextView ... android:layout_gravity="center" android:text="Chồng lớp"/>
</FrameLayout>
        """.trimIndent()
        else -> """
<androidx.constraintlayout.widget.ConstraintLayout
    android:layout_width="match_parent"
    android:layout_height="220dp"
    android:padding="16dp">

    <TextView ... app:layout_constraintTop_toTopOf="parent" app:layout_constraintStart_toStartOf="parent" app:layout_constraintEnd_toEndOf="parent" />
    <Button ... app:layout_constraintStart_toStartOf="parent" app:layout_constraintTop_toBottomOf="@id/tvHeader" />
    <Button ... app:layout_constraintStart_toEndOf="@id/btnLeft" app:layout_constraintEnd_toStartOf="@id/btnRight" />
    <Button ... app:layout_constraintEnd_toEndOf="parent" app:layout_constraintTop_toBottomOf="@id/tvHeader" />
</androidx.constraintlayout.widget.ConstraintLayout>
        """.trimIndent()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Layout Type Selector Buttons
        Text("Chọn loại Layout XML cần xem:", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(6.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            layoutTypes.forEach { type ->
                Text(
                    text = type,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (selectedLayoutType == type) MaterialTheme.colorScheme.primary else Color.Gray.copy(alpha = 0.3f))
                        .clickable { selectedLayoutType = type }
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    color = if (selectedLayoutType == type) Color.White else MaterialTheme.colorScheme.onSurface,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Render Panel using AndroidView
        Text("Giao diện Render thực tế (Android XML):", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
        ) {
            AndroidView(
                modifier = Modifier.fillMaxWidth(),
                factory = { context ->
                    val layoutInflater = LayoutInflater.from(context)
                    when (selectedLayoutType) {
                        "LinearLayout (Dọc)", "LinearLayout (Ngang)" -> {
                            val view = layoutInflater.inflate(R.layout.lesson5_layout_linear, null) as LinearLayout
                            view
                        }
                        "FrameLayout" -> {
                            layoutInflater.inflate(R.layout.lesson5_layout_frame, null)
                        }
                        else -> {
                            layoutInflater.inflate(R.layout.lesson5_layout_constraint, null)
                        }
                    }
                },
                update = { view ->
                    if (view is LinearLayout) {
                        if (selectedLayoutType == "LinearLayout (Ngang)") {
                            view.orientation = LinearLayout.HORIZONTAL
                            for (i in 0 until view.childCount) {
                                val child = view.getChildAt(i)
                                val params = child.layoutParams as LinearLayout.LayoutParams
                                params.width = 0
                                params.weight = 1f
                                child.layoutParams = params
                            }
                        } else {
                            view.orientation = LinearLayout.VERTICAL
                            for (i in 0 until view.childCount) {
                                val child = view.getChildAt(i)
                                val params = child.layoutParams as LinearLayout.LayoutParams
                                params.width = LinearLayout.LayoutParams.MATCH_PARENT
                                params.weight = 0f
                                child.layoutParams = params
                            }
                        }
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Code XML Preview Card
        Text("Mã nguồn XML cấu hình:", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(6.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2D3748))
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = xmlCode,
                    color = Color(0xFFFFD700),
                    fontFamily = FontFamily.Monospace,
                    fontSize = 11.sp,
                    lineHeight = 15.sp
                )
            }
        }
    }
}

// ==========================================
// 3. VIEWS & RECYCLERVIEW DEMO
// ==========================================
@Composable
fun ViewsDemoView() {
    val context = LocalContext.current
    var widgetClicks by remember { mutableStateOf(0) }

    // Mock data for RecyclerView
    val items = remember {
        listOf(
            XmlLessonItem("TextView", "Hiển thị văn bản dạng tĩnh hoặc động", android.R.drawable.ic_menu_edit),
            XmlLessonItem("Button", "Lắng nghe sự kiện click từ người dùng", android.R.drawable.ic_menu_send),
            XmlLessonItem("ImageView", "Hiển thị ảnh từ assets, drawables hoặc URL", android.R.drawable.ic_menu_gallery),
            XmlLessonItem("RecyclerView", "Hiển thị danh sách cuộn mượt mà hiệu năng cao", android.R.drawable.ic_menu_agenda),
            XmlLessonItem("CardView", "Tạo bo góc và hiệu ứng đổ bóng cho phần tử", android.R.drawable.ic_menu_view)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Traditional XML Widgets Section
        Text("Thành phần View Cơ Bản (TextView, ImageView, Button):", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(6.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            AndroidView(
                modifier = Modifier.fillMaxWidth(),
                factory = { ctx ->
                    val view = LayoutInflater.from(ctx).inflate(R.layout.lesson5_views_demo, null)
                    
                    val tvDemo = view.findViewById<TextView>(R.id.tvDemo)
                    val btnDemo = view.findViewById<Button>(R.id.btnDemo)
                    val ivDemo = view.findViewById<ImageView>(R.id.ivDemo)

                    btnDemo.setOnClickListener {
                        widgetClicks++
                        tvDemo.text = "Bạn đã nhấn Button $widgetClicks lần!"
                        Toast.makeText(ctx, "Button clicked!", Toast.LENGTH_SHORT).show()
                    }

                    ivDemo.setOnClickListener {
                        Toast.makeText(ctx, "ImageView clicked!", Toast.LENGTH_SHORT).show()
                    }

                    view
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // RecyclerView Demo Section
        Text("RecyclerView Cổ Điển (Hiển thị danh sách cuộn):", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .border(1.dp, Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
        ) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { ctx ->
                    val recyclerView = RecyclerView(ctx)
                    recyclerView.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    recyclerView.layoutManager = LinearLayoutManager(ctx)
                    
                    recyclerView.adapter = XmlLessonAdapter(items) { item ->
                        Toast.makeText(ctx, "Đã chọn: ${item.title}", Toast.LENGTH_SHORT).show()
                    }
                    recyclerView
                }
            )
        }
    }
}

// ==========================================
// 4. INTENT & BUNDLE SIMULATOR VIEW
// ==========================================
@Composable
fun IntentSimulatorView() {
    var inputName by remember { mutableStateOf("") }
    var inputAge by remember { mutableStateOf("") }
    var selectSerialization by remember { mutableStateOf("Bundle (Primitives)") }
    
    // Result State
    var showMockActivity by remember { mutableStateOf(false) }
    var receivedDataBundle by remember { mutableStateOf<Bundle?>(null) }

    val serializationOptions = listOf("Bundle (Primitives)", "Parcelable Object", "Serializable Object")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text("Mô phỏng Truyền Dữ Liệu bằng Intent & Bundle:", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))

        // Input forms
        OutlinedTextField(
            value = inputName,
            onValueChange = { inputName = it },
            label = { Text("Nhập Tên (String)") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = inputAge,
            onValueChange = { inputAge = it },
            label = { Text("Nhập Tuổi (Int)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Dropdown type
        Text("Chọn cách đóng gói dữ liệu:", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            serializationOptions.forEach { option ->
                Text(
                    text = option,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (selectSerialization == option) MaterialTheme.colorScheme.primary else Color.Gray.copy(alpha = 0.2f))
                        .clickable { selectSerialization = option }
                        .padding(vertical = 8.dp),
                    color = if (selectSerialization == option) Color.White else MaterialTheme.colorScheme.onSurface,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val ageInt = inputAge.toIntOrNull() ?: 18
                val nameStr = if (inputName.isBlank()) "Khách" else inputName

                val bundle = Bundle()
                when (selectSerialization) {
                    "Bundle (Primitives)" -> {
                        bundle.putString("EXTRA_NAME", nameStr)
                        bundle.putInt("EXTRA_AGE", ageInt)
                    }
                    "Parcelable Object" -> {
                        val user = UserParcelable(nameStr, ageInt)
                        bundle.putParcelable("EXTRA_USER", user)
                    }
                    "Serializable Object" -> {
                        val user = UserSerializable(nameStr, ageInt)
                        bundle.putSerializable("EXTRA_USER", user)
                    }
                }
                receivedDataBundle = bundle
                showMockActivity = true
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Gửi Intent (Giả lập sang Màn hình khác)")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Target Mock Activity Rendering
        if (showMockActivity) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color(0xFF4CAF50), RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "TargetActivity [Đã nhận Intent]",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2E7D32),
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Đóng",
                            color = Color.Red,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            modifier = Modifier.clickable { showMockActivity = false }
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Parse & show received data
                    val name: String
                    val age: Int
                    when (selectSerialization) {
                        "Bundle (Primitives)" -> {
                            name = receivedDataBundle?.getString("EXTRA_NAME") ?: ""
                            age = receivedDataBundle?.getInt("EXTRA_AGE") ?: 0
                        }
                        "Parcelable Object" -> {
                            val user = receivedDataBundle?.getParcelable<UserParcelable>("EXTRA_USER")
                            name = user?.name ?: ""
                            age = user?.age ?: 0
                        }
                        else -> {
                            val user = receivedDataBundle?.getSerializable("EXTRA_USER") as? UserSerializable
                            name = user?.name ?: ""
                            age = user?.age ?: 0
                        }
                    }

                    Text(
                        text = "1. Dữ liệu giải nén từ Bundle:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Color.Black
                    )
                    Text("• Tên: $name", fontSize = 14.sp, color = Color.DarkGray, modifier = Modifier.padding(start = 12.dp))
                    Text("• Tuổi: $age", fontSize = 14.sp, color = Color.DarkGray, modifier = Modifier.padding(start = 12.dp))

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "2. Mã nguồn Kotlin nhận dữ liệu:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Color.Black
                    )

                    val codeSample = when (selectSerialization) {
                        "Bundle (Primitives)" -> """
val name = intent.getStringExtra("EXTRA_NAME")
val age = intent.getIntExtra("EXTRA_AGE", 0)
                        """.trimIndent()
                        "Parcelable Object" -> """
// Parcelable truyền cực nhanh, khuyên dùng trên Android
val user = intent.getParcelableExtra<UserParcelable>("EXTRA_USER")
val name = user?.name
val age = user?.age
                        """.trimIndent()
                        else -> """
// Serializable dùng reflection của Java, chậm hơn
val user = intent.getSerializableExtra("EXTRA_USER") as? UserSerializable
val name = user?.name
val age = user?.age
                        """.trimIndent()
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        shape = RoundedCornerShape(6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF263238))
                    ) {
                        Text(
                            text = codeSample,
                            color = Color(0xFF80DEEA),
                            fontFamily = FontFamily.Monospace,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

// ==========================================
// 5. EVENT HANDLING VIEW
// ==========================================
@Composable
fun EventHandlingView() {
    val eventLogs = remember { mutableStateListOf<String>() }
    val timeFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())

    fun logEvent(msg: String) {
        val time = timeFormat.format(Date())
        eventLogs.add(0, "[$time] $msg")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text("Tương tác các View để ghi nhận Sự Kiện (Events):", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))

        // Touch event sandbox box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f))
        ) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { ctx ->
                    val frameLayout = LinearLayout(ctx)
                    frameLayout.orientation = LinearLayout.VERTICAL
                    frameLayout.gravity = android.view.Gravity.CENTER

                    val tvInfo = TextView(ctx)
                    tvInfo.text = "Chạm, Nhấn và Rê tay trong ô này để Test onTouch()"
                    tvInfo.setTextColor(android.graphics.Color.DKGRAY)
                    tvInfo.gravity = android.view.Gravity.CENTER
                    tvInfo.textSize = 15f
                    frameLayout.addView(tvInfo)

                    val btnEvent = Button(ctx)
                    btnEvent.text = "Click / Long Click Me!"
                    btnEvent.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        topMargin = 20
                    }
                    frameLayout.addView(btnEvent)

                    // Event Handling setup
                    btnEvent.setOnClickListener {
                        logEvent("OnClickListener: Đã CLICK vào Button")
                    }
                    btnEvent.setOnLongClickListener {
                        logEvent("OnLongClickListener: Đã LONG CLICK vào Button")
                        true
                    }

                    frameLayout.setOnTouchListener { _, event ->
                        val x = event.x.toInt()
                        val y = event.y.toInt()
                        when (event.action) {
                            MotionEvent.ACTION_DOWN -> {
                                logEvent("onTouch: ACTION_DOWN tại vị trí ($x, $y)")
                            }
                            MotionEvent.ACTION_MOVE -> {
                                // Limit logging to prevent spam
                                if (Math.random() < 0.15) {
                                    logEvent("onTouch: ACTION_MOVE tại vị trí ($x, $y)")
                                }
                            }
                            MotionEvent.ACTION_UP -> {
                                logEvent("onTouch: ACTION_UP tại vị trí ($x, $y)")
                            }
                        }
                        true
                    }

                    frameLayout
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Output logger
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Danh sách Event nhận được:", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(
                text = "Xóa Log",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                modifier = Modifier.clickable { eventLogs.clear() }
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF212121))
                .padding(8.dp)
        ) {
            if (eventLogs.isEmpty()) {
                Text(
                    "Hãy chạm/rê tay trong vùng kiểm thử ở trên...",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(eventLogs) { log ->
                        val color = when {
                            log.contains("CLICK", true) -> Color(0xFF81C784)
                            log.contains("DOWN", true) -> Color(0xFF64B5F6)
                            log.contains("UP", true) -> Color(0xFFFFB74D)
                            else -> Color(0xFFE0E0E0)
                        }
                        Text(
                            text = log,
                            color = color,
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        }
    }
}
