// =========================================================================
// 3. CÁC KIỂU HÀM TRONG KOTLIN (FUNCTIONS)
// =========================================================================

// --- 3.1. Hàm cơ bản (Standard Function) ---
fun sayHello(name: String) {
    println("Xin chào, $name!")
}

// --- 3.2. Hàm rút gọn (Single-Expression Function) ---
fun calculateSquare(x: Int): Int = x * x

// --- 3.3. Tham số mặc định & Tham số đặt tên (Default & Named Arguments) ---
fun configureMessage(
    message: String,
    prefix: String = "INFO",
    uppercase: Boolean = false
) {
    val result = if (uppercase) "$prefix: ${message.uppercase()}" else "$prefix: $message"
    println(result)
}

// --- 3.4. Hàm mở rộng (Extension Function) ---
fun Int.isEven(): Boolean = this % 2 == 0

fun String.addExclamation(): String = "$this!"

// --- 3.5. Hàm Lambda ---
val sumLambda: (Int, Int) -> Int = { a, b -> a + b }
val multiplyLambda = { a: Int, b: Int -> a * b }

// --- 3.6. Hàm bậc cao (Higher-Order Function) ---
fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

// --- 3.7. Hàm Inline (Inline Function) - Tối ưu hiệu năng ---
inline fun executeTask(task: () -> Unit) {
    task()
}

// --- 3.8. Hàm với nhiều kiểu trả về (Unit vs explicit return) ---
fun getCurrentTime(): String {
    return java.time.LocalDateTime.now().toString()
}

// --- 3.9. Hàm với varargs (tham số biến) ---
fun printAll(vararg items: String) {
    for (item in items) {
        println(item)
    }
}

// --- 3.10. Ví dụ về overload function (nạp chồng hàm) ---
// Các hàm cùng tên nhưng khác tham số
fun display(value: Int) {
    println("Display Int: $value")
}

fun display(value: String) {
    println("Display String: $value")
}

fun display(value: Int, prefix: String) {
    println("$prefix: $value")
}

// =========================================================================
// 4. HÀM CHẠY CHÍNH (MAIN FUNCTION)
// =========================================================================

fun main() {
    // 1. Biến và Kiểu dữ liệu
    variables()
    kieuDuLieu()

    // 2. Các kiểu Hàm
    println("=== CÁC KIỂU HÀM ===")

    // Hàm cơ bản
    sayHello("Quốc Khánh")

    // Hàm rút gọn - đã đổi tên
    println("Bình phương của 5 là: ${calculateSquare(5)}")

    // Tham số mặc định và đặt tên
    configureMessage("Hệ thống đang chạy") // Dùng default
    configureMessage("Lỗi nghiêm trọng", "ERROR", true) // Truyền đầy đủ
    configureMessage(uppercase = true, message = "Thông báo", prefix = "WARN") // Named args

    // Extension Function
    val checkNumber = 4
    println("Số $checkNumber có phải số chẵn? ${checkNumber.isEven()}")
    println("Hello".addExclamation())

    // Lambda
    println("Lambda sum: ${sumLambda(10, 20)}")
    println("Lambda multiply: ${multiplyLambda(5, 6)}")

    // Higher-Order Function
    val addResult = calculate(10, 5) { x, y -> x + y }
    val subResult = calculate(10, 5) { x, y -> x - y }
    val mulResult = calculate(10, 5) { x, y -> x * y }
    println("Hàm bậc cao: 10 + 5 = $addResult")
    println("Hàm bậc cao: 10 - 5 = $subResult")
    println("Hàm bậc cao: 10 * 5 = $mulResult")

    // Inline Function
    executeTask {
        println("Inline function executed!")
    }

    // Hàm với varargs
    println("Varargs:")
    printAll("A", "B", "C", "D")

    // Hàm trả về thời gian
    println("Thời gian hiện tại: ${getCurrentTime()}")

    // Ví dụ overload function (nạp chồng hàm)
    println("\n--- NẠP CHỒNG HÀM (OVERLOAD) ---")
    display(100)
    display("Hello Kotlin")
    display(200, "Giá trị")
}

/* =========================================================================
   TÓM TẮT NHANH (CHEATSHEET)
   =========================================================================

   🔹 Biến: val (read-only), var (mutable)
   🔹 Null Safety: ? (nullable), ?. (safe call), ?: (elvis), !! (force)
   🔹 Hàm: fun, có thể có tham số mặc định và named arguments
   🔹 Extension: Thêm hàm cho class có sẵn
   🔹 Lambda: { params -> body }
   🔹 Higher-Order: Hàm nhận/trả về hàm khác
   🔹 Inline: Tối ưu hiệu năng cho higher-order functions
   🔹 varargs: Nhận nhiều tham số cùng kiểu
   🔹 Overload: Nhiều hàm cùng tên nhưng khác tham số
   =========================================================================
*/