FROM maven:3.6.3-jdk-11

WORKDIR /app

RUN apt-get install -y curl \
  && curl -sL https://deb.nodesource.com/setup_12.x | bash - \
  && apt-get install -y nodejs

