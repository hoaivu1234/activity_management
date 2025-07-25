package com.ltm.extracurricular.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {

    /**
     * Mã token truy cập.
     * Đây là mã xác thực người dùng đã đăng nhập thành công và có quyền truy cập vào các tài nguyên được bảo vệ.
     */
    private String accessToken;

    /**
     * Kiểu của token.
     * Thông thường sẽ là "Bearer" để xác nhận token truy cập.
     */
    private String tokenType;
    private String role;

    /**
     * Bản đồ chứa các lỗi nếu có trong quá trình đăng nhập.
     * Nếu đăng nhập không thành công, các lỗi này sẽ được trả về dưới dạng thông điệp chi tiết.
     */
    private String error;

    /**
     * Constructor tạo đối tượng LoginResponse với accessToken.
     * Sử dụng kiểu token mặc định là "Bearer".
     *
     * @param accessToken Mã token truy cập cho người dùng
     */
    public LoginResponse(String accessToken, String tokenType, String role) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.role = role;
    }

    public LoginResponse(String error) {
        this.error = error;
    }
}
