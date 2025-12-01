package service;

import domain.Repository;

import java.util.List;

public interface ChangeDetector {
    List<String> detectChanges(Repository repository, String targetBranch);
}
