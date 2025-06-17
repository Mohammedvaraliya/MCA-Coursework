package org.kjsim.session6CustomTags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;

public class SimpleTagDemo extends SimpleTagSupport {

    private String name;
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println("Hello, Welcome to Custom JSP Tag Demo. " + (name == null || name.isEmpty() ? "NoName" : "Hello " + name));
    }
}
