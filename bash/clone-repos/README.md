GIT Clone/Update Tool
=

This is a simple tool that allows cloning or updating multiple repositories
at once. The tools is written is bash and relies on the `git` command. 

In order to make it work it's necessary having a `props.properties` file
(the name can be changed and set on run). The file should contain the
following set of properties:

```
repos.folder=
repos.list=

credentials.login=
credentials.password=

repo.checkout.branch=
``` 

`repos.folder` is a folder where repos should be cloned to

`repos.list` is a path to the file with a list of repo. One repo a line

`credentials.login` and `credentials.password` are credentials to access 
the repository. Anonymous access is used if empty. 

`repo.checkout.branch` what branch to checkout to. 