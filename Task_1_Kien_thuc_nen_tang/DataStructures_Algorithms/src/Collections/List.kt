package Collections

/**
 * =========================================================
 *  BÀI TẬP TỔNG HỢP: THAO TÁC VỚI ARRAY / LIST TRONG KOTLIN
 *  KÈM ĐỘ PHỨC TẠP BIG O
 * =========================================================
 *
 * Lưu ý:
 * - Array trong Kotlin có kích thước cố định (fixed-size)
 * - List có 2 loại:
 *   + Immutable List (không thể thay đổi) - List<T>
 *   + Mutable List (có thể thay đổi) - MutableList<T>
 *
 * Trong file này, tôi sử dụng MutableList để minh họa các thao tác.
 */

fun main() {
    println("========== KHỞI TẠO ==========")
    // =========================================================
    //  1. KHỞI TẠO MẢNG / LIST
    // =========================================================

    // Cách 1: Tạo từ các phần tử
    val numbers = mutableListOf(10, 20, 30, 40, 50)
    println("Khởi tạo: $numbers")
    // Độ phức tạp: O(n) - phải duyệt qua các phần tử để tạo

    // Cách 2: Tạo rỗng
    val emptyList = mutableListOf<Int>()
    println("List rỗng: $emptyList")
    // Độ phức tạp: O(1)

    // Cách 3: Tạo với kích thước cố định và giá trị mặc định
    val fixedList = MutableList(5) { index -> index * 2 }
    println("Tạo với kích thước: $fixedList") // [0, 2, 4, 6, 8]
    // Độ phức tạp: O(n)

    // Cách 4: Từ mảng (Array) sang List
    val array = arrayOf(1, 2, 3)
    val fromArray = array.toMutableList()
    println("Từ Array sang List: $fromArray")
    // Độ phức tạp: O(n)

    println("\n========== THÊM PHẦN TỬ ==========")
    // =========================================================
    //  2. THÊM PHẦN TỬ
    // =========================================================

    val list = mutableListOf(1, 2, 3)
    println("List ban đầu: $list")

    // 2.1. Thêm vào cuối
    list.add(4)
    println("Thêm cuối (add): $list") // [1, 2, 3, 4]
    // Độ phức tạp: O(1) trung bình, O(n) khi phải cấp phát lại bộ nhớ

    // 2.2. Thêm vào vị trí cụ thể
    list.add(1, 99) // Thêm 99 vào index 1
    println("Thêm tại index 1: $list") // [1, 99, 2, 3, 4]
    // Độ phức tạp: O(n) - phải dịch chuyển các phần tử

    // 2.3. Thêm nhiều phần tử
    list.addAll(listOf(100, 200))
    println("Thêm nhiều phần tử: $list") // [1, 99, 2, 3, 4, 100, 200]
    // Độ phức tạp: O(k) với k là số phần tử thêm vào

    // 2.4. Thêm vào đầu (không có hàm riêng, phải dùng add(0, value))
    list.add(0, 0)
    println("Thêm vào đầu: $list") // [0, 1, 99, 2, 3, 4, 100, 200]
    // Độ phức tạp: O(n) - phải dịch chuyển tất cả phần tử

    println("\n========== TRUY XUẤT PHẦN TỬ ==========")
    // =========================================================
    //  3. TRUY XUẤT PHẦN TỬ
    // =========================================================

    val data = mutableListOf(10, 20, 30, 40, 50)
    println("Data: $data")

    // 3.1. Lấy phần tử theo index
    val first = data[0]
    val second = data.get(1)
    println("Phần tử index 0: $first")
    println("Phần tử index 1: $second")
    // Độ phức tạp: O(1) - truy xuất trực tiếp

    // 3.2. Lấy phần tử đầu tiên
    val head = data.first()
    val headOrNull = data.firstOrNull()
    println("Phần tử đầu tiên: $head")
    // Độ phức tạp: O(1)

    // 3.3. Lấy phần tử cuối cùng
    val tail = data.last()
    val tailOrNull = data.lastOrNull()
    println("Phần tử cuối cùng: $tail")
    // Độ phức tạp: O(1)

    // 3.4. Lấy index của phần tử
    val index = data.indexOf(30)
    println("Index của 30: $index")
    // Độ phức tạp: O(n) - phải duyệt tìm

    // 3.5. Kiểm tra có chứa phần tử không
    val contains = data.contains(40)
    println("Có chứa 40 không? $contains")
    // Độ phức tạp: O(n) - phải duyệt tìm

    // 3.6. Lấy phần tử trong khoảng (sublist)
    val sublist = data.subList(1, 3)
    println("Sublist từ 1 đến 3: $sublist") // [20, 30]
    // Độ phức tạp: O(1) - chỉ là view, không copy dữ liệu
    // Lưu ý: Sublist là view, thay đổi sublist sẽ thay đổi list gốc!

    println("\n========== CẬP NHẬT PHẦN TỬ ==========")
    // =========================================================
    //  4. CẬP NHẬT PHẦN TỬ
    // =========================================================

    val updateList = mutableListOf(1, 2, 3, 4, 5)
    println("List ban đầu: $updateList")

    // 4.1. Sửa giá trị tại index
    updateList[2] = 999
    updateList.set(3, 888)
    println("Sau khi sửa index 2 và 3: $updateList") // [1, 2, 999, 888, 5]
    // Độ phức tạp: O(1) - gán trực tiếp

    // 4.2. Thay thế tất cả phần tử thỏa mãn điều kiện
    updateList.replaceAll { if (it > 100) it / 3 else it }
    println("Sau replaceAll: $updateList") // [1, 2, 333, 296, 5]
    // Độ phức tạp: O(n) - duyệt toàn bộ

    println("\n========== XÓA PHẦN TỬ ==========")
    // =========================================================
    //  5. XÓA PHẦN TỬ
    // =========================================================

    val removeList = mutableListOf(10, 20, 30, 40, 50, 30, 60)
    println("List ban đầu: $removeList")

    // 5.1. Xóa theo index
    removeList.removeAt(2)
    println("Xóa index 2: $removeList") // [10, 20, 40, 50, 30, 60]
    // Độ phức tạp: O(n) - phải dịch chuyển các phần tử

    // 5.2. Xóa theo giá trị (xóa lần xuất hiện đầu tiên)
    removeList.remove(30)
    println("Xóa giá trị 30 (lần đầu): $removeList") // [10, 20, 40, 50, 60]
    // Độ phức tạp: O(n) - phải tìm + dịch chuyển

    // 5.3. Xóa tất cả các phần tử có giá trị cụ thể
    removeList.removeAll { it == 50 }
    println("Xóa tất cả 50: $removeList") // [10, 20, 40, 60]
    // Độ phức tạp: O(n) - duyệt toàn bộ

    // 5.4. Xóa phần tử đầu tiên
    removeList.removeFirst()
    println("Xóa phần tử đầu: $removeList") // [20, 40, 60]
    // Độ phức tạp: O(n) - phải dịch chuyển

    // 5.5. Xóa phần tử cuối cùng
    removeList.removeLast()
    println("Xóa phần tử cuối: $removeList") // [20, 40]
    // Độ phức tạp: O(1) - xóa cuối không cần dịch chuyển

    // 5.6. Xóa theo điều kiện
    val dataRemove = mutableListOf(1, 2, 3, 4, 5, 6)
    dataRemove.removeAll { it % 2 == 0 }
    println("Xóa số chẵn: $dataRemove") // [1, 3, 5]
    // Độ phức tạp: O(n)

    // 5.7. Xóa tất cả
    dataRemove.clear()
    println("Xóa tất cả: $dataRemove") // []
    // Độ phức tạp: O(n) - xóa từng phần tử

    println("\n========== DUYỆT / ITERATION ==========")
    // =========================================================
    //  6. DUYỆT PHẦN TỬ
    // =========================================================

    val iterateList = listOf("A", "B", "C", "D")

    // 6.1. Duyệt bằng for
    print("for loop: ")
    for (item in iterateList) {
        print("$item ")
    }
    println()
    // Độ phức tạp: O(n)

    // 6.2. Duyệt bằng forEach
    print("forEach: ")
    iterateList.forEach { print("$it ") }
    println()
    // Độ phức tạp: O(n)

    // 6.3. Duyệt với index
    print("for với index: ")
    for (i in iterateList.indices) {
        print("${iterateList[i]} ")
    }
    println()
    // Độ phức tạp: O(n)

    // 6.4. Duyệt với cả index và value
    print("forEachIndexed: ")
    iterateList.forEachIndexed { index, value ->
        print("[$index:$value] ")
    }
    println()
    // Độ phức tạp: O(n)

    // 6.5. Duyệt ngược
    print("Duyệt ngược: ")
    for (i in iterateList.size - 1 downTo 0) {
        print("${iterateList[i]} ")
    }
    println()
    // Độ phức tạp: O(n)

    println("\n========== SẮP XẾP ==========")
    // =========================================================
    //  7. SẮP XẾP
    // =========================================================

    val sortList = mutableListOf(5, 2, 8, 1, 9, 3)
    println("List ban đầu: $sortList")

    // 7.1. Sắp xếp tăng dần (thay đổi trực tiếp)
    sortList.sort()
    println("Sort tăng dần: $sortList") // [1, 2, 3, 5, 8, 9]
    // Độ phức tạp: O(n log n) - sử dụng thuật toán TimSort

    // 7.2. Sắp xếp giảm dần
    sortList.sortDescending()
    println("Sort giảm dần: $sortList") // [9, 8, 5, 3, 2, 1]
    // Độ phức tạp: O(n log n)

    // 7.3. Sắp xếp với comparator tùy chỉnh
    val customSort = mutableListOf("apple", "banana", "kiwi", "pear")
    customSort.sortBy { it.length } // Sắp xếp theo độ dài
    println("Sort theo độ dài: $customSort") // [kiwi, pear, apple, banana]
    // Độ phức tạp: O(n log n)

    // 7.4. Sắp xếp và trả về list mới (không thay đổi list gốc)
    val sortedList = sortList.sorted()
    println("List gốc: $sortList")
    println("List mới (sắp xếp): $sortedList")
    // Độ phức tạp: O(n log n)

    // 7.5. Đảo ngược
    sortList.reverse()
    println("Reverse: $sortList") // [1, 2, 3, 5, 8, 9]
    // Độ phức tạp: O(n)

    println("\n========== TÌM KIẾM ==========")
    // =========================================================
    //  8. TÌM KIẾM
    // =========================================================

    val searchList = listOf(10, 20, 30, 40, 50, 30, 60)
    println("List: $searchList")

    // 8.1. Tìm index của phần tử (lần xuất hiện đầu tiên)
    val idx = searchList.indexOf(30)
    println("Index của 30 (đầu tiên): $idx") // 2
    // Độ phức tạp: O(n) - duyệt tuyến tính

    // 8.2. Tìm index cuối cùng
    val lastIdx = searchList.lastIndexOf(30)
    println("Index của 30 (cuối cùng): $lastIdx") // 5
    // Độ phức tạp: O(n) - duyệt tuyến tính

    // 8.3. Tìm phần tử thỏa mãn điều kiện
    val found = searchList.find { it > 35 }
    println("Phần tử > 35: $found") // 40
    // Độ phức tạp: O(n) - duyệt đến khi tìm thấy

    // 8.4. Tìm tất cả phần tử thỏa mãn
    val allFound = searchList.filter { it > 35 }
    println("Tất cả phần tử > 35: $allFound") // [40, 50, 60]
    // Độ phức tạp: O(n) - duyệt toàn bộ

    // 8.5. Binary Search (chỉ dùng khi list đã sắp xếp)
    val sortedSearch = searchList.sorted() // [10, 20, 30, 30, 40, 50, 60]
    val binaryIdx = sortedSearch.binarySearch(40)
    println("Binary Search index của 40: $binaryIdx") // 4
    // Độ phức tạp: O(log n) - thuật toán tìm kiếm nhị phân

    println("\n========== BIẾN ĐỔI / TRANSFORM ==========")
    // =========================================================
    //  9. BIẾN ĐỔI DỮ LIỆU
    // =========================================================

    val transformList = listOf(1, 2, 3, 4, 5)
    println("List gốc: $transformList")

    // 9.1. Map - ánh xạ mỗi phần tử thành giá trị khác
    val doubled = transformList.map { it * 2 }
    println("Map (x2): $doubled") // [2, 4, 6, 8, 10]
    // Độ phức tạp: O(n)

    // 9.2. Filter - lọc các phần tử thỏa mãn
    val evens = transformList.filter { it % 2 == 0 }
    println("Filter (số chẵn): $evens") // [2, 4]
    // Độ phức tạp: O(n)

    // 9.3. FlatMap - map + flatten
    val nestedList = listOf(
        listOf(1, 2),
        listOf(3, 4),
        listOf(5, 6)
    )
    val flat = nestedList.flatMap { it.map { it * 2 } }
    println("FlatMap (x2 cho từng list): $flat") // [2, 4, 6, 8, 10, 12]
    // Độ phức tạp: O(n) với n là tổng số phần tử sau khi flatten

    // 9.4. Reduce - gộp các phần tử thành 1 giá trị
    val sum = transformList.reduce { acc, value -> acc + value }
    println("Reduce (tổng): $sum") // 15
    // Độ phức tạp: O(n)

    // 9.5. Fold - giống reduce nhưng có giá trị khởi tạo
    val sumWithInit = transformList.fold(10) { acc, value -> acc + value }
    println("Fold (tổng + 10): $sumWithInit") // 25
    // Độ phức tạp: O(n)

    // 9.6. GroupBy - nhóm các phần tử theo key
    val words = listOf("apple", "banana", "apricot", "blueberry")
    val grouped = words.groupBy { it.first() }
    println("GroupBy theo chữ cái đầu: $grouped")
    // {a=[apple, apricot], b=[banana, blueberry]}
    // Độ phức tạp: O(n)

    // 9.7. Distinct - loại bỏ trùng lặp
    val duplicateList = listOf(1, 2, 2, 3, 3, 4, 5, 5)
    val distinct = duplicateList.distinct()
    println("Distinct: $distinct") // [1, 2, 3, 4, 5]
    // Độ phức tạp: O(n)

    println("\n========== THỐNG KÊ / STATISTICS ==========")
    // =========================================================
    //  10. CÁC HÀM THỐNG KÊ
    // =========================================================

    val statsList = listOf(10, 20, 30, 40, 50)
    println("List: $statsList")

    println("Size: ${statsList.size}") // O(1)
    println("Min: ${statsList.minOrNull()}") // O(n)
    println("Max: ${statsList.maxOrNull()}") // O(n)
    println("Sum: ${statsList.sum()}") // O(n)
    println("Average: ${statsList.average()}") // O(n)
    println("Is empty? ${statsList.isEmpty()}") // O(1)
    println("Is not empty? ${statsList.isNotEmpty()}") // O(1)

    println("\n========== CHUYỂN ĐỔI KIỂU ==========")
    // =========================================================
    //  11. CHUYỂN ĐỔI GIỮA CÁC KIỂU DỮ LIỆU
    // =========================================================

    val originalList = listOf(1, 2, 3)

    // 11.1. List -> MutableList
    val mutableCopy = originalList.toMutableList()
    println("MutableList: $mutableCopy")
    // Độ phức tạp: O(n)

    // 11.2. List -> Array
    val arrayCopy = originalList.toTypedArray()
    println("Array: ${arrayCopy.joinToString()}")
    // Độ phức tạp: O(n)

    // 11.3. List -> Set (loại bỏ duplicate)
    val setCopy = originalList.toSet()
    println("Set: $setCopy")
    // Độ phức tạp: O(n)

    // 11.4. List -> Map (cần có key cho mỗi phần tử)
    val mapCopy = originalList.associateWith { it * 2 }
    println("Map: $mapCopy") // {1=2, 2=4, 3=6}
    // Độ phức tạp: O(n)

    // 11.5. List -> String
    val stringList = listOf("A", "B", "C")
    val joined = stringList.joinToString(separator = ", ", prefix = "[", postfix = "]")
    println("String: $joined") // [A, B, C]
    // Độ phức tạp: O(n)

    println("\n========== TÓM TẮT ĐỘ PHỨC TẠP ==========")
    // =========================================================
    //  TÓM TẮT ĐỘ PHỨC TẠP CỦA CÁC THAO TÁC
    // =========================================================

    println("""
        |================================================
        |  TÓM TẮT ĐỘ PHỨC TẠP BIG O
        |================================================
        |  
        |  O(1) - Truy xuất theo index, gán giá trị, size, isEmpty
        |  O(log n) - Binary Search (khi list đã sắp xếp)
        |  O(n) - Duyệt, tìm kiếm tuyến tính, filter, map, insert/delete ở giữa
        |  O(n log n) - Sắp xếp (sort, sorted)
        |  O(n) trung bình - add (khi cần cấp phát lại bộ nhớ)
        |
        |  * Lưu ý: add() vào cuối thường là O(1) trong hầu hết trường hợp
        |  * insert() vào đầu hoặc giữa luôn là O(n)
        |  * Xóa cuối (removeLast) là O(1), xóa đầu/giữa là O(n)
        =================================================
    """.trimMargin())
}

