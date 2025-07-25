package com.ltm.extracurricular.config.jwt;

import com.ltm.extracurricular.common.UserRole;
import com.ltm.extracurricular.entity.User;
import com.ltm.extracurricular.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    final UserRepository userRepository;
    UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tìm kiếm nhân viên trong DB theo username (tức là loginId của nhân viên)
        Optional<User> entity = this.userRepository.findByUsername(username);
        Collection<GrantedAuthority> roles;  // Khai báo biến để lưu danh sách quyền (role) của người dùng

        if (entity.isPresent()) { // Nếu tìm thấy nhân viên
            // Nếu role của nhân viên là ADMIN thì gán quyền ROLE_ADMIN
            if (UserRole.ADMIN.equals(entity.get().getRole())) {
                roles = Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else if (UserRole.TEACHER.equals(entity.get().getRole())) {
                roles = Collections.singleton(new SimpleGrantedAuthority("ROLE_TEACHER"));
            } else {
                roles = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
            }
            // Trả về đối tượng AuthUserDetails (custom UserDetails) chứa thông tin nhân viên và danh sách quyền
            return new AuthUserDetails(entity.get(), roles);
        } else {
            // Nếu không tìm thấy nhân viên thì ném ra exception thông báo lỗi
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
