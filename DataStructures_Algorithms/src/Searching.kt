/**
 * SEARCHING TRONG KOTLIN - CÁC CÁCH TÌM KIẾM
 *
 * 1. Tìm kiếm tuyến tính (Linear Search) - O(n)
 * 2. Tìm kiếm nhị phân (Binary Search) - O(log n)
 * 3. Các hàm tìm kiếm có sẵn trong Kotlin
 */

fun main() {

    // ==========================================
    // 1. TÌM KIẾM TUYẾN TÍNH (LINEAR SEARCH)
    // ==========================================

    val numbers = listOf(10, 20, 30, 40, 50, 30, 60, 70)
    println("numbers: $numbers")

    // 1.1. indexOf() - Tìm index đầu tiên
    val index = numbers.indexOf(30)
    println("indexOf(30): $index") // 2

    // 1.2. lastIndexOf() - Tìm index cuối cùng
    val lastIndex = numbers.lastIndexOf(30)
    println("lastIndexOf(30): $lastIndex") // 5

    // 1.3. contains() - Kiểm tra tồn tại
    val has40 = numbers.contains(40)
    val has99 = numbers.contains(99)
    println("contains(40): $has40") // true
    println("contains(99): $has99") // false

    // 1.4. find() - Tìm phần tử đầu tiên thỏa mãn
    val firstGreaterThan35 = numbers.find { it > 35 }
    println("find { it > 35 }: $firstGreaterThan35") // 40

    // 1.5. findLast() - Tìm phần tử cuối cùng thỏa mãn
    val lastGreaterThan35 = numbers.findLast { it > 35 }
    println("findLast { it > 35 }: $lastGreaterThan35") // 70

    // 1.6. filter() - Tìm tất cả phần tử thỏa mãn
    val allGreaterThan35 = numbers.filter { it > 35 }
    println("filter { it > 35 }: $allGreaterThan35") // [40, 50, 60, 70]

    // 1.7. any() - Kiểm tra có ít nhất 1 phần tử thỏa mãn
    val hasEven = numbers.any { it % 2 == 0 }
    val hasNegative = numbers.any { it < 0 }
    println("any { it % 2 == 0 }: $hasEven") // true
    println("any { it < 0 }: $hasNegative") // false

    // 1.8. all() - Kiểm tra tất cả phần tử thỏa mãn
    val allPositive = numbers.all { it > 0 }
    val allGreaterThan20 = numbers.all { it > 20 }
    println("all { it > 0 }: $allPositive") // true
    println("all { it > 20 }: $allGreaterThan20") // false

    // 1.9. none() - Kiểm tra không có phần tử nào thỏa mãn
    val noneNegative = numbers.none { it < 0 }
    val noneGreaterThan100 = numbers.none { it > 100 }
    println("none { it < 0 }: $noneNegative") // true
    println("none { it > 100 }: $noneGreaterThan100") // true

    // 1.10. count() - Đếm số phần tử thỏa mãn
    val countGreaterThan30 = numbers.count { it > 30 }
    println("count { it > 30 }: $countGreaterThan30") // 5

    // ==========================================
    // 2. TÌM KIẾM NHỊ PHÂN (BINARY SEARCH)
    // ==========================================

    // Lưu ý: Chỉ dùng được khi List đã SẮP XẾP

    val sortedNumbers = listOf(10, 20, 30, 40, 50, 60, 70, 80, 90)
    println("sortedNumbers: $sortedNumbers")

    // 2.1. binarySearch() - Tìm index (trả về -insertionPoint-1 nếu không thấy)
    val idx40 = sortedNumbers.binarySearch(40)
    println("binarySearch(40): $idx40") // 3

    val idx75 = sortedNumbers.binarySearch(75)
    println("binarySearch(75): $idx75") // -7 (vị trí chèn vào là 6)

    // 2.2. binarySearch() với khoảng (range)
    val idxInRange = sortedNumbers.binarySearch(60, 2, 6) // Tìm từ index 2 đến 6
    println("binarySearch(60, 2, 6): $idxInRange") // 5

    // 2.3. binarySearch() với Comparator (cho Object)
    data class Person(val name: String, val age: Int)

    val people = listOf(
        Person("Alice", 25),
        Person("Bob", 30),
        Person("Charlie", 35),
        Person("David", 40)
    )

    // Tìm theo tuổi (sorted by age)
    val idxPerson = people.binarySearchBy(35) { it.age }
    println("binarySearchBy(35): $idxPerson") // 2

    // ==========================================
    // 3. TÌM KIẾM VỚI OBJECT
    // ==========================================

    data class Product(val id: Int, val name: String, val price: Double)

    val products = listOf(
        Product(1, "Laptop", 999.99),
        Product(2, "Mouse", 25.50),
        Product(3, "Keyboard", 45.00),
        Product(4, "Monitor", 299.99),
        Product(5, "Headphone", 89.99)
    )

    // 3.1. Tìm theo điều kiện
    val expensiveProduct = products.find { it.price > 100 }
    println("find { price > 100 }: ${expensiveProduct?.name}") // Laptop

    // 3.2. Tìm tất cả theo điều kiện
    val cheapProducts = products.filter { it.price < 50 }
    println("filter { price < 50 }: ${cheapProducts.map { it.name }}") // [Mouse, Keyboard]

    // 3.3. Tìm theo id
    val productById = products.find { it.id == 3 }
    println("find { id == 3 }: ${productById?.name}") // Keyboard

    // 3.4. Tìm index theo điều kiện
    val idxOfMouse = products.indexOfFirst { it.name == "Mouse" }
    println("indexOfFirst { name == 'Mouse' }: $idxOfMouse") // 1

    val idxOfExpensive = products.indexOfLast { it.price > 100 }
    println("indexOfLast { price > 100 }: $idxOfExpensive") // 3 (Monitor)

    // ==========================================
    // 4. TÌM KIẾM VỚI STRING
    // ==========================================

    val words = listOf("apple", "banana", "cherry", "durian", "elderberry")

    // 4.1. Tìm theo prefix
    val startsWithC = words.find { it.startsWith('c') }
    println("starts with 'c': $startsWithC") // cherry

    // 4.2. Tìm theo suffix
    val endsWithY = words.filter { it.endsWith('y') }
    println("ends with 'y': $endsWithY") // [cherry, durian]

    // 4.3. Tìm theo substring
    val containsBer = words.filter { it.contains("ber") }
    println("contains 'ber': $containsBer") // [elderberry]

    // 4.4. Tìm theo length
    val lengthGreaterThan6 = words.filter { it.length > 6 }
    println("length > 6: $lengthGreaterThan6") // [banana, cherry, durian, elderberry]

    // ==========================================
    // 5. TÌM KIẾM TRONG MẢNG (ARRAY)
    // ==========================================

    val arr = arrayOf(10, 20, 30, 40, 50)

    // 5.1. Tìm index
    val idxArr = arr.indexOf(30)
    println("Array indexOf(30): $idxArr") // 2

    // 5.2. Kiểm tra tồn tại
    val containsArr = arr.contains(40)
    println("Array contains(40): $containsArr") // true

    // 5.3. Tìm kiếm nhị phân trên Array (đã sắp xếp)
    val sortedArr = intArrayOf(10, 20, 30, 40, 50)
    val binaryIdx = sortedArr.binarySearch(40)
    println("Array binarySearch(40): $binaryIdx") // 3

    // ==========================================
    // 6. TÌM KIẾM VỚI MAP
    // ==========================================

    val map = mapOf(
        "A" to 1,
        "B" to 2,
        "C" to 3,
        "D" to 4
    )

    // 6.1. Tìm theo key
    val valueOfA = map["A"]
    println("map['A']: $valueOfA") // 1

    // 6.2. Tìm key từ value
    val keyOf3 = map.entries.find { it.value == 3 }?.key
    println("find key for value 3: $keyOf3") // C

    // 6.3. Tìm tất cả key có value thỏa mãn
    val keysWithEven = map.filter { it.value % 2 == 0 }.keys
    println("keys with even values: $keysWithEven") // [B, D]

    // ==========================================
    // 7. TÌM KIẾM AN TOÀN (KHÔNG BỊ LỖI)
    // ==========================================

    val emptyList = emptyList<Int>()

    // 7.1. firstOrNull() thay vì first() (tránh lỗi khi list rỗng)
    val first = emptyList.firstOrNull()
    println("firstOrNull(): $first") // null

    // 7.2. find() thay vì first { } (không bị lỗi nếu không tìm thấy)
    val found = emptyList.find { it > 5 }
    println("find { it > 5 }: $found") // null

    // 7.3. elementAtOrNull() thay vì get() (tránh lỗi index out of bounds)
    val element = emptyList.elementAtOrNull(0)
    println("elementAtOrNull(0): $element") // null

    // 7.4. indexOf() trả về -1 nếu không tìm thấy
    val idxNotFound = emptyList.indexOf(99)
    println("indexOf(99): $idxNotFound") // -1

    // ==========================================
    // 8. SO SÁNH HIỆU NĂNG
    // ==========================================

    println("\n========== SO SÁNH HIỆU NĂNG ==========")
    println("""
        | Linear Search (indexOf, find, contains):
        |   - O(n) - duyệt từ đầu đến cuối
        |   - Dùng được cho list CHƯA sắp xếp
        |   - Nhanh với list nhỏ, chậm với list lớn
        |
        | Binary Search (binarySearch):
        |   - O(log n) - cực nhanh với list lớn
        |   - CHỈ dùng được cho list ĐÃ SẮP XẾP
        |   - Tốt nhất khi cần tìm kiếm nhiều lần
        |
        | Filter, Find, Any, All, None:
        |   - O(n) - duyệt toàn bộ hoặc đến khi tìm thấy
        |   - Rất tiện lợi và dễ đọc
        |
        | Map lookup (map["key"]):
        |   - O(1) - nhanh nhất
        |   - Dùng Map thay vì List khi cần tìm theo key
    """.trimMargin())

    // ==========================================
    // 9. BÀI TẬP THỰC HÀNH
    // ==========================================

    println("\n========== BÀI TẬP ==========")

    // Bài 1: Tìm số chẵn đầu tiên trong list
    val nums1 = listOf(1, 3, 5, 7, 8, 9, 10)
    val firstEven = nums1.find { it % 2 == 0 }
    println("Bài 1 - First even: $firstEven") // 8

    // Bài 2: Đếm số lẻ trong list
    val nums2 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val oddCount = nums2.count { it % 2 != 0 }
    println("Bài 2 - Count odd: $oddCount") // 5

    // Bài 3: Kiểm tra tất cả số đều dương
    val nums3 = listOf(1, 2, 3, 4, 5)
    val allPositive1 = nums3.all { it > 0 }
    println("Bài 3 - All positive: $allPositive1") // true

    // Bài 4: Tìm index của số 7
    val nums4 = listOf(10, 20, 30, 40, 50, 60, 70)
    val idx7 = nums4.indexOf(70)
    println("Bài 4 - Index of 70: $idx7") // 6

    // Bài 5: Binary Search (list đã sắp xếp)
    val sorted = listOf(1, 3, 5, 7, 9, 11, 13, 15)
    val idxBinary = sorted.binarySearch(9)
    println("Bài 5 - Binary search 9: $idxBinary") // 4

    // Bài 6: Tìm tên dài nhất
    val names = listOf("An", "Binh", "Chi", "Dung", "Emily", "Franklin")
    val longest = names.maxByOrNull { it.length }
    println("Bài 6 - Longest name: $longest") // Franklin
}

