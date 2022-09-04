package petfriends.petfriendsAuth.repository;

import petfriends.petfriendsAuth.model.UserInfo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String>{

    Optional<UserInfo> findByUserId(String userId);
    boolean existsByUserId(String userId);
    // List<UserInfo> findAllByUserId(String userId);

}
