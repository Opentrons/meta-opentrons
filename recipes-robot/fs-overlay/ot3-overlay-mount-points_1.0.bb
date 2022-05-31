LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM ?= "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SUMMARY = "ot3 overlay mount point directory structure"
BB_STRICT_CHECKSUM = "0"
PV = "1.0"

S = "${WORKDIR}"

inherit allarch

do_install () {
	# todo: making /etc read only would create issues for systemd and such - requires a work around.
        install -d ${D}/data/
        install -d ${D}/data/etc/
        install -d ${D}/data/.work/etc/
        install -d ${D}/data/var/
        install -d ${D}/data/.work/var/
}

FILES_${PN} += "/data/* /data/.work/*"