/**
 * =========================================================
 *  BÀI TẬP THỰC HÀNH (KHÔNG CÓ ĐÁP ÁN)
 * =========================================================
 *
 * Hãy thử viết code cho các yêu cầu sau:
 *
 * 1. Cho list = [5, 2, 8, 1, 9], hãy tìm:
 *    - Phần tử lớn nhất
 *    - Phần tử nhỏ nhất
 *    - Tổng của tất cả phần tử
 *
 * 2. Cho list = [1, 2, 3, 4, 5], hãy tạo:
 *    - List mới với mỗi phần tử được nhân đôi
 *    - List mới chỉ chứa các số chẵn
 *    - List mới chứa bình phương của từng số
 *
 * 3. Cho list = ["apple", "banana", "apricot", "blueberry", "avocado"]
 *    - Nhóm các từ theo chữ cái đầu tiên
 *    - Lọc ra các từ có độ dài > 6
 *    - Sắp xếp theo độ dài tăng dần
 *
 * 4. Cho list = [1, 2, 2, 3, 3, 3, 4, 4, 4, 4]
 *    - Loại bỏ các phần tử trùng lặp
 *    - Đếm số lần xuất hiện của mỗi phần tử
 *
 * 5. Cho 2 list: [1, 2, 3] và [4, 5, 6]
 *    - Gộp 2 list thành 1 list
 *    - Tạo list chứa các cặp (1,4), (2,5), (3,6)
 *    - Tính tổng từng cặp: [5, 7, 9]
 *
 * 6. Viết hàm kiểm tra 1 list có phải là palindrome không
 *    (đọc xuôi và đọc ngược đều giống nhau)
 *    Ví dụ: [1, 2, 3, 2, 1] -> true
 *           [1, 2, 3, 4, 5] -> false
 */

