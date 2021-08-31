import { Identifiable } from "./Identifiable";
import { User } from  "./User"
import { Post } from  "./Post"

export interface Comment extends Identifiable {
	user: User;
	post: Post;
	body: string;
}
