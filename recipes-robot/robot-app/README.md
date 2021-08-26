# Building Electron

This recipe directory holds code for building electron with slightly different configuration arguments so it supports drm or wayland.

## Source of this recipe

A lot of this recipe and includes is inspired by https://github.com/OSSystems/meta-browser with tweaks to function properly as an electron build rather than a full chromium build.

## Source Location

The normal way to get the electron source code is to use depot_tools, as noted here: https://www.electronjs.org/docs/development/build-instructions-gn#build-tools

This is a pain and takes forever.

Instead, we did this once and uploaded it to s3, which is the awful url. 

To update, or to verify that the contents are correct, you can follow the above instructions (but go to src/electron in the checkout, `git checkout v13.1.8` or whatever it currently says in the recipe package version, and then sync again); from the directory where you see `src`, run `tar --exclude-vcs -czf electron-13.1.8.tar.gz` and then checksum that.


