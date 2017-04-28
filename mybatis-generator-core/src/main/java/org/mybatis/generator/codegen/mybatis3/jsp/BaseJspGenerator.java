package org.mybatis.generator.codegen.mybatis3.jsp;

import static org.mybatis.generator.internal.util.JavaBeansUtil.getJavaBeansField;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.jsp.Jsp;
import org.mybatis.generator.codegen.AbstractJavaGenerator;

public class BaseJspGenerator extends AbstractJavaGenerator{

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
            introspectedTable.getBaseRecordType());
        Jsp jsp = new Jsp(type.getFullyQualifiedName()+"jsp");
        
        
        List<IntrospectedColumn> introspectedColumns = getColumnsInThisClass();
        
        for (IntrospectedColumn introspectedColumn : introspectedColumns) {
            Field field = getJavaBeansField(introspectedColumn, context, introspectedTable);
            
            jsp.addField(field);
        }
        
        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        answer.add(jsp);
        return answer;
    }
    
    
    private List<IntrospectedColumn> getColumnsInThisClass() {
        List<IntrospectedColumn> introspectedColumns;

        introspectedColumns = introspectedTable.getAllColumns();

        return introspectedColumns;
    }

}
