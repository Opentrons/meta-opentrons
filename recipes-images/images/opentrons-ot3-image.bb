SUMMARY = "Opentrons OT3 Image"
DESCRIPTION = "Opentrons OT3 Robot Image"

LICENSE = "apache-2"

inherit core-image

#Prefix to the resulting deployable tarball name
export IMAGE_BASENAME = "opentrons-ot3-image"
MACHINE_NAME ?= "${MACHINE}"
IMAGE_NAME = "${MACHINE_NAME}_${IMAGE_BASENAME}"

# Copy Licenses to image /usr/share/common-license
COPY_LIC_MANIFEST ?= "1"
COPY_LIC_DIRS ?= "1"

SYSTEMD_DEFAULT_TARGET = "graphical.target"

add_rootfs_version () {
    printf "${DISTRO_NAME} ${DISTRO_VERSION} (${DISTRO_CODENAME}) \\\n \\\l\n" > ${IMAGE_ROOTFS}/etc/issue
    printf "${DISTRO_NAME} ${DISTRO_VERSION} (${DISTRO_CODENAME}) %%h\n" > ${IMAGE_ROOTFS}/etc/issue.net
    printf "${IMAGE_NAME}\n\n" >> ${IMAGE_ROOTFS}/etc/issue
    printf "${IMAGE_NAME}\n\n" >> ${IMAGE_ROOTFS}/etc/issue.net
}
# add the rootfs version to the welcome banner
ROOTFS_POSTPROCESS_COMMAND += " add_rootfs_version;"

IMAGE_LINGUAS = "en-us"
#IMAGE_LINGUAS = "de-de fr-fr en-gb en-us pt-br es-es kn-in ml-in ta-in"

CONMANPKGS ?= "connman connman-plugin-loopback connman-plugin-ethernet connman-plugin-wifi connman-client"

IMAGE_INSTALL += " \
    bmap-tools \
    libubootenv \
    packagegroup-boot \
    packagegroup-basic \
    packagegroup-base-tdx-cli \
    packagegroup-tdx-cli \
    packagegroup-machine-tdx-cli \
    packagegroup-wifi-tdx-cli \
    packagegroup-wifi-fw-tdx-cli \
    packagegroup-tdx-graphical \
    packagegroup-fsl-isp \
    udev-extraconf \
    v4l-utils \
    bash coreutils makedevs mime-support util-linux \
    timestamp-service \
    ${CONMANPKGS} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'timestamp-service systemd-analyze', '', d)} \
    python3-robot-server python3-robot-shared-data \
    python3-update-server python3-notify-server \
    python3-robot-api robotserversystemd python3-uvicorn \
    weston-xwayland weston weston-init imx-gpu-viv \
    robot-app-wayland-launch robot-app \
 "
