SUMMARY = "Download sources and dependencies for chromium project dependent systems"
DESCRIPTION = "The Chromium depot_tools(7) suite contains many git workflow-enhancing \
               tools which are designed to work together to enable anyone to wrangle \
               the Chromium codebase expertly."
HOMEPAGE = "https://commondatastorage.googleapis.com/chrome-infra-docs/flat/depot_tools/docs/html/depot_tools_tutorial.html"
LICENSE = "CHROMIUM"
LIC_FILES_CHKSUM = " \
  file://LICENSE;md5=c2c05f9bdd5fc0b458037c2d1fb8d95e"

PV = "${SRCPV}"

SRC_URI = "\
  git://chromium.googlesource.com/chromium/tools/depot_tools.git;protocol=https;branch=main; \
"

SRCREV = "cf70f3dcf353ee0c8c20e165cfab2f710a6edec7"

DEPENDS = "\
  python3-native \
  git-native \
"

S = "${WORKDIR}/git"
B = "${WORKDIR}/gclient"

inherit native

PROVIDES = "gclient"

PACKAGES = "${PN}"
FILES = "${bindir}/gclient"

do_compile () {
   cd ${S}
   PATH=${S}:${PATH} gclient
}

do_install () {
   install -d ${D}${bindir}
   cp -r ${S} ${D}${bindir}/gclient
}
