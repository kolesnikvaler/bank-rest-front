#!/usr/bin/env sh

docker stop app-front
docker rm app-front
docker rmi front-image
docker build -t front-image .
#docker tag front-image alphabetcom/front-image:latest
#docker push alphabetcom/front-image:latest
docker run \
  --name app-front \
  -p 8081:8081 \
  --env-file .env \
  --network bank_rest_app_bankcards-network \
  front-image