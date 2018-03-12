################################################################
# Copyright (C) 2016 Schneider Electric                        #
################################################################

SUMMARY = "Replacement for /sbin/init that launches an Erlang/OTP Release"
DESCRIPTION = "This is a replacement for /sbin/init that launches an Erlang/OTP release. It is intentionally minimalist as it expects Erlang/OTP to be in charge of application initialization and supervision. It can be thought of as a simple Erlang/OTP release start script with some basic system initialization."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1cb512268740591efa7a3791b3924ef8"

S = "${WORKDIR}/erlinit-${PV}"

PR = "r1"

SRC_URI  = "https://github.com/nerves-project/erlinit/archive/v${PV}.tar.gz;md5="
SRC_URI += "file://erlinit.config"

SRC_URI[md5sum]    = "025baf966da29ea8c86a93b22c744a00"
SRC_URI[sha256sum] = "948399227ca34f3b02c82022f252df0c0e3e6010320aa35600ba73c19eda9eb2"


EXTRA_OEMAKE = "erlinit"

do_install() {
        install -d 0755 ${D}/${base_sbindir}
        install -d 0755 ${D}/${sysconfdir}
	install -m 0755 ${S}/erlinit ${D}/${base_sbindir}/init
        install -m 0644 ${WORKDIR}/erlinit.config ${D}/${sysconfdir}/erlinit.config
}

FILES_${PN} = "${base_sbindir} ${sysconfdir}"
FILES_${PN}-dbg += "/.debug"

PACKAGES = "${PN}-dbg ${PN}" 
