package vn.hcmute.baitap01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Ánh xạ các View
    private EditText editTextArray, editTextString;
    private Button buttonArray, buttonString;
    private TextView textViewArrayResult, textViewStringResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các View từ layout
        editTextArray = findViewById(R.id.editTextArray);
        buttonArray = findViewById(R.id.buttonArray);
        textViewArrayResult = findViewById(R.id.textViewArrayResult);

        editTextString = findViewById(R.id.editTextString);
        buttonString = findViewById(R.id.buttonString);
        textViewStringResult = findViewById(R.id.textViewStringResult);

        // Set padding cho hệ thống bar khi có cửa sổ ẩn (notch, system bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Tìm Button và thiết lập sự kiện nhấn
        Button button = findViewById(R.id.button); // ID của Button Ẩn tiêu đề
        // Chuyển sang Activity AnThanhTieuDeAndroid khi bấm nút
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AnThanhTieuDeAndroid.class);
            startActivity(intent);
        });

        // Xử lý sự kiện cho buttonArray: phân loại số chẵn và lẻ
        buttonArray.setOnClickListener(v -> {
            String input = editTextArray.getText().toString();
            if (input.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập mảng số!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tách chuỗi đầu vào thành mảng
            String[] numbers = input.split(",");

            // Khởi tạo các StringBuilder để chứa số chẵn và số lẻ
            StringBuilder evenNumbers = new StringBuilder("Số chẵn: ");
            StringBuilder oddNumbers = new StringBuilder("Số lẻ: ");

            // Duyệt qua từng số trong mảng
            for (String number : numbers) {
                // Loại bỏ khoảng trắng và kiểm tra nếu số hợp lệ
                try {
                    int num = Integer.parseInt(number.trim());

                    // Kiểm tra số chẵn hoặc lẻ
                    if (num % 2 == 0) {
                        evenNumbers.append(num).append(" ");
                    } else {
                        oddNumbers.append(num).append(" ");
                    }
                } catch (NumberFormatException e) {
                    // Nếu có lỗi khi chuyển đổi thành số
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đúng định dạng số!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            // Hiển thị kết quả
            textViewArrayResult.setText(evenNumbers.toString().trim() + "\n" + oddNumbers.toString().trim());
        });


        // Xử lý sự kiện cho buttonString: đảo ngược và in hoa chuỗi
        buttonString.setOnClickListener(v -> {
            String input = editTextString.getText().toString();
            if (input.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập chuỗi ký tự!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Đảo ngược chuỗi
            String[] words = input.split(" ");
            StringBuilder reversed = new StringBuilder();
            for (int i = words.length - 1; i >= 0; i--) {
                reversed.append(words[i]).append(" ");
            }

            // Chuyển thành chữ hoa
            String result = reversed.toString().trim().toUpperCase();
            textViewStringResult.setText(result);
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        });
    }
}
