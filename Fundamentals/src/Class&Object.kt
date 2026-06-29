// =========================================================================
// 5. LỚP VÀ ĐỐI TƯỢNG (CLASSES & OBJECTS)
// =========================================================================
// !--- 5.1. Class cơ bản (Constructor mặc định) ---
//* Định nghĩa các thuộc tính (properties) ngay trong hàm khởi tạo (Primary Constructor)
class Person(val name: String, var age: Int) {

    // Khối init: Chạy ngay khi một đối tượng (instance) được tạo ra từ Class này
    init {
        println("Một đối tượng Person mới vừa được khởi tạo cho: $name")
    }

    // Phương thức (Method) của Class
    fun introduce() {
        println("Xin chào, mình là $name, năm nay mình $age tuổi.")
    }
}

//! --- 5.2. Data Class ---
// Thường dùng để định nghĩa các lớp chỉ chứa dữ liệu (Model/DTO).
// Kotlin tự động sinh ra các hàm tiện ích như: .toString(), .equals(), .copy(),...
data class User(val id: Int, val username: String, val email: String)

// --- 5.3. Object (Singleton Pattern) ---
// Từ khóa `object` giúp tạo ra một class và đồng thời khởi tạo luôn 1 instance duy nhất của nó.
// Thường dùng cho các cấu hình hệ thống, kết nối cơ sở dữ liệu (Database).
object AppConfig {
    val databaseName = "MyAndroidAppDB"
    val maxLoginRetries = 5

    fun showConfig() {
        println("Database: $databaseName | Max Retries: $maxLoginRetries")
    }
}

// ! Hàm bổ trợ để chạy thử nghiệm các Class/Object trên
fun classesAndObjects() {
    println("--- MINH HỌA CLASS & OBJECT ---")

    //* Khởi tạo một đối tượng từ class thông thường (Kotlin KHÔNG cần dùng từ khóa 'new')
    val person1 = Person("Khánh", 22)
    person1.introduce()

    // Thay đổi thuộc tính var của đối tượng
    person1.age = 23
    println("Tuổi sau khi cập nhật của ${person1.name} là: ${person1.age}")

    println("\n--- MINH HỌA DATA CLASS ---")
    val user1 = User(1, "quzkhanh", "khanh@gmail.com")

    // Thử nghiệm in đối tượng Data Class (Nó tự hiển thị chuỗi thông tin rất đẹp)
    println(user1.toString()) // Kết quả: User(id=1, username=quzkhanh, email=khanh@gmail.com)

    // Sao chép đối tượng và chỉ sửa đổi 1 vài thuộc tính bằng hàm .copy()
    val user2 = user1.copy(email = "khanhnew@gmail.com")
    println("User mới sau khi copy và sửa email: $user2")

    println("\n--- MINH HỌA SINGLETON OBJECT ---")
    // Gọi trực tiếp thông qua tên Object mà không cần thông qua bước khởi tạo
    println("Tên database lấy từ AppConfig: ${AppConfig.databaseName}")
    AppConfig.showConfig()
}

fun main() {
    classesAndObjects()
}