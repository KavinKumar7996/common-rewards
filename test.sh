#!/bin/sh

gradle --init-script init.gradle.kts clean detekt ktlintCheck test-all jacocoTestReport --no-daemon
