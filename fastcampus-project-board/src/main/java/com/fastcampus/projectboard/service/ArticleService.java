package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.ArticleWithCommentsDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable){
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtagContaining(searchKeyword, pageable).map(ArticleDto::from);
        };
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long articleId){
        return articleRepository.findById(articleId).map(ArticleWithCommentsDto::from).orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다."));

    }

    public void saveArticle(ArticleDto articleDto){
        articleRepository.save(articleDto.toEntity());
    }

    public void updateArticle(ArticleDto articleDto){

        try {
            Article article = articleRepository.getReferenceById(articleDto.id());
            if(articleDto.title() != null) {
                article.setTitle(articleDto.title());
            }
            if(articleDto.content() != null) {
                article.setContent(articleDto.content());
            }
            article.setHashtag(articleDto.hashtag());
        }catch (EntityNotFoundException e){
            log.warn("게시글 업데이트 실패 dto = {}", articleDto);
        }
    }


    public void deleteArticle(Long articleId){
        articleRepository.deleteById(articleId);
    }

    public Long getArticleCount(){
        return articleRepository.count();
    }
}
