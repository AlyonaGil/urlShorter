package ru.stroki.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "URL")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Url {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    @NotBlank
    @Column(name = "LONG_URL")
    private String longUrl;
    @Column(name = "SHORT_URL")
    private String shortUrl;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(name = "CREATE_DATE", updatable = false)
    private LocalDateTime createDate;
    @Column(name = "DELETE_DATE", insertable = false)
    private LocalDateTime deleteDate;
    @OneToMany(mappedBy = "url")
    private List<Transition> transitions;

    @PrePersist
    void onCreate() {
        this.setCreateDate(LocalDateTime.now().withNano(0));
    }
}
