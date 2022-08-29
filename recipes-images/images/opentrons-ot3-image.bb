SUMMARY = "Opentrons OT3 Image"
DESCRIPTION = "Opentrons OT3 Robot Image"

LICENSE = "apache-2"

inherit core-image image_type_tezi_ot3

DEPENDS += "rsync-native"

IMAGE_LINGUAS = "en-us"
# Copy Licenses to image /usr/share/common-license
COPY_LIC_MANIFEST ?= "1"
COPY_LIC_DIRS ?= "1"

SYSTEMD_DEFAULT_TARGET = "graphical.target"

IMAGE_INSTALL += " \
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
    networkmanager crda \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'timestamp-service systemd-analyze', '', d)} \
    weston-xwayland weston weston-init imx-gpu-viv \
    robot-app-wayland-launch robot-app \
    opentrons-robot-server opentrons-update-server \
    python3 python3-misc python3-modules \
 "

#Prefix to the resulting deployable tarball name
export IMAGE_BASENAME = "opentrons-ot3-image"
MACHINE_NAME ?= "${MACHINE}"
IMAGE_NAME = "${MACHINE_NAME}_${IMAGE_BASENAME}"
SYSTEMFS_DIR = "${WORKDIR}/systemfs"
USERFS_DIR = "${WORKDIR}/userfs"
SYSTEMFS_OUTPUT = "${IMGDEPLOYDIR}/${IMAGE_NAME}.systemfs.ext4"
USERFS_OUTPUT = "${IMGDEPLOYDIR}/${IMAGE_NAME}.userfs.ext4"

do_create_filesystem_trees[depends] = "fakeroot-native:do_populate_sysroot"
do_create_filesystem[depends] = "do_create_filesystem_trees"
do_compress_filesystem[depends] = "do_create_filesystem"
do_image_zip[depends] = "zip-native:do_populate_sysroot"
addtask do_image_zip after do_image_complete before do_populate_lic_deploy

add_rootfs_version () {
    printf "${DISTRO_NAME} ${DISTRO_VERSION} (${DISTRO_CODENAME}) \\\n \\\l\n" > ${IMAGE_ROOTFS}/etc/issue
    printf "${DISTRO_NAME} ${DISTRO_VERSION} (${DISTRO_CODENAME}) %%h\n" > ${IMAGE_ROOTFS}/etc/issue.net
    printf "${IMAGE_NAME}\n\n" >> ${IMAGE_ROOTFS}/etc/issue
    printf "${IMAGE_NAME}\n\n" >> ${IMAGE_ROOTFS}/etc/issue.net
}
# add the rootfs version to the welcome banner
ROOTFS_POSTPROCESS_COMMAND += "add_rootfs_version;"

fakeroot do_create_filesystem_trees() {
    # this will create the systemfs tree
    rsync -a ${IMAGE_ROOTFS}/ ${SYSTEMFS_DIR} --exclude 'home/' --exclude 'var/' --delete-excluded
    # create the userfs tree
    rsync -a ${IMAGE_ROOTFS}/home ${USERFS_DIR}/
    rsync -a ${IMAGE_ROOTFS}/var ${USERFS_DIR}/
}
# create the filesystem trees
IMAGE_PREPROCESS_COMMAND += "do_create_filesystem_trees;"

do_create_filesystem() {
    # get size of the filesystem trees
    SYSTEMFS_SIZE=$(du -Lbks ${SYSTEMFS_DIR} | cut -f1)
    USERFS_SIZE=$(du -Lbks ${USERFS_DIR} | cut -f1)
 
    # create sparse file a bit larger than source dir
    dd if=/dev/zero of=${SYSTEMFS_OUTPUT} seek=${SYSTEMFS_SIZE}w bs=1024 count=0
    mkfs.ext4 -F ${SYSTEMFS_OUTPUT} -d ${SYSTEMFS_DIR}

    dd if=/dev/zero of=${USERFS_OUTPUT} seek=${USERFS_SIZE}b bs=1024 count=0
    mkfs.ext4 -F ${USERFS_OUTPUT} -d ${USERFS_DIR}
}
IMAGE_POSTPROCESS_COMMAND += "do_create_filesystem;"
# create the actual filesystem

do_compress_filesystem() {
    tar --xattrs --xattrs-include=* --numeric-owner -cf ${IMGDEPLOYDIR}/${IMAGE_NAME}.systemfs.tar -C ${WORKDIR}/systemfs ./
    tar --xattrs --xattrs-include=* --numeric-owner -cf ${IMGDEPLOYDIR}/${IMAGE_NAME}.userfs.tar -C ${WORKDIR}/userfs ./

    # compress
    xz -f -k -c -9 ${XZ_DEFAULTS} --check=crc32 ${IMGDEPLOYDIR}/${IMAGE_NAME}.systemfs.tar > ${IMGDEPLOYDIR}/${IMAGE_NAME}.systemfs.tar.xz
    xz -f -k -c -9 ${XZ_DEFAULTS} --check=crc32 ${IMGDEPLOYDIR}/${IMAGE_NAME}.userfs.tar > ${IMGDEPLOYDIR}/${IMAGE_NAME}.userfs.tar.xz
    xz -f -k -c -9 ${XZ_DEFAULTS} --check=crc32 ${IMGDEPLOYDIR}/${IMAGE_NAME}.systemfs.ext4 > ${IMGDEPLOYDIR}/${IMAGE_NAME}.systemfs.ext4.xz
}
# compress the filesystem
IMAGE_POSTPROCESS_COMMAND += "do_compress_filesystem;"

do_image_zip() {
    cd ${DEPLOY_DIR_IMAGE}/
    sha256sum ${IMAGE_NAME}.systemfs.ext4.xz > rootfs.xz.256
    zip ot3-system.zip ${IMAGE_NAME}.systemfs.ext4.xz rootfs.xz.256
}
