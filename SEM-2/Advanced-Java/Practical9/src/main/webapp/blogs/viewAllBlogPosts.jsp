<%@ page import="java.util.List" %>
<%@ page import="org.kjsim.blogs.BlogPost" %>
<%@ page import="org.kjsim.blogs.HibernateBlogPost" %>
<%@ page import="java.net.URLEncoder" %>
<%@ include file="/header.jsp" %>

<%
    HibernateBlogPost blogService = new HibernateBlogPost();
    List<BlogPost> posts = null;

    try {
        posts = blogService.getAllBlogPosts();
    } catch (Exception e) {
        String errorMessage = "Error fetching blog posts: " + e.getMessage();
        response.sendRedirect("../error.jsp?message=" + URLEncoder.encode(errorMessage, "UTF-8"));
        return;
    }
%>

<!-- Bootstrap Icons -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

<div class="container my-5">
    <div class="card shadow-sm">
        <!-- Card Header -->
        <div class="card-header bg-info text-dark py-3 px-4 d-flex justify-content-between align-items-center">
            <h4 class="mb-0"><i class="bi bi-journal-text me-2"></i>All Blog Posts</h4>
            <a href="createBlogPost.jsp" class="btn bg-primary-subtle text-primary-emphasis">
                <i class="bi bi-plus-circle me-1"></i> Create New Post
            </a>
        </div>

        <!-- Card Body -->
        <div class="card-body px-4 py-4">
            <% if (posts != null && !posts.isEmpty()) { %>
            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
                <% for (BlogPost post : posts) { %>
                <div class="col">
                    <div class="card h-100 shadow-sm border-0">
                        <div class="card-header bg-light px-3 py-2">
                            <h5 class="card-title mb-0 text-primary"><%= post.getTitle() %></h5>
                        </div>
                        <div class="card-body px-3 py-2">
                            <p class="card-text text-muted mb-2">
                                <%= post.getContent().length() > 100 ? post.getContent().substring(0, 100) + "..." : post.getContent() %>
                            </p>
                        </div>
                        <div class="card-footer bg-white border-top-0 d-flex justify-content-between align-items-center px-3 py-2">
                            <small class="text-muted"><i class="bi bi-calendar3 me-1"></i><%= post.getCreatedAt() %></small>
                            <a href="viewBlogPostById.jsp?id=<%= post.getId() %>" class="btn btn-outline-primary btn-sm">
                                <i class="bi bi-box-arrow-in-right me-1"></i> Read More
                            </a>
                        </div>
                    </div>
                </div>
                <% } %>
            </div>
            <% } else { %>
            <div class="alert alert-info mt-4"><i class="bi bi-info-circle me-2"></i>No blog posts available yet.</div>
            <% } %>
        </div>
    </div>
</div>

<%@ include file="/footer.jsp" %>