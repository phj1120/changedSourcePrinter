# Source Change Tracker

Git 레포지토리의 브랜치별 소스 변경사항을 추적하는 Java 애플리케이션

## 설정

### 1. 레포지토리 경로 설정
`src/main/java/enums/RepositoryType.java`에서 추적할 레포지토리 정보 입력:

```java
public enum RepositoryType {
    REPO1("repo-name", "master", "/path/to/repo1"),
    REPO2("repo-name", "master", "/path/to/repo2"),
    ;
}
```

### 2. 추적 브랜치 설정
`src/main/java/SourceChangeTrackerApplication.java`에서 추적할 브랜치 입력:

```java
private static final List<String> TRACKING_BRANCHES = Arrays.asList("develop", "release/v1.0");
```

### 3. 출력 방식 설정 (선택)
`src/main/java/config/AppConfig.java`에서 출력 방식 선택:

```java
private static final DisplayMode DISPLAY_MODE = DisplayMode.REPOSITORY_FIRST;
// REPOSITORY_FIRST: 레포지토리 -> 브랜치 -> 변경목록
// BRANCH_FIRST: 브랜치 -> 레포지토리 -> 변경목록
```

## 제약사항

### 실행 전 필수 작업

**프로그램 실행 전에 대상 레포지토리를 fetch 받아야 합니다.**

- 로컬 Git 정보를 사용하기 때문에 fetch하지 않으면 과거 브랜치 기준으로 비교됩니다.
- `origin/branch-name` 형식을 사용하면 로컬에 체크아웃하지 않아도 원격 브랜치를 조회할 수 있습니다.

### 비교 방식 이해

**이 프로그램은 단순히 기준 브랜치와 대상 브랜치의 차이(`git diff`)를 보여줍니다.**

- 대상 브랜치에서 직접 수정한 파일만 보여주는 것이 아닙니다.
- 기준 브랜치(master)가 업데이트되면, 대상 브랜치(feature)에 기준 브랜치를 머지해야 정확한 비교가 가능합니다.
- 머지하지 않으면 대상 브랜치에서 수정하지 않은 내용도 차이로 표시될 수 있습니다.

**예시:**
```
1. feature 브랜치 생성 (master에서 분기)
2. master에 새로운 커밋 추가
3. 비교 시 → master의 새 변경사항도 차이로 표시됨
4. 해결 → feature에 master를 머지 후 비교
```