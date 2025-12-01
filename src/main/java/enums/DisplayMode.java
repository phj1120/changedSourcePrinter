package enums;

import display.BranchFirstDisplayStrategy;
import display.DisplayStrategy;
import display.RepositoryFirstDisplayStrategy;

public enum DisplayMode {
    REPOSITORY_FIRST(new RepositoryFirstDisplayStrategy()),
    BRANCH_FIRST(new BranchFirstDisplayStrategy());

    private final DisplayStrategy strategy;

    DisplayMode(DisplayStrategy strategy) {
        this.strategy = strategy;
    }

    public DisplayStrategy getStrategy() {
        return strategy;
    }
}
