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
  gclient-native \
"

PV = "13.1.8"

S = "${WORKDIR}/electron"
SRC_URI = "git://{ELECTRON_URI};protocol=https;branch=main "
ELECTRON_URI = "github.com/electron/electron"

do_fetch () {
   mkdir -p ${S}/src/electron
   cd ${S}
   PATH=${STAGING_BINDIR_NATIVE}/gclient:${PATH} gclient config --name="src/electron" --unmanaged https://${ELECTRON_URI}
   PATH=${STAGING_BINDIR_NATIVE}/gclient:${PATH} gclient sync --with_branch_heads --with_tags
}

do_fetch[deptask] = "do_populate_sysroot"
