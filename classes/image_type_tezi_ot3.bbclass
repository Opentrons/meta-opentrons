# This class implements Toradex Easy Installer image type
# It allows to use OpenEmbedded to build images which can be consumed
# by the Toradex Easy Installer.
#
# Since it also generates the image.json description file it is rather
# interwind with the boot flow which is U-Boot target specific.

WKS_FILE_DEPENDS_append = " tezi-metadata virtual/dtb"
DEPENDS += "${WKS_FILE_DEPENDS}"
IMAGE_BOOT_FILES_REMOVE = "${@make_dtb_boot_files(d) if d.getVar('KERNEL_IMAGETYPE') == 'fitImage' else ''}"
IMAGE_BOOT_FILES_REMOVE_apalis-tk1 = "${@ d.getVar('KERNEL_DEVICETREE') if d.getVar('KERNEL_IMAGETYPE') == 'fitImage' else ''}"
IMAGE_BOOT_FILES_append = " overlays.txt ${@'' if d.getVar('KERNEL_IMAGETYPE') == 'fitImage' else 'overlays/*;overlays/'}"
IMAGE_BOOT_FILES_remove = "${IMAGE_BOOT_FILES_REMOVE}"

RM_WORK_EXCLUDE += "${PN}"

# set defaults if not building with a Toradex distro
TDX_RELEASE ??= "0.0.0"
TDX_MATRIX_BUILD_TIME ??= "${DATETIME}"
TDX_MATRIX_BUILD_TIME[vardepsexclude] = "DATETIME"

EMMCDEV = "mmcblk0"
EMMCDEV_verdin-imx8mp = "emmc"
EMMCDEVBOOT0 = "mmcblk0boot0"
EMMCDEVBOOT0_verdin-imx8mp = "emmc-boot0"
TEZI_VERSION ?= "${DISTRO_VERSION}"
TEZI_DATE ?= "${TDX_MATRIX_BUILD_TIME}"
TEZI_IMAGE_NAME ?= "${IMAGE_NAME}"
TEZI_ROOT_FSTYPE ??= "ext4"
TEZI_ROOT_LABEL ??= "RFS"
TEZI_ROOT_NAME ??= "rootfs"
TEZI_ROOT_LABEL2 ??= "RFS2"
TEZI_ROOT_NAME2 ??= "rootfs2"
TEZI_ROOT_SUFFIX ??= "tar.xz"
TEZI_USE_BOOTFILES ??= "true"
TEZI_AUTO_INSTALL ??= "false"
TEZI_BOOT_SUFFIX ??= "${@'bootfs.tar.xz' if oe.types.boolean('${TEZI_USE_BOOTFILES}') else ''}"
TEZI_CONFIG_FORMAT ??= "2"
# Require newer Tezi for mx8 Socs with the u-boot environment bugfix
TEZI_CONFIG_FORMAT_mx8 ??= "4"
TORADEX_FLASH_TYPE ??= "emmc"
UBOOT_BINARY_TEZI_EMMC ?= "${UBOOT_BINARY}"
UBOOT_BINARY_TEZI_RAWNAND ?= "${UBOOT_BINARY}"
UBOOT_ENV_TEZI ?= "${@ 'u-boot-initial-env-%s' % d.getVar('UBOOT_CONFIG') if d.getVar('UBOOT_CONFIG') else 'u-boot-initial-env'}"
UBOOT_ENV_TEZI_EMMC ?= "${UBOOT_ENV_TEZI}"
UBOOT_ENV_TEZI_RAWNAND ?= "${UBOOT_ENV_TEZI}"

# use DISTRO_FLAVOUR to append to the image name displayed in TEZI
DISTRO_FLAVOUR ??= ""
SUMMARY_append = "${DISTRO_FLAVOUR}"

TEZI_EULA_URL ?= "https://www.nxp.com/docs/en/disclaimer/LA_OPT_NXP_SW.html"
export TEZI_EULA_URL

