<%@ page import="org.kjsim.blogs.Comment" %>
<%@ page import="org.kjsim.blogs.BlogPost" %>
<%@ page import="org.kjsim.blogs.HibernateBlogPost" %>
<%@ page import="org.kjsim.blogs.HibernateComment" %>

<%
    String content = request.getParameter("commentContent");
    String postIdParam = request.getParameter("postId");

    if (content != null && postIdParam != null) {
        try {
            Long postId = Long.parseLong(postIdParam);
            HibernateBlogPost blogService = new HibernateBlogPost();
            BlogPost post = blogService.getBlogPostById(postId);

            if (post != null) {
                Comment comment = new Comment();
                comment.setContent(content);
                comment.setPost(post);

                HibernateComment commentService = new HibernateComment();
                commentService.addCommentToPost(postId, comment);
            }
        } catch (Exception e) {
            out.println("<div class='alert alert-danger'>Error adding comment.</div>");
        }
    }

    response.sendRedirect("viewBlogPostById.jsp?id=" + postIdParam);
%>