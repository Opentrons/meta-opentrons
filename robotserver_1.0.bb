#
#Opentrons - Robot Server recipe 
#
DESCRIPTION = "Opentrons Robot Server"
SECTION = "Opentrons"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRCREV = "3fa59b1d00ff90bf16a1d6badb959780c9e76d2e"
SRC_URI = "https://github.com/Opentrons/opentrons.git"

S = "${WORKDIR}/git"

inherit autotools

# The autotools configuration seems to have a problem with a race condition when parallel make is enabled
PARALLEL_MAKE = ""
