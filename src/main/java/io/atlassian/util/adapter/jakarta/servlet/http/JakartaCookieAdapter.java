package io.atlassian.util.adapter.jakarta.servlet.http;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.http.JavaXCookieAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.http.Cookie;
import java.util.Map;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaCookieAdapter extends Cookie implements Adapted<javax.servlet.http.Cookie> {

    /**
     * This is non-final only to ensure correct behaviour of {@link #clone()}. Do not modify this field anywhere else.
     */
    private javax.servlet.http.Cookie delegate;

    public static Cookie from(javax.servlet.http.Cookie delegate) {
        if (delegate instanceof JavaXCookieAdapter) {
            JavaXCookieAdapter castDelegate = (JavaXCookieAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaCookieAdapter::new);
    }

    protected JakartaCookieAdapter(javax.servlet.http.Cookie delegate) {
        super("null", null);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.http.Cookie getDelegate() {
        return delegate;
    }

    @Override
    public void setComment(String purpose) {
        delegate.setComment(purpose);
    }

    @Override
    public String getComment() {
        return delegate.getComment();
    }

    @Override
    public void setAttribute(String name, String value) {
        // Unadaptable
    }

    @Override
    public String getAttribute(String name) {
        // Unadaptable
        return null;
    }

    @Override
    public Map<String, String> getAttributes() {
        // Unadaptable
        return Map.of();
    }

    @Override
    public void setDomain(String domain) {
        delegate.setDomain(domain);
    }

    @Override
    public String getDomain() {
        return delegate.getDomain();
    }

    @Override
    public void setMaxAge(int expiry) {
        delegate.setMaxAge(expiry);
    }

    @Override
    public int getMaxAge() {
        return delegate.getMaxAge();
    }

    @Override
    public void setPath(String uri) {
        delegate.setPath(uri);
    }

    @Override
    public String getPath() {
        return delegate.getPath();
    }

    @Override
    public void setSecure(boolean flag) {
        delegate.setSecure(flag);
    }

    @Override
    public boolean getSecure() {
        return delegate.getSecure();
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public void setValue(String newValue) {
        delegate.setValue(newValue);
    }

    @Override
    public String getValue() {
        return delegate.getValue();
    }

    @Override
    public int getVersion() {
        return delegate.getVersion();
    }

    @Override
    public void setVersion(int v) {
        delegate.setVersion(v);
    }

    @Override
    public Object clone() {
        var clone = (JakartaCookieAdapter) super.clone();
        clone.delegate = (javax.servlet.http.Cookie) delegate.clone();
        return clone;
    }

    @Override
    public void setHttpOnly(boolean isHttpOnly) {
        delegate.setHttpOnly(isHttpOnly);
    }

    @Override
    public boolean isHttpOnly() {
        return delegate.isHttpOnly();
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
