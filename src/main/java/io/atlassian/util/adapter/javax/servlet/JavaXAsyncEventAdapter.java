package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.JakartaAsyncEventAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXAsyncEventAdapter extends AsyncEvent implements Adapted<jakarta.servlet.AsyncEvent> {

    private final jakarta.servlet.AsyncEvent delegate;

    public static AsyncEvent from(jakarta.servlet.AsyncEvent delegate) {
        if (delegate instanceof JakartaAsyncEventAdapter) {
            JakartaAsyncEventAdapter castDelegate = (JakartaAsyncEventAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXAsyncEventAdapter::new);
    }

    protected JavaXAsyncEventAdapter(jakarta.servlet.AsyncEvent delegate) {
        super(null, null, null);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.AsyncEvent getDelegate() {
        return delegate;
    }

    @Override
    public AsyncContext getAsyncContext() {
        return JavaXAsyncContextAdapter.from(delegate.getAsyncContext());
    }

    @Override
    public ServletRequest getSuppliedRequest() {
        return asJavaX(delegate.getSuppliedRequest());
    }

    @Override
    public ServletResponse getSuppliedResponse() {
        return asJavaX(delegate.getSuppliedResponse());
    }

    @Override
    public Throwable getThrowable() {
        return delegate.getThrowable();
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
