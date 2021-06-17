# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
RDEPENDS_${PN} = "python3 python3-pip"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/Opentrons/opentrons.git;protocol=https"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "bf8fbe8a98c14061af8d5bbb22d7a6b95a25eaab"

S = "${WORKDIR}"

DEPENDS_${PN} = "${PYTHON_PN}-modules
# NOTE: this is a Makefile-only piece of software, so we cannot generate much of the
# recipe automatically - you will need to examine the Makefile yourself and ensure
# that the appropriate arguments are passed in.

do_configure () {
	# Specify any needed configure commands here
	:
}

do_compile () {
	# You will almost certainly need to add additional arguments here
	oe_runmake
}

do_install () {
	# NOTE: unable to determine what to put here - there is a Makefile but no
	# target named "install", so you will need to define this yourself
	python3 ${WORKDIR}/shared-date/python/setup.py bdist_wheel -d ${WORKDIR}/dist
	python3 ${WORKDIR}/api/setup.py bdist_wheel -d ${WORKDIR}/dist
	python3 ${WORKDIR}/notify-server/setup.py bdist_wheel -d ${WORKDIR}/dist
	python3 ${WORKDIR}/robot-server/setup.py bdist_wheel -d ${WORKDIR}/dist
	pip install ${WORKDIR}/dist/*
}

