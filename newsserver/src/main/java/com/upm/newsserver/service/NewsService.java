package com.upm.newsserver.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.upm.newsserver.DatabaseConnection;
import com.upm.newsserver.model.New;
import com.upm.newsserver.model.User;

@Component
public class NewsService {

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private String query;
	private static List<String> loggedUsers = new ArrayList<>();
	
	public NewsService() {
		conn = DatabaseConnection.getConnection();
	}

	public Response addArticle(New news) {
		
		if (news.isPremium() && !loggedUsers.contains(news.getAuthor()))
			return Response.status(Response.Status.UNAUTHORIZED).build();
		
		query = "INSERT INTO articulo VALUES(?,?,?,?,?,?)";
		
		try {
			ps = conn.prepareStatement(query);
			
			ps.setString(1, news.getTitle());
			ps.setString(2, news.getHeader());
			ps.setString(3, news.getAuthor());
			ps.setString(4, news.getCategory());
			ps.setString(5, news.getBody());
			ps.setBoolean(6, news.isPremium());

			ps.executeUpdate();
			
			return Response.status(201).entity("SUCCESS : New " + news.getTitle() + " Created\n").build();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage() + " : " + e.getSQLState() + "\n").build();
		}
	}
	
	public New getArticle(String title) {
		query = "SELECT * FROM articulo WHERE titulo=?";
		try {
			ps = conn.prepareStatement(query);
			
			ps.setString(1, title);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				
				String result_title = rs.getString(1);
				String header = rs.getString(2);
				String author = rs.getString(3);
				String category = rs.getString(4);
				String body = rs.getString(5);
				boolean premium = rs.getBoolean(6);
				
				if (premium && !loggedUsers.contains(author))
					return null;
				
				New result_new = new New(result_title, header, author, category, body, premium); 
				return result_new;
			}
			
			else 
				return null;
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}

	public Response createUser(User user) {
		query = "INSERT INTO usuario VALUES(?,?)";
		try {
			ps = conn.prepareStatement(query);
			
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			
			ps.executeUpdate();
			
			return Response.status(201).entity("SUCCESS: New user " + user.getUsername() + " created\n").build();

		} catch (SQLException e) {
			
			System.out.println(e.getMessage() + " : " + e.getSQLState());
			
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	public Response login(User user) {
		query = "SELECT * FROM usuario WHERE nombre=? && password=?";
		
		try {
			ps = conn.prepareStatement(query);
			
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				
				if (loggedUsers.contains(user.getUsername()))
					return Response.status(Response.Status.BAD_REQUEST).entity("ERROR: user alredy logged").build();
				
				loggedUsers.add(user.getUsername());
				return Response.status(Response.Status.CREATED).entity("SUCCESS: Login done for " + user.getUsername()).build();
			}
			
			return Response.status(Response.Status.NOT_FOUND).build();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " : " + e.getSQLState());
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage() + " : " + e.getSQLState() + "\n").build();
		}
	}

	public Response logout(String username) {
		
		if (loggedUsers.contains(username)) {
			loggedUsers.remove(username);
			return Response.status(Response.Status.OK).entity("SUCCESS: Logout done").build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
