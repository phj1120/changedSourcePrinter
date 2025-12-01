package domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ChangeInfo {
    private final Repository repository;
    private final String branch;
    private final List<String> changedFiles;

    public ChangeInfo(Repository repository, String branch, List<String> changedFiles) {
        this.repository = Objects.requireNonNull(repository, "Repository는 필수입니다.");
        this.branch = Objects.requireNonNull(branch, "Branch는 필수입니다.");
        this.changedFiles = Collections.unmodifiableList(
                Objects.requireNonNull(changedFiles, "Changed files는 필수입니다.")
        );
    }

    public Repository getRepository() {
        return repository;
    }

    public String getBranch() {
        return branch;
    }

    public List<String> getChangedFiles() {
        return changedFiles;
    }

    public boolean hasChanges() {
        return !changedFiles.isEmpty();
    }

    @Override
    public String toString() {
        return "ChangeInfo{" +
                "repository=" + repository.getName() +
                ", branch='" + branch + '\'' +
                ", changedFilesCount=" + changedFiles.size() +
                '}';
    }
}
