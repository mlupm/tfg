package com.upm.newsserver.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
		if (news == null)
			return Response.status(400).entity("The New object is missing or malformed").build();
		return newsService.addArticle(news);
	}
	
	@GET
	@Path("/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticle(@PathParam("title") String title) {
		New result_new = newsService.getArticle(title);
		if (result_new != null)
			return Response.status(200).entity(result_new).build();
		return Response.status(404).build();
	}
	
	@POST
	@Path("/users")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {
		return newsService.createUser(user);
	}
	
	@POST
	@Path("/users/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(User user) {
		return newsService.login(user);
	}
	
	@DELETE
	@Path("/users/logout/{username}")
	public Response logout(@PathParam("username") String username) {
		return newsService.logout(username);
	}
	
}
