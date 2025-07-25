package com.ltm.extracurricular.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {

    /**
     * Vai trò người dùng thông thường.
     */
    STUDENT(0),
    TEACHER(1),

    /**
     * Vai trò quản trị viên hệ thống.
     */
    ADMIN(2);

    /**
     * Giá trị số nguyên tương ứng với vai trò.
     */
    private final int value;
}
