<%@ page import="org.kjsim.blogs.Comment" %>
<%@ page import="org.kjsim.blogs.HibernateComment" %>

<%
    String commentIdParam = request.getParameter("id");
    String postIdParam = request.getParameter("postId");

    if (commentIdParam != null && postIdParam != null) {
        try {
            Long commentId = Long.parseLong(commentIdParam);
            HibernateComment commentService = new HibernateComment();
            Comment comment = commentService.getCommentById(commentId);

            if (comment != null) {
                commentService.deleteComment(comment.getId());
            }
        } catch (Exception e) {
            out.println("<div class='alert alert-danger'>Error deleting comment: " + e.getMessage() + "</div>");
        }
    }

    response.sendRedirect("viewBlogPostById.jsp?id=" + postIdParam);
%>