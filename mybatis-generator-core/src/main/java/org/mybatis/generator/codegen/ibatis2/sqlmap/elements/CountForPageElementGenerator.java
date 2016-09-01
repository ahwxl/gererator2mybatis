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
package org.mybatis.generator.codegen.ibatis2.sqlmap.elements;

import java.text.MessageFormat;
import java.util.Iterator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.ibatis2.Ibatis2FormattingUtilities;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class CountForPageElementGenerator extends AbstractXmlElementGenerator {

    public CountForPageElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

        answer.addAttribute(new Attribute("id","countForPage"));
        answer.addAttribute(new Attribute("resultClass","java.lang.Integer"));

        StringBuilder sb = new StringBuilder(" select count(0) from "+ introspectedTable.getFullyQualifiedTableNameAtRuntime() );
        
        answer.addElement(0,new TextElement(sb.toString()));
        
        Iterator<IntrospectedColumn> iter = introspectedTable
                .getNonBLOBColumns().iterator();
        
        XmlElement dynamic = new XmlElement("dynamic");
        answer.addElement(dynamic);
        
        //²åÈë¶¯Ì¬²éÑ¯
        while (iter.hasNext()) {
            
        	IntrospectedColumn column = iter.next();
        	
        	XmlElement isNotEmpty = new XmlElement("isNotEmpty");
        	isNotEmpty.addAttribute(new Attribute("prepend","AND"));
        	isNotEmpty.addAttribute(new Attribute("property",column.getJavaProperty()));
        	
        	isNotEmpty.addElement(new TextElement(MessageFormat.format("{0} = #{1}#",column.getActualColumnName(),column.getJavaProperty())  ));
        	
        	dynamic.addElement(isNotEmpty);

            /*if (sb.length() > 80) {
                answer.addElement(new TextElement(sb.toString()));
                sb.setLength(0);
            }*/
        }

        if (context.getPlugins().sqlMapBaseColumnListElementGenerated(
                answer, introspectedTable)) {
            parentElement.addElement(answer);
        }
    }
}
