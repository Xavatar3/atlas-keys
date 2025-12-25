# Contributing to AtlasKeys ⚡

Thank you for your interest in contributing! 💡  
This guide will help keep contributions consistent, readable, and professional.

---

## 1. Commit Message Guidelines

Use the following format for all commits:  
```
[type] (Short summary of the change)
Optional detailed description if needed.
```

### Types
- `[feature]` → adding a new feature  
- `[bugfix]` → fixing a bug  
- `[refactor]` → code changes that don’t affect functionality  
- `[docs]` → documentation updates  
- `[test]` → adding or updating tests  
- `[ci/cd]` → CI/CD changes  
- `[chore]` → maintenance or config updates  

### Summary Rules
- Use **imperative mood**: Add, Fix, Update, Remove, Refactor  
- Keep it concise (<50–72 characters)  
- Be specific: describe **what changed**, not why  

**Example:**
```
[feature] Add predictive engine placeholder
[bugfix] Fix crash on empty keyboard input
[docs] Update README with getting started instructions
[feature] Add initial Settings activity
```

---

## 2. Branching Strategy

AtlasKeys uses a structured branching model inspired by **Git Flow**. This keeps development organized, prevents conflicts, and ensures stable releases.

### **Permanent Branches**

| Branch | Purpose | Merge Rules |
|:-------|:--------|:------------|
| `main` | Stable, production-ready code | Only merged via PR from `dev` or `hotfix` |
| `dev`  | Active development | Receives merges from feature and bugfix branches; eventually merges into `main` |
| `hotfix/*` | Critical fixes for production | Branch from `main`; merge back into both `main` and `dev` |

### **Short-lived Branches**

| Branch | Purpose | Merge Target |
|:-------|:--------|:-------------|
| `feature/<name>` | New feature or functionality | Merge into `dev` after completion |
| `bugfix/<name>` | Minor bug fixes | Merge into `dev` after completion |
| `experiment/<name>` | Experimental changes or prototypes | Merge into `dev` only if stable |

### Git Flow Diagram
```
main ━┓  
      ┃  
      ┣━ dev ━━━━━━━━━━━━━━━┓  
      ┃      feature/* ━━━━━┫  
      ┃      bugfix/* ━━━━━━┫  
      ┃      ci/cd/* ━━━━━━━┫  
      ┃      experiment/* ━━┛  
      ┃  
      ┗━hotfix/*  
```

### **Rules and Best Practices**

1. Always pull the latest target branch before creating a new branch.  
2. Do **not commit directly** to `main` or `dev`.  
3. Keep each branch focused on **one logical change**.  
4. Merge via PRs only; require review if collaborating with others.  
5. Delete feature/experiment branches after merging to keep the repo clean.  
6. Hotfixes merge directly into `main` for urgent fixes, then merged into `dev` to stay in sync.

### **Example Workflow**

  1. **Add a new feature:**  
    ```dev → feature/predictive-engine → dev```
  2. **Fix a minor bug:**  
    ```dev → bugfix/layout-fix → dev```
  3. **Critical production bug:**  
    `main → hotfix/crash-fix → main + dev`

---

## 3. Pull Requests (PRs)

1. Push your branch to GitHub.  
2. Open a PR targeting the `main` branch.  
3. Use a descriptive PR title:
4. Include context or relevant issues in the PR body.  
5. Wait for review and CI checks to pass before merging.  

**Note:** You don’t need a PR for personal branches unless merging into `main` or `develop`.  

---

## 4. Building & Testing

- Build the project in your IDE (Android Studio) before committing.  
- Ensure any new code compiles and passes existing tests.  
- If adding a new feature, include tests where possible.  
- Push broken builds only to experimental branches.  

---

## 5. Code Style & Formatting

- Follow standard **Kotlin conventions**:  
- PascalCase for classes, camelCase for functions/variables.  
- 4-space indentation, spaces around operators.  
- Use **Android Studio auto-formatting**: `Code → Reformat Code`.  
- Keep resources named clearly and consistently (`ic_settings.xml`, `background_placeholder.png`).  

---

## 6. Documentation

- Update the README if a new feature, structure, or setup step is added.  
- Include instructions for contributors in relevant sections.  
- Optional additional docs:  
- `STRUCTURE.md` for folder explanations  
- `CHANGELOG.md` for version tracking  

---

## 7. CI/CD Updates

- Only update workflows if adding new build steps, tests, or modifying project structure.  
- Test CI locally or on a feature branch before merging to `main`.  

---

## 8. Notice / Disclaimer 📌

I am a **beginner in Android development**.  
I welcome advice, constructive criticism, and suggestions to improve this project.  
Contributions, guidance, and feedback are highly appreciated! 💡

---

Thank you for helping make AtlasKeys ⚡ better! 🚀