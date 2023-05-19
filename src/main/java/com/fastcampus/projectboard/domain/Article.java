package com.fastcampus.projectboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Article extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne(optional = false) @JoinColumn(name = "userId")
    private UserAccount userAccount;

    @Setter @Column(nullable = false)
    private String title; // 제목
    @Setter @Column(nullable = false, length = 10000)
    private String content; // 본문

    @Setter
    private String hashtag; // 해시태그

    @Setter
    private String category;


    @ToString.Exclude
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();


    protected Article() {}

    private Article(UserAccount userAccount, String title, String content, String hashtag, String category) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.category = category;
    }

    public static Article of(UserAccount userAccount, String title, String content, String hashtag, String category) {
        return new Article(userAccount, title, content, hashtag, category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return this.getId() != null && this.getId().equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

}
