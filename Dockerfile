# Use Ubuntu 16.04 LTS as the basis for the Docker image.
FROM ubuntu:16.04

# Install all the Linux packages required for Yocto / Toradex BSP builds. Note that the packages python3,
# tar, locales and cpio are not listed in the official Yocto / Toradex BSP documentation. The build, however, fails
# without them. curl is used for brining in the repo tool. repo tool uses git, so thats being instaled here aswell.
RUN apt-get update && apt-get -y install gawk wget git-core diffstat unzip texinfo gcc-multilib \
     build-essential chrpath socat cpio python python3 python3-pip python3-pexpect \
     xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev \
     pylint3 xterm tar locales curl git
RUN apt-get -y install software-properties-common && \
    add-apt-repository ppa:deadsnakes/ppa && \
    apt-get -y update && \
    apt-get -y install python3.6

# By default, Ubuntu uses dash as an alias for sh. Dash does not support the source command
# needed for setting up the build environment in CMD. Use bash as an alias for sh.
RUN rm /bin/sh && ln -s bash /bin/sh

# Set the locale to en_US.UTF-8, because the Yocto build fails without any locale set.
RUN locale-gen en_US.UTF-8 && update-locale LC_ALL=en_US.UTF-8 LANG=en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LC_ALL en_US.UTF-8

ENV USER_NAME ot3
ENV PROJECT ot3

# The running container writes all the build artifacts to a host directory (outside the container).
# The container can only write files to host directories, if it uses the same user ID and
# group ID owning the host directories. The host_uid and group_uid are passed to the docker build
# command with the --build-arg option. By default, they are both 1001. The docker image creates
# a group with host_gid and a user with host_uid and adds the user to the group. The symbolic
# name of the group and user is ot3.
ARG host_uid=1001
ARG host_gid=1001
RUN groupadd -g $host_gid $USER_NAME && useradd -g $host_gid -m -s /bin/bash -u $host_uid $USER_NAME



# Perform the Yocto build as user cuteradio (not as root).
# NOTE: The USER command does not set the environment variable HOME.

# By default, docker runs as root. However, Yocto builds should not be run as root, but as a 
# normal user. Hence, we switch to the newly created user ot3.
USER $USER_NAME

RUN git config --global user.name "ot3" && \
    git config --global user.email ot3@ot3.com

# Create the directory structure for the Yocto build in the container. The lowest two directory
# levels must be the same as on the host.
ENV BUILD_INPUT_DIR /home/$USER_NAME/oe-core
ENV BUILD_OUTPUT_DIR /home/$USER_NAME/oe-core/build
RUN mkdir -p $BUILD_INPUT_DIR $BUILD_OUTPUT_DIR

WORKDIR $BUILD_INPUT_DIR

RUN mkdir bin 
ENV PATH="bin:${PATH}" 
RUN curl https://commondatastorage.googleapis.com/git-repo-downloads/repo > bin/repo && \
    chmod a+x bin/repo
RUN repo init -u https://git.toradex.com/toradex-manifest.git -b dunfell-5.x.y -m tdxref/default.xml 
RUN repo sync

ADD start.sh /home/ot3/start.sh
USER root
RUN chown -R $USER_NAME:$USER_NAME /home/ot3/start.sh
RUN chmod a+x /home/ot3/start.sh
USER $USER_NAME
CMD /home/ot3/start.sh
