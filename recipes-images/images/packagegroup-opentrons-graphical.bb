SUMMARY = "Packagegroups which provide graphical/display/multimedia releated packages"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES += " \
    packagegroup-drm-utils-tdx-graphical \
    packagegroup-gstreamer-tdx-graphical \
    packagegroup-gpu-tdx-graphical \
    packagegroup-x11-components-tdx-graphical \
    packagegroup-x11-utils-tdx-graphical \
"

RRECOMMENDS_packagegroup-tdx-graphical = " \
    packagegroup-gstreamer-tdx-graphical \
    packagegroup-gpu-tdx-graphical \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', \
       bb.utils.contains('DISTRO_FEATURES', 'x11', \
                         'packagegroup-x11-components-tdx-graphical \
                          packagegroup-x11-utils-tdx-graphical', \
                         '', d), d)} \
"
RRECOMMENDS_packagegroup-tdx-graphical_append_mx8 = " \
    packagegroup-drm-utils-tdx-graphical \
"
RRECOMMENDS_packagegroup-tdx-graphical_append_upstream = " \
    packagegroup-drm-utils-tdx-graphical \
"

SUMMARY_packagegroup-drm-utils-tdx-graphical = "Utilities for DRM, Direct Rendering Manager"
RRECOMMENDS_packagegroup-drm-utils-tdx-graphical = " \
    libdrm-tests \
"

SUMMARY_packagegroup-gstreamer-tdx-graphical = "gstreamer packages"
RRECOMMENDS_packagegroup-gstreamer-tdx-graphical = "\
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-good \
    gstreamer1.0-plugins-bad \
"
GSTREAMER_MX6QDL = " \
    gstreamer1.0-plugins-base-ximagesink \
    gstreamer1.0-plugins-imx \
    imx-gst1.0-plugin \
    imx-gst1.0-plugin-gplay \
    imx-gst1.0-plugin-grecorder \
"
RRECOMMENDS_packagegroup-gstreamer-tdx-graphical_append_mx6dl = " ${GSTREAMER_MX6QDL}"
RRECOMMENDS_packagegroup-gstreamer-tdx-graphical_append_mx6q = " ${GSTREAMER_MX6QDL}"
RRECOMMENDS_packagegroup-gstreamer-tdx-graphical_colibri-imx6ull = ""
RRECOMMENDS_packagegroup-gstreamer-tdx-graphical_append_mx7 = " imx-gst1.0-plugin"
RRECOMMENDS_packagegroup-gstreamer-tdx-graphical_append_mx8 = " \
    imx-gst1.0-plugin \
    imx-gst1.0-plugin-gplay \
    imx-gst1.0-plugin-grecorder \
    packagegroup-fsl-gstreamer1.0-full \
    gst-examples \
"

SUMMARY_packagegroup-gpu-utils-tdx-graphical = "Utilities for GPU (OpenGL...)"
IMAGE_INSTALL_OPENCL_IMX = " \
    clpeak \
    libopencl-imx \
"
RRECOMMENDS_packagegroup-gpu-tdx-graphical = " \
    glmark2 \
"
IMAGE_INSTALL_GPU_MX6QDL = " \
    packagegroup-fsl-gpu-libs \
"
RRECOMMENDS_packagegroup-gpu-tdx-graphical_append_mx6dl = " \
    ${IMAGE_INSTALL_GPU_MX6QDL} \
"
RRECOMMENDS_packagegroup-gpu-tdx-graphical_append_mx6q = " \
    ${IMAGE_INSTALL_GPU_MX6QDL} \
    ${IMAGE_INSTALL_OPENCL_IMX} \
"
RRECOMMENDS_packagegroup-gpu-tdx-graphical_colibri-imx6ull = ""
RRECOMMENDS_packagegroup-gpu-tdx-graphical_append_mx8 = " \
    tinycompress \
    libvdk-imx \
    vulkan-headers \
    vulkan-loader \
    vulkan-tools \
    ${IMAGE_INSTALL_OPENCL_IMX} \
"
RRECOMMENDS_packagegroup-gpu-tdx-graphical_remove_mx8mm = " \
    vulkan \
    ${IMAGE_INSTALL_OPENCL_IMX} \
"
RRECOMMENDS_packagegroup-gpu-tdx-graphical_append_mx8qm = " \
    libopenvx-imx \
"
RRECOMMENDS_packagegroup-gpu-tdx-graphical_append_tegra124 = " \
    libglu \
    freeglut \
    tiff \
    xvinfo \
"

SUMMARY_packagegroup-x11-components-tdx-graphical = "Components of X11"
RRECOMMENDS_packagegroup-x11-components-tdx-graphical = "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', \
       bb.utils.contains('DISTRO_FEATURES', 'x11', \
       'libxcursor \
        libxdamage \
        libxres \
        libxvmc \
        xcursor-transparent-theme \
        xorg-minimal-fonts \
        xrdb \
        xserver-xorg-extension-dbe \
        xserver-xorg-extension-extmod \
        xserver-xorg-multimedia-modules \
        xserver-xorg-utils', \
       '', d), d)} \
"

SUMMARY_packagegroup-x11-utils-tdx-graphical = "Utilities for X11"
RRECOMMENDS_packagegroup-x11-utils-tdx-graphical = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', \
       bb.utils.contains('DISTRO_FEATURES', 'x11', \
       'scrot \
        setxkbmap \
        unclutter', \
       '', d), d)} \
"
