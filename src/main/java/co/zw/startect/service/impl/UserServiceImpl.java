//package co.zw.startect.service.impl;
//
//import co.zw.startect.entity.Role;
//import co.zw.startect.entity.User;
//import co.zw.startect.repository.UserRepository;
//import co.zw.startect.service.UserService;
//import co.zw.startect.web.dto.UserRegistrationDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//
//    private UserRepository userRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    public UserServiceImpl(UserRepository userRepository) {
//        super();
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//
//    @Override
//    public User save(UserRegistrationDto registrationDto) {
//        User user = new User();
//        user.setFirstName(registrationDto.getFirstName());
//        user.setLastName(registrationDto.getLastName());
//                user.setEmail(registrationDto.getEmail());
//                user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
//                user.setRoles(Arrays.asList(new Role("ROLE_USER")));
//        return userRepository.save(user);
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException("Invalid username or password");
//        }
//
//        return new org.springframework.security.core.userdetails.User(user.getEmail(),
//                user.getPassword(), mapRolesToAuthorities(user.getRoles()));
//    }
//
//    private Collection <? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//       return roles.stream()
//               .map(role -> new SimpleGrantedAuthority(role.getName()))
//               .collect(Collectors.toList());
//    }
//
//}
