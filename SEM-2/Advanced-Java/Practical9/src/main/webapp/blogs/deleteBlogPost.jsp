<%@ page import="org.kjsim.blogs.BlogPost" %>
<%@ page import="org.kjsim.blogs.HibernateBlogPost" %>

<%
    String idParam = request.getParameter("id");
    if (idParam != null) {
        try {
            Long id = Long.parseLong(idParam);
            HibernateBlogPost blogService = new HibernateBlogPost();
            BlogPost post = blogService.getBlogPostById(id);
            if (post != null) {
                blogService.deleteBlogPost(post.getId());
            }
        } catch (Exception e) {
            out.println("<div class='alert alert-danger'>Failed to delete the blog post.</div>");
        }
    }
    response.sendRedirect("viewAllBlogPosts.jsp");
%>