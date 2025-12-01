package service;

import exception.GitCommandException;

public interface GitCommandExecutor {
    String execute(String workingDirectory, String... commands) throws GitCommandException;
}
