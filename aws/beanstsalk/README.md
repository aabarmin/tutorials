AWS Beanstalk Example
==

There are two apps: 

* API Gateway running on 5050 or on any other port provided with `PORT` environment variable. 
* Data provider which runs on port 8080. 

Also, the following environment variables are in place: 

* `DB_HOST`
* `DB_PORT`
* `DB_NAME`
* `DB_USERNAME`
* `DB_PASSWORD`

Execute the following command to build both apps at once: 

```shell
$ gradle --settings-file=beanstalk-settings.gradle assemble
```

As a result, two jar's will be added to the `dist` folder:

```shell
$ tree dist
dist
├── api-gateway.jar
└── data-provider.jar
```