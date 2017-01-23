#!/usr/bin/env bash
echo on
version=$1

usage() { echo "Usage: $0 [version]" 1>&2; exit 1; }

if [ -z "${version}" ] ; then
    usage
fi

# build type value
echo "creating version mousify-server-$version.jar"
cp ./target/mousify-server-jar-with-dependencies.jar ./releases/mousify-server-$version.jar