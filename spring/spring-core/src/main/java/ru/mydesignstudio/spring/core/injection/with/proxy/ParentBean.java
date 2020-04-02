package ru.mydesignstudio.spring.core.injection.with.proxy;

public class ParentBean {
    private ChildBean childBean;

    public void setChildBean(ChildBean childBean) {
        this.childBean = childBean;
    }

    public ChildBean getChildBean() {
        return childBean;
    }

    public int doSomething() {
        return childBean.doSomething();
    }
}
