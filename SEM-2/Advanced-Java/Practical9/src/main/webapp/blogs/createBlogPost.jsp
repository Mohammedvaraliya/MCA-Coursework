<%@ page import="org.kjsim.blogs.BlogPost" %>
<%@ page import="org.kjsim.blogs.HibernateBlogPost" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.net.URLEncoder" %>
<%@ include file="/header.jsp" %>

<%
  if ("POST".equalsIgnoreCase(request.getMethod())) {
    String title = request.getParameter("title");
    String content = request.getParameter("content");

    if (title != null && content != null && !title.trim().isEmpty() && !content.trim().isEmpty()) {
      try {
        BlogPost post = new BlogPost();
        post.setTitle(title);
        post.setContent(content);
        post.setCreatedAt(LocalDate.now());

        HibernateBlogPost blogService = new HibernateBlogPost();
        blogService.createBlogPost(post);

        response.sendRedirect("viewAllBlogPosts.jsp");
        return;
      } catch (Exception e) {
        String errorMessage = "Error saving blog post: " + e.getMessage();
        response.sendRedirect("../error.jsp?message=" + URLEncoder.encode(errorMessage, "UTF-8"));
        return;
      }
    } else {
      String errorMessage = "Both Title and Content are required.";
      response.sendRedirect("../error.jsp?message=" + URLEncoder.encode(errorMessage, "UTF-8"));
      return;
    }
  }
%>

<!-- Bootstrap Icons -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

<div class="container my-5">
  <div class="card shadow-lg">
    <div class="card-header bg-primary text-white">
      <h4 class="mb-0"><i class="bi bi-pencil-square me-2"></i>Create New Blog Post</h4>
    </div>
    <div class="card-body">
      <form method="post" action="createBlogPost.jsp">
        <div class="mb-3">
          <label for="title" class="form-label">Title</label>
          <input type="text" class="form-control" name="title" id="title" required placeholder="Enter blog post title">
        </div>

        <div class="mb-3">
          <label for="content" class="form-label">Content</label>
          <textarea class="form-control" name="content" id="content" rows="6" required placeholder="Write your blog content here..."></textarea>
        </div>

        <div class="d-flex justify-content-between">
          <a href="viewAllBlogPosts.jsp" class="btn btn-secondary">
            <i class="bi bi-arrow-left-circle me-1"></i>Cancel
          </a>
          <button type="submit" class="btn btn-success">
            <i class="bi bi-save me-1"></i>Create Post
          </button>
        </div>
      </form>
    </div>
  </div>
</div>

<%@ include file="/footer.jsp" %>