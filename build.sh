#!/bin/sh

gradle --init-script init.gradle.kts clean assemble --no-daemon
