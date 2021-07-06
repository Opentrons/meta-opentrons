# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Timeout context manager for asyncio programs"
HOMEPAGE = "https://github.com/aio-libs/async_timeout/"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "Apache"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRC_URI[md5sum] = "305c4fa529f2485c403d0dbe14390175"
SRC_URI[sha1sum] = "0498faefffb67a09a12a2eca109de5faf518e324"
SRC_URI[sha256sum] = "0c3c816a028d47f659d6ff5c745cb2acf1f966da1fe5c19c77a70282b25f4c5f"
SRC_URI[sha384sum] = "03e395e8b01522f118bd50f86c17b44cde68d58921c3792db8a57bdb03730e4f286cab3891ecb1b2dd87e80955ca6e5e"
SRC_URI[sha512sum] = "fd30842671a79edfd52c7350e7fb2120533a6d97b44975f7b071ce2cbde43443bd5bbe1f2ad0ad3ab2156e1987b9e58e0c149b0ecfea8674eb0cb78eee79c986"

S = "${WORKDIR}/async-timeout-${PV}"

inherit setuptools3 pypi

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-asyncio python3-core python3-typing-extensions"
