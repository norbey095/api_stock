package com.emazon.api_stock.application.handler.article;

import com.emazon.api_stock.application.dto.ResponseSuccess;
import com.emazon.api_stock.application.dto.article.ArticleRequestDto;
import com.emazon.api_stock.application.dto.article.ArticleResponseDto;
import com.emazon.api_stock.application.mapper.ArticleMapper;
import com.emazon.api_stock.application.util.Constants;
import com.emazon.api_stock.domain.api.IArticleServicePort;
import com.emazon.api_stock.domain.model.ArticleSave;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleHandler implements IArticleHandler {

    private final IArticleServicePort articleServicePort;
    private final ArticleMapper articleMapper;


    @Override
    public ResponseSuccess saveArticle(ArticleRequestDto articleRequestDto) {
        ArticleSave article = articleMapper.articleDtoToArticle(articleRequestDto);
        articleServicePort.saveArticle(article);
        return new ResponseSuccess(Constants.MESSAGES_SUCCESS);
    }

    @Override
    public List<ArticleResponseDto> getAllArticles(Integer page, Integer size, boolean descending, String filterBy) {
        return articleMapper.toArticleDtoList(articleServicePort.getAllArticles(page,size,descending,filterBy));
    }
}
