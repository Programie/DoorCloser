# DoorCloser

A Minecraft Bukkit plugin which automatically closes doors opened by players after some time.

[![pipeline status](https://gitlab.com/Programie/DoorCloser/badges/master/pipeline.svg)](https://gitlab.com/Programie/DoorCloser/commits/master)
[![download latest release](https://img.shields.io/badge/download-latest-blue.svg)](https://gitlab.com/Programie/DoorCloser/-/jobs/artifacts/master/raw/target/DoorCloser.jar?job=release)

## What is it?

DoorCloser automatically closes doors which have been opened by players after a defined timeout (default: 60 seconds).

The plugin also supports defining a list of door types which should be automatically closed. In that way, you might let the plugin only close oak doors while keeping jungle doors open all the time.

## How to use it?

The plugin doesn't use any commands. Just open any door and it will close it for you after the defined timeout has been elapsed.

## Installation

You can get the latest release from [GitLab](https://gitlab.com/Programie/DoorCloser/pipelines?scope=tags).

## Build

You can build the project in the following 2 steps:

 * Check out the repository
 * Build the jar file using maven: *mvn clean package*

**Note:** JDK 1.8 and Maven is required to build the project!