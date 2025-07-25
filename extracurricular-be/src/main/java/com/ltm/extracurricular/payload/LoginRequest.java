package com.ltm.extracurricular.payload;

import lombok.Data;

@Data
public class LoginRequest {

    /**
     * Tên người dùng (username) để đăng nhập.
     * Đây là thông tin cần thiết để nhận dạng người dùng trong hệ thống.
     */
    private String username;

    /**
     * Mật khẩu của người dùng.
     * Mật khẩu được sử dụng để xác thực người dùng và đảm bảo rằng chỉ có người dùng hợp lệ mới có thể truy cập tài khoản.
     */
    private String password;
}
