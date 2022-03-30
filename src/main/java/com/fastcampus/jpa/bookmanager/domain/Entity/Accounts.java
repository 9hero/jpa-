package com.fastcampus.jpa.bookmanager.domain.Entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
@DynamicInsert
// entitylitener는 빈을 주입받을 수 없음 컨텍스트에서 꺼내와야함
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Accounts extends BasicTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    private String password;
    private String username;
    private LocalDateTime lastLogin;
    @Enumerated(value = EnumType.STRING)
    private Role userRole;

    public enum Role {

        ROLE_ADMIN,
        ROLE_USER
    }

/* Entity Listener 이벤트 발생시 처리(동작)
    @PrePersist insert method 전에 실행
    @PreUpdate merge 이전
    @PreRemove delete 이전
    @PostPersist 이후
    @PostUpdate
    @PostRemove
    @PostLoad select method 직후



    @PrePersist
    public void prePersist(){
        System.out.println(">>> prePersist <<<");
        userRole = Role.ROLE_USER;
    }

    @PreUpdate
    public void PreUpdate(){
        System.out.println(">>> PreUpdate <<<");
    }
    @PreRemove
    public void PreRemove(){
        System.out.println(">>> PreRemove <<<");
    }
    @PostPersist
    public void PostPersist(){
        System.out.println(">>> PostPersist <<<");
    }
    @PostUpdate
    public void PostUpdate(){
        System.out.println(">>> PostUpdate <<<");
    }
    @PostRemove
    public void PostRemove(){
        System.out.println(">>> PostRemove <<<");
    }
    @PostLoad
    public void PostLoad(){
        System.out.println(">>> PostLoad <<<");
    }
//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> addresses;

 */

}
