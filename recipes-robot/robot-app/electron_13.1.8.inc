require chromium-gn.inc

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
S = "${WORKDIR}/electron"

SRC_URI = "https://d2irdh6zupqygx.cloudfront.net/mirrors/electron/electron-${PV}.tar.gz"
SRC_URI[sha256sum] = "38c477e09a8c4325bd385761afa2f5d6cb5047ae1023868af586f197c49a3021"
