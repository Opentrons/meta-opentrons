# Copyright (C) 2021 Seth Foster <seth@opentrons.com>
# Released under the MIT License (see COPYING.MIT for the terms)
DESCRIPTION = "Firmware bindings for canbus messages for OT3"
HOMEPAGE = "https://github.com/Opentrons/ot3-firmware"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"
B = "${WORKDIR}/build"
BRANCH = "main"
inherit insane
SRC_URI = "git://github.com/Opentrons/ot3-firmware.git;protocol=https;branch=${BRANCH};"

PIPENV_APP_BUNDLE_PROJECT_ROOT = "${S}/python"
PIPENV_APP_BUNDLE_DIR = "/opt/opentrons-robot-server"
PIPENV_APP_BUNDLE_USE_GLOBAL = "typing-extensions"
PIPENV_APP_BUNDLE_STRIP_HASHES = "yes"
PIPENV_APP_BUNDLE_EXTRAS = ""
PIPENV_APP_BUNDLE_PACKAGE_SOURCE = "poetry"

inherit pipenv_app_bundle
FILES_${PN} = "${PIPENV_APP_BUNDLE_DIR}/opentrons_ot3_firmware ${PIPENV_APP_BUNDLE_DIR}/opentrons_ot3_firmware-*.dist-info"
