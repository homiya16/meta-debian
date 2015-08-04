#
# base recipe: meta/recipes-graphics/drm/libdrm_2.4.52.bb
# base branch: daisy
#

PR = "r0"

SUMMARY = "Userspace interface to the kernel DRM services"
DESCRIPTION = "The runtime library for accessing the kernel DRM services.  DRM \
stands for \"Direct Rendering Manager\", which is the kernel portion of the \
\"Direct Rendering Infrastructure\" (DRI).  DRI is required for many hardware \
accelerated OpenGL drivers."

LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
file://xf86drm.c;beginline=9;endline=32;md5=c8a3b961af7667c530816761e949dc71 \
"

inherit debian-package

DEBIAN_PATCH_TYPE = "quilt"

PROVIDES = "drm"
DEPENDS = "libpthread-stubs udev"

# be aware that libdrm_2.4.44.bb ignores this
INC_PR = "r4"

#libpciaccess is required starting from libdrm 2.4.26
DEPENDS += " libpciaccess"

inherit autotools pkgconfig

EXTRA_OECONF += "--disable-cairo-tests \
                 --enable-omap-experimental-api \
                 --enable-install-test-programs \
                "
ALLOW_EMPTY_${PN}-drivers = "1"
PACKAGES =+ "${PN}-tests ${PN}-drivers ${PN}-radeon ${PN}-nouveau ${PN}-omap \
             ${PN}-intel ${PN}-exynos ${PN}-kms"

RRECOMMENDS_${PN}-drivers = "${PN}-radeon ${PN}-nouveau ${PN}-omap ${PN}-intel \
                             ${PN}-exynos"

FILES_${PN}-tests = "${bindir}/dr* ${bindir}/mode* ${bindir}/*test"
FILES_${PN}-radeon = "${libdir}/libdrm_radeon.so.*"
FILES_${PN}-nouveau = "${libdir}/libdrm_nouveau.so.*"
FILES_${PN}-omap = "${libdir}/libdrm_omap.so.*"
FILES_${PN}-intel = "${libdir}/libdrm_intel.so.*"
FILES_${PN}-exynos = "${libdir}/libdrm_exynos.so.*"
FILES_${PN}-kms = "${libdir}/libkms*.so.*"
