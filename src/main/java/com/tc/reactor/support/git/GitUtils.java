package com.tc.reactor.support.git;

import javafx.scene.control.TreeItem;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.lib.IndexDiff;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class GitUtils {

    private Repository repository;

    /**
     * Sets the repository for the utility by specifying the path to a Git repository.
     * This method verifies the existence of a valid `.git` directory in the specified path
     * and initializes the repository to be used by the utility.
     *
     * @param repoPath The file system path to a Git repository.
     * @throws IOException If an I/O error occurs during the repository setup.
     * @throws RepositoryNotFoundException If no Git repository exists at the specified path.
     */
    public void setRepository(String repoPath) throws IOException {
        File gitDir = new File(repoPath, ".git");
        if (!gitDir.exists() || !gitDir.isDirectory()) {
            throw new RepositoryNotFoundException("No Git repository found in the selected directory: " + repoPath);
        }

        this.repository = new FileRepositoryBuilder()
                .setGitDir(gitDir) // Set the .git directory explicitly
                .readEnvironment() // Automatically read environment settings
                .build();
    }

    /**
     * Returns a TreeItem containing the current status, including uncommitted changes.
     * This method retrieves added, modified, removed, and untracked files/folders.
     *
     * @return a TreeItem with the uncommitted changes
     * @throws GitAPIException if the Git command fails
     */
    public TreeItem<String> getUncommittedChanges() throws GitAPIException {
        TreeItem<String> root = new TreeItem<>("Uncommitted Changes");
        try (Git git = new Git(repository)) {
            Status status = git.status().call();
            System.out.println("Uncommitted Changes: " + status);
            // Added files
            addStatusToTreeItem(root, "Added Files", status.getAdded());
            // Modified files
            addStatusToTreeItem(root, "Modified Files", status.getModified());
            // Removed files
            addStatusToTreeItem(root, "Removed Files", status.getRemoved());
            // Untracked files
            addStatusToTreeItem(root, "Untracked Files", status.getUntracked());
        }
        System.out.println("Uncommitted Changes TreeItem: " + root);
        return root;
    }

    private void addStatusToTreeItem(TreeItem<String> parent, String category, Set<String> changes) {
        if (!changes.isEmpty()) {
            TreeItem<String> categoryNode = new TreeItem<>(category);
            for (String file : changes) {
                categoryNode.getChildren().add(new TreeItem<>(file));
            }
            parent.getChildren().add(categoryNode);
        }
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