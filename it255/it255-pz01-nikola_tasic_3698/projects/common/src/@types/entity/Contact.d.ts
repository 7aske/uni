import { Identifiable } from "./Identifiable";
import { User } from  "./User"

export interface Contact extends Identifiable {
	user: User;
	value: string;
	contactType: string;
}
