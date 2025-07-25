package com.ltm.extracurricular.config;

public class Constants {

    private Constants() {
    }

    public static final boolean IS_CROSS_ALLOW = true;

    public static final String JWT_SECRET = "LTM-QLHDNK";
    public static final long JWT_EXPIRATION = 160 * 60 * 60; // 7 day

    // config endpoints public
    public static final String[] ENDPOINTS_PUBLIC = new String[] {
            "/",
            "/login/**",
            "/error/**",
    };

    // config endpoints for USER role
    public static final String[] ENDPOINTS_WITH_ROLE = new String[] {
            "/user/**"
    };

    // user attributies put to token
    public static final String[] ATTRIBUTIES_TO_TOKEN = new String[] {
            "id",
            "username",
    };
}
