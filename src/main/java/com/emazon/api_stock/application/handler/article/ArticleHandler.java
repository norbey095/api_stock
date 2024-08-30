package com.emazon.api_stock.application.handler.article;

import com.emazon.api_stock.application.dto.ResponseSuccess;
import com.emazon.api_stock.application.dto.article.ArticleRequestDto;
import com.emazon.api_stock.application.mapper.ArticleMapper;
import com.emazon.api_stock.domain.api.IArticleServicePort;
import com.emazon.api_stock.domain.model.Article;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleHandler implements IArticleHandler {

    private final IArticleServicePort articleServicePort;
    private final ArticleMapper articleMapper;


    @Override
    public ResponseSuccess saveArticle(ArticleRequestDto articleRequestDto) {
        Article article = articleMapper.articleDtoToArticle(articleRequestDto);
        articleServicePort.saveArticle(article);
        return new ResponseSuccess("Article created successfully");
    }
}
