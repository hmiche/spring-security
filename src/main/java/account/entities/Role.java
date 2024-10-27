package account.entities;
import jakarta.persistence.*;
import lombok.*;
import shared.entities.BaseEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table( name = "roles",uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Role extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String label;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    Set<Permission> permissions = new HashSet<>();
}
