#!/bin/sh
version=$1

if [ -z "$version" ];
then
    echo 'version should be specified.\n > make.sh x.y'
    exit 1
fi

file=./target/mousify-server-$version.jar
if [ -e $file ]
then
    # create folder
    mkdir -p ./releases/$version
    # copy to release folder
    cp ./target/mousify-server-jar-with-dependencies.jar ./releases/$version/mousify-server-$version.jar
    # create Linux executable
	cat stub.sh ./target/mousify-server-jar-with-dependencies.jar > ./releases/$version/mousify-$version.run && chmod +x ./releases/$version/mousify-$version.run
	echo 'Linux executable for [version: '$version' ] has been generated'
	exit 0
else
	echo "$file not found"
	exit 2
fi
