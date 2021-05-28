package com.upm.newsserver.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.upm.newsserver.model.New;
import com.upm.newsserver.model.User;
import com.upm.newsserver.service.NewsService;

@Path("/news")
public class NewsApi {

	@Autowired
	NewsService newsService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addArticle(New news) {
		return newsService.addArticle(news);
	}
	
	@GET
	@Path("/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public New getArticle(@PathParam("title") String title) {
		return newsService.getArticle(title);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticles() {
		return newsService.getArticles();
	}
	
	@POST
	@Path("/users")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {
		return newsService.createUser(user);
	}
	
}
