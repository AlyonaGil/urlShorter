package ru.stroki.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    @NotBlank
    @Size(max = 25)
    @Column(name = "LOGIN")
    private String login;
    @NotNull
    @NotBlank
    @Column(name = "HASH")
    private String hash;
    @Column(name = "CREATE_DATE", updatable = false)
    private LocalDateTime createDate;
    @OneToMany(mappedBy = "user")
    private List<Url> urls;

    @PrePersist
    void onCreate() {
        this.setCreateDate(LocalDateTime.now().withNano(0));
    }
}
