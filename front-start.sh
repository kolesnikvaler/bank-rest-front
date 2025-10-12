#!/usr/bin/env sh

docker stop app-front
docker rm app-front
docker rmi front-image
docker build -t front-image .
#docker tag front-image alphabetcom/front-image:latest
#docker push alphabetcom/front-image:latest
docker run --name app-front -p 8081:8081 front-image
docker network connect bank_rest_app_bankcards-network app-front