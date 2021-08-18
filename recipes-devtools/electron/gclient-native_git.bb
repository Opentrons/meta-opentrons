SUMMARY = "Download sources and dependencies for chromium project dependent systems"
DESCRIPTION = "The Chromium depot_tools(7) suite contains many git workflow-enhancing \
               tools which are designed to work together to enable anyone to wrangle \
               the Chromium codebase expertly."
HOMEPAGE = "https://commondatastorage.googleapis.com/chrome-infra-docs/flat/depot_tools/docs/html/depot_tools_tutorial.html"
LICENSE = "CHROMIUM"
LICENSE_PATH = "${LAYERDIR}/custom-licenses/CHROMIUM"
LIC_FILES_CHKSUM = " \
  file://LICENSE;md5=407e6223cc4cd861e035eddc06804262"

PV = "${SRCPV}"

SRC_URI = "\
  https://chromium.googlesource.com/chromium/tools/depot_tools.git \
"

DEPENDS = "\
  python3-native \
  git-native \
"

inherit native

do_compile () {
   ${B}/bootstrap_python3
}
