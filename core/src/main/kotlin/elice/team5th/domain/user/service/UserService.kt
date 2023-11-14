package elice.team5th.domain.user.service

import elice.team5th.domain.user.exception.UserNotFoundException
import elice.team5th.domain.user.dto.UserDto
import elice.team5th.domain.user.model.User
import elice.team5th.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun signup(signupRequest: UserDto.SignupRequest): User {
        return userRepository.save(
            User(
                profileImage = signupRequest.profileImage,
                nickname = signupRequest.nickname,
                socialProvider = signupRequest.socialProvider
            )
        )
    }

    fun findAllUsers(pageable: Pageable): Page<User> {
        return userRepository.findAll(pageable)
    }

    fun findUserById(userId: String): User {
        return userRepository.findByUserId(userId) ?: throw UserNotFoundException("유저를 찾을 수 없습니다.")
    }

    fun findUserByNickname(nickname: String): User {
        return userRepository.findUserByNickname(nickname) ?: throw UserNotFoundException("$nickname 닉네임을 가진 유저를 찾을 수 없습니다.")
    }

    fun updateProfile(userId: String, updateRequest: UserDto.UpdateProfileRequest): User {
        return userRepository.findByUserId(userId)?.apply {
            updateRequest.profileImage?.let { this.profileImage = it }
            updateRequest.nickname?.let { this.nickname = it }
        } ?: throw UserNotFoundException("유저를 찾을 수 없습니다.")
    }

    fun findUsersByBanIsTrue(pageable: Pageable): Page<User> {
        return userRepository.findUsersByBanIsTrue(pageable)
    }

    fun banUser(userId: String): User {
        return userRepository.findByUserId(userId)?.apply {
            this.ban = true
        } ?: throw UserNotFoundException("유저를 찾을 수 없습니다.")
    }

}
