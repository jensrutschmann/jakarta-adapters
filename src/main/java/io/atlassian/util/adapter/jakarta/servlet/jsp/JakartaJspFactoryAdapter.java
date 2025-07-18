package io.atlassian.util.adapter.jakarta.servlet.jsp;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.jsp.JavaXJspFactoryAdapter;
import io.atlassian.util.adapter.javax.servlet.jsp.JavaXPageContextAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.jsp.JspApplicationContext;
import jakarta.servlet.jsp.JspEngineInfo;
import jakarta.servlet.jsp.JspFactory;
import jakarta.servlet.jsp.PageContext;

import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaJspFactoryAdapter extends JspFactory implements Adapted<javax.servlet.jsp.JspFactory> {
    private final javax.servlet.jsp.JspFactory delegate;

    public static JspFactory from(javax.servlet.jsp.JspFactory delegate) {
        if (delegate instanceof JavaXJspFactoryAdapter) {
            JavaXJspFactoryAdapter castDelegate = (JavaXJspFactoryAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaJspFactoryAdapter::new);
    }

    protected JakartaJspFactoryAdapter(javax.servlet.jsp.JspFactory delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.jsp.JspFactory getDelegate() {
        return delegate;
    }

    @Override
    public PageContext getPageContext(Servlet servlet,
                                      ServletRequest servletRequest,
                                      ServletResponse servletResponse,
                                      String s,
                                      boolean b,
                                      int i,
                                      boolean b1) {
        return JakartaPageContextAdapter.from(
                delegate.getPageContext(asJavaX(servlet), asJavaX(servletRequest), asJavaX(servletResponse), s, b, i, b1));
    }

    @Override
    public void releasePageContext(PageContext pageContext) {
        delegate.releasePageContext(JavaXPageContextAdapter.from(pageContext));
    }

    @Override
    public JspEngineInfo getEngineInfo() {
        return JakartaJspEngineInfoAdapter.from(delegate.getEngineInfo());
    }

    @Override
    public JspApplicationContext getJspApplicationContext(ServletContext servletContext) {
        return JakartaJspApplicationContextAdapter.from(delegate.getJspApplicationContext(asJavaX(servletContext)));
    }

    @Override
    public boolean equals(Object obj) {
        return WrapperUtil.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return WrapperUtil.hashCode(this);
    }
}
