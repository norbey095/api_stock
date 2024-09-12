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


    private InfraestructureConstants() {
    }

}
