package com.fastcampus.projectboard.controller;

import com.fastcampus.projectboard.domain.constant.FormStatus;
import com.fastcampus.projectboard.domain.constant.SearchType;
import com.fastcampus.projectboard.dto.UserAccountDto;
import com.fastcampus.projectboard.dto.request.ArticleRequest;
import com.fastcampus.projectboard.dto.response.ArticleResponse;
import com.fastcampus.projectboard.dto.response.ArticleWithCommentsResponse;
import com.fastcampus.projectboard.dto.security.BoardPrincipal;
import com.fastcampus.projectboard.service.ArticleService;
import com.fastcampus.projectboard.service.PaginationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final ArticleService articleService;
    private final PaginationService paginationService;

    @GetMapping
    public String articles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map){

        log.info("[jaeyeon] searchType = {}", searchType);
        log.info("[jaeyeon] searchValue = {}", searchValue);

        Page<ArticleResponse> articles = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());

        searchType = SearchType.CATEGORY;

        searchValue = "humor";
        Page<ArticleResponse> humors = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);

        searchValue = "politics";
        Page<ArticleResponse> politics = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);

        searchValue = "economy";
        Page<ArticleResponse> economy = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);

        searchValue = "society";
        Page<ArticleResponse> society = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);

        searchValue = "sports";
        Page<ArticleResponse> sports = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);

        searchValue = "entertainer";
        Page<ArticleResponse> entertainer = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);

        map.addAttribute("articles", articles);
        map.addAttribute("paginationBarNumbers", barNumbers);
        
        map.addAttribute("humors", humors);
        map.addAttribute("politics", politics);
        map.addAttribute("society", society);
        map.addAttribute("economy", economy);
        map.addAttribute("sports", sports);
        map.addAttribute("entertainer", entertainer);

        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, ModelMap map){
        ArticleWithCommentsResponse article = ArticleWithCommentsResponse.from(articleService.getArticleWithComments(articleId));

        String hashtag = article.hashtag();

        log.info("[jaeyeon] hashtag = {}", hashtag);
        map.addAttribute("article", article);
        map.addAttribute("articleComments", article.articleCommentsResponse());
        map.addAttribute("totalCount", articleService.getArticleCount());

        return "articles/detail";
    }

    @GetMapping("/form")
    public String articleForm(ModelMap map){
        map.addAttribute("fromStatus", FormStatus.CREATE);

        return "articles/form";
    }

    @PostMapping("/form")
    public String postNewArticle(ArticleRequest articleRequest, @AuthenticationPrincipal BoardPrincipal boardPrincipal){
        articleService.saveArticle(articleRequest.toDto(boardPrincipal.toDto()));

        return "redirect:/articles";
    }

    @GetMapping("/{articleId}/form")
    public String updateArticleForm(@PathVariable Long articleId, ModelMap map){
        ArticleResponse article = ArticleResponse.from(articleService.getArticle(articleId));

        map.addAttribute("article", article);
        map.addAttribute("formStatus", FormStatus.UPDATE);

        return "articles/form";
    }
    @PostMapping ("/{articleId}/form")
    public String updateArticle(@PathVariable Long articleId, @AuthenticationPrincipal BoardPrincipal boardPrincipal, ArticleRequest articleRequest) {
        // TODO: 인증 정보를 넣어줘야 한다.
        articleService.updateArticle(articleId, articleRequest.toDto(boardPrincipal.toDto()));

        return "redirect:/articles/" + articleId;
    }

    @PostMapping("/{articleId}/delete")
    public String deleteArticle(@PathVariable Long articleId, @AuthenticationPrincipal BoardPrincipal boardPrincipal){
        articleService.deleteArticle(articleId, boardPrincipal.getUsername());

        return "redirect:/articles";
    }
}
