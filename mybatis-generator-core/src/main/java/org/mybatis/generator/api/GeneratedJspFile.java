package org.mybatis.generator.api;

import org.mybatis.generator.api.dom.java.CompilationUnit;

public class GeneratedJspFile extends GeneratedFile {

    /** The compilation unit. */
    private CompilationUnit compilationUnit;

    /** The file encoding. */
    private String          fileEncoding;

    /** The java formatter. */
    private JavaFormatter   javaFormatter;

    public GeneratedJspFile(CompilationUnit compilationUnit, String targetProject,
                            String fileEncoding, JavaFormatter javaFormatter) {
        super(targetProject);
        this.compilationUnit = compilationUnit;
        this.fileEncoding = fileEncoding;
        this.javaFormatter = javaFormatter;
    }

    @Override
    public String getFormattedContent() {
        return javaFormatter.getFormattedContent(compilationUnit);
    }

    @Override
    public String getFileName() {
        return compilationUnit.getType().getShortName() + ".jsp";
    }

    @Override
    public String getTargetPackage() {
        return compilationUnit.getType().getPackageName();
    }

    @Override
    public boolean isMergeable() {
        return false;
    }
    
    public String getFileEncoding() {
        return fileEncoding;
    }

}
