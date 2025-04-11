<%@ page import="org.kjsim.blogs.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.net.URLEncoder" %>
<%@ include file="/header.jsp" %>

<%
    Long id = null;
    BlogPost post = null;
    List<Comment> comments = Collections.emptyList();

    try {
        id = Long.parseLong(request.getParameter("id"));
        HibernateBlogPost blogService = new HibernateBlogPost();
        HibernateComment commentService = new HibernateComment();

        post = blogService.getBlogPostById(id);
        if (post == null) {
            String errorMessage = "Blog post not found.";
            response.sendRedirect("../error.jsp?message=" + URLEncoder.encode(errorMessage, "UTF-8"));
            return;
        }

        List<Comment> fetchedComments = commentService.getCommentsForPost(id);
        if (fetchedComments != null) {
            comments = fetchedComments;
        }
    } catch (Exception e) {
        String errorMessage = "Error loading blog post: " + e.getMessage();
        response.sendRedirect("../error.jsp?message=" + URLEncoder.encode(errorMessage, "UTF-8"));
        return;
    }
%>

<!-- Bootstrap Icons -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

<div class="container my-5">
    <!-- Blog Post Card -->
    <div class="card shadow-lg mb-4 border-0">
        <div class="card-body">
            <h2 class="card-title mb-3 text-primary"><i class="bi bi-journal-text me-2"></i><%= post.getTitle() %></h2>
            <p class="card-text fs-5"><%= post.getContent() %></p>
            <p class="text-muted"><i class="bi bi-calendar3 me-2"></i>Posted on: <strong><%= post.getCreatedAt() %></strong></p>
        </div>
        <div class="card-footer bg-white border-top-0 d-flex justify-content-end gap-2">
            <a href="updateBlogPost.jsp?id=<%= post.getId() %>" class="btn btn-outline-warning">
                <i class="bi bi-pencil-square me-1"></i>Edit
            </a>
            <a href="deleteBlogPost.jsp?id=<%= post.getId() %>" class="btn btn-outline-danger"
               onclick="return confirm('Are you sure you want to delete this blog post?')">
                <i class="bi bi-trash3 me-1"></i>Delete
            </a>
        </div>
    </div>

    <!-- Comments Section -->
    <div class="mb-5">
        <h4 class="mb-3"><i class="bi bi-chat-left-text me-2"></i>Comments (<%= comments.size() %>)</h4>

        <%
            try {
                if (!comments.isEmpty()) {
                    for (Comment comment : comments) {
        %>
        <div class="alert alert-light d-flex justify-content-between align-items-center shadow-sm border rounded">
            <div><%= comment.getContent() %></div>
            <div>
                <a href="updateComment.jsp?id=<%= comment.getId() %>&postId=<%= post.getId() %>"
                   class="btn btn-sm btn-outline-warning me-2">
                    <i class="bi bi-pencil"></i>
                </a>
                <a href="deleteComment.jsp?id=<%= comment.getId() %>&postId=<%= post.getId() %>"
                   class="btn btn-sm btn-outline-danger"
                   onclick="return confirm('Delete this comment?')">
                    <i class="bi bi-trash"></i>
                </a>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <div class="alert alert-info shadow-sm"><i class="bi bi-info-circle me-2"></i>No comments yet. Be the first to comment!</div>
        <%
                }
            } catch (Exception e) {
                String errorMessage = "Error rendering comments: " + e.getMessage();
                response.sendRedirect("../error.jsp?message=" + URLEncoder.encode(errorMessage, "UTF-8"));
                return;
            }
        %>
    </div>

    <!-- Add Comment Section -->
    <div class="card shadow-sm border-0">
        <div class="card-header bg-primary text-white">
            <h5 class="mb-0"><i class="bi bi-plus-circle me-2"></i>Add a Comment</h5>
        </div>
        <div class="card-body">
            <form action="addCommentOnBlogPost.jsp" method="post">
                <div class="mb-3">
                    <textarea name="commentContent" class="form-control" rows="3" placeholder="Write your comment here..." required></textarea>
                </div>
                <input type="hidden" name="postId" value="<%= post.getId() %>">
                <button type="submit" class="btn btn-success">
                    <i class="bi bi-send-fill me-1"></i>Submit
                </button>
            </form>
        </div>
    </div>
</div>

<%@ include file="/footer.jsp" %>