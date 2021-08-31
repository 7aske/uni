import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "../environments/environment";
import { Category } from "../../../common/src/@types/entity/Category";

@Injectable({
	providedIn: "root",
})
export class CategoryService {
	private baseUrl = `${environment.baseUrl}/categories`;

	constructor(private http: HttpClient) {

	}

	public getAll() {
		return this.http.get<Category[]>(`${this.baseUrl}`).toPromise();
	}

	public save(categ: Category) {
		return this.http.post<Category>(`${this.baseUrl}`, categ).toPromise();
	}

	public update(categ: Category) {
		return this.http.put<Category>(`${this.baseUrl}`, categ).toPromise();
	}

	public delete(id: number | string) {
		return this.http.delete<void>(`${this.baseUrl}/${id}`).toPromise();
	}
}
