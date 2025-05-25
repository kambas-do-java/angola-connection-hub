# Conventional Commits Guide for Open Source Contributors

## What are Conventional Commits?

Conventional Commits is a specification for adding human and machine-readable meaning to commit messages. It provides an easy set of rules for creating an explicit commit history, making it easier to write automated tools on top of. This convention dovetails with semantic versioning, by describing the features, fixes, and breaking changes made in commit messages.

## Why Use Conventional Commits?

**For Contributors:**
- Creates consistency across the project
- Makes it easier to understand what changes were made
- Helps with code review process
- Reduces cognitive load when writing commit messages

**For Maintainers:**
- Automatically generates changelogs
- Enables semantic versioning automation
- Improves project navigation and debugging
- Facilitates better release management

**For Users:**
- Clear understanding of what changed between versions
- Better bug tracking and issue resolution
- Transparent project evolution

## Basic Structure

```
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```

### Examples:
```
feat: add user authentication system
fix: resolve memory leak in data processor
docs: update installation instructions
style: fix code formatting in auth module
refactor: extract common validation logic
test: add unit tests for payment service
chore: update dependencies to latest versions
```

## Commit Types

### Primary Types (Must Use)

#### `feat` - New Features
- **Use for**: Adding new functionality
- **Impact**: Minor version bump in semantic versioning
- **Examples**:
  ```
  feat: add dark mode toggle
  feat: implement user profile management
  feat(api): add pagination support for users endpoint
  ```

#### `fix` - Bug Fixes
- **Use for**: Fixing bugs or issues
- **Impact**: Patch version bump in semantic versioning
- **Examples**:
  ```
  fix: resolve login button not responding on mobile
  fix: prevent null pointer exception in data parsing
  fix(auth): handle expired token gracefully
  ```

### Secondary Types (Recommended)

#### `docs` - Documentation
- **Use for**: Documentation changes only
- **Impact**: No version bump
- **Examples**:
  ```
  docs: add API usage examples
  docs: fix typos in README
  docs(contributing): update pull request guidelines
  ```

#### `style` - Code Style
- **Use for**: Changes that don't affect code meaning (white-space, formatting, missing semi-colons)
- **Impact**: No version bump
- **Examples**:
  ```
  style: fix indentation in config files
  style: remove trailing whitespace
  style(components): apply consistent naming convention
  ```

#### `refactor` - Code Refactoring
- **Use for**: Code changes that neither fix bugs nor add features
- **Impact**: No version bump (unless breaking changes)
- **Examples**:
  ```
  refactor: extract database connection logic
  refactor: simplify error handling mechanism
  refactor(utils): consolidate string manipulation functions
  ```

#### `test` - Tests
- **Use for**: Adding or modifying tests
- **Impact**: No version bump
- **Examples**:
  ```
  test: add integration tests for user service
  test: fix flaky test in payment processing
  test(e2e): add checkout flow test scenarios
  ```

#### `chore` - Maintenance
- **Use for**: Changes to build process, auxiliary tools, libraries, documentation
- **Impact**: No version bump
- **Examples**:
  ```
  chore: update webpack configuration
  chore: bump lodash from 4.17.19 to 4.17.21
  chore(deps): update development dependencies
  ```

### Additional Types (Optional)

#### `perf` - Performance Improvements
```
perf: improve database query performance
perf(images): optimize image loading strategy
```

#### `ci` - Continuous Integration
```
ci: add automated testing workflow
ci: fix deployment pipeline configuration
```

#### `build` - Build System
```
build: update Dockerfile for production
build: configure webpack for development
```

#### `revert` - Reverting Changes
```
revert: revert "feat: add experimental feature"
```

## Scopes

Scopes provide additional contextual information about the change. They should be specific to your project structure.

### Common Scope Examples:

**By Component/Module:**
```
feat(auth): add OAuth2 integration
fix(database): resolve connection timeout
docs(api): update endpoint documentation
```

**By Technology/Layer:**
```
style(css): improve responsive design
test(unit): add service layer tests
chore(npm): update package dependencies
```

**By Feature Area:**
```
feat(checkout): implement guest checkout
fix(search): improve search result accuracy
refactor(payments): modernize payment processing
```

## Breaking Changes

When a commit introduces breaking changes, it should be indicated in the commit message.

### Method 1: Exclamation Mark
```
feat!: remove deprecated user API endpoints
fix!: change function signature for calculateTotal
```

### Method 2: Footer
```
feat: add new configuration system

BREAKING CHANGE: configuration file format has changed from JSON to YAML.
Existing config.json files need to be converted to config.yaml format.
```

### Method 3: Body and Footer
```
refactor: restructure user data model

The user object structure has been simplified to improve performance.

BREAKING CHANGE: 
- `user.profile.firstName` is now `user.firstName`
- `user.profile.lastName` is now `user.lastName`  
- `user.contactInfo.email` is now `user.email`
```

## Writing Good Commit Messages

### Description Guidelines

**Do:**
- Use imperative mood ("add" not "added" or "adds")
- Start with lowercase letter
- Be concise but descriptive
- Limit to 50-72 characters
- Focus on what the commit does, not how

**Don't:**
- End with a period
- Use past tense
- Be vague or generic
- Include implementation details in description

### Good Examples:
```
✅ feat: add user registration validation
✅ fix: resolve memory leak in image processing
✅ docs: clarify installation requirements
✅ refactor: extract authentication middleware
```

