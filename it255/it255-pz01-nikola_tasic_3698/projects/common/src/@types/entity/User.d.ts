import { Identifiable } from "./Identifiable";
import { Role } from  "./Role"

export interface User extends Identifiable {
	username: string;
	password: string;
	email: string;
	firstName: string;
	lastName: string;
	about: string;
	displayName: string;
	roles: Role[];
}
