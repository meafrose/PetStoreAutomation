package api.endpoints;

/*
Swagger URI--> https://petstore.swagger.io/
Create user(post)--> https://petstore.swagger.io/v2/user
Get user--> https://petstore.swagger.io/v2/user{username}
Update user--> https://petstore.swagger.io/v2/user{username}
Delete user--> https://petstore.swagger.io/v2/user{username}
 */

//here we are just maintaining the routes or the URLs

public class Routes {
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	//User_Module
	
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user{username}";
	public static String update_url=base_url+"/user{username}";
	public static String delete_url=base_url+"/user{username}";
	
	//Store_Module: here you will create Store module URLs
	//Pet_Module: here you will create Pet module URLs



}