### Bad Examples:
```
❌ feat: Added some new stuff for users
❌ fix: Fixed a bug
❌ update: Updated things
❌ refactor: Changed how we do authentication by moving the logic to a separate middleware file and updating all the routes
```

## Body and Footer Guidelines

### When to Use Body:
- Explain **why** the change was made
- Provide context for complex changes
- Reference issues or requirements
- Explain any non-obvious effects

### Body Example:
```
feat: implement lazy loading for user avatars

Users reported slow loading times on the team page. This change
loads avatars only when they become visible, reducing initial
page load time by approximately 40%.

Resolves: #123
See also: #456, #789
```

### Footer Uses:
- Reference issues: `Fixes #123`, `Closes #456`, `Resolves #789`
- Breaking changes: `BREAKING CHANGE: description`
- Co-authors: `Co-authored-by: name <email>`
- Reviewers: `Reviewed-by: name <email>`

## Project-Specific Guidelines

### Always Check:
1. **Project's CONTRIBUTING.md** - May have specific conventions
2. **Existing commit history** - Follow established patterns
3. **CI/CD requirements** - Some projects enforce conventional commits
4. **Issue/PR templates** - May specify commit format requirements

### Common Project Variations:
- Some projects use `feature` instead of `feat`
- Some require ticket numbers: `feat(#123): add user auth`
- Some have custom scopes: `feat(frontend)`, `fix(backend)`
- Some enforce specific case: `Feat:` vs `feat:`

## Tools and Automation

### Commit Message Tools:
- **Commitizen**: Interactive commit message builder
- **Husky + Commitlint**: Enforce conventional commits in git hooks
- **Conventional Changelog**: Generate changelogs from commits
- **Semantic Release**: Automate versioning and publishing

### IDE Extensions:
- **VS Code**: Conventional Commits extension
- **IntelliJ**: Git Commit Template plugin
- **Vim**: vim-commitizen plugin

### Setup Example (Node.js):
```bash
npm install --save-dev @commitlint/config-conventional @commitlint/cli husky

# Add to package.json
{
  "husky": {
    "hooks": {
      "commit-msg": "commitlint -E HUSKY_GIT_PARAMS"
    }
  }
}
```

## Examples by Scenario

### New Feature Development:
```
feat: implement user dashboard

Add personalized dashboard showing user statistics,
recent activity, and quick action buttons.

- Display user stats (posts, comments, likes)
- Show recent activity timeline  
- Add quick create post button
- Implement responsive design for mobile

Closes #234
```

### Bug Fix:
```
fix: prevent duplicate form submissions

Add client-side validation and server-side idempotency
key to prevent users from accidentally submitting
forms multiple times.

The issue occurred when users clicked submit button
rapidly or when network was slow.

Fixes #567
Tested-by: Jane Doe <jane@example.com>
```

### Documentation Update:
```
docs: add authentication flow diagram

Include sequence diagram showing OAuth2 flow and
add troubleshooting section for common auth issues.

The diagram helps new contributors understand the
authentication process more quickly.
```

### Breaking Change:
```
feat!: migrate to new configuration format

BREAKING CHANGE: Configuration file format changed from JSON to YAML.

Migration required:
1. Rename config.json to config.yaml
2. Convert JSON syntax to YAML
3. Update environment variable names (see migration guide)

Migration script available at scripts/migrate-config.js

Closes #890
```

## Common Mistakes and How to Avoid Them

### Mistake 1: Mixing Multiple Changes
```
❌ feat: add user auth and fix login bug and update docs
✅ feat: add user authentication system
✅ fix: resolve login validation error  
✅ docs: update authentication guide
```

### Mistake 2: Vague Descriptions
```
❌ fix: bug fix
✅ fix: resolve null pointer exception in user profile
```

### Mistake 3: Wrong Type Usage
```
❌ feat: fix typo in documentation
✅ docs: fix typo in installation guide
```

### Mistake 4: Past Tense
```
❌ feat: added user registration
✅ feat: add user registration
```

### Mistake 5: Including Implementation Details in Description
```
❌ feat: add new UserService class with registerUser method
✅ feat: implement user registration functionality
```

## Quick Reference Checklist

Before committing, ask yourself:

- [ ] Is the type correct for what I changed?
- [ ] Is the description clear and concise?
- [ ] Did I use imperative mood?
- [ ] Is the scope appropriate and consistent?
- [ ] Do I need a body to explain why?
- [ ] Are there any breaking changes to mention?
- [ ] Did I reference relevant issues?
- [ ] Does this follow the project's specific conventions?

## Resources and Further Reading

- **Official Specification**: [conventionalcommits.org](https://www.conventionalcommits.org/)
- **Semantic Versioning**: [semver.org](https://semver.org/)
- **Angular Commit Guidelines**: [GitHub - Angular Contributing](https://github.com/angular/angular/blob/main/CONTRIBUTING.md#commit)
- **Commitizen**: [GitHub - commitizen/cz-cli](https://github.com/commitizen/cz-cli)
- **Commitlint**: [GitHub - conventional-changelog/commitlint](https://github.com/conventional-changelog/commitlint)

## Conclusion

Conventional commits may seem restrictive at first, but they become second nature with practice. They significantly improve project maintainability, collaboration, and automation capabilities. When contributing to open source projects, following these conventions shows professionalism and respect for the project's standards.

Remember: consistency is key. When in doubt, look at recent commits in the project and follow the established patterns. Good commit messages are a gift to your future self and your fellow contributors.