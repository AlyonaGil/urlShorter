package ru.stroki.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSITION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Url url;
    @Column(name = "CREATE_DATE", updatable = false)
    private LocalDateTime createDate;
    @NotNull
    @NotBlank
    @Column(name = "REFERER")
    private String referer;

    @PrePersist
    void onCreate() {
        this.setCreateDate(LocalDateTime.now().withNano(0));
    }
}
