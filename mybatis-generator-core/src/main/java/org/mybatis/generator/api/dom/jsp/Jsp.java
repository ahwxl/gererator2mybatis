package org.mybatis.generator.api.dom.jsp;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

public class Jsp implements CompilationUnit {

    private FullyQualifiedJavaType type;

    private List<Field>            fields = new ArrayList<Field>();

    public Jsp(String typeName) {
        type = new FullyQualifiedJavaType(typeName);
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public String getFormattedContent() {
        
        Properties p = new Properties();
        String path = "";
        p.setProperty(Velocity.OUTPUT_ENCODING, "GBK");
        p.setProperty(Velocity.INPUT_ENCODING, "GBK");
        p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, path);
  
        VelocityEngine engine = new VelocityEngine();
        try {  
            engine.init(p);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringWriter sw = new StringWriter();

        VelocityContext context = new VelocityContext();

        context.put("fields", fields);

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("jsp.vm");
        
        try {
            engine.evaluate(context, sw, "mylag", new InputStreamReader(in));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sw.toString();
    }

    public Set<FullyQualifiedJavaType> getImportedTypes() {
        return null;
    }

    public Set<String> getStaticImports() {
        return null;
    }

    public FullyQualifiedJavaType getSuperClass() {
        return null;
    }

    public boolean isJavaInterface() {
        return false;
    }

    public boolean isJavaEnumeration() {
        return false;
    }

    public Set<FullyQualifiedJavaType> getSuperInterfaceTypes() {
        return null;
    }

    public FullyQualifiedJavaType getType() {
        return type;
    }

    public void addImportedType(FullyQualifiedJavaType importedType) {
    }

    public void addImportedTypes(Set<FullyQualifiedJavaType> importedTypes) {
    }

    public void addStaticImport(String staticImport) {
    }

    public void addStaticImports(Set<String> staticImports) {
    }

    public void addFileCommentLine(String commentLine) {
    }

    public List<String> getFileCommentLines() {
        return null;
    }

}
