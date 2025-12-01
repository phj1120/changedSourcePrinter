package service;

import config.AppConfig;
import domain.ChangeInfo;
import domain.Repository;

import java.util.ArrayList;
import java.util.List;

public class ChangeTracker {
    private final ChangeDetector detector;

    private ChangeTracker(ChangeDetector detector) {
        this.detector = detector;
    }

    public static List<ChangeInfo> track(List<Repository> repositories, List<String> targetBranches) {
        AppConfig config = AppConfig.getInstance();
        ChangeTracker tracker = new ChangeTracker(config.getDetector());
        return tracker.collectAllChanges(repositories, targetBranches);
    }

    private List<ChangeInfo> collectAllChanges(List<Repository> repositories, List<String> targetBranches) {
        List<ChangeInfo> allChanges = new ArrayList<>();

        for (Repository repo : repositories) {
            for (String branch : targetBranches) {
                try {
                    List<String> changes = detector.detectChanges(repo, branch);

                    if (!changes.isEmpty()) {
                        allChanges.add(new ChangeInfo(repo, branch, changes));
                    }

                } catch (Exception e) {
                    System.err.println("경고: " + repo.getName() + " (" + branch + ") 처리 실패 - " + e.getMessage());
                }
            }
        }

        return allChanges;
    }

}
