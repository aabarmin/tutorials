#!/bin/bash

clone_all() {
  local property_file=$1

  local repos_list="$(property_read $property_file "repos.list")"
  local repos_folder="$(property_read $property_file "repos.folder")"

  ## Checking parameters
  if [[ -z "$repos_list" ]]; then
    log_error "repos.list property is empty"
    exit 1
  fi

  if [[ -z "$repos_folder" ]]; then
    log_error "repos.folder property is empty"
    exit 1
  fi

  ## Create a folder to store repos
  if [[ ! -d "$repos_folder" ]]; then
    log "Creating folder $repos_folder"
    mkdir "$repos_folder"
  fi

  ## Cloning repos
  do_clone_all "$property_file"
}

do_clone_all() {
  local property_file=$1

  local repos_list="$(property_read $property_file "repos.list")"
  local credentials_login="$(property_read $property_file "credentials.login")"
  local credentials_password="$(property_read $property_file "credentials.password")"

  ## Check the login/password pair
  if [[ -z "$credentials_login" ]]; then
    log "Login is not set (credentials.login is empty)"
  fi

  if [[ -z "$credentials_password" ]]; then
    log "Password is not set (credentials.password is empty)"
  fi

  ## Start cloning repos one by one
  while IFS= read -r line
  do
    if [[ -n "$line" ]]; then
      clone "$property_file" "$line"
      log ""
    fi
  done < "$repos_list"
}

clone() {
  local property_file=$1
  local repo_url=$2

  local repo_folder="$(get_repository_folder $repo_url)"
  local updated_repo_url="$(get_repository_url $repo_url $property_file)"
  local repos_folder="$(property_read $property_file "repos.folder")"
  local repo_branch="$(property_read $property_file "repo.checkout.branch")"

  if [[ -d "$repos_folder/$repo_folder" ]]; then
    debug "Updating $repo_folder"

    if [[ -n "$repo_branch" ]]; then
      do_checkout "$repos_folder/$repo_folder" "$repo_branch"
    fi
    do_update "$repos_folder/$repo_folder"
  else
    debug "Cloning $repo_folder"
    do_clone "$updated_repo_url" "$repos_folder/$repo_folder"

    if [[ -n "$repo_branch" ]]; then
      do_checkout "$repos_folder/$repo_folder" "$repo_branch"
    fi
  fi
}

do_clone() {
	local repo_url=$1
	local repo_folder=$2

  git clone "$repo_url" "$repo_folder"
}

do_update() {
	local repo_folder=$1

	local current_folder="$(pwd)"
	cd "$repo_folder"
	git pull
	cd "$current_folder"
}

do_checkout() {
  local repo_folder=$1
  local repo_branch=$2
  local current_folder="$(pwd)"

  cd "$repo_folder"

  ## Check if the current branch is target
  local current_branch="$(git branch --show-current)"
  if [[ "$current_branch" == "$repo_branch" ]]; then
    ## Do nothing, on the target branch
    log "Current branch is $repo_branch, no checkout"
    return 0
  fi

  ## Check if the necessary branch exist
  local remote_branches="$(git branch --all | grep origin/$repo_branch)"
  if [[ -n "$remote_branches" ]]; then
    ## There is no expected branch remotely
    log "There is no branch origin/$repo_branch, no checkout"
    return 0
  fi

  git checkout --track "origin/$repo_branch"
  cd "$current_folder"
}

get_repository_folder() {
	local repo_url=$1

	pattern='.+/(.+).git'
	[[ "$repo_url" =~ $pattern ]]

	echo "${BASH_REMATCH[1]}"	
}

get_repository_url() {
  local repo_url=$1
  local property_file=$2

  local credentials_login="$(property_read $property_file "credentials.login")"
  local credentials_password="$(property_read $property_file "credentials.password")"

  local check=""

  if [[ -n "$credentials_login" ]]; then
    check="1"
  fi

  if [[ -n "$credentials_password" ]]; then
    check="$check 2"
  fi

  if [[ "$check" != "1 2" ]]; then
    echo "$repo_url"
    return 0
  fi

  local pattern='(http|https)://(.+)'
  [[ "$repo_url" =~ $pattern ]]

  local protocol="${BASH_REMATCH[1]}"
  local url="${BASH_REMATCH[2]}"

  echo "$protocol://$credentials_login:$credentials_password@$url"
}