package com.fastcampus.projectboard.dto.response;

import com.fastcampus.projectboard.dto.ArticleDto;

import java.time.LocalDateTime;
import java.util.Set;

public record ArticleResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        String createdAt,
        String email,
        String nickname
) {
    public static ArticleResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String email, String nickname){
        String date = createdAt.toString().split("T")[0];
        return new ArticleResponse(id, title, content, hashtag, date, email, nickname);
    }

    public static ArticleResponse from(ArticleDto dto){
        String nickname = dto.userAccountDto().nickname();
        if(nickname == null || nickname.isBlank()){
            nickname = dto.userAccountDto().userId();
        }

        String date = dto.createdAt().toString().split("T")[0];

        return new ArticleResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtag(),
                date,
                dto.userAccountDto().email(),
                nickname
        );
    }
}
