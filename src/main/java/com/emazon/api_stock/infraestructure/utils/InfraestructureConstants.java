package com.emazon.api_stock.infraestructure.utils;

import lombok.Getter;;

@Getter
public class InfraestructureConstants {
    public static final String CATEGORY_ALREADY_EXISTS = "There is already a category with that name";
    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data found in the database";
    public static final String NEGATIVE_NOT_ALLOWED = "The page and size fields cannot be negative or null.";
    public static final String BRAND_ALREADY_EXISTS = "There is already a brand with that name";
    public static final String ARTICLE_ALREADY_EXISTS = "There is already an article with that name";
    public static final String VALUE_FALSE = "false";
    public static final String VALUE_ARTICLE = "article";
    public static final String NAME = "name";
    public static final String BRAND = "brand";
    public static final String CATEGORY = "category";
    public static final String CATEGORY_NAME = "c.name";
    public static final String BRAND_NAME = "b.name";
    public static final String ARTICLE_NAME = "a.name";
    public static final String CATEGORIES = "categories";
    public static final String ID_BRAND = "idbrand";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final Integer VALUE_7 = 7;
    public static final String AUTHORITIES = "authorities";
    public static final String JWT_SECRET = "${jwt.secret}";
    public static final String COMA = "";
    public static final String ADMIN = "ADMIN";
    public static final String INVALID_HEADER = "InvalidHeader";
    public static final String FILTER_INTERNAL = "doFilterInternal";
    public static final String EMAIL_EJEM = "test@example.com";
    public static final String NAME_INVALID = "The extracted username is not the expected one.";
    public static final Integer VALUE_100 = 1000;
    public static final Integer VALUE_60 = 60;
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    private InfraestructureConstants() {
    }

}
