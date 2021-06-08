#! /bin/bash
branch=  
while getopts ":b:" opt; do
  case $opt in
    b)
      branch=$OPTARG >&2
      ;;
    \?)
      echo "Invalid option: -$OPTARG" >&2
      exit 1
      ;;
    :)
      echo "Option -$OPTARG requires an argument." >&2
      exit 1
      ;;
  esac
done
shift $((OPTIND - 1))

mkdir -p docker
cd docker
mkdir -p oe-core/build

curl 'https://raw.githubusercontent.com/Opentrons/meta-opentrons/${branch}/Dockerfile' > DockerFile
curl 'https://raw.githubusercontent.com/Opentrons/meta-opentrons/${branch}/start.sh' > start.sh
docker run -it --rm -v $PWD/oe-core/build:/home/ot3/oe-core/build/deploy ot3-image:latest

