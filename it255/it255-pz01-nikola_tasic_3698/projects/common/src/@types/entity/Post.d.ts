import { Identifiable } from "./Identifiable";
import { User } from  "./User"
import { Category } from  "./Category"
import { Tag } from  "./Tag"
import { Auditable } from "./Auditable";

export interface Post extends Identifiable, Auditable {
	user: User;
	category: Category;
	title: string;
	excerpt: string;
	body: string;
	deleted?: boolean;
	published?: boolean;
	views?: number;
	slug: string;
	tags: Tag[];
}
