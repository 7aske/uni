import { Identifiable } from "./Identifiable";
import { User } from  "./User"

export interface Role extends Identifiable {
	name: string;
	users: User[];
}
