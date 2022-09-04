package petfriends.petfriendsAuth.service;

import petfriends.petfriendsAuth.model.UserInfo;
import petfriends.petfriendsAuth.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserInfoService implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    private Collection<? extends GrantedAuthority> authorities(UserInfo userInfo) {
        return userInfo.getUserRole().stream().map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<UserInfo> option = userInfoRepository.findById(userId);
        if (!option.isPresent()) {
            throw new UsernameNotFoundException(userId);
        }
        UserInfo userInfo = option.get();
        // return new User(userInfo.getId(), userInfo.getPassword(), authorities(userInfo));
        return new User(userInfo.getUserId(), userInfo.getPassword(), authorities(userInfo));
    }
}