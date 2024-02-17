package me.InKyung.Blog.springbootdeveloper.controller;

import me.InKyung.Blog.springbootdeveloper.domain.Article;
import me.InKyung.Blog.springbootdeveloper.dto.ArticleViewResponse;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

import me.InKyung.Blog.springbootdeveloper.dto.ArticleListViewResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import me.InKyung.Blog.springbootdeveloper.service.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);

        return "articleList";
    }
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);

        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }
    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }
}
