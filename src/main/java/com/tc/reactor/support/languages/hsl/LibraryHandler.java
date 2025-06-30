package com.tc.reactor.support.languages.hsl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.time.LocalDate;

public class LibraryHandler {

    public void CreateLibrary(String libraryName,
                              String majorId,
                              Float libraryVersion,
                              Path libraryPath,
                              Optional<String> parentNamespace,
                              Optional<String> libraryDescription)
    {
        File libraryFile = new File(libraryPath.toString() + "\\" + libraryName + ".hsl");

        try {
            if (libraryFile.getParentFile() != null && !libraryFile.getParentFile().exists()) {
                if (!libraryFile.getParentFile().mkdirs()) {
                    throw new IOException("Failed to create directories for the file path");
                }
            }
            if (libraryFile.createNewFile()) {
                System.out.println("Library file created: " + libraryFile.getAbsolutePath());
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(libraryFile))) {
                    writer.write(generateTemplate(libraryName, majorId, libraryVersion, parentNamespace, libraryDescription));
                    System.out.println("File populated with template contents");
                }
            } else {
                System.err.println("File already exists: " + libraryFile.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateTemplate(String libraryName,
                                    String majorId,
                                    Float libraryVersion,
                                    Optional<String> parentNamespace,
                                    Optional<String> libraryDescription) {
        StringBuilder templateBuilder = new StringBuilder();
        String prefix = parentNamespace.map(ns -> ns + "_").orElse("");
        String indent = parentNamespace.isPresent() ? "\t" : "";
        templateBuilder.append("//----------------------------------------------------------------------------------------");
        templateBuilder.append("\n//");
        templateBuilder.append("\n// Library Name:\t\t");
        parentNamespace.ifPresent(ns -> templateBuilder.append(ns).append("::"));
        templateBuilder.append(libraryName);
        templateBuilder.append("\n// Desription:\t\t\t").append(libraryDescription.orElse("No description provided"));
        templateBuilder.append("\n// Author:\t\t\t").append(System.getProperty("user.name"));
        templateBuilder.append("\n// Date Created:\t\t\t").append(LocalDate.now());
        templateBuilder.append("\n// Major ID:\t\t\t").append(majorId);
        templateBuilder.append("\n//\n// Library Version:\t\tv").append(libraryVersion);
        templateBuilder.append("\n//\n// Changelog:");
        templateBuilder.append("\n//\t\t\tv").append(libraryVersion).append("\t-\t").append(LocalDate.now()).append("\t-\t").append("Library Created");
        templateBuilder.append("\n//");
        templateBuilder.append("\n//----------------------------------------------------------------------------------------");
        templateBuilder.append("\n\n#ifndef __").append(prefix).append(libraryName.toUpperCase()).append("__");
        templateBuilder.append("\n#define __").append(prefix).append(libraryName.toUpperCase()).append("__");
        templateBuilder.append("\n\n//======================= Includes =======================");
        templateBuilder.append("\n");
        templateBuilder.append("\n#ifndef __HSLMTHLIB_HSL__");
        templateBuilder.append("\n\t#include \"HSLMthLib.hsl\"");
        templateBuilder.append("\n#endif");
        templateBuilder.append("\n\n//======================= ").append(libraryName).append(" =======================");
        templateBuilder.append("\n");
        parentNamespace.ifPresent(ns -> templateBuilder.append("namespace ").append(ns).append(" {\n"));
        templateBuilder.append("\n").append(indent).append("namespace ").append(libraryName).append(" {\n");
        templateBuilder.append("\n").append(indent).append("\tnamespace Errors {\n");
        templateBuilder.append("\n").append(indent).append("\t\tstatic const variable MajorID\t\t\t\t(").append(majorId).append(");\t// Major Error ID");
        templateBuilder.append("\n");
        templateBuilder.append("\n").append(indent).append("\t\tnamespace MinorID { // Minor Error IDs");
        templateBuilder.append("\n").append(indent).append("\t\t\t");
        templateBuilder.append("\n").append(indent).append("\t\t} // End of MinorID namespace");
        templateBuilder.append("\n");
        templateBuilder.append("\n").append(indent).append("\t\tnamespace SpecificErrorID { // Specific Error IDs");
        templateBuilder.append("\n").append(indent).append("\t\t\t");
        templateBuilder.append("\n").append(indent).append("\t\t} // End of SpecificErrorID namespace");
        templateBuilder.append("\n");
        templateBuilder.append("\n").append(indent).append("\t\t// --------------------------");
        templateBuilder.append("\n").append(indent).append("\t\t// Function: Raise Runtime Error");
        templateBuilder.append("\n").append(indent).append("\t\t// Scope: Static");
        templateBuilder.append("\n").append(indent).append("\t\t// Description: Handles the generation of error codes and descriptions in the trace");
        templateBuilder.append("\n").append(indent).append("\t\t// Parameters:");
        templateBuilder.append("\n").append(indent).append("\t\t//\t[i] majorID\t-\tThe major error ID");
        templateBuilder.append("\n").append(indent).append("\t\t//\t[i] minorID\t-\tThe minor error ID");
        templateBuilder.append("\n").append(indent).append("\t\t//\t[i] specificID\t-\tThe specific error ID");
        templateBuilder.append("\n").append(indent).append("\t\t//\t[i] errorDescription\t-\tThe error description");
        templateBuilder.append("\n").append(indent).append("\t\t//\t[i] functionName\t-\tThe name of the function that raised the error");
        templateBuilder.append("\n").append(indent).append("\t\t//\t[i] lineNumber\t-\tThe line number of the function that raised the error");
        templateBuilder.append("\n").append(indent).append("\t\t// Returns: Void");
        templateBuilder.append("\n").append(indent).append("\t\t// --------------------------");
        templateBuilder.append("\n").append(indent).append("\t\tstatic function RaiseRuntimeError(");
        templateBuilder.append("\n").append(indent).append("\t\t\tvariable majorID,");
        templateBuilder.append("\n").append(indent).append("\t\t\tvariable minorID,");
        templateBuilder.append("\n").append(indent).append("\t\t\tvariable specificID,");
        templateBuilder.append("\n").append(indent).append("\t\t\tstring errorDescription,");
        templateBuilder.append("\n").append(indent).append("\t\t\tstring functionName,");
        templateBuilder.append("\n").append(indent).append("\t\t\tinteger lineNumber) void");
        templateBuilder.append("\n").append(indent).append("\t\t{");
        templateBuilder.append("\n").append(indent).append("\t\t\t// Defining function variables");
        templateBuilder.append("\n").append(indent).append("\t\t\tvariable HxResult;");
        templateBuilder.append("\n").append(indent).append("\t\t\tvariable description;");
        templateBuilder.append("\n");
        templateBuilder.append("\n").append(indent).append("\t\t\t// Generating error code");
        templateBuilder.append("\n").append(indent).append("\t\t\tHxResult = MthShiftLeft(minorID & 0x1F, 24) | MthShiftLeft(majorID & 0xFF, 16) | (specificID & 0xFFFF);");
        templateBuilder.append("\n").append(indent).append("\t\t\t// Defining error description;");
        templateBuilder.append("\n").append(indent).append("\t\t\tdescription = \"").append(libraryName).append(".hsl (\"+lineNumber+\") : \" + functionName + \"() : \" + errorDescription;");
        templateBuilder.append("\n").append(indent).append("\t\t\terr.SetDescription(description);");
        templateBuilder.append("\n").append(indent).append("\t\t\t// Raising error");
        templateBuilder.append("\n").append(indent).append("\t\t\terr.Raise(HxResult, err.GetDescription());");
        templateBuilder.append("\n").append(indent).append("\t\t\t// Returning void");
        templateBuilder.append("\n").append(indent).append("\t\t\treturn;");
        templateBuilder.append("\n").append(indent).append("\t\t}");
        templateBuilder.append("\n").append(indent).append("\t} // End of Errors Namespace\n");
        templateBuilder.append("\n").append(indent).append("\t// Private functions");
        templateBuilder.append("\n");
        templateBuilder.append("\n").append(indent).append("\t// Public functions");
        templateBuilder.append("\n");
        templateBuilder.append("\n").append(indent).append("} // End of ").append(libraryName).append(" namespace");
        parentNamespace.ifPresent(ns -> templateBuilder.append("\n} // End of ").append(ns).append(" namespace"));
        templateBuilder.append("\n\n#endif // __").append(prefix).append(libraryName.toUpperCase()).append("__");

        return templateBuilder.toString();
    }
}
