package control_folow
// =========================================================================
// 4. CẤU TRÚC ĐIỀU KHIỂN & VÒNG LẶP (CONTROL FLOW)
// =========================================================================

fun controlFlow() {
    println("--- CẤU TRÚC ĐIỀU KHIỂN (IF-ELSE, WHEN) ---")

    val score = 85

    // --- 4.1. If - Else dạng Biểu thức (Expression) ---
    // Trong Kotlin, bạn có thể gán trực tiếp kết quả của if-else vào một biến.
    // (Thay thế cho toán tử 3 ngôi `condition ? true : false` trong Java/Dart)
    val grade = if (score >= 90) {
        "Xuất sắc"
    } else if (score >= 80) {
        "Giỏi"
    } else {
        "Trung bình/Khá"
    }
    println("Xếp loại bằng If-Else: $grade")


    // --- 4.2. Cấu trúc `when` (Thay thế hoàn hảo cho switch-case) ---
    // `when` trong Kotlin rất mạnh mẽ, có thể kiểm tra khoảng giá trị, kiểu dữ liệu,...
    val x = 2
    when (x) {
        1 -> println("x là 1")
        2, 3 -> println("x là 2 hoặc 3") // Gộp nhiều điều kiện bằng dấu phẩy
        in 4..10 -> println("x nằm trong khoảng từ 4 đến 10") // Kiểm tra thuộc một khoảng (Range)
        else -> println("x là số khác") // Tương đương `default` trong switch-case
    }

    // `when` dạng Biểu thức (Trả về giá trị để gán vào biến)
    val evaluation = when (score) {
        in 90..100 -> "A"
        in 80..89 -> "B"
        in 70..79 -> "C"
        else -> "F"
    }
    println("Xếp loại bằng When: $evaluation")


    println("\n--- CÁC LOẠI VÒNG LẶP (FOR, WHILE, DO-WHILE) ---")

    // --- 4.3. Vòng lặp For và Các kiểu khoảng giá trị (Ranges) ---

    // Tăng dần từ 1 đến 5 (bao gồm cả 5) -> 1 2 3 4 5
    print("For (1..5): ")
    for (i in 1..5) {
        print("$i ")
    }
    println()

    // Tăng dần nhưng loại trừ số cuối (until) -> 1 2 3 4
    print("For (1 until 5): ")
    for (i in 1 until 5) {
        print("$i ")
    }
    println()

    // Vòng lặp bước nhảy (step 2) -> 1 3 5
    print("For với bước nhảy (step 2): ")
    for (i in 1..5 step 2) {
        print("$i ")
    }
    println()

    // Vòng lặp lùi dần (downTo) -> 5 4 3 2 1
    print("For lùi dần (downTo): ")
    for (i in 5 downTo 1) {
        print("$i ")
    }
    println()

    // Duyệt qua một danh sách (List)
    val fruits = listOf("Táo", "Chuối", "Cam")
    println("Duyệt List trái cây:")
    for (fruit in fruits) {
        println("- $fruit")
    }

    // Duyệt kèm theo chỉ số (Index) của phần tử
    println("Duyệt List kèm Index:")
    for ((index, fruit) in fruits.withIndex()) {
        println("Vị trí $index là quả $fruit")
    }


    // --- 4.4. Vòng lặp While và Do-While (Giống Java/C++) ---
    var count = 3
    println("Vòng lặp While:")
    while (count > 0) {
        println("Count còn: $count")
        count--
    }

    var y = 0
    println("Vòng lặp Do-While (Luôn chạy ít nhất 1 lần):")
    do {
        println("Giá trị y hiện tại là: $y")
        y++
    } while (y < 0) // Điều kiện sai ngay từ đầu nhưng vẫn chạy phần 'do' 1 lần
}