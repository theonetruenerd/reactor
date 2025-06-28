package com.tc.reactor.support.languages.hsl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HexFormat;
import java.util.Optional;

public class LibraryHandler {

    public void CreateLibrary(String libraryName,
                              String majorId,
                              Integer libraryVersion,
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
                                    Integer libraryVersion,
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
        templateBuilder.append("\n// Desription:\t\t").append(libraryDescription.orElse("No description provided"));
        templateBuilder.append("\n// Major ID:\t\t").append(majorId);
        templateBuilder.append("\n//\n// Library Version:\t\t").append(libraryVersion);
        templateBuilder.append("\n//\n// Changelog:");
        templateBuilder.append("\n//");
        templateBuilder.append("\n//----------------------------------------------------------------------------------------");
        templateBuilder.append("\n\n#ifndef __").append(prefix).append(libraryName.toUpperCase()).append("__");
        templateBuilder.append("\n#define __").append(prefix).append(libraryName.toUpperCase()).append("__");
        templateBuilder.append("\n\n//======================= Includes =======================");
        templateBuilder.append("\n\n//======================= ").append(libraryName).append(" =======================");
        templateBuilder.append("\n");
        parentNamespace.ifPresent(ns -> templateBuilder.append("namespace ").append(ns).append("\n{"));
        templateBuilder.append("\n").append(indent).append("namespace ").append(libraryName).append("\n{");
        templateBuilder.append("\n").append(indent).append("\tnamespace Errors\n");
        templateBuilder.append("\n").append(indent).append("\t{\n");
        templateBuilder.append("\n").append(indent).append("\t\t//======================= Error codes =======================\n");
        templateBuilder.append("\n").append(indent).append("\t\t"); // CANT REMEMBER HOW I DO ERROR CODES IN DEMO LIBRARY
        templateBuilder.append("\n");
        templateBuilder.append("\n").append(indent).append("\t}\n");
        templateBuilder.append("\n").append(indent).append("\t// Private functions");
        templateBuilder.append("\n");
        templateBuilder.append("\n").append(indent).append("\t// Public functions");
        templateBuilder.append("\n");
        templateBuilder.append("\n").append(indent).append("} // End of ").append(libraryName).append(" namespace");
        parentNamespace.ifPresent(ns -> templateBuilder.append("\n} // End of ").append(ns).append(" namespace"));

        return templateBuilder.toString();
    }
}
