## pagalba, vaizdo medziaga : https://www.youtube.com/watch?v=V3jAGbTwS-M


## Sukurti dockerio image
docker build -t app .

## Paleisti docker image (Localhost:8888 pagrindinis, bet serveris nukreips į konteinerio 8080)
docker run -p 8888:8080 app

## Prisijungti prie debesies
gcloud auth login

## Pasirinkti projekta
gcloud config set project spring-boot-rest-406510 

## Sukuriame dockerio registra (repozitorija) ir suteikiam taga
docker tag app gcr.io/spring-boot-rest-406510/app


## TURIME AKTYVUOTI CONTAINER REGISTRY API google CLOUD
## publikuojame dockerio image
docker push gcr.io/spring-boot-rest-406510/app



