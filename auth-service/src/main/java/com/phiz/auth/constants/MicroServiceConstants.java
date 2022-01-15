package com.phiz.auth.constants;

public class MicroServiceConstants {

    public static final String LOGIN_MICROSERVICE = "/user-service/api/user/login/username";

    public static final String USER_MICROSERVICE = "user-service";

    public static final String BASE_API = "/api";

    public interface UserMicroServiceConstants {
        String FETCH_USER_BY_USERNAME = "/user/{username}";
    }
}