# Append tar command to store uncompressed image size to ${T}.
# If a custom rootfs type is used make sure this file is created
# before compression.
IMAGE_CMD_tar_append = "; du -ks ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.tar | cut -f 1 > ${T}/image-size${IMAGE_NAME_SUFFIX}"
CONVERSION_CMD_tar_append = "; du -ks ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type}.tar | cut -f 1 > ${T}/image-size.${type}"
CONVERSION_CMD_tar = "touch ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type}; ${IMAGE_CMD_TAR} --numeric-owner -cf ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type}.tar -C ${TAR_IMAGE_ROOTFS} . || [ $? -eq 1 ]"
CONVERSIONTYPES_append = " tar"

def get_uncompressed_size(d, type):
    path = os.path.join(d.getVar('T'), "image-size.%s" % type)
    if not os.path.exists(path):
        return 0

    with open(path, "r") as f:
        size = f.read().strip()
    return float(size) / 1024

# Make an educated guess of the needed boot partition size
# max(16MB, 3x the size of the payload rounded up to the next 2^x number)
def get_bootfs_part_size(d):
    from math import log
    part_size = 3 * 2 ** (1 + int(log(get_uncompressed_size(d, 'bootfs'), 2)))
    return max(16, part_size)

# Whitespace separated list of files declared by 'deploy_var' variable
# from 'source_dir' (DEPLOY_DIR_IMAGE by default) to place in 'deploy_dir'.
# Entries will be installed under a same name as the source file. To change
# the destination file name, pass a desired name after a semicolon
# (eg. u-boot.img;uboot). Exactly same rules with how IMAGE_BOOT_FILES being
# handled by wic.
def tezi_deploy_files(d, deploy_var, deploy_dir, source_dir=None):
    import os, re, glob, subprocess

    src_files = d.getVar(deploy_var) or ""
    src_dir = source_dir or d.getVar('DEPLOY_DIR_IMAGE')
    dst_dir = deploy_dir

    # list of tuples (src_name, dst_name)
    deploy_files = []
    for src_entry in re.findall(r'[\w;\-\./\*]+', src_files):
        if ';' in src_entry:
            dst_entry = tuple(src_entry.split(';'))
            if not dst_entry[0] or not dst_entry[1]:
                bb.fatal('Malformed file entry: %s' % src_entry)
        else:
            dst_entry = (src_entry, src_entry)
        deploy_files.append(dst_entry)

    # list of tuples (src_path, dst_path)
    install_task = []
    for deploy_entry in deploy_files:
        src, dst = deploy_entry
        if '*' in src:
            # by default install files under their basename
            entry_name_fn = os.path.basename
            if dst != src:
                # unless a target name was given, then treat name
                # as a directory and append a basename
                entry_name_fn = lambda name: \
                                os.path.join(dst,
                                             os.path.basename(name))

            srcs = glob.glob(os.path.join(src_dir, src))
            for entry in srcs:
                src = os.path.relpath(entry, src_dir)
                entry_dst_name = entry_name_fn(entry)
                install_task.append((src, entry_dst_name))
        else:
            install_task.append((src, dst))

    # install src_path to dst_path
    for task in install_task:
        src_path, dst_path = task
        install_cmd = "install -m 0644 -D %s %s" \
                      % (os.path.join(src_dir, src_path),
                         os.path.join(dst_dir, dst_path))
        try:
            subprocess.check_output(install_cmd, stderr=subprocess.STDOUT, shell=True)
        except subprocess.CalledProcessError as e:
            bb.fatal("Command '%s' returned %d:\n%s" % (e.cmd, e.returncode, e.output))

