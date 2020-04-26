# GIT Clone/Update Tool

## Params

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

## Usage

The tool could be started simply by running the `./run.sh` script:

```bash
$ ./run.sh

Input path to the properties file [props.properties]: 
...
```

It's also possible passing the path to the property-file via the command-line:

```bash
$ ./run.sh props.properties
```

## Building a Docker Image

In order to use the script anywhere I suppose it's better using something like a Docker. In order
to build a Docker Image with the `clone-repos` script use the following script:

```shell script
$ ./build.sh

Sending build context to Docker daemon  18.43kB
Step 1/13 : FROM ubuntu:focal
 ---> 1d622ef86b13
Step 2/13 : COPY *.sh /opt/code/clone-repos/

...

Step 13/13 : ENTRYPOINT ["/opt/entrypoint.sh"]
 ---> Using cache
 ---> b6d03129b465
Successfully built b6d03129b465
Successfully tagged clone-repos:latest
```

## Running using a Docker

In order to clone or update repos using a tool started inside the Docker Container you still need
to have a `repos-list.txt` file but all other things could be started without any installation. This
might be done using the following command:

```shell script
docker run \
    -e CHECKOUT_BRANCH=<GIT_BRANCH_TO_CHECKOUT> \
    -e CREDENTIALS_LOGIN=<YOUR_GIT_LOGIN> \
    -e CREDENTIALS_PASSWORD=<YOUR_GIT_PASSWORD> \
    -v /data/repos:/opt/data/repos \ 
    -v /data/repos-list.txt:/opt/data/repos-list.txt \
    clone-repos:latest
``` 

The following environment variables might be used (all of them are optional):

`CHECKOUT_BRANCH` - branch to checkout (actually, it's `repo.checkout.branch` alias)

`CREDENTIALS_LOGIN` - login to the Git repository

`CREDENTIALS_PASSWORD` - password to the Git repository
