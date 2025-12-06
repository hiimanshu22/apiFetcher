# API Fetcher Application

## Project Overview
This is a Spring Boot application that fetches data from the public [JSONPlaceholder](https://jsonplaceholder.typicode.com/) API.
It provides endpoints to list posts and users, filter posts by userId, and get details of a single post or user by ID.
The application also includes proper error handling for missing resources and network failures.

## Setup Instruction 
To set up the project, clone the repository using `git clone <your-github-repo-url>`, navigate to the project folder using `cd apiFetcher`, build the project using `mvn clean install`, and run the application using `mvn spring-boot:run`. The application will start at `http://localhost:8080`.

## Endpoints
| Endpoint        | Description                               | Query Parameters                  |
|-----------------|-------------------------------------------|----------------------------------|
| `/posts`        | List all posts                             | Optional: `userId` to filter    |
| `/posts/{id}`   | Get details of a single post               | -                                |
| `/users`        | List all users                             | -                                |
| `/users/{id}`   | Get details of a single user               | -                                |

You can filter posts by `userId` using `/posts?userId=1`. 

## Error Handling
The application returns 404 Not Found when a post or user with the specified ID does not exist, and 500 Internal Server Error if an external API/network call fails or any other unexpected error occurs.


## Sample Requests / Responses
You can get all posts using `GET http://localhost:8080/posts`, filter posts by userId using `GET http://localhost:8080/posts?userId=1`, or get a single post using `GET http://localhost:8080/posts/5`. The JSON response for a single post looks like this:

```json
{
  "userId": 1,
  "id": 5,
  "title": "nesciunt quas odio",
  "body": "repudiandae veniam quaerat sunt sed\nalias aut fugiat sit autem sed est..."
}

```
If a resource is not found (for example, a post or user with a non-existing ID), the error response looks like this:
```json
{
  "error": "Post not found with id 999"
}
```
Similarly, /users can be used to get the list of users or /users/{id} to get details of a single user. The project is built using Java 17, Spring Boot 3.5.8, Lombok, Maven, and the JSONPlaceholder API. It demonstrates proper use of REST APIs, caching, filtering, and error handling.