def rootfs_tezi_emmc(d, use_bootfiles):
    from collections import OrderedDict
    emmcdev = d.getVar('EMMCDEV')
    emmcdevboot0 = d.getVar('EMMCDEVBOOT0')
    imagename = d.getVar('IMAGE_LINK_NAME')
    offset_bootrom = d.getVar('OFFSET_BOOTROM_PAYLOAD')
    offset_spl = d.getVar('OFFSET_SPL_PAYLOAD')

    bootpart_rawfiles = []
    filesystem_partitions = []

    if offset_spl:
        bootpart_rawfiles.append(
              {
                "filename": d.getVar('SPL_BINARY'),
                "dd_options": "seek=" + offset_bootrom
              })
    bootpart_rawfiles.append(
              {
                "filename": d.getVar('UBOOT_BINARY_TEZI_EMMC'),
                "dd_options": "seek=" + (offset_spl if offset_spl else offset_bootrom)
              })

    if use_bootfiles:
        filesystem_partitions.append(
              {
                "partition_size_nominal": get_bootfs_part_size(d),
                "want_maximised": False,
                "content": {
                  "label": "BOOT",
                  "filesystem_type": "FAT",
                  "mkfs_options": "",
                  "filename": imagename + "." + d.getVar('TEZI_BOOT_SUFFIX'),
                  "uncompressed_size": get_uncompressed_size(d, 'bootfs')
                }
              })

    filesystem_partitions.append(
          {
            "partition_size_nominal": 512,
            "want_maximised": True,
            "content": {
              "label": d.getVar('TEZI_ROOT_LABEL'),
              "filesystem_type": d.getVar('TEZI_ROOT_FSTYPE'),
              "mkfs_options": "-E nodiscard",
              "filename": imagename + "." + d.getVar('TEZI_ROOT_SUFFIX'),
              "uncompressed_size": get_uncompressed_size(d, d.getVar('TEZI_ROOT_NAME'))
            }
          })
    
    filesystem_partitions.append(
          {
            "partition_size_nominal": 512,
            "want_maximised": True,
            "content": {
              "label": d.getVar('TEZI_ROOT_LABEL2'),
              "filesystem_type": d.getVar('TEZI_ROOT_FSTYPE'),
              "mkfs_options": "-E nodiscard",
              "filename": imagename + "." + d.getVar('TEZI_ROOT_SUFFIX'),
              "uncompressed_size": get_uncompressed_size(d, d.getVar('TEZI_ROOT_NAME2'))
            }
          })

    return [
        OrderedDict({
          "name": emmcdev,
          "partitions": filesystem_partitions
        }),
        OrderedDict({
          "name": emmcdevboot0,
          "erase": True,
          "content": {
            "filesystem_type": "raw",
            "rawfiles": bootpart_rawfiles
          }
        })]


def rootfs_tezi_rawnand(d):
    from collections import OrderedDict
    imagename = d.getVar('IMAGE_LINK_NAME')

    uboot1 = OrderedDict({
               "name": "u-boot1",
               "content": {
                 "rawfile": {
                   "filename": d.getVar('UBOOT_BINARY_TEZI_RAWNAND'),
                   "size": 1
                 }
               },
             })

    uboot2 = OrderedDict({
               "name": "u-boot2",
               "content": {
                 "rawfile": {
                   "filename": d.getVar('UBOOT_BINARY_TEZI_RAWNAND'),
                   "size": 1
                 }
               }
             })

    env = OrderedDict({
        "name": "u-boot-env",
        "erase": True,
        "content": {}
    })

    rootfs = {
               "name": "rootfs",
               "content": {
                 "filesystem_type": "ubifs",
                 "filename": imagename + "." + d.getVar('TEZI_ROOT_SUFFIX'),
                 "uncompressed_size": get_uncompressed_size(d, d.getVar('TEZI_ROOT_NAME'))
               }
             }

    kernel = {
               "name": "kernel",
               "size_kib": 8192,
               "type": "static",
               "content": {
                 "rawfile": {
                   "filename": d.getVar('KERNEL_IMAGETYPE'),
                   "size": 5
                 }
               }
             }

    # Use device tree mapping to create product id <-> device tree relationship
    dtmapping = d.getVarFlags('TORADEX_PRODUCT_IDS')
    dtfiles = []
    for f, v in dtmapping.items():
        dtfiles.append({ "filename": v, "product_ids": f })

    dtb = {
            "name": "dtb",
            "content": {
              "rawfiles": dtfiles
            },
            "size_kib": 128,
            "type": "static"
          }

    m4firmware = {
                   "name": "m4firmware",
                   "size_kib": 896,
                   "type": "static"
                 }

    ubi = OrderedDict({
            "name": "ubi",
            "ubivolumes": [kernel, dtb, m4firmware, rootfs]
          })

    return [uboot1, uboot2, env, ubi]

