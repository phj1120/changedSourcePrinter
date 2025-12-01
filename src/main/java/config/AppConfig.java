package config;

import display.DisplayStrategy;
import enums.DisplayMode;
import service.ChangeDetector;
import service.GitChangeDetector;
import service.GitCommandExecutor;
import service.ProcessBuilderGitExecutor;

public class AppConfig {
    /**
     * 출력 방식 설정
     * - REPOSITORY_FIRST: 레포지토리 -> 브랜치 -> 변경목록
     * - BRANCH_FIRST: 브랜치 -> 레포지토리 -> 변경목록
     */
    private static final DisplayMode DISPLAY_MODE = DisplayMode.REPOSITORY_FIRST;

    private static final AppConfig INSTANCE = new AppConfig();

    private final GitCommandExecutor executor;
    private final ChangeDetector detector;
    private final DisplayStrategy displayStrategy;

    private AppConfig() {
        this.executor = new ProcessBuilderGitExecutor();
        this.detector = new GitChangeDetector(executor);
        this.displayStrategy = DISPLAY_MODE.getStrategy();
    }

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    public GitCommandExecutor getExecutor() {
        return executor;
    }

    public ChangeDetector getDetector() {
        return detector;
    }

    public DisplayStrategy getDisplayStrategy() {
        return displayStrategy;
    }

    public DisplayMode getDisplayMode() {
        return DISPLAY_MODE;
    }
}
