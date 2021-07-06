# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "WebSockets state-machine based protocol implementation"
HOMEPAGE = "https://github.com/python-hyper/wsproto/"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=69fabf732409f4ac61875827b258caaf"

SRC_URI = "https://files.pythonhosted.org/packages/2b/a4/aded0882f8f1cddd68dcd531309a15bf976f301e6a3554055cc06213c227/wsproto-${PV}.tar.gz"
SRC_URI[md5sum] = "74e9e80d520004697a41b6c304649bbc"
SRC_URI[sha1sum] = "2ca02ee672cf290ed5ce4802e0337b859a314c09"
SRC_URI[sha256sum] = "868776f8456997ad0d9720f7322b746bbe9193751b5b290b7f924659377c8c38"
SRC_URI[sha384sum] = "1da89e19fc338a244b60d9a6d0b43dec47b620c913f75d2081e3a1cd8563e578d031baeb9df77a39a2ddf561fb33ff2e"
SRC_URI[sha512sum] = "36f8c4eebf84bfe869f7124330898d22416b423f47396f2e3f72069e14f1763514e1df700c3e8ee81467cf973197e10143e088978d0688930fa98518af8773fb"


inherit setuptools3 pypi

# WARNING: the following rdepends are from setuptools install_requires. These
# upstream names may not correspond exactly to bitbake package names.
RDEPENDS_${PN} += "python3-h11"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-core python3-misc"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    mypy_extensions
#    pytest
#    wsproto
#    wsproto.connection
#    wsproto.events
#    wsproto.extensions
#    wsproto.frame_protocol
#    wsproto.handshake
#    wsproto.typing
#    wsproto.utilities
