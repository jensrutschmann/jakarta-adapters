package io.atlassian.util.adapter.jakarta.servlet.http;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.http.JavaXPartAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaPartAdapter implements Part, Adapted<javax.servlet.http.Part> {

    private final javax.servlet.http.Part delegate;

    public static Part from(javax.servlet.http.Part delegate) {
        if (delegate instanceof JavaXPartAdapter) {
            JavaXPartAdapter castDelegate = (JavaXPartAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaPartAdapter::new);
    }

    protected JakartaPartAdapter(javax.servlet.http.Part delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.http.Part getDelegate() {
        return delegate;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return delegate.getInputStream();
    }

    @Override
    public String getContentType() {
        return delegate.getContentType();
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public String getSubmittedFileName() {
        return delegate.getSubmittedFileName();
    }

    @Override
    public long getSize() {
        return delegate.getSize();
    }

    @Override
    public void write(String fileName) throws IOException {
        delegate.write(fileName);
    }

    @Override
    public void delete() throws IOException {
        delegate.delete();
    }

    @Override
    public String getHeader(String name) {
        return delegate.getHeader(name);
    }

    @Override
    public Collection<String> getHeaders(String name) {
        return delegate.getHeaders(name);
    }

    @Override
    public Collection<String> getHeaderNames() {
        return delegate.getHeaderNames();
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
