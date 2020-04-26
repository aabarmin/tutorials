FROM ubuntu:focal

COPY *.sh /opt/code/clone-repos/
COPY ./tmp/common/log.sh /opt/code/common/log.sh
COPY ./docker/entrypoint.sh /opt/entrypoint.sh
COPY ./docker/repos-list.txt /opt/data/repos-list.txt

VOLUME /opt/data/repos

ENV CHECKOUT_BRANCH master
ENV CREDENTIALS_LOGIN none
ENV CREDENTIALS_PASSWORD none
ENV REPOS_LIST_FILE /opt/data/repos-list.txt

RUN apt-get update
RUN apt-get install -y git

ENTRYPOINT ["/opt/entrypoint.sh"]