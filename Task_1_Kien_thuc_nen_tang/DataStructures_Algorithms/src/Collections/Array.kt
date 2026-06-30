/**
 * ============================================
 *  ARRAY TRONG KOTLIN - CƠ BẢN
 *  Dành cho người mới bắt đầu
 * ============================================
 * 
 * Array là gì?
 * - Là tập hợp các phần tử cùng kiểu dữ liệu
 * - Các phần tử nằm liên tiếp trong bộ nhớ
 *- Kích thước CỐ ĐỊNH sau khi tạo
 * - Truy xuất nhanh qua index (chỉ số)
 */

fun main() {

    // ==========================================
    // 1. CÁCH TẠO ARRAY
    // ==========================================

    println("========== 1. TẠO ARRAY ==========")

    // Cách 1: Dùng arrayOf() - Thông dụng nhất
    val numbers = arrayOf(10, 20, 30, 40, 50)
    println("numbers: ${numbers.joinToString()}")
    // Kết quả: numbers: 10, 20, 30, 40, 50

    // Cách 2: Dùng arrayOf với kiểu cụ thể
    val names: Array<String> = arrayOf("An", "Bình", "Chi", "Dũng")
    println("names: ${names.joinToString()}")
    // Kết quả: names: An, Bình, Chi, Dũng

    // Cách 3: Dùng Array constructor
    val squares = Array(5) { index -> index * index }
    println("squares: ${squares.joinToString()}")
    // Kết quả: squares: 0, 1, 4, 9, 16
    // Giải thích: tạo array 5 phần tử, mỗi phần tử = index * index

    // Cách 4: Kiểu nguyên thủy (hiệu năng cao hơn)
    val intArray = intArrayOf(1, 2, 3, 4, 5)
    val doubleArray = doubleArrayOf(1.5, 2.5, 3.5)
    val charArray = charArrayOf('a', 'b', 'c')
    val booleanArray = booleanArrayOf(true, false, true)
    println("intArray: ${intArray.joinToString()}")
    // Kết quả: intArray: 1, 2, 3, 4, 5

    // Cách 5: Tạo array rỗng
    val emptyArray = emptyArray<Int>()
    println("emptyArray size: ${emptyArray.size}") // 0

    println("\n========== 2. TRUY XUẤT PHẦN TỬ ==========")

    // ==========================================
    // 2. TRUY XUẤT PHẦN TỬ
    // ==========================================

    val fruits = arrayOf("Apple", "Banana", "Cherry", "Durian", "Elderberry")
    println("fruits: ${fruits.joinToString()}")

    // 2.1. Lấy phần tử theo index (bắt đầu từ 0)
    println("Phần tử đầu tiên: ${fruits[0]}")      // Apple
    println("Phần tử thứ 3: ${fruits[2]}")         // Cherry
    println("Phần tử cuối cùng: ${fruits[4]}")     // Elderberry

    // Hoặc dùng hàm get()
    println("fruits.get(1): ${fruits.get(1)}")     // Banana

    // 2.2. Lấy phần tử đầu và cuối (hàm mở rộng)
    println("first(): ${fruits.first()}")          // Apple
    println("last(): ${fruits.last()}")            // Elderberry

    // 2.3. Lấy phần tử an toàn (tránh lỗi null)
    println("firstOrNull(): ${fruits.firstOrNull()}")  // Apple
    println("lastOrNull(): ${fruits.lastOrNull()}")    // Elderberry
    // Dùng khi array có thể rỗng

    // 2.4. Lấy index của phần tử
    val indexOfCherry = fruits.indexOf("Cherry")
    println("Index của 'Cherry': $indexOfCherry")      // 2

    val indexOfNotFound = fruits.indexOf("Grape")
    println("Index của 'Grape': $indexOfNotFound")     // -1 (không tìm thấy)

    // 2.5. Kiểm tra phần tử có tồn tại không
    val hasBanana = fruits.contains("Banana")
    val hasGrape = fruits.contains("Grape")
    println("Có 'Banana' không? $hasBanana")           // true
    println("Có 'Grape' không? $hasGrape")             // false

    println("\n========== 3. SỬA PHẦN TỬ ==========")

    // ==========================================
    // 3. SỬA PHẦN TỬ
    // ==========================================

    val colors = arrayOf("Red", "Green", "Blue", "Yellow")
    println("colors ban đầu: ${colors.joinToString()}")
    // Kết quả: colors ban đầu: Red, Green, Blue, Yellow

    // Sửa phần tử tại index
    colors[2] = "Purple"
    println("Sau khi sửa index 2: ${colors.joinToString()}")
    // Kết quả: Sau khi sửa index 2: Red, Green, Purple, Yellow

    // Hoặc dùng set()
    colors.set(1, "Orange")
    println("Sau khi sửa index 1: ${colors.joinToString()}")
    // Kết quả: Sau khi sửa index 1: Red, Orange, Purple, Yellow

    println("\n========== 4. KÍCH THƯỚC ==========")

    // ==========================================
    // 4. KÍCH THƯỚC
    // ==========================================

    val numbers2 = arrayOf(10, 20, 30, 40, 50)
    println("numbers2: ${numbers2.joinToString()}")
    println("Số lượng phần tử: ${numbers2.size}")   // 5
    println("Có rỗng không? ${numbers2.isEmpty()}") // false

    val emptyArr = emptyArray<String>()
    println("emptyArr có rỗng không? ${emptyArr.isEmpty()}") // true

    // Lưu ý: Không thể thay đổi kích thước array!
    // numbers2.size = 10  // ❌ LỖI! Không thể gán

    println("\n========== 5. DUYỆT (ITERATE) ==========")

    // ==========================================
    // 5. DUYỆT QUA CÁC PHẦN TỬ
    // ==========================================

    val scores = arrayOf(85, 90, 78, 92, 88)
    println("scores: ${scores.joinToString()}")

    // Cách 1: for loop - Phổ biến nhất
    print("Cách 1 - for: ")
    for (score in scores) {
        print("$score ")
    }
    println()
    // Kết quả: Cách 1 - for: 85 90 78 92 88

    // Cách 2: for với index
    print("Cách 2 - for với index: ")
    for (i in scores.indices) {
        print("scores[$i]=${scores[i]} ")
    }
    println()
    // Kết quả: Cách 2 - for với index: scores[0]=85 scores[1]=90 scores[2]=78 scores[3]=92 scores[4]=88

    // Cách 3: forEach
    print("Cách 3 - forEach: ")
    scores.forEach { score ->
        print("$score ")
    }
    println()
    // Kết quả: Cách 3 - forEach: 85 90 78 92 88

    // Cách 4: forEachIndexed (có cả index và value)
    print("Cách 4 - forEachIndexed: ")
    scores.forEachIndexed { index, score ->
        print("[$index:$score] ")
    }
    println()
    // Kết quả: Cách 4 - forEachIndexed: [0:85] [1:90] [2:78] [3:92] [4:88]

    // Cách 5: while loop
    print("Cách 5 - while: ")
    var i = 0
    while (i < scores.size) {
        print("${scores[i]} ")
        i++
    }
    println()
    // Kết quả: Cách 5 - while: 85 90 78 92 88

    println("\n========== 6. MỘT SỐ THAO TÁC HỮU ÍCH ==========")

    // ==========================================
    // 6. CÁC THAO TÁC HỮU ÍCH
    // ==========================================

    val nums = arrayOf(5, 2, 8, 1, 9, 3, 7, 4, 6)
    println("nums: ${nums.joinToString()}")

    // 6.1. Sắp xếp (trả về array mới)
    val sorted = nums.sortedArray()
    println("Sắp xếp tăng: ${sorted.joinToString()}")
    // Kết quả: Sắp xếp tăng: 1, 2, 3, 4, 5, 6, 7, 8, 9

    val sortedDesc = nums.sortedArrayDescending()
    println("Sắp xếp giảm: ${sortedDesc.joinToString()}")
    // Kết quả: Sắp xếp giảm: 9, 8, 7, 6, 5, 4, 3, 2, 1

    // 6.2. Đảo ngược (trả về array mới)
    val reversed = nums.reversedArray()
    println("Đảo ngược: ${reversed.joinToString()}")
    // Kết quả: Đảo ngược: 6, 4, 7, 3, 9, 1, 8, 2, 5

    // 6.3. Tìm min, max
    val min = nums.minOrNull()
    val max = nums.maxOrNull()
    println("Min: $min, Max: $max") // Min: 1, Max: 9

    // 6.4. Tính tổng
    val sum = nums.sum()
    println("Tổng: $sum") // Tổng: 45

    // 6.5. Lọc các phần tử thỏa mãn (trả về array mới)
    val evens = nums.filter { it % 2 == 0 }.toTypedArray()
    println("Số chẵn: ${evens.joinToString()}")
    // Kết quả: Số chẵn: 2, 8, 4, 6

    val greaterThan5 = nums.filter { it > 5 }.toTypedArray()
    println("Số > 5: ${greaterThan5.joinToString()}")
    // Kết quả: Số > 5: 8, 9, 7, 6

    // 6.6. Biến đổi mỗi phần tử (map)
    val doubled = nums.map { it * 2 }.toTypedArray()
    println("Nhân đôi: ${doubled.joinToString()}")
    // Kết quả: Nhân đôi: 10, 4, 16, 2, 18, 6, 14, 8, 12

    // 6.7. Loại bỏ trùng lặp
    val duplicate = arrayOf(1, 2, 2, 3, 3, 3, 4, 4, 4, 4)
    val distinct = duplicate.distinct().toTypedArray()
    println("duplicate: ${duplicate.joinToString()}")
    println("distinct: ${distinct.joinToString()}")
    // Kết quả: distinct: 1, 2, 3, 4

    println("\n========== 7. CHUYỂN ĐỔI GIỮA ARRAY VÀ LIST ==========")

    // ==========================================
    // 7. CHUYỂN ĐỔI GIỮA ARRAY VÀ LIST
    // ==========================================

    val myArray = arrayOf("A", "B", "C", "D")
    println("Array: ${myArray.joinToString()}")

    // 7.1. Array -> List (Immutable)
    val myList = myArray.toList()
    println("List: $myList")
    // Kết quả: List: [A, B, C, D]

    // 7.2. Array -> MutableList (có thể thay đổi)
    val myMutableList = myArray.toMutableList()
    myMutableList.add("E")
    myMutableList.add("F")
    println("MutableList: $myMutableList")
    // Kết quả: MutableList: [A, B, C, D, E, F]

    // 7.3. List -> Array
    val listToArray = myList.toTypedArray()
    println("List to Array: ${listToArray.joinToString()}")
    // Kết quả: List to Array: A, B, C, D

    println("\n========== 8. BÀI TẬP THỰC HÀNH ==========")

    // ==========================================
    // 8. BÀI TẬP THỰC HÀNH (Có lời giải)
    // ==========================================

    println("--- Bài 1: Tính tổng các phần tử ---")
    val arr1 = arrayOf(10, 20, 30, 40, 50)
    var total = 0
    for (num in arr1) {
        total += num
    }
    println("Tổng = $total") // 150

    println("\n--- Bài 2: Tìm số lớn nhất ---")
    val arr2 = arrayOf(5, 2, 9, 1, 7, 3)
    var maxNum = arr2[0]
    for (num in arr2) {
        if (num > maxNum) {
            maxNum = num
        }
    }
    println("Số lớn nhất = $maxNum") // 9

    println("\n--- Bài 3: Đếm số chẵn ---")
    val arr3 = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    var countEven = 0
    for (num in arr3) {
        if (num % 2 == 0) {
            countEven++
        }
    }
    println("Số chẵn = $countEven") // 5

    println("\n--- Bài 4: Tạo array mới với bình phương ---")
    val arr4 = arrayOf(1, 2, 3, 4, 5)
    val squares2 = Array(arr4.size) { index -> arr4[index] * arr4[index] }
    println("Bình phương: ${squares2.joinToString()}") // 1, 4, 9, 16, 25

    println("\n--- Bài 5: Kiểm tra palindrome ---")
    val arr5 = arrayOf(1, 2, 3, 2, 1)
    var isPalindrome = true
    for (i in 0 until arr5.size / 2) {
        if (arr5[i] != arr5[arr5.size - 1 - i]) {
            isPalindrome = false
            break
        }
    }
    println("Array $arr5 là palindrome? $isPalindrome") // true

    println("\n========== 9. TÓM TẮT ==========")
    println("""
        ✅ Array là tập hợp các phần tử cùng kiểu
        ✅ Kích thước CỐ ĐỊNH sau khi tạo
        ✅ Index bắt đầu từ 0
        ✅ Truy xuất nhanh O(1) bằng index
        ✅ size - lấy số lượng phần tử
        ✅ Không thể thêm/xóa phần tử (kích thước cố định)
        ✅ Dùng arrayOf() để tạo array nhanh nhất
        ✅ Thường dùng List/MutableList hơn trong Kotlin
    """.trimIndent())
}

/**
 * ============================================
 *  TỔNG KẾT NHANH
 * ============================================
 * 
 * 1. Tạo array: arrayOf(1, 2, 3)
 * 2. Lấy phần tử: array[0] hoặc array.get(0)
 * 3. Sửa phần tử: array[0] = 10 hoặc array.set(0, 10)
 * 4. Kích thước: array.size
 * 5. Duyệt: for (item in array) { ... }
 * 6. Tìm index: array.indexOf("value")
 * 7. Kiểm tra tồn tại: array.contains("value")
 * 8. Sắp xếp: array.sortedArray()
 * 9. Đảo ngược: array.reversedArray()
 * 10. Lọc: array.filter { it > 5 }.toTypedArray()
 */