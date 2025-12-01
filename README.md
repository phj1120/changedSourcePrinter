# Changed Source Tracking

Git 레포지토리의 브랜치별 변경사항을 추적하는 Java 애플리케이션

## 기능

- 여러 레포지토리의 브랜치별 변경사항을 한 번에 추적
- 두 가지 출력 방식 지원:
  - 레포지토리 우선: Repository -> Branch -> 변경목록
  - 브랜치 우선: Branch -> Repository -> 변경목록
- 설정 파일로 출력 방식 변경 가능

## 프로젝트 구조

```
src/main/java/
├── ChangedSourcePrinterApplication.java  # 메인 애플리케이션
├── domain/                                # 도메인 모델
│   ├── Repository.java                    # 레포지토리 정보
│   └── ChangeInfo.java                    # 변경 정보
├── enums/                                 # Enum 타입
│   ├── RepositoryType.java                # 레포지토리 타입 정의
│   └── DisplayMode.java                   # 출력 방식
├── service/                               # 비즈니스 로직
│   ├── GitCommandExecutor.java            # Git 명령 실행 인터페이스
│   ├── ProcessBuilderGitExecutor.java     # Git 명령 실행 구현체
│   ├── ChangeDetector.java                # 변경사항 감지 인터페이스
│   └── GitChangeDetector.java             # 변경사항 감지 구현체
├── display/                               # 출력 전략
│   ├── DisplayStrategy.java               # 출력 전략 인터페이스
│   ├── RepositoryFirstDisplayStrategy.java # 레포지토리 우선 출력
│   └── BranchFirstDisplayStrategy.java    # 브랜치 우선 출력
├── config/                                # 설정
│   └── AppConfig.java                     # 설정 관리
└── exception/                             # 예외
    ├── GitCommandException.java           # Git 명령 예외
    └── ChangeDetectionException.java      # 변경 감지 예외
```

## 설정

### 1. 레포지토리 설정

`src/main/java/enums/RepositoryType.java` 파일에서 레포지토리 정보를 수정하세요:

```java
public enum RepositoryType {
    B2CKSHOP_EMC("b2ckshop-emc", "master", "C:/path/to/b2ckshop-emc"),
    NKSHOP_EMC("nkshop-emc", "master", "C:/path/to/nkshop-emc"),
    NKSHOP_BOS("nkshop-bos", "master", "C:/path/to/nkshop-bos");
    // 필요한 레포지토리 추가...
}
```

### 2. 출력 방식 설정

`config.properties` 파일에서 출력 방식을 설정하세요:

```properties
# REPOSITORY_FIRST: 레포지토리 -> 브랜치 -> 변경목록
# BRANCH_FIRST: 브랜치 -> 레포지토리 -> 변경목록
display.mode=REPOSITORY_FIRST

# Git 실행 파일 경로
git.path=git
```

## 사용법

### 컴파일

```bash
# src/main/java 디렉토리에서
javac -d out ChangedSourcePrinterApplication.java domain/*.java enums/*.java service/*.java display/*.java config/*.java exception/*.java
```

### 실행

```bash
# 단일 브랜치 추적
java -cp out ChangedSourcePrinterApplication feature/user-auth

# 여러 브랜치 동시 추적
java -cp out ChangedSourcePrinterApplication develop release/v1.0 hotfix/bug-123
```

### 출력 예시

#### 레포지토리 우선 출력

```
========== 변경사항 (레포지토리 우선) ==========

Repository: b2ckshop-emc
  Branch: feature/user-auth (3 files)
    - src/main/User.java
    - src/main/AuthService.java
    - README.md

Repository: nkshop-emc
  Branch: feature/user-auth (2 files)
    - src/api/UserController.java
    - src/api/AuthController.java

==============================================
```

#### 브랜치 우선 출력

```
========== 변경사항 (브랜치 우선) ==========

Branch: feature/user-auth
  Repository: b2ckshop-emc (3 files)
    - src/main/User.java
    - src/main/AuthService.java
    - README.md

  Repository: nkshop-emc (2 files)
    - src/api/UserController.java
    - src/api/AuthController.java

==========================================
```

## 적용된 객체지향 원칙

- **SOLID 원칙**: 모든 클래스가 단일 책임을 가지며, 인터페이스로 추상화
- **Strategy Pattern**: 출력 방식을 런타임에 변경 가능
- **Dependency Injection**: 생성자를 통한 의존성 주입
- **Immutability**: 도메인 모델은 불변 객체로 설계

상세한 내용은 `docs/CODING_GUIDELINES.md`를 참고하세요.

## 요구사항

- Java 8 이상
- Git CLI (PATH에 등록되어 있어야 함)
- Windows 환경 (다른 OS에서도 동작하지만 경로 수정 필요)

## 라이선스

MIT
