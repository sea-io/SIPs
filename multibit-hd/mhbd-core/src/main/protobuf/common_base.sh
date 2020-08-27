function get_branch_sha_apple() {
  local branch_sha_apple="$(git merge-base --fork-point FETCH_HEAD HEAD || git merge-base FETCH_HEAD HEAD)"
  echo "$branch_sha_apple"
}