def rootfs_tezi_json(d, flash_type, flash_data, json_file, uenv_file):
    import json
    from collections import OrderedDict
    from datetime import datetime

    deploydir = d.getVar('DEPLOY_DIR_IMAGE')
    data = OrderedDict({ "config_format": d.getVar('TEZI_CONFIG_FORMAT'), "autoinstall": oe.types.boolean(d.getVar('TEZI_AUTO_INSTALL')) })

    # Use image recipes SUMMARY/DESCRIPTION...
    data["name"] = d.getVar('SUMMARY')
    data["description"] = d.getVar('DESCRIPTION')
    data["version"] = d.getVar('TEZI_VERSION')
    data["release_date"] = datetime.strptime(d.getVar('TEZI_DATE'), '%Y%m%d%H%M%S').date().isoformat()
    data["u_boot_env"] = uenv_file
    if os.path.exists(os.path.join(deploydir, "prepare.sh")):
        data["prepare_script"] = "prepare.sh"
    if os.path.exists(os.path.join(deploydir, "wrapup.sh")):
        data["wrapup_script"] = "wrapup.sh"
    if os.path.exists(os.path.join(deploydir, "marketing.tar")):
        data["marketing"] = "marketing.tar"
    if os.path.exists(os.path.join(deploydir, "toradexlinux.png")):
        data["icon"] = "toradexlinux.png"
    if d.getVar('TEZI_SHOW_EULA_LICENSE')  == "1":
        url = d.getVar('TEZI_EULA_URL')
        data["license"] = os.path.basename(url)

    product_ids = d.getVar('TORADEX_PRODUCT_IDS')
    if product_ids is None:
        bb.fatal("Supported Toradex product ids missing, assign TORADEX_PRODUCT_IDS with a list of product ids.")

    dtmapping = d.getVarFlags('TORADEX_PRODUCT_IDS')
    data["supported_product_ids"] = []

    # If no varflags are set, we assume all product ids supported with single image/U-Boot
    if dtmapping is not None:
        for f, v in dtmapping.items():
            dtbflashtypearr = v.split(',')
            if len(dtbflashtypearr) < 2 or dtbflashtypearr[1] == flash_type:
                data["supported_product_ids"].append(f)
    else:
        data["supported_product_ids"].extend(product_ids.split())

    if flash_type == "rawnand":
        data["mtddevs"] = flash_data
    elif flash_type == "emmc":
        data["blockdevs"] = flash_data

    with open(os.path.join(d.getVar('IMGDEPLOYDIR'), json_file), 'w') as outfile:
        json.dump(data, outfile, indent=4)
    bb.note("Toradex Easy Installer metadata file {0} written.".format(json_file))

