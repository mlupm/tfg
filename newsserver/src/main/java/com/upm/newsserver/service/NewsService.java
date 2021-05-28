package com.upm.newsserver.service;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.upm.newsserver.model.New;
import com.upm.newsserver.model.User;

@Component
public class NewsService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Response addArticle(New news) {
		String query = "INSERT INTO article VALUES(?,?,?,?,?,?)";
		try {
			int returnCode = jdbcTemplate.update(query, 
					news.getTitle(), 
					news.getHeader(), 
					news.getAuthor(),
					news.getCategory(), 
					news.getBody(), 
					news.isPremium());
			
			return Response.status(returnCode).build();

		} catch (DataAccessException e) {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}
	
	public New getArticle(String title) {
		String query = "SELECT * FROM article WHERE title=?";
		try {
			return (New) jdbcTemplate.queryForObject(query, new Object[]{title}, new BeanPropertyRowMapper<New>(New.class));
			
		} catch(DataAccessException e) {
			return null;
		}
	}

	public Response getArticles() {
		String query = "SELECT * FROM article";

		List<New> news = jdbcTemplate.query(query, new BeanPropertyRowMapper<New>(New.class));  
		
		return Response.status(Response.Status.OK).entity(news).build();
	}

	public Response createUser(User user) {
		String query = "INSERT INTO users VALUES(?,?)";
		try {
			int returnCode = jdbcTemplate.update(query, 
					user.getUsername(),
					user.getPassword());
			
			return Response.status(returnCode).build();

		} catch (DataAccessException e) {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}

}
