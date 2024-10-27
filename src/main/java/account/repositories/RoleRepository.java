package account.repositories;

import account.entities.Role;
import shared.repositories.BaseRepository;

import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<Role,Long>{
    Optional<Role> findById(Long id);
    Optional<Role> findByName(String name);
}
