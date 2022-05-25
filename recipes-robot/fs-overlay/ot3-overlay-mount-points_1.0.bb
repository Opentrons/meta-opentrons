SUMMARY = "ot3 overlay mount point directory structure"
LICENSE = "CLOSED"
BB_STRICT_CHECKSUM = "0"
PV = "1.0"

S = "${WORKDIR}"

inherit allarch

do_install () {
        install -d ${D}/data/upper/
        install -d ${D}/data/upper/etc/
        install -d ${D}/data/upper/work/etc/
        install -d ${D}/data/upper/var/
        install -d ${D}/data/upper/work/var/
}

FILES_${PN} += "/data/upper/*"
