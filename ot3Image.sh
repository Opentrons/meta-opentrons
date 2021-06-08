#! /bin/bash
branch=  
while getopts ":b:" opt; do
  case $opt in
    b)
      branch=$OPTARG >&2
      echo $OPTARG
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

echo $branch
curl "https://raw.githubusercontent.com/Opentrons/meta-opentrons/${branch}/Dockerfile" > Dockerfile
curl "https://raw.githubusercontent.com/Opentrons/meta-opentrons/${branch}/start.sh" > start.sh
docker build --no-cache --build-arg "host_uid=$(id -u)"   --build-arg "host_gid=$(id -g)" --tag "ot3-image:latest" .
docker run -it --rm -v $PWD/oe-core/build:/home/ot3/oe-core/build/deploy ot3-image:latest

