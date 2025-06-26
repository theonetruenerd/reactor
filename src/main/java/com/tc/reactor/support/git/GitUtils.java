package com.tc.reactor.support.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.IOException;

public class GitUtils {

    private Git git;

    public GitUtils(String repositoryPath) throws Exception {
        this.git = Git.open(new java.io.File(repositoryPath));
    }

    public void pull(String username, String password) throws GitAPIException {
        git.pull()
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password))
                .call();
        System.out.println("Pull successful");
    }

    public void push(String username, String password) throws GitAPIException {
        git.push()
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password))
                .call();
        System.out.println("Push successful");
    }

    public void commit(String message) throws GitAPIException {
        git.commit().setMessage(message).call();
        System.out.println("Commit successful");
    }

    public void checkout(String branchName) throws GitAPIException {
        git.checkout().setName(branchName).call();
        System.out.println("Checked out to branch " + branchName);
    }

    public void fetch(String username, String password) throws GitAPIException {
        git.fetch()
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password))
                .call();
        System.out.println("Fetch successful");
    }

    public void merge(String branchToMerge) throws GitAPIException, IOException {
        git.merge()
                .include(git.getRepository().resolve(branchToMerge))
                .call();
        System.out.println("Merge successful: " + branchToMerge);
    }

    public void rebase(String branchName) throws GitAPIException {
        git.rebase().setUpstream(branchName).call();
        System.out.println("Rebase successful: " + branchName);
    }

    public void add(String path) throws GitAPIException {
        git.add().addFilepattern(path).call();
        System.out.println("Added " + path);
    }
}