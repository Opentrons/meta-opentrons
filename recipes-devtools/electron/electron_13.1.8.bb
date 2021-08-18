SUMMARY = "Build cross platform desktop apps with web technologies"
DESCRIPTION = "The Electron framework lets you write cross-platform \
desktop applications using JavaScript, HTML and CSS. It is based on \
io.js and Chromium and is used in the Atom editor."
HOMEPAGE = "http://electron.atom.io/"
LICENSE = "MIT"

LIC_FILES_CHKSUM = " \
  file://LICENSE;md5=dd413c962a5a67c951cc5dd842060ace \
"

DEPENDS += " \
  ninja-native \
  nodejs-native \
  clang-cross-${TARGET_ARCH} \
  util-linux \
  libnotify3 \
  gtk+ \
  gconf \
  dbus \
  alsa-lib \
  cups \
  xinput \
  nss \
  libxtst \
  libxi \
  libcap \
"

PV = "13.1.8"

S = "${WORKDIR}/gclient"
SRC_URI = "git://chromium.googlesource.com/chromium/tools/depot_tools.git;protocol=https;"
ELECTRON_URI = "https://github.com/electron/electron"
E_S = "${WORKDIR}/electron"

do_fetch () {
   mkdir -p ${E_S}/src/electron
   cd ${E_S}
   PATH=${S}:${PATH} gclient config --name="src/electron" --unmanaged ${ELECTRON_URI}
   PATH=${S}:${PATH} gclient sync --with_branch_heads --with_tags
}
