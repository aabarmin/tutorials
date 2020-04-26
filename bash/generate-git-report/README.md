# GIT Reporting Tool

The main idea of this tool is to generate a report regarding changed files
based on the GIT history. 

## Usage

In order to use the report generator execute the following command:

```shell script
$ ./run.sh git_parent_folder number_of_days reports_folder
```

`git_parent_folder` is a folder that contains git repositories. It's assumed
that the tool will be used (actually I need the tool to work in this way)
with many git repos. 

So, the `git_parent_folder` contains other folders with git repos. 

`number_of_days` - how many days of changes should be included into the report

`reports_folder` - the folder where reports are placed. 

**Example:**

```shell script
$ ./run.sh ./repos 1 ./reports

Generate a report for repo-1
Generate a report for repo-2
Generating a daily report
Done
```

As a result, the `reports_folder` will contain a folder with a name
equals to the today's day and a single-file report:

```shell script
$ tree ./reports

reports
├── 2020-04-26
│   ├── repo-1
│   ├── repo-2
└── 2020-04-26.txt
```

## Generating a report for the single repository

In order to generate a report for the single repository there is no 
need to use any tool, just use the following command:

```shell script
git log --since=10.days --pretty=format:"%Cblue%h%Creset - %Cred%an:%Creset %Cgreen%s%Creset" -p --no-merges
```

It'll show the output in the following format:

```shell script
CommitID - Change Author: Commit message 
diff --git a/src/test/java/org/name/document/UpdateXmlServiceTest.java b/src/test/java/org/name/document/UpdateXmlServiceTest.java
index 827b944..93af88c 100644
--- a/src/test/java/org/name/document/UpdateXmlServiceTest.java
+++ b/src/test/java/org/name/document/UpdateXmlServiceTest.java
@@ -10,6 +10,7 @@ import org.name.document.config.binary.PermanentBinaryStora
 import org.name.document.model.ContentDocument;
 import org.name.document.model.version.VersionDocument;
 import org.name.document.repository.resolver.Collection;
+import org.name.document.validator.DocumentValidator;
@@ -36,6 +37,8 @@ public class UpdateXmlServiceTest {
   @MockBean @PermanentBinaryStorage private BinaryStorageService permanentStorage;
   @MockBean private NeliDocumentFolderService neliDocumentFolderService;
   @MockBean private DocumentService documentService;
+  @MockBean private DocumentValidator documentValidator;
+  @MockBean private DocumentHandleHelper documentHandleHelper;
```

## Building a Docker Image

In order to use the script anywhere I suppose it's better using something like a Docker. In order
to build a Docker Image with the `generate-git-report` script use the following script:

```shell script
$ ./build.sh

Sending build context to Docker daemon  18.43kB
Step 1/13 : FROM ubuntu:focal
 ---> 1d622ef86b13
Step 2/13 : COPY *.sh /opt/code/generate-git-report/

...

Step 13/13 : ENTRYPOINT ["/opt/entrypoint.sh"]
 ---> Using cache
 ---> b6d03129b465
Successfully built b6d03129b465
Successfully tagged generate-git-report:latest
```

## Running using a Docker

In order to generate a report using a Docker the following shell script could be used:

```shell script
docker run \
    -e REPORT_DAYS=1 \
    -v /data/repos:/opt/data/repos \
    -v /data/reports:/opt/data/reports \
    generate-git-report:latest
```

The following environment variables might be used (all of them are optional):

`REPORT_DAYS` - number of days to generate report for (1 day by default ans might not be set)