/**
 * ============================================
 *  TÓM TẮT NHANH CÁC HÀM TÌM KIẾM
 * ============================================
 *
 * Tìm kiếm cơ bản:
 *   indexOf(value)      -> index đầu tiên, -1 nếu không thấy
 *   lastIndexOf(value)  -> index cuối cùng, -1 nếu không thấy
 *   contains(value)     -> true/false
 *
 * Tìm kiếm theo điều kiện:
 *   find { }            -> phần tử đầu tiên, null nếu không thấy
 *   findLast { }        -> phần tử cuối cùng, null nếu không thấy
 *   filter { }          -> list các phần tử thỏa mãn
 *   any { }             -> true nếu có ít nhất 1 phần tử thỏa mãn
 *   all { }             -> true nếu tất cả phần tử thỏa mãn
 *   none { }            -> true nếu không có phần tử nào thỏa mãn
 *   count { }           -> số lượng phần tử thỏa mãn
 *
 * Tìm kiếm với index:
 *   indexOfFirst { }    -> index đầu tiên thỏa mãn, -1 nếu không thấy
 *   indexOfLast { }     -> index cuối cùng thỏa mãn, -1 nếu không thấy
 *
 * Tìm kiếm nhị phân (chỉ dùng khi đã sắp xếp):
 *   binarySearch(value) -> index, -insertionPoint-1 nếu không thấy
 *   binarySearchBy(value) { selector } -> index với selector
 *
 * An toàn (không bị lỗi):
 *   firstOrNull()       -> null nếu list rỗng
 *   elementAtOrNull()   -> null nếu index out of bounds
 *   find { }            -> null nếu không thấy
 *   indexOf(value)      -> -1 nếu không thấy
 */