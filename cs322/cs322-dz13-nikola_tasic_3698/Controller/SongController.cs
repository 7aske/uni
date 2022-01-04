using cs322_dz13_nikola_tasic_3698.Model;
using Microsoft.AspNetCore.Mvc;

namespace cs322_dz13_nikola_tasic_3698.Controller;

[Route("songs")]
public class SongController : Microsoft.AspNetCore.Mvc.Controller {
	[HttpPost]
	public IActionResult index(IFormCollection form) {
		if (ModelState.IsValid) {
			using (var ctx = new DataContext()) {
				Song song;
				if (int.TryParse(form["id"], out int id)) {
					song = ctx.Songs.First(s => s.id == id);
					song.name = form["name"];
					song.artist = form["artist"];
					ctx.Update(song);
				} else {
					song = new Song();
					song.name = form["name"];
					song.artist = form["artist"];
					ctx.Add(song);
				}

				ctx.SaveChanges();
			}
		}

		return RedirectToPage("/Index");
	}

	[HttpPost]
	[Route("delete/{id:int}")]
	public IActionResult indexDelete(int id) {
		using (var ctx = new DataContext()) {
			var song = ctx.Songs.First(song => song.id == id);
			ctx.Remove(song);
			ctx.SaveChanges();
		}

		return RedirectToPage("/Index");
	}
}