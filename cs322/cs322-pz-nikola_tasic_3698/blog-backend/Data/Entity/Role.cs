namespace blog_backend.Data;

public class Role {
	private string name;

	public string Name {
		get => name.Replace("ROLE_", "");
		set => name = "ROLE_" + value.Replace("ROLE_", "");
	}

	public Role(string name) {
		this.name = name;
	}
}