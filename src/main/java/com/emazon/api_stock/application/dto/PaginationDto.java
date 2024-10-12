package com.emazon.api_stock.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginationDto<T> {
    private List<T> contentList;
    Long totalElement;
}
