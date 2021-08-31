package com.example.backend.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationUtil {
	public static final Pattern EMAIL_REGEX =
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	private static final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+).{8,40}$");
	//(?=.*[@#$%^&+=]) for special characters

	public static boolean validateEmail(String emailStr) {
		Matcher matcher = EMAIL_REGEX.matcher(emailStr);
		return matcher.find();
	}

	public static boolean validatePassword(String passwordStr) {
		Matcher matcher = PASSWORD_REGEX.matcher(passwordStr);
		return matcher.matches();
	}

	// private void validateUser(UserDTO userDTO) throws UserServiceImpl.UserValidationException {
	// 	validateDisplayName(userDTO.getUserDisplayName());
	// 	validateFirstName(userDTO.getUserFirstName());
	// 	validateLastName(userDTO.getUserLastName());
	// 	validateAbout(userDTO.getUserAbout());
	// 	validateEmail(userDTO.getUserEmail());
	// 	validateAddress(userDTO.getUserAddress());
	// 	validateRoles(userDTO.getUserRoles());
	// }
	//
	// void validateRoles(List<String> roles) throws UserServiceImpl.UserValidationException {
	// 	for (String userRole : roles) {
	// 		roleRepository.findByRoleName(userRole).orElseThrow(() -> new UserServiceImpl.UserValidationException("user.update.role-not-found"));
	// 	}
	// }
	//
	// void validateUsername(String value) throws UserServiceImpl.UserValidationException {
	// 	if (value == null) {
	// 		throw new UserServiceImpl.UserValidationException("user.update.username-invalid");
	// 	}
	// 	if (value.isEmpty()) {
	// 		throw new UserServiceImpl.UserValidationException("user.update.username-empty");
	// 	}
	//
	// 	if (value.length() < 4) {
	// 		throw new UserServiceImpl.UserValidationException("user.update.username-short");
	// 	}
	//
	// 	if (userRepository.findByUserUsername(value).isPresent()) {
	// 		throw new UserServiceImpl.UserValidationException("user.update.user-exists");
	// 	}
	// }
	//
	//
	// void validateDisplayName(String value) throws UserServiceImpl.UserValidationException {
	// 	if (value == null) {
	// 		throw new UserServiceImpl.UserValidationException("user.update.displayname-invalid");
	// 	}
	// 	if (value.isEmpty()) {
	// 		throw new UserServiceImpl.UserValidationException("user.update.displayname-empty");
	// 	}
	//
	// 	if (value.length() < 4) {
	// 		throw new UserServiceImpl.UserValidationException("user.update.displayname-short");
	// 	}
	//
	// }
	//
	// void validateFirstName(String value) throws UserServiceImpl.UserValidationException {
	// 	if (value == null) {
	// 		throw new UserServiceImpl.UserValidationException("user.update.firstname-invalid");
	// 	}
	// 	if (value.isEmpty()) {
	// 		throw new UserServiceImpl.UserValidationException("user.update.firstname-empty");
	// 	}
	//
	// }
	//
	// void validateLastName(String value) throws UserServiceImpl.UserValidationException {
	// 	if (value == null) {
	// 		throw new UserServiceImpl.UserValidationException("user.update.lastname-invalid");
	// 	}
	// 	if (value.isEmpty()) {
	// 		throw new UserServiceImpl.UserValidationException("user.update.lastname-empty");
	// 	}
	//
	// }
	//
	// void validateEmail(String value) throws UserServiceImpl.UserValidationException {
	// 	if (value == null || !ValidationUtil.validateEmail(value)) {
	// 		throw new UserServiceImpl.UserValidationException("user.update.email-invalid");
	// 	}
	// 	if (value.isEmpty()) {
	// 		throw new UserServiceImpl.UserValidationException("user.update.email-empty");
	// 	}
	// }
	//
	// void validateAbout(String value) throws UserServiceImpl.UserValidationException {
	// 	if (value == null) {
	// 		throw new UserServiceImpl.UserValidationException("user.update.about-invalid");
	// 	}
	// }
	//
	// void validateAddress(String value) throws UserServiceImpl.UserValidationException {
	// 	if (value == null) {
	// 		throw new UserServiceImpl.UserValidationException("user.update.address-invalid");
	// 	}
	// }
	//
	// void validatePassword2(String value) throws UserServiceImpl.UserValidationException {
	// 	if (value == null || !ValidationUtil.validatePassword(value)) {
	// 		throw new UserServiceImpl.UserValidationException("user.update.password-invalid");
	// 	}
	// 	if (value.isEmpty()) {
	// 		throw new UserServiceImpl.UserValidationException("user.update.password-empty");
	// 	}
	// }
	//
	//
	// private void validatePost(PostDTO post) throws PostServiceImpl.PostValidationException {
	//
	// 	if (post.getPostSlug() == null || post.getPostSlug().isEmpty()) {
	// 		throw new PostServiceImpl.PostValidationException("post.save.slug-empty");
	// 	}
	//
	// 	if (post.getPostTitle() == null || post.getPostTitle().isEmpty()) {
	// 		throw new PostServiceImpl.PostValidationException("post.save.title-empty");
	// 	}
	//
	// 	if (post.getPostBody() == null || post.getPostBody().isEmpty()) {
	// 		throw new PostServiceImpl.PostValidationException("post.save.body-empty");
	// 	}
	//
	// 	if (post.getPostExcerpt() == null || post.getPostExcerpt().isEmpty()) {
	// 		throw new PostServiceImpl.PostValidationException("post.save.excerpt-empty");
	// 	}
	//
	// 	if (post.getIdCategory() == null) {
	// 		throw new PostServiceImpl.PostValidationException("post.save.category-invalid");
	// 	}
	//
	// 	if (!categoryRepository.findByIdCategory(post.getIdCategory()).isPresent()) {
	// 		throw new PostServiceImpl.PostValidationException("post.save.category-not-found");
	// 	}
}
