package com.mediabox.mediabox.Security.config;

public final class jwtconst {

    public static final String JWT_HEADER = "Authorization";
    private static final String SECRET_KEY = "The Authorization Key for Meidabox Application is 1122@3#^$&^QRWQTJBVHVC";

    private jwtconst() {
    }

    public static String getJWT_HEADER() {
        return JWT_HEADER;
    }

    public static String getSecretKey() {
        return SECRET_KEY;
    }

}
