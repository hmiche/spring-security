package account.services;

import account.dto.UserRequest;
import account.entities.User;
import account.repositories.RoleRepository;
import account.repositories.UserRepository;
import shared.services.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService extends BaseService <User, Long>{

    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final ModelMapper modelMapper;
    final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository repository,
                       PasswordEncoder passwordEncoder,
                       ModelMapper modelMapper,
                       RoleRepository roleRepository,
                       UserRepository userRepository
    ) {
        super(repository);
        this.passwordEncoder =passwordEncoder;
        this.modelMapper =modelMapper;
        this.roleRepository =roleRepository;
        this.userRepository =userRepository;
    }
    public User store(UserRequest.AddRequest userRequest){
        User user = this.modelMapper.map(userRequest,User.class);
        user.setRole(this.roleRepository.findByName(userRequest.getRole()).orElseThrow());
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User update(UserRequest.UpdateRequest userRequest){
        User user = this.userRepository.findById(userRequest.getId()).orElseThrow();
        user.setRole(this.roleRepository.findByName(userRequest.getRole()).orElseThrow());
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
