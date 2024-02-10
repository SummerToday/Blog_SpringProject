package me.InKyung.Blog.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.InKyung.Blog.springbootdeveloper.domain.Article;
import me.InKyung.Blog.springbootdeveloper.dto.AddArticleRequest;
import me.InKyung.Blog.springbootdeveloper.dto.ArticleResponse;
import me.InKyung.Blog.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles") // HTTP 메서드가 POST일 때 전달 받은 URL과 동일하면 해당 메서드로 매핑
    public ResponseEntity<Article> addArticle(
            @RequestBody AddArticleRequest request){ // HTTP를 요청할 때 응답에 해당하는 값을 @RequestBody 애너테이션이 붙은 대상 객체인 AddArticleRequest에 매핑한다.
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle); // 요청한 자원이 성공적으로 생성되었으면 저장된 블로그 글 정보를 응답 객체에 담아 반환.
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

}
