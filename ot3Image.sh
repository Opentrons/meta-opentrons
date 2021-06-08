#! /bin/sh
mkdir docker-test
cd docker-test
mkdir -p oe-core/build
#change branch to main eventually, or pass as cmdline args
curl 'https://raw.githubusercontent.com/Opentrons/meta-opentrons/docker-ot3/Dockerfile' > DockerFile
curl 'https://raw.githubusercontent.com/Opentrons/meta-opentrons/docker-ot3/start.sh' > start.sh
docker run -it --rm -v $PWD/oe-core/build:/home/ot3/oe-core/build/deploy ot3-image:latest

