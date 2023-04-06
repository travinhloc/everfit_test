## New branch
git checkout -b feature/dev-abc-def
git checkout -b bugs/dev-abc-def
git checkout -b chores/dev-abc-def


## Formatting
Use lowercase and kebab-case for branches names.

- Bad -> 
feature/list_job_positions
Bug/userResetPassword

- Good ->
feature/list-job-positions
bug/user-reset-password
Use the forward slash / to separate the branch type from the branch title.

- Bad ->
feature-list-job-positions
bug_user-reset-password

- Good ->
feature/list-job-positions
bug/user-reset-password

# Naming
Find a balance between conciseness and descriptiveness for branch titles.

- Bad ->
feature/as-a-user-i-can-logout
bug/as-an-admin-when-i-select-a-menu-item-i-see-a-highlight

- Good ->
feature/user-logout
bug/admin-menu-item-highlight
This helps developers distinguish each branch easily.

Prefix the branch title with the Areas of Implementation.

- Bad ->
ui/feature/add-cart-item
integrate/feature/add-cart-item

- Good ->
feature/ui-add-cart-item
feature/integrate-add-cart-item
This allows recognizing right away the specific work area of a given feature or bug via the branch. It is beneficial when stories of a single feature are divided into multiple areas of implementation and developers need to create separated branches per each story.

Prefix the branch title with the backlog item ID when using project management tools such as Shortcut and JIRA.

- Bad ->
sc-6862/ui/feature/list-job-positions
939/feature/improve-multiple-dependent-branches-management-guideline

- Good ->
feature/sc-6862-ui-list-job-positions
feature/939-improve-multiple-dependent-branches-management-guideline

## Notes
- Features: provide the list of features in the release.
- Chores: provide the list of chores in the release.
- Bugs: provide the list of bugs fixes in the release.

