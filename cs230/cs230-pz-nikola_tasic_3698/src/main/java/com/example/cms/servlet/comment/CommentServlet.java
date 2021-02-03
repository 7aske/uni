package com.example.cms.servlet.comment;

import com.example.cms.database.dao.CommentDAO;
import com.example.cms.database.dao.PostDAO;
import com.example.cms.database.entity.Comment;
import com.example.cms.database.entity.Post;
import com.google.common.collect.Iterables;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idPostString = request.getParameter("idPost");
		String commenterName = request.getParameter("commenter_name");
		String commenterEmail = request.getParameter("commenter_email");
		String body = request.getParameter("body");
		LocalDate dateCommented = LocalDate.now();
		PostDAO postDAO = new PostDAO();

		List<String> errors = new ArrayList<>();

		Long idPost = null;
		Post post = null;


		if (commenterName == null || commenterName.equals("")) {
			errors.add("Commenter name required.");
		}

		if (commenterName == null || commenterEmail.equals("")) {
			errors.add("Commenter email required.");
		}

		if (body == null || body.equals("")) {
			errors.add("Comment body required.");
		}

		try {
			idPost = Long.parseLong(idPostString);
			post = postDAO.find(idPost);
		} catch (NumberFormatException ignored) {
			errors.add("Post not found.");
		}

		if (post == null) {
			errors.add("Post not found.");
		}

		if (errors.size() == 0) {
			assert idPost != null;
			CommentDAO commentDAO = new CommentDAO();
			Comment comment = new Comment();

			comment.setIdPost(idPost);
			comment.setCommenterName(commenterName);
			comment.setCommenterEmail(commenterEmail);
			comment.setBody(body);
			comment.setDateCommented(dateCommented.plusDays(1));

			commentDAO.create(comment);
		}

		if (post != null) {
			if (errors.size()==0){
				response.sendRedirect(request.getContextPath() + "/post/" + post.getSlug());
			} else {
				request.setAttribute("errors", Iterables.toArray(errors, String.class));
				request.getRequestDispatcher("/post/" + post.getSlug()).forward(request, response);
			}
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}
}
