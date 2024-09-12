package com.emazon.api_stock.infraestructure.util;

public class ConstantsInfraestructure {

    public static final String FIELD_NAME = "Electronics";
    public static final String FIELD_DESCRIPTION = "Category for electronics";
    public static final String FIELD_BRAND_DESCRIPTION = "brand for electronics";
    public static final String URL_CREATE_CATEGORY = "/stock/category/registry";
    public static final String URL_CREATE_BRAND = "/stock/brand/registry";
    public static final String URL_GET_CATEGORY = "/stock/category/";
    public static final String URL_GET_BRAND = "/stock/brand/";
    public static final String JSON_REQUEST = "{\"name\": \"Electronics\", \"description\":" +
            " \"Category for electronics\"}";
    public static final String JSON_BRAND_REQUEST = "{\"name\": \"Electronics\", \"description\":" +
            " \"brand for electronics\"}";
    public static final String CATEGORY_EXISTS = "There is already a category with that name";
    public static final String BRAND_EXISTS = "There is already a brand with that name";
    public static final String INVALID_NAME = "Invalid argument";
    public static final String INVALID_DESCRIPTION = "Invalid argument";
    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data found in the database";
    public static final String NEGATIVE_NOT_ALLOWED = "The page and size fields cannot be negative or null.";
    public static final String FIELD_ARTICLE_DESCRIPTION = "article for electronics";
    public static final String FIELD_CATEGORIES_INVALID_NUMBER = "An article cannot have more than 3 categories.";
    public static final String URL_CREATE_ARTICLE = "/stock/article/registry";
    public static final String URL_GET_ARTICLE = "/stock/article/";
    public static final String JSON_ARTICLE_REQUEST="{ \"name\": \"TEST\", \"description\": \"TEST\"," +
            " \"quantity\": \"1\", \"" +
            "price\": \"20000\", \"idbrand\": \"1\", \"categories\": [10,9,10] }";
    public static final String FIELD_CATEGORIES_NULL="The 'categories' field cannot be empty.";
    public static final String REPEATED_CATEGORIES="Categories cannot be repeated";
    public static final String FIELD_ARTICLES_DESCRIPTION="articles for electronics";
    public static final String ARTICLE_EXISTS="There is already an article with that name";
    public static final String MESSAGESS = "$.message";
    public static final String MESSAGESS_NAME = "$[0].name";
    public static final String MESSAGESS_DESCRIPTION = "$[0].description";
    public static final String MESSAGESS_QUANTITY = "$[0].quantity";
    public static final String PAGE = "page";
    public static final String SIZE = "size";
    public static final String VALUE_UNO = "1";
    public static final Integer VALUE_0 = 0;
    public static final Integer VALUE_1 = 1;
    public static final Integer VALUE_2 = 2;
    public static final Integer VALUE_3 = 3;
    public static final boolean VALUE_FALSE = false;
    public static final String ARTICLE = "article";
    public static final String COMILLAS = "";
    public static final String ARTICLE_NAME = "a.name";
    public static final String NAME = "name";
    public static final double PRICE = 20000;


    private ConstantsInfraestructure() {
    }
}
