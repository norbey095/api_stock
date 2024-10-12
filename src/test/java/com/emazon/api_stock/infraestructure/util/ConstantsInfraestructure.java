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
    public static final String CATEGORY_EXISTS = "Ya existe una categoría con ese nombre";
    public static final String BRAND_EXISTS = "There is already a brand with that name";
    public static final String INVALID_NAME = "Invalid argument";
    public static final String INVALID_DESCRIPTION = "Invalid argument";
    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No se encontraron datos";
    public static final String NEGATIVE_NOT_ALLOWED = "Los campos de página y tamaño no pueden ser negativos o nulos.";
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
    public static final String VALUE_DOS = "2";
    public static final Integer VALUE_0 = 0;
    public static final Integer VALUE_1 = 1;
    public static final Integer VALUE_2 = 2;
    public static final Integer VALUE_3 = 3;
    public static final String TRUE = "true";
    public static final boolean VALUE_FALSE = false;
    public static final String ARTICLE = "article";
    public static final String COMILLAS = "";
    public static final String ARTICLE_NAME = "a.name";
    public static final String NAME = "name";
    public static final double PRICE = 20000;
    public static final String USER_NAME = "testuser";
    public static final String ADMIN = "ADMIN";
    public static final String CLIENT = "CLIENT";
    public static final String AUX_WAREHOUSE = "AUX_WAREHOUSE";
    public static final String ARTICLE_DOES_EXISTS="The article does not exist.";
    public static final String ACCESS_DENE = "Access denied";
    public static final String URL_UPDATE_ARTICLE = "/stock/article/update";
    public static final String URL_SUBTRACT_ARTICLE = "/stock/article/subtract";
    public static final String URL_GET_A_ARTICLE = "/stock/article/{articleId}";
    public static final String URL_GET_ITEMSCART = "/stock/article/getItemsCart";
    public static final String URL_GET_PRICE = "/stock/article/getPriceByIds";


    private ConstantsInfraestructure() {
    }
}
