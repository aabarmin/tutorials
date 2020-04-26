FROM ubuntu:focal

COPY *.sh /opt/code/generate-git-report/
COPY ./tmp/common/log.sh /opt/code/common/log.sh
COPY ./docker/entrypoint.sh /opt/entrypoint.sh

VOLUME /opt/data/repos
VOLUME /opt/data/reports

ENV REPORT_DAYS 1

RUN apt-get update
RUN apt-get install -y git

ENTRYPOINT ["/opt/entrypoint.sh"]