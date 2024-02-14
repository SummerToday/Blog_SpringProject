package me.InKyung.Blog.springbootdeveloper.controller;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

import me.InKyung.Blog.springbootdeveloper.dto.ArticleListViewResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import me.InKyung.Blog.springbootdeveloper.service.*;

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
}
