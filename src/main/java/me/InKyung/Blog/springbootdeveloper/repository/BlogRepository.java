package me.InKyung.Blog.springbootdeveloper.repository;

import me.InKyung.Blog.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
