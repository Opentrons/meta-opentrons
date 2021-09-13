require electron-gn.inc

SUMMARY = "Build cross platform desktop apps with web technologies"
DESCRIPTION = "The Electron framework lets you write cross-platform \
desktop applications using JavaScript, HTML and CSS. It is based on \
io.js and Chromium and is used in the Atom editor."
HOMEPAGE = "http://electron.atom.io/"
LICENSE = "MIT"

PV = "13.1.8"

GN_ARGS += "use_lld=true use_gold=false"

UNWINDLIB_toolchain-clang = "--unwindlib=libgcc"

DEPENDS_remove = "compiler-rt-native libcxx-native"

BUILD_CPPFLAGS_remove = "-isysroot=${STAGING_DIR_NATIVE} -stdlib=libc++"
BUILD_LDFLAGS_remove = "-rtlib=libgcc -unwindlib=libgcc -stdlib=libc++ -lc++abi -rpath ${STAGING_LIBDIR_NATIVE}"

REQUIRED_DISTRO_FEATURES = "wayland"

DEPENDS += "\
        virtual/egl \
        libxkbcommon \
        at-spi2-atk \
		libnotify \
        wayland \
        wayland-native \
"

#         ozone_platform_drm=true

GN_ARGS += "\
        ${PACKAGECONFIG_CONFARGS} \
        use_ozone=true \
        ozone_auto_platforms=false \
        ozone_platform_headless=false \
        ozone_platform_wayland=true \
        ozone_platform_x11=true \
        system_wayland_scanner_path="${STAGING_BINDIR_NATIVE}/wayland-scanner" \
        use_system_wayland_scanner=true \
        use_xkbcommon=true \
        use_system_libwayland=true \
        use_system_libdrm=true \
        use_gtk=false \
        use_x11=false \
        use_system_minigbm=true \
        enable_basic_printing=false \
"

GN_ARGS_prepend = 'import("//electron/build/args/release.gn") \
'

do_install() {
    oewarn "INSTALLING"
}

#do_install() {
do_walsjbad() {
	install -d ${D}${bindir}
	install -d ${D}${datadir}
	install -d ${D}${datadir}/applications
	install -d ${D}${datadir}/icons
	install -d ${D}${datadir}/icons/hicolor
	install -d ${D}${libdir}/chromium
	install -d ${D}${libdir}/chromium/locales

	install -m 4755 chrome_sandbox ${D}${libdir}/chromium/chrome-sandbox
	install -m 0755 chrome ${D}${libdir}/chromium/chromium-bin
	install -m 0644 *.bin ${D}${libdir}/chromium/
	install -m 0644 icudtl.dat ${D}${libdir}/chromium/icudtl.dat

	# Process and install Chromium's template .desktop file.
	sed -e "s,@@MENUNAME@@,Chromium Browser,g" \
	    -e "s,@@PACKAGE@@,chromium,g" \
	    -e "s,@@USR_BIN_SYMLINK_NAME@@,chromium,g" \
	    ${S}/chrome/installer/linux/common/desktop.template > chromium.desktop
	install -m 0644 chromium.desktop ${D}${datadir}/applications/chromium.desktop

	# Install icons.
	for size in 16 24 32 48 64 128 256; do
		install -d ${D}${datadir}/icons/hicolor/${size}x${size}
		install -d ${D}${datadir}/icons/hicolor/${size}x${size}/apps
		for dirname in "chromium" "default_100_percent/chromium"; do
			icon="${S}/chrome/app/theme/${dirname}/product_logo_${size}.png"
			if [ -f "${icon}" ]; then
				install -m 0644 "${icon}" \
					${D}${datadir}/icons/hicolor/${size}x${size}/apps/chromium.png
			fi
		done
	done

	# A wrapper for the proprietary Google Chrome version already exists.
	# We can just use that one instead of reinventing the wheel.
	WRAPPER_FILE=${S}/chrome/installer/linux/common/wrapper
	sed -e "s,@@CHANNEL@@,stable,g" \
		-e "s,@@PROGNAME@@,chromium-bin,g" \
		${WRAPPER_FILE} > chromium-wrapper
	install -m 0755 chromium-wrapper ${D}${libdir}/chromium/chromium-wrapper
	ln -s ${libdir}/chromium/chromium-wrapper ${D}${bindir}/chromium

	# Chromium *.pak files
	install -m 0644 chrome_*.pak ${D}${libdir}/chromium/
	install -m 0644 resources.pak ${D}${libdir}/chromium/resources.pak

	# Locales.
	install -m 0644 locales/*.pak ${D}${libdir}/chromium/locales/

	# Add extra command line arguments to the chromium-wrapper script by
	# modifying the dummy "CHROME_EXTRA_ARGS" line
	sed -i "s/^CHROME_EXTRA_ARGS=\"\"/CHROME_EXTRA_ARGS=\"${CHROMIUM_EXTRA_ARGS}\"/" ${D}${libdir}/chromium/chromium-wrapper

	# This is ANGLE, not to be confused with the similarly named files under swiftshader/
	if [ -e libEGL.so ]; then
		install -m 0755 libEGL.so ${D}${libdir}/chromium/
	fi
	if [ -e libGLESv2.so ]; then
		install -m 0755 libGLESv2.so ${D}${libdir}/chromium/
	fi

	if [ -n "${@bb.utils.contains('PACKAGECONFIG', 'component-build', 'component-build', '', d)}" ]; then
		install -m 0755 *.so ${D}${libdir}/chromium/
	fi

	# When building chromium with use_system_minigbm=false,
	# libminigbm.so does not seem to get linked in statically.
	# So we simply check whether it exists in all cases and ship it.
	if [ -e libminigbm.so ]; then
		install -m 0755 libminigbm.so ${D}${libdir}/chromium/
	fi

	# Swiftshader is only built for x86 and x86-64.
	if [ -d "swiftshader" ]; then
		install -d ${D}${libdir}/chromium/swiftshader
		install -m 0644 swiftshader/libEGL.so ${D}${libdir}/chromium/swiftshader/
		install -m 0644 swiftshader/libGLESv2.so ${D}${libdir}/chromium/swiftshader/
	fi

	# ChromeDriver.
	install -m 0755 chromedriver.unstripped ${D}${bindir}/chromedriver
}

#PACKAGES =+ "${PN}-chromedriver"

#FILES_${PN}-chromedriver = "${bindir}/chromedriver"

#FILES_${PN} = " \
#        ${bindir}/chromium \
#        ${datadir}/applications/chromium.desktop \
#        ${datadir}/icons/hicolor/*x*/apps/chromium.png \
#        ${libdir}/chromium/* \
#"
