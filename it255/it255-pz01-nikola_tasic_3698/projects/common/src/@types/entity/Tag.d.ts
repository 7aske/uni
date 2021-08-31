import { Identifiable } from "./Identifiable";
import { Post } from  "./Post"

export interface Tag extends Identifiable {
	name: string;
}
