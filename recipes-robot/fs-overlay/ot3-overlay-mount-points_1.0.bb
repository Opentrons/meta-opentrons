SUMMARY = "ot3 overlay mount point directory structure"
LICENSE = "CLOSED"
BB_STRICT_CHECKSUM = "0"
PV = "1.0"

S = "${WORKDIR}"

inherit allarch

do_install () {
        install -d ${D}/mnt/upper/
        install -d ${D}/mnt/upper/etc/
        install -d ${D}/mnt/upper/work/etc/
}

FILES_${PN} += "/mnt/upper/*"
