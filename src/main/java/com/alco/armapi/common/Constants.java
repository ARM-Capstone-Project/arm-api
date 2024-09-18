package com.alco.armapi.common;

public class Constants {
    private Constants() {
        throw new IllegalStateException(UTILITY_CLASS);
    }
    public static final String UTILITY_CLASS = "Utility Class";


    public static final String ROLE_ADMIN = "ADMIN";

    public static final String ROLE_USER = "USER";

    public static final String ROLE_MANAGER = "MANAGER";

    public static final String ROLE_OPERATOR = "OPERATOR";


}


//INSERT INTO roles(id,name) VALUES('eaef4f26-b4d9-11ed-84bd-fa9067b2300f','ADMIN');
//INSERT INTO roles(id,name) VALUES('48283894-b4dd-11ed-84bd-fa9067b2300f','USER');
//INSERT INTO roles(id,name) VALUES('54d5fb3a-b4dd-11ed-84bd-fa9067b2300f','MANAGER');
//INSERT INTO roles(id,name) VALUES('96bf9d2e-cd3f-11ed-bde4-38c9863c66b0','OPERATOR');