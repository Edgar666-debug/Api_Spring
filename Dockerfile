FROM ubuntu:latest
LABEL authors="qrsof"

ENTRYPOINT ["top", "-b"]