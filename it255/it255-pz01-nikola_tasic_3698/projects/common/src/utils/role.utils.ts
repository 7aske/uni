export const hasRole = (roles: string[], role: string) => {
	const adapt = (_role: string) => {
		return _role.toLocaleUpperCase().replace("ROLE_", "");
	};
	return roles.some(_role => adapt(_role) === adapt(role));
};
