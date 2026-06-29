// =========================================================================
// 1. BIẾN (VARIABLES)
// =========================================================================

fun variables() {
    // val (Value): Biến chỉ đọc (Read-only), không thể tái gán giá trị sau khi khởi tạo
    val name: String = "quzkhanh"

    // var (Variable): Biến có thể thay đổi, cho phép gán lại giá trị mới
    var age = 22
    age += 1

    // String Template: Nhúng biến vào chuỗi bằng dấu $
    println("=== BIẾN ===")
    println("Name: $name")
    println("Age: $age")
    println()
}

// =========================================================================
// 2. KIỂU DỮ LIỆU & XỬ LÝ NULL (DATA TYPES & NULL SAFETY)
// =========================================================================

fun kieuDuLieu() {
    println("=== KIỂU DỮ LIỆU ===")

    // --- Các kiểu dữ liệu cơ bản ---
    val age: Int = 22
    val salary: Long = 20000000L
    val price: Double = 99.99
    val height: Float = 1.75f
    val isActive: Boolean = true
    val grade: Char = 'A'
    val name: String = "Khanh"

    println("Int: $age, Long: $salary, Double: $price")
    println("Float: ${height}f, Boolean: $isActive, Char: $grade, String: $name")

    // --- Chuyển đổi kiểu dữ liệu (Type Conversion) ---
    val number = "100"
    val value = number.toInt()
    println("Chuyển đổi String '$number' -> Int: $value")

    // --- Xử lý Null Safety (Tính năng cốt lõi của Kotlin) ---
    println("\n--- NULL SAFETY ---")

    // 1. Non-Null: Mặc định KHÔNG được phép null
    val nonNullName: String = "quzkhanh"
    // val errorName: String = null // LỖI: Không thể gán null cho non-null type

    // 2. Nullable (?): Cho phép nhận null
    val nullableName: String? = null
    val nullableName2: String? = "quoc khanh"

    // 3. Safe Call (?.): Gọi an toàn, nếu null trả về null
    val length1 = nullableName?.length
    val length2 = nullableName2?.length
    println("Safe call trên null: $length1")
    println("Safe call trên không null: $length2")

    // 4. Elvis Operator (?:): Cung cấp giá trị mặc định
    val safeLength = nullableName?.length ?: 0
    val safeLength2 = nullableName2?.length ?: 0
    println("Elvis operator (null -> 0): $safeLength")
    println("Elvis operator (not null): $safeLength2")

    // 5. Not-null Assertion (!!): Ép buộc không null - CẨN THẬN!
    // Chỉ dùng khi bạn CHẮC CHẮN biến không null
    val definitelyNotNull: String? = "chắc chắn không null"
    println("!! operator: ${definitelyNotNull!!.length}")
    // ⚠️ Nếu nullableName !! sẽ gây NullPointerException
    // println(nullableName!!.length) // LỖI: Sẽ crash!

    println()
}

