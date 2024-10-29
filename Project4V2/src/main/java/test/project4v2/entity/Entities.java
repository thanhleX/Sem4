package test.project4v2.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Entities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createDate;

    @Column(name = "updated_at")
    private LocalDateTime updateDate;


    protected Entities(Long id) {
        this.id = id;
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }
    @PrePersist
    protected void onCreate() {
        createDate = LocalDateTime.now();
        updateDate = LocalDateTime.now();
    }


    @PreUpdate
    protected void onUpdate() {
        updateDate = LocalDateTime.now();
    }
}