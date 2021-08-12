LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"
SRC_URI = "git://github.com/Opentrons/opentrons.git;protocol=https;branch=electron-13; "

# Modify these as desired
PV = "1.0+git${SRCPV}"

inherit features_check

SRCREV = "88c9d63c8c525656719ba93d9a369ab127a1c0cf"
S = "${WORKDIR}/git"

inherit insane


do_configure(){
    npm install -g yarn
    cd ${S}
    yarn
    cd ${S}/app-shell
    yarn electron-rebuild --arch=arm64
    cd ${S}
    make -C shared-data setup-js
}

do_compile(){
    export BUILD_ID=${CODEBUILD_BUILD_NUMBER:-dev}
    make -C app dist
    make -C app-shell lib
    cd ${S}/app-shell
    yarn run electron-builder --config electron-builder.config.js --linux --arm64 dir
}

fakeroot do_install(){
    DISTDIR=${S}/app-shell/dist/linux-arm64-unpacked
    DESTDIR=${D}/opt/opentrons-app
    install -d ${D}/opt/opentrons-app
    cd ${DISTDIR}
    find -type d -exec install -o root -g root -Dm 755 "{}" "${DESTDIR}/{}" \;
    find -type f -exec install -o root -g root -Dm 755 "{}" "${DESTDIR}/{}" \;
}

REQUIRED_DISTRO_FEATURES = "x11"

do_install[depends] += "virtual/fakeroot-native:do_populate_sysroot"
INSANE_SKIP_${PN} = " already-stripped file-rdeps"
FILES_${PN} = "/opt/opentrons-app/* /opt/opentrons-app/**/*"
# todo figure out how to not need cups
RDEPENDS_${PN} = "udev \
                  nss \
                  dbus \
                  nspr libasound \
                  gtk+3 cairo \
                  libxcomposite libx11 libxrender libxext libx11-xcb libxi \
                  libxtst libxcursor libxrandr libxscrnsaver \
                  atk at-spi2-atk\
                  cups"
DEPENDS = " nodejs-native udev"
