package ru.mydesignstudio.spring.core.property.editor;

import java.beans.PropertyEditorSupport;

public class ComplexObjectPropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new ComplexObject(text));
    }
}
