package io.atlassian.util.adapter.jakarta.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.el.JavaXValueExpressionAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.el.ELContext;
import jakarta.el.ValueExpression;
import jakarta.el.ValueReference;

import static io.atlassian.util.adapter.javax.JavaXJspAdapters.asJavaXJsp;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaValueExpressionAdapter extends ValueExpression implements Adapted<javax.el.ValueExpression> {

    private final javax.el.ValueExpression delegate;

    public static ValueExpression from(javax.el.ValueExpression delegate) {
        if (delegate instanceof JavaXValueExpressionAdapter) {
            JavaXValueExpressionAdapter castDelegate = (JavaXValueExpressionAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaValueExpressionAdapter::new);
    }

    protected JakartaValueExpressionAdapter(javax.el.ValueExpression delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.el.ValueExpression getDelegate() {
        return delegate;
    }

    @Override
    public ValueReference getValueReference(ELContext context) {
        return JakartaValueReferenceAdapter.from(delegate.getValueReference(asJavaXJsp(context)));
    }

    @Override
    public Object getValue(ELContext elContext) {
        return delegate.getValue(asJavaXJsp(elContext));
    }

    @Override
    public void setValue(ELContext elContext, Object o) {
        delegate.setValue(asJavaXJsp(elContext), o);
    }

    @Override
    public boolean isReadOnly(ELContext elContext) {
        return delegate.isReadOnly(asJavaXJsp(elContext));
    }

    @Override
    public Class<?> getType(ELContext elContext) {
        return delegate.getType(asJavaXJsp(elContext));
    }

    @Override
    public Class<?> getExpectedType() {
        return delegate.getExpectedType();
    }

    @Override
    public String getExpressionString() {
        return delegate.getExpressionString();
    }

    @Override
    public boolean isLiteralText() {
        return delegate.isLiteralText();
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
