package me.InKyung.Blog.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.InKyung.Blog.springbootdeveloper.domain.Article;
import me.InKyung.Blog.springbootdeveloper.dto.AddArticleRequest;
import me.InKyung.Blog.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가 -> 생성자가 하나 이상 존재할 시 @Autowierd가 자동으로 생성 -> 의존성 주입.
@Service // 빈으로 등록
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){ // 블로그 글 추가 메소드
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }
}
