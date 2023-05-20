package com.fastcampus.projectboard.dto.request;

import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.UserAccountDto;

public record ArticleRequest(
        String title,
        String content,
        String category
) {

    public static ArticleRequest of(String title, String content, String category) {
        return new ArticleRequest(title, content, category);
    }

    public ArticleDto toDto(UserAccountDto userAccountDto) {
        return ArticleDto.of(
                userAccountDto,
                title,
                content,
                category
        );
    }

}
