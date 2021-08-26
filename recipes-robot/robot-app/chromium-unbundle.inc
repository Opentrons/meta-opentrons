# This file is originally
# https://github.com/OSSystems/meta-browser/blob/master/meta-chromium/recipes-browser/chromium/chromium-unbundle.inc
# This is a list of libraries bundled by Chromium that can be replaced by their
# system-wide counterparts.
GN_UNBUNDLE_LIBS = " \
        flac \
        libjpeg \
        libxslt \
"
