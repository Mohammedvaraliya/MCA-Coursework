<%@ page import="org.kjsim.blogs.Comment" %>
<%@ page import="org.kjsim.blogs.HibernateComment" %>
<%@ page import="java.net.URLEncoder" %>
<%@ include file="/header.jsp" %>

<%
    String method = request.getMethod();
    String commentIdParam = request.getParameter("id");
    String postIdParam = request.getParameter("postId");

    HibernateComment commentService = new HibernateComment();
    Comment comment = null;

    try {
        Long commentId = Long.parseLong(commentIdParam);
        comment = commentService.getCommentById(commentId);

        if ("POST".equalsIgnoreCase(method)) {
            String newContent = request.getParameter("content");
            if (newContent != null && comment != null) {
                comment.setContent(newContent);
                commentService.updateComment(comment);
                response.sendRedirect("viewBlogPostById.jsp?id=" + postIdParam);
                return;
            }
        }
    } catch (Exception e) {
        String errorMessage = "Error loading or updating comment: " + e.getMessage();
        response.sendRedirect("../error.jsp?message=" + URLEncoder.encode(errorMessage, "UTF-8"));
        return;
    }
%>

<!-- Bootstrap Icons -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

<% if (comment != null) { %>
<div class="container my-5">
    <div class="card shadow-sm border-0">
        <div class="card-header bg-warning text-dark">
            <h4 class="mb-0"><i class="bi bi-pencil-square me-2"></i>Edit Comment</h4>
        </div>
        <div class="card-body">
            <form method="post" action="updateComment.jsp?id=<%= comment.getId() %>&postId=<%= postIdParam %>">
                <div class="mb-3">
                    <label class="form-label"><i class="bi bi-chat-left-text me-1"></i>Comment Content</label>
                    <textarea name="content" class="form-control" rows="4" required><%= comment.getContent() %></textarea>
                </div>
                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-success">
                        <i class="bi bi-check-circle me-1"></i>Update
                    </button>
                    <a href="viewBlogPostById.jsp?id=<%= postIdParam %>" class="btn btn-secondary">
                        <i class="bi bi-x-circle me-1"></i>Cancel
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
<% } %>

<%@ include file="/footer.jsp" %>