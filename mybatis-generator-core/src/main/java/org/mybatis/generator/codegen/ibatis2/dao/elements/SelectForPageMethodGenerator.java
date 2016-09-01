/**
 *    Copyright 2006-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.codegen.ibatis2.dao.elements;

import java.text.MessageFormat;
import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;


/**
 * 
 * @author Jeff Butler
 * 
 */
public class SelectForPageMethodGenerator extends AbstractDAOElementGenerator {

    private boolean generateForJava5;

    public SelectForPageMethodGenerator() {
        super();
    }

    @Override
    public void addImplementationElements(TopLevelClass topLevelClass) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = getMethodShell(importedTypes);

        // generate the implementation method
        StringBuilder sb = new StringBuilder();

        sb.append("IPagination pagination =  "); //$NON-NLS-1$
        sb.append(MessageFormat.format("this.queryForPagenation(\"{0}.{1}\",\"{0}.{2}\",record);",new String[]{
        		introspectedTable.getIbatis2SqlMapNamespace(),"selectCounts","selectPage"
        }));
        method.addBodyLine(sb.toString());

        method.addBodyLine("return pagination;"); //$NON-NLS-1$

        if (context.getPlugins().clientCountByExampleMethodGenerated(method,
                topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        if (getExampleMethodVisibility() == JavaVisibility.PUBLIC) {
            Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
            Method method = getMethodShell(importedTypes);

            if (context.getPlugins().clientCountByExampleMethodGenerated(
                    method, interfaze, introspectedTable)) {
                interfaze.addImportedTypes(importedTypes);
                interfaze.addMethod(method);
            }
        }
    }

    private Method getMethodShell(Set<FullyQualifiedJavaType> importedTypes) {
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                introspectedTable.getExampleType());
        FullyQualifiedJavaType pageType = new FullyQualifiedJavaType(
                "com.bplow.netconn.base.dao.pagination.IPagination");
        FullyQualifiedJavaType domain = new FullyQualifiedJavaType(
                introspectedTable.getBaseRecordType());
        importedTypes.add(type);

        Method method = new Method();
        method.setVisibility(getExampleMethodVisibility());
        method.setReturnType(pageType);
        method.setName("selectPage");
        method.addParameter(new Parameter(domain, "record")); //$NON-NLS-1$

        for (FullyQualifiedJavaType fqjt : daoTemplate.getCheckedExceptions()) {
            method.addException(fqjt);
            importedTypes.add(fqjt);
        }

//        context.getCommentGenerator().addGeneralMethodComment(method,
//                introspectedTable);

        return method;
    }
}
