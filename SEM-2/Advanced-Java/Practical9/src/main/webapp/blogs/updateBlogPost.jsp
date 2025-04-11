<%@ page import="org.kjsim.blogs.BlogPost" %>
<%@ page import="org.kjsim.blogs.HibernateBlogPost" %>
<%@ page import="java.net.URLEncoder" %>
<%@ include file="/header.jsp" %>

<%
  HibernateBlogPost blogService = new HibernateBlogPost();
  String method = request.getMethod();
  Long postId = null;
  BlogPost post = null;

  try {
    postId = Long.parseLong(request.getParameter("id"));
    post = blogService.getBlogPostById(postId);

    if (post == null) {
      String errorMessage = "Blog post not found for ID: " + postId;
      response.sendRedirect("../error.jsp?message=" + URLEncoder.encode(errorMessage, "UTF-8"));
      return;
    }
  } catch (Exception e) {
    String errorMessage = "Invalid blog post ID or error loading blog post.";
    response.sendRedirect("../error.jsp?message=" + URLEncoder.encode(errorMessage, "UTF-8"));
    return;
  }

  if ("POST".equalsIgnoreCase(method)) {
    try {
      String updatedTitle = request.getParameter("title");
      String updatedContent = request.getParameter("content");

      if (updatedTitle != null && updatedContent != null && !updatedTitle.trim().isEmpty() && !updatedContent.trim().isEmpty()) {
        post.setTitle(updatedTitle.trim());
        post.setContent(updatedContent.trim());
        blogService.updateBlogPost(post);

        response.sendRedirect("viewBlogPostById.jsp?id=" + post.getId());
        return;
      } else {
        String errorMessage = "Both Title and Content are required.";
        response.sendRedirect("../error.jsp?message=" + URLEncoder.encode(errorMessage, "UTF-8"));
        return;
      }
    } catch (Exception e) {
      String errorMessage = "Error updating blog post: " + e.getMessage();
      response.sendRedirect("../error.jsp?message=" + URLEncoder.encode(errorMessage, "UTF-8"));
      return;
    }
  }
%>

<!-- Bootstrap Icons -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

<div class="container my-5">
  <div class="card shadow">
    <div class="card-header bg-warning text-dark">
      <h4 class="mb-0"><i class="bi bi-pencil-square me-2"></i>Edit Blog Post</h4>
    </div>
    <div class="card-body">
      <form method="post" action="updateBlogPost.jsp?id=<%= post.getId() %>">
        <div class="mb-3">
          <label for="title" class="form-label">Title</label>
          <input type="text" name="title" class="form-control" value="<%= post.getTitle() %>" required placeholder="Enter updated title" />
        </div>

        <div class="mb-3">
          <label for="content" class="form-label">Content</label>
          <textarea name="content" class="form-control" rows="6" required placeholder="Enter updated content"><%= post.getContent() %></textarea>
        </div>

        <div class="d-flex justify-content-between">
          <a href="viewBlogPostById.jsp?id=<%= post.getId() %>" class="btn btn-secondary">
            <i class="bi bi-arrow-left-circle me-1"></i>Cancel
          </a>
          <button type="submit" class="btn btn-primary">
            <i class="bi bi-save me-1"></i>Update Post
          </button>
        </div>
      </form>
    </div>
  </div>
</div>

<%@ include file="/footer.jsp" %>