/**
 * =========================================================
 *  LỜI KHUYÊN KHI LÀM VIỆC VỚI LIST
 * =========================================================
 *
 * 1. Chọn đúng loại List:
 *    - Dùng List (immutable) nếu không cần thay đổi
 *    - Dùng MutableList nếu cần thêm/xóa/sửa
 *
 * 2. Hiểu rõ độ phức tạp:
 *    - Truy xuất theo index: O(1) - cực nhanh
 *    - Thêm/xóa ở đầu: O(n) - chậm với list lớn
 *    - Thêm/xóa ở cuối: O(1) trung bình - nhanh
 *    - Tìm kiếm: O(n) - chậm với list lớn
 *
 * 3. Sử dụng đúng hàm:
 *    - Dùng find thay vì filter khi chỉ cần 1 kết quả
 *    - Dùng any để kiểm tra có phần tử thỏa mãn không
 *    - Dùng all để kiểm tra tất cả phần tử thỏa mãn
 *    - Dùng none để kiểm tra không có phần tử nào thỏa mãn
 *
 * 4. Tối ưu hiệu năng:
 *    - Với list lớn, cân nhắc dùng Set thay vì List nếu cần tìm kiếm nhanh (O(1))
 *    - Với list có thứ tự, dùng Binary Search (O(log n)) thay vì tìm kiếm tuyến tính
 *    - Tránh thêm/xóa ở đầu list khi làm việc với dữ liệu lớn
 *
 * 5. Code dễ đọc:
 *    - Đặt tên biến rõ ràng (numbers, items, result...)
 *    - Dùng lambda với tên tham số có ý nghĩa (không chỉ dùng it khi code phức tạp)
 *    - Tách thành các hàm nhỏ nếu logic phức tạp
 *
 * 6. Kiểm tra null:
 *    - Sử dụng firstOrNull(), lastOrNull(), find() thay vì first(), last()
 *    - Kiểm tra list không rỗng trước khi gọi min(), max()
 *
 * 7. Hiệu năng với dữ liệu lớn:
 *    - Tránh tạo nhiều list tạm (intermediate list) không cần thiết
 *    - Sử dụng sequence (lazy evaluation) khi xử lý chain các hàm như map + filter
 *    - Ví dụ: list.asSequence().map { it*2 }.filter { it > 5 }.toList()
 */