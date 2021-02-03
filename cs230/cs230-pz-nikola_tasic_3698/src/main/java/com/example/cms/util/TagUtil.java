package com.example.cms.util;

import com.example.cms.database.dao.TagDAO;
import com.example.cms.database.entity.Tag;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

public class TagUtil {

	public static Set<Tag> parseTags(HttpServletRequest request) {
		if (request.getParameter("tags") != null) {
			return parseTags(request.getParameter("tags"));
		}
		return new HashSet<>();
	}

	public static Set<Tag> parseTags(String[] tags) {
		Set<Tag> tagList = new HashSet<>();
		TagDAO tagDAO = new TagDAO();
		for (String tagName : tags) {
			Tag tag = tagDAO.findByName(tagName);
			if (tag != null) {
				tagList.add(tag);
			}
		}
		return tagList;
	}

	public static Set<Tag> parseTags(String tagsString) {
		return parseTags(tagsString.split(","));
	}
}