python rootfs_tezi_run_json() {
    artifacts = "%s/%s.%s" % (d.getVar('IMGDEPLOYDIR'), d.getVar('IMAGE_LINK_NAME'), d.getVar('TEZI_ROOT_SUFFIX'))
    flash_type = d.getVar('TORADEX_FLASH_TYPE')

    if len(flash_type.split()) > 1:
        bb.fatal("This class only supports a single flash type.")

    if flash_type == "rawnand":
        flash_data = rootfs_tezi_rawnand(d)
        uenv_file = d.getVar('UBOOT_ENV_TEZI_RAWNAND')
        uboot_file = d.getVar('UBOOT_BINARY_TEZI_RAWNAND')
        artifacts += " " + d.getVar('KERNEL_IMAGETYPE') + " " + d.getVar('KERNEL_DEVICETREE')
    elif flash_type == "emmc":
        use_bootfiles = oe.types.boolean(d.getVar('TEZI_USE_BOOTFILES'))
        flash_data = rootfs_tezi_emmc(d, use_bootfiles)
        uenv_file = d.getVar('UBOOT_ENV_TEZI_EMMC')
        uboot_file = d.getVar('UBOOT_BINARY_TEZI_EMMC')
        # TODO: Multi image/raw NAND with SPL currently not supported
        uboot_file += " " + d.getVar('SPL_BINARY') if d.getVar('OFFSET_SPL_PAYLOAD') else ""
        artifacts += " " + "%s/%s.%s" % (d.getVar('IMGDEPLOYDIR'), d.getVar('IMAGE_LINK_NAME'), d.getVar('TEZI_BOOT_SUFFIX')) if use_bootfiles else ""
    else:
        bb.fatal("Toradex flash type unknown")

    artifacts += " " + uenv_file + " " + uboot_file
    d.setVar("TEZI_ARTIFACTS", artifacts)

    rootfs_tezi_json(d, flash_type, flash_data, "image-%s.json" % d.getVar('IMAGE_BASENAME'), uenv_file)
}

python tezi_deploy_bootfs_files() {
    tezi_deploy_files(d, 'IMAGE_BOOT_FILES', os.path.join(d.getVar('WORKDIR'), 'bootfs'))
}
tezi_deploy_bootfs_files[dirs] =+ "${WORKDIR}/bootfs"
tezi_deploy_bootfs_files[cleandirs] += "${WORKDIR}/bootfs"
tezi_deploy_bootfs_files[vardeps] += "IMAGE_BOOT_FILES"

TAR_IMAGE_ROOTFS_task-image-bootfs = "${WORKDIR}/bootfs"
IMAGE_CMD_bootfs () {
       :
}
TEZI_IMAGE_BOOTFS_PREFUNCS ??= "tezi_deploy_bootfs_files"
do_image_bootfs[prefuncs] += "${TEZI_IMAGE_BOOTFS_PREFUNCS}"

TEZI_IMAGE_TEZIIMG_PREFUNCS ??= "rootfs_tezi_run_json"
IMAGE_TYPEDEP_teziimg += "${TEZI_BOOT_SUFFIX} ${TEZI_ROOT_SUFFIX}"
IMAGE_CMD_teziimg () {
	bbnote "Create Toradex Easy Installer tarball"

	# Copy image json file to ${WORKDIR}/image-json
	cp ${IMGDEPLOYDIR}/image*.json ${WORKDIR}/image-json/image.json

	curl -k --retry 5 -O ${TEZI_EULA_URL} || true
	EULA_FILE=$(echo "${TEZI_EULA_URL##*/}")

	# The first transform strips all folders from the files to tar, the
	# second transform "moves" them in a subfolder ${TEZI_IMAGE_NAME}-Tezi_${TEZI_VERSION}.
	${IMAGE_CMD_TAR} \
		--transform='s/.*\///' \
		--transform 's,^,${TEZI_IMAGE_NAME}-Tezi_${TEZI_VERSION}/,' \
		-chf ${IMGDEPLOYDIR}/${TEZI_IMAGE_NAME}-Tezi_${TEZI_VERSION}.tar \
		toradexlinux.png marketing.tar prepare.sh wrapup.sh ${EULA_FILE} \
		${WORKDIR}/image-json/image.json ${TEZI_ARTIFACTS}
}
do_image_teziimg[dirs] += "${WORKDIR}/image-json ${DEPLOY_DIR_IMAGE}"
do_image_teziimg[cleandirs] += "${WORKDIR}/image-json"
do_image_teziimg[prefuncs] += "${TEZI_IMAGE_TEZIIMG_PREFUNCS}"
do_image_teziimg[recrdeptask] += "do_deploy"
do_image_teziimg[vardepsexclude] = "TEZI_VERSION TEZI_DATE"
