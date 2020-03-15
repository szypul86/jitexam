package com.jitexam.jitexam.service;

import com.jitexam.jitexam.dto.UserDto;
import com.jitexam.jitexam.entity.User;
import com.jitexam.jitexam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;


	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}

	@Override
	public User findOne(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.orElse(null);
	}

    @Override
    public UserDto update(UserDto userDto) {
        User user = findById(userDto.getId());
        if(user != null) {
            BeanUtils.copyProperties(userDto, user, "password");
            userRepository.save(user);
        }
        return userDto;
    }

    @Override
    public User save(UserDto user) {
		Optional<User> optionalUser = userRepository.findByFirstNameAndLastName(user.getFirstName(),user.getLastName());
		if(optionalUser.isPresent()){
			log.error("User with that name and surname already exists");
			throw new IllegalArgumentException("User with that name and surname already exists");
		}
		else {
			User newUser = User.builder()
					.firstName(user.getFirstName())
					.lastName(user.getLastName())
					.username(user.getUsername())
					.password(encoder.encode(user.getPassword()))
					.build();
			return userRepository.save(newUser);
		}
    }
}
