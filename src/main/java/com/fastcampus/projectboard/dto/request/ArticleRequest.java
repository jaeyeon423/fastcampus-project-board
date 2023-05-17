package com.fastcampus.projectboard.dto.request;

import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.UserAccountDto;

public record ArticleRequest(
        String title,
        String content,
        String hashtag,
        String category,
        String region
) {

    public static ArticleRequest of(String title, String content, String hashtag, String category, String region) {
        return new ArticleRequest(title, content, hashtag, category, region);
    }

    public ArticleDto toDto(UserAccountDto userAccountDto) {
        return ArticleDto.of(
                userAccountDto,
                title,
                content,
                hashtag,
                category,
                region
        );
    }

}
