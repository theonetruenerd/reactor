package com.tc.reactor.support.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;

public class GitUtils {

    private Repository repository;

    /**
     * Loads the Git repository from the specified directory.
     *
     * @param repoPath The path to the directory where the .git folder is located.
     * @throws IOException If the repository cannot be loaded.
     */
    public void setRepository(String repoPath) throws IOException {
        this.repository = new FileRepositoryBuilder()
                .setGitDir(new File(repoPath + "/.git")) // Path to the .git folder
                .readEnvironment() // Automatically read environment settings, such as user configs
                .findGitDir()      // Search for the .git directory if the path is incomplete
                .build();
    }

    /**
     * Returns the current loaded repository.
     *
     * @return the Repository object
     */
    public Repository getRepository() {
        return repository;
    }

    /**
     * Creates a new Git repository at the specified file system path.
     *
     * @param repoPath The file system path where the repository will be initialized.
     * @throws IOException If an error occurs while accessing the file system.
     * @throws GitAPIException If an error occurs during the repository creation process.
     */
    public void createRepository(String repoPath) throws IOException, GitAPIException {
        try {
            Git git = Git.init().setDirectory(new File(repoPath)).call(); {
                System.out.println("Repository created at " + repoPath);
            }
        } catch (GitAPIException e) {
            throw new RuntimeException(e);
        }
    }
}