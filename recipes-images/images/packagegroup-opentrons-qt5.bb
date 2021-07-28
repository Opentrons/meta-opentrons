SUMMARY = "Packagegroup which provides most QT5 libraries and a QT5 demo"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES += " \
    ${PN}-demos \
    ${PN}-fonts \
    ${PN}-libs \
"

RRECOMMENDS_${PN} = " \
    ${PN}-demos \
    ${PN}-fonts \
    ${PN}-libs \
"
# Only install qtbase-examples with its dependencies on modules with limited
# storage and no gpu.
RRECOMMENDS_${PN}_colibri-imx6ull = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland', '', d)} \
    qtbase-examples \
"
RRECOMMENDS_${PN}_colibri-imx7 = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland', '', d)} \
    qtbase-examples \
"

SUMMARY_${PN}-demos = "QT5 Demos"
RRECOMMENDS_${PN}-demos = " \
    cinematicexperience \
    qtsmarthome \
"

SUMMARY_${PN}-fonts = "Some fonts useful for QT5"
RRECOMMENDS_${PN}-fonts = " \
    ttf-dejavu-common \
    ttf-dejavu-sans \
    ttf-dejavu-sans-mono \
    ttf-dejavu-serif \
"

SUMMARY_${PN}-libs = "QT5 libraries"
RRECOMMENDS_${PN}-libs = " \
    qt3d \
    qt5ledscreen \
    qtbase \
    qtcharts \
    qtcoap \
    qtconnectivity \
    qtdatavis3d \
    qtdeclarative \
    qtgamepad \
    qtgraphicaleffects \
    qtimageformats \
    qtknx \
    qtlocation \
    qtlottie \
    qtmqtt \
    qtmultimedia \
    qtnetworkauth \
    qtopcua \
    qtpurchasing \
    qtquick3d \
    qtquickcontrols \
    qtquickcontrols2 \
    qtquicktimeline \
    qtremoteobjects \
    qtscript \
    qtscxml \
    qtsensors \
    qtserialbus \
    qtserialport \
    qtsvg \
    qtsystems \
    qttools \
    qttranslations \
    qtvirtualkeyboard \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland', '', d)} \
    qtwebchannel \
    qtwebglplugin \
    qtwebsockets \
    qtxmlpatterns \
"

