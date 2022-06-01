LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM ?= "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SUMMARY = "ot3 overlay mount point directory structure"
BB_STRICT_CHECKSUM = "0"
PV = "1.0"

S = "${WORKDIR}"

inherit allarch

do_install () {
        install -d ${D}/mnt/var/
        install -d ${D}/mnt/var/.work/
}

FILES_${PN} += "/mnt/* mnt/var/.work/*"
