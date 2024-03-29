AWS Beanstalk Example
==

There are two apps: 

* API Gateway running on 5050 or on any other port provided with `PORT` environment variable. 
* Data provider which runs on port 8080. 

Also, the following environment variables are in place: 

* `RDS_HOSTNAME`
* `RDS_PORT`
* `RDS_DB_NAME`
* `RDS_USERNAME`
* `RDS_PASSWORD`

Execute the following command to build both apps at once: 

```shell
$ ./gradlew --settings-file=beanstalk-settings.gradle assemble
```

As a result, two jar's will be added to the `dist` folder:

```shell
$ tree dist
dist
├── api-gateway.jar
└── data-provider.jar
```

To prepare a zip archive to be uploaded to AWS Beanstalk, execute the following command: 

```shell
$ ./gradlew --settings-file=beanstalk-settings.gradle archive
```

File `archive.zip` will be created in the root directory. 