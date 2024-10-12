package com.emazon.api_stock.domain.util;

public class Constants {

    public static final String FIELD_NAME_NULL = "El campo 'nombre' no puede estar vacío.";
    public static final String FIELD_NAME_MAX = "El campo 'nombre' no puede exceder de 50 caracteres.";
    public static final String FIELD_DESCRIPTION_NULL = "El campo 'descripción' no puede estar vacío";
    public static final String FIELD_DESCRIPTION_CATEGORY_MAX = "El campo 'descripción' no puede superar los 90 caracteres";
    public static final String FIELD_DESCRIPTION_BRAND_MAX = "El campo 'descripción' no puede superar los 120 caracteres";
    public static final String FIELD_CATEGORIES_NULL = "El campo 'categorías' no puede estar vacío.";
    public static final String FIELD_CATEGORIES_INVALID_NUMBER = "Un artículo no puede tener más de 3 categorías.";
    public static final String REPEATED_CATEGORIES = "Las categorías no se pueden repetir";
    public static final Integer VALUE_3 = 3;
    public static final Integer VALUE_1 = 1;
    public static final Integer VALUE_120 = 120;
    public static final Integer VALUE_50 = 50;
    public static final Integer VALUE_90 = 90;
    public static final Integer VALUE_0 = 0;

    private Constants() {
    }
}